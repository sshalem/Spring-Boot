package com.emac.service;

import com.emac.entity.UserEntity;
import com.emac.exception.UserServiceException;

public interface UserService {

	UserEntity createUser(UserEntity userEntity) throws UserServiceException;

	UserEntity getUser(long id);

	UserEntity updateUser(UserEntity userEntity);

	void deleteUser(UserEntity userEntity);
}
