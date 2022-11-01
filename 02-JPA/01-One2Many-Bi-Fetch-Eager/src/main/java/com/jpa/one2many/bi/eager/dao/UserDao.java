package com.jpa.one2many.bi.eager.dao;

import java.util.List;

import com.jpa.one2many.bi.eager.entity.RoleEntity;
import com.jpa.one2many.bi.eager.entity.UserEntity;

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
