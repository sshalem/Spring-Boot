package com.jpa.dao;

import com.jpa.entity.RoleEntity;
import com.jpa.entity.UserEntity;

public interface UserDao {
	
	UserEntity createUser(UserEntity userEntity);

	UserEntity getUserByName(String name);
	
	UserEntity addRoleToUser(long userPid, RoleEntity roleEntity);

	UserEntity removeRoleFromUser(long userPid, RoleEntity roleEntity);

	UserEntity getUserByPid(long id);
}
