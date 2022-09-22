package com.form.login.dao;

import java.util.List;

import com.form.login.dto.UserRequestModel;
import com.form.login.entity.UserEntity;
import com.form.login.exceptions.EmailOrUserAlreadyExistException;
import com.form.login.exceptions.ObjectNotExistException;

public interface UserDao {

	UserEntity getByUserid(long id) throws ObjectNotExistException;

	UserEntity getByUsername(String username) throws ObjectNotExistException;

	UserEntity createUser(UserRequestModel userRequestModel)
			throws EmailOrUserAlreadyExistException, NullPointerException;

	UserEntity updateUser(UserEntity user);

	void deleteUser(long id);

	List<UserEntity> getAllUsers();
}
