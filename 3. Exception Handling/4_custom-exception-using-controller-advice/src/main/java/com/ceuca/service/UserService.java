package com.ceuca.service;

import com.ceuca.entity.UserEntity;
import com.ceuca.exception.UserServiceException;

public interface UserService {

	UserEntity createUser(UserEntity userEntity) throws UserServiceException;

	UserEntity getUser(long id);

	UserEntity updateUser(UserEntity userEntity);

	void deleteUser(UserEntity userEntity);
}
