package com.basic.auth.dao;

import java.util.List;

import com.basic.auth.dto.UserRequestModel;
import com.basic.auth.entity.UserEntity;
import com.basic.auth.exceptions.EmailOrUserAlreadyExistException;
import com.basic.auth.exceptions.ObjectNotExistException;

public interface UserDao {

	UserEntity getByUserid(long id) throws ObjectNotExistException;

	UserEntity getByUsername(String username) throws ObjectNotExistException;

	UserEntity createUser(UserRequestModel userRequestModel) throws EmailOrUserAlreadyExistException, NullPointerException;

	UserEntity updateUser(UserEntity user);

	void deleteUser(long id);

	List<UserEntity> getAllUsers();
}
