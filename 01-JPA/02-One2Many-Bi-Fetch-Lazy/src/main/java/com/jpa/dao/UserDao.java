package com.jpa.dao;

import java.util.Set;

import com.jpa.dto.UserDto;
import com.jpa.entity.RoleEntity;
import com.jpa.entity.UserEntity;

public interface UserDao {
	
	UserEntity createUser(UserEntity userEntity);

	UserDto getUserByName(String name);
	
	Set<RoleEntity> getUserRoles(long pid);
	
	UserEntity addRoleToUser(long userPid, RoleEntity roleEntity);

	UserEntity removeRoleFromUser(long userPid, RoleEntity roleEntity);

	UserEntity getUserByPid(long id);
}
