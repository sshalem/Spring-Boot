package com.em.service;

import com.em.entity.UserEntity;
import com.em.exception.UserServiceException;

public interface UserService {

	UserEntity createUser(UserEntity userEntity) throws UserServiceException;

	UserEntity getUser(long id);

	UserEntity updateUser(UserEntity userEntity);

	void deleteUser(UserEntity userEntity);
}
