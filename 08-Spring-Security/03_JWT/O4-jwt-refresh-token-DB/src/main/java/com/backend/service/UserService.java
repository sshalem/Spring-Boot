package com.backend.service;

import java.util.List;

import com.backend.entity.RoleEntity;
import com.backend.entity.UserEntity;
import com.backend.model.UserRegisterRequest;
import com.backend.model.UserRegisterResponse;

public interface UserService {

	/**************
	 * Create
	 *************/
	UserRegisterResponse createUser(UserRegisterRequest userRegisterRequest);

	/****************
	 * Read
	 ***************/
	List<UserEntity> getUserByName(String name);

	UserEntity getUserByEmail(String email);

	List<UserEntity> getUsersWithRole(String role);

	List<RoleEntity> getAllRolesOfUserByEmail(String email);

	List<UserEntity> getAllUsers();

	String getUserName(String email);

	/****************
	 * Update
	 ***************/
	UserEntity updateUserDetails(String email, UserEntity userEntity);

	/****************
	 * Delete
	 ***************/
	void deleteUserByEmail(String email);

	void removeAllUsersFromRole(String role);

	void deleteAllUsers();
}
