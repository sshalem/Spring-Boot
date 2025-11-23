package com.backend.service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.backend.config.SecurityConstants;
import com.backend.entity.RefreshTokenEntity;
import com.backend.entity.UserEntity;
import com.backend.exceptions.RefreshTokenExpiredException;
import com.backend.repository.RefreshTokenRepository;
import com.backend.repository.UserRepository;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	@Autowired
	private UserRepository userRepository;


	/*********************************************************************
	 * ✅ Best Practice for RefreshToken 
	 * ✅ To Use a random UUID (or long random string) 
	 *********************************************************************/
	@Override
	public String generateRefreshToken(String email, String invokedMethod, String refreshToken) {
		RefreshTokenEntity refreshTokenEntity = null;

		if (invokedMethod.equals(SecurityConstants.INVOKED_LOGIN_URL)) {
			UserEntity userEntity = userRepository.findByEmail(email);
			refreshTokenEntity = new RefreshTokenEntity();
			refreshTokenEntity.setUserEntity(userEntity);
		} else if (invokedMethod.equals(SecurityConstants.INVOKED_REFRESH_URL)) {
			refreshTokenEntity = refreshTokenRepository.findByToken(refreshToken).get();
		}

		refreshTokenEntity.setExpiryDate(Instant.now().plusMillis(SecurityConstants.REFRESH_TOKEN_EXPIRATION_TIME_ms));
		refreshTokenEntity.setToken(UUID.randomUUID().toString());
		refreshTokenEntity = refreshTokenRepository.save(refreshTokenEntity);

		return refreshTokenEntity.getToken();
	}

	
	@Override
	public RefreshTokenEntity validateRefreshToken(String refreshToken) {
		RefreshTokenEntity refreshTokenEntity = refreshTokenRepository.findByToken(refreshToken).get();

		if (refreshTokenEntity.getExpiryDate().compareTo(Instant.now()) < 0) {
			refreshTokenRepository.delete(refreshTokenEntity);			
			throw new RefreshTokenExpiredException("Refresh token expired. Please make a new Login request");
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

}
