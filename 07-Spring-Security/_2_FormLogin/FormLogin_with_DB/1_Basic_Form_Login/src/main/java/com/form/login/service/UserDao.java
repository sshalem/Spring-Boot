package com.form.login.service;

import java.util.List;

import com.form.login.entity.UserEntity;
import com.form.login.exception.ObjectNotFoundException;

public interface UserDao {

	UserEntity getByUserid(long id) throws ObjectNotFoundException;

	UserEntity getByUsername(String username) throws ObjectNotFoundException;

	UserEntity createUser(UserEntity user) throws Exception;

	UserEntity updateUser(UserEntity user);

	void deleteUser(long id);

	List<UserEntity> getAllUsers();
}
