package com.backend.dao;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.entity.UserEntity;
import com.backend.exceptions.EmailAlreadyExistException;
import com.backend.model.UserRegisterRequest;
import com.backend.model.UserRegisterResponse;
import com.backend.model.UserLoginDetails;
import com.backend.repository.UserRepository;

@Service
public class UserDaoImpl implements UserDao {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserRegisterResponse createUser(UserRegisterRequest userRegisterRequest) {

		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(userRegisterRequest, userEntity);

		if (userRepo.findByEmail(userEntity.getEmail()) != null)
			throw new EmailAlreadyExistException("Email Already Exist");

		UUID randomUUID = UUID.randomUUID();
		
		userEntity.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));
		
		userEntity.setUuid(randomUUID);
		UserEntity createdUser = userRepo.save(userEntity);
		
		UserRegisterResponse returnedValue = new UserRegisterResponse();
		
		BeanUtils.copyProperties(createdUser, returnedValue);
		return returnedValue;
	}

	@Override
	public String getUserName(String email) {
		UserEntity userEntity = userRepo.findByEmail(email);
		return userEntity.getName();
	}

	@Override
	public UserLoginDetails getUserLoginDetailsByEmail(String email) {		
		
		UserEntity userEntity = userRepo.findByEmail(email);
		
		UserLoginDetails returnedValue = new UserLoginDetails();
		
		BeanUtils.copyProperties(userEntity, returnedValue);
		return returnedValue;
	}

	@Override
	public UserLoginDetails updateUserDetails(UserLoginDetails userLoginDetails) {
		
		// First Need to load the Current User details by its UUID
		UserEntity userEntity = userRepo.findByUuid(userLoginDetails.getUuid());		
		
		BeanUtils.copyProperties(userLoginDetails, userEntity);		
				
		UserEntity updatedUserEntity = userRepo.save(userEntity);
		
		UserLoginDetails returnedValue = new UserLoginDetails();
		
		BeanUtils.copyProperties(updatedUserEntity, returnedValue);		
		return returnedValue;
	}
 
}
