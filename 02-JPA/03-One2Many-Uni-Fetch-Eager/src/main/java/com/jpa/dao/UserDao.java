package com.jpa.dao;

import java.util.List;

import com.jpa.entity.RoleEntity;
import com.jpa.entity.UserEntity;

public interface UserDao {
	
	UserEntity createUser(UserEntity userEntity);	
	
	UserEntity getUserById(long id);
	
	UserEntity getUserByPid(long id);
	
	UserEntity getUserByName(String name);
	
	UserEntity getUserByEmail(String email);
	
	List<UserEntity> getAllUsers();
	
	void removeUserByPid(long pid);
	
	UserEntity addRoleToUser(long userPid, RoleEntity roleEntity);

	UserEntity removeRoleFromUser(long userPid, String role);

}
