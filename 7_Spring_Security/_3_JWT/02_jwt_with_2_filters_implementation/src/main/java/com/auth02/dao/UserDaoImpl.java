package com.auth02.dao;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth02.entity.UserEntity;
import com.auth02.model.UserSignUpRequest;
import com.auth02.model.UserSignUpResponse;
import com.auth02.repository.UserRepository;

@Service
public class UserDaoImpl implements UserDao {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserSignUpResponse createUser(UserSignUpRequest userSignUpRequest) {

		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(userSignUpRequest, userEntity);
		userEntity.setPassword(passwordEncoder.encode(userSignUpRequest.getPassword()));
		UserEntity createdUser = userRepo.save(userEntity);

		UserSignUpResponse userSignUpResponse = new UserSignUpResponse();
		BeanUtils.copyProperties(createdUser, userSignUpResponse);

		userSignUpResponse.setId(UUID.randomUUID());

		return userSignUpResponse;
	}

}
