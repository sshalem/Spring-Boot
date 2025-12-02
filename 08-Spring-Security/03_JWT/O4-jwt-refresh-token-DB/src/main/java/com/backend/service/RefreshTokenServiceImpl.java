package com.backend.service;

import java.time.Instant;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.config.SecurityConstants;
import com.backend.entity.RefreshTokenEntity;
import com.backend.entity.UserEntity;
import com.backend.exceptions.RefreshTokenExpiredException;
import com.backend.exceptions.ResourceNotFoundException;
import com.backend.repository.RefreshTokenRepository;
import com.backend.repository.UserRepository;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

	private final static Logger LOGGER = LoggerFactory.getLogger(RefreshTokenServiceImpl.class);

	private final RefreshTokenRepository refreshTokenRepository;
	private final UserRepository userRepository;

	public RefreshTokenServiceImpl(RefreshTokenRepository refreshTokenRepository, UserRepository userRepository) {
		super();
		this.refreshTokenRepository = refreshTokenRepository;
		this.userRepository = userRepository;
	}

	/*********************************************************************
	 * ✅ Best Practice for RefreshToken 
	 * ✅ To Use a random UUID (or long random string)
	 * ✅ If refreshToken is revoked then 
	 * 		set revoked as 'true' 
	 * 		update Rotation
	 * 		save it in DB for track
	 * ✅ Then Generate new RefreshToken 
	 *********************************************************************/
	@Override
	@Transactional
	public String generateRefreshToken(String email, String invokedMethod, String oldRefreshToken) {

		LOGGER.info("invoke generateRefreshToken()");

		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null)
			throw new ResourceNotFoundException("User with Email : " + email + " , Not Exist");
		
		RefreshTokenEntity newRefreshTokenEntity = new RefreshTokenEntity();
		newRefreshTokenEntity.setUserEntity(userEntity);
		newRefreshTokenEntity.setExpiryDate(Instant.now().plusMillis(SecurityConstants.REFRESH_TOKEN_EXPIRATION_TIME_ms));
		newRefreshTokenEntity.setToken(UUID.randomUUID().toString());
		newRefreshTokenEntity.setRevoked(false);

		if (invokedMethod.equals(SecurityConstants.INVOKED_LOGIN_URL)) {
			newRefreshTokenEntity.setRefTokenUuid(UUID.randomUUID());
			newRefreshTokenEntity.setRotate(1);
		} else if (invokedMethod.equals(SecurityConstants.INVOKED_REFRESH_URL)) {
			RefreshTokenEntity _oldRefreshTokenEntity = refreshTokenRepository.findByToken(oldRefreshToken).get();
			int rotate = _oldRefreshTokenEntity.getRotate();
			if (rotate > 3) {				
				refreshTokenRepository.deleteByUuid(_oldRefreshTokenEntity.getRefTokenUuid());
				// Scenario 1: 
				// Service w/o @Transactionl , Repository : with @Transactionl 
				// This works  OK,
				// But, Problem having @Transactionl in both places : Service , Repository
				// Since I might want to have both operations of save() and delete in the same method.

				// Scenario 2: 
				// Service with @Transactionl , Repository : w/o @Transactionl 
				// (1) the delete SQL runs as we see in console,
				// (2) But, in DB it won't be delete, 
				// (3) Why?  because throwing exception, causes a roll back 

				
				// Scenario 3 :
				// Service with @Transactionl , Repository : with @Transactionl
				// This is my case , where I want to use delete and save in the same method.
				
				// (1) the delete SQL runs as we see in console,
				// (2) But, in DB it won't be delete, 
				// (3) Why?  because throwing exception, causes a roll back 
				
				// Solution :		
				// use Both Service & Repository with @Transactionl 
				// And add in repository @Transactional(propagation = REQUIRES_NEW) on the delete method in Repository 
				// ✔ Now delete will ALWAYS be committed
				// ✔ Even if service method throws exception
				// ✔ Recommended when delete must not be rolled back
				throw new RefreshTokenExpiredException("Refresh token expired. Please send new Login request");
			} else {
				_oldRefreshTokenEntity.setRevoked(true);
				newRefreshTokenEntity.setRefTokenUuid(_oldRefreshTokenEntity.getRefTokenUuid());
				newRefreshTokenEntity.setRotate(rotate + 1);								
				refreshTokenRepository.save(_oldRefreshTokenEntity);				
			}
		}

		RefreshTokenEntity _newRefreshTokenEntity = refreshTokenRepository.save(newRefreshTokenEntity);

		return _newRefreshTokenEntity.getToken();
	}


	@Override
	public RefreshTokenEntity validateRefreshToken(String refreshToken) {
		RefreshTokenEntity refreshTokenEntity = refreshTokenRepository.findByToken(refreshToken).get();

		// I check Rotation , during generateRefreshToken() process
		if (refreshTokenEntity.getExpiryDate().compareTo(Instant.now()) < 0) {
			refreshTokenRepository.delete(refreshTokenEntity);
			throw new RefreshTokenExpiredException("Refresh token expired. Please send new Login request");
		}
		return refreshTokenEntity;
	}

	@Override
	public UserEntity getUserByRefreshToken(String refreshToken) {
		UserEntity userEntity = refreshTokenRepository.findUserByRefreshToken(refreshToken);
		if (userEntity == null)
			throw new UsernameNotFoundException("Could Not extract username from Refresh Token");
		return userEntity;
	}

	@Override
	public void deleteRefreshToken(String refreshToken) {
		refreshTokenRepository.delete(refreshTokenRepository.findByToken(refreshToken).get());
	}

	
	@Override
	@Scheduled(cron = "0 0 */2 * * *") 
	public void scheduledRefreshTokenCleanup() {
		refreshTokenRepository.deleteByExpiryDateBefore(Instant.now());		
	}
}
