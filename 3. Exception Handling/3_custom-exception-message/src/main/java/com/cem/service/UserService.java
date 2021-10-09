package com.cem.service;

import com.cem.entity.UserEntity;
import com.cem.exception.UserServiceException;

public interface UserService {

	UserEntity createUser(UserEntity userEntity) throws UserServiceException;

	UserEntity getUser(long id);

	UserEntity updateUser(UserEntity userEntity);

	void deleteUser(UserEntity userEntity);
}
