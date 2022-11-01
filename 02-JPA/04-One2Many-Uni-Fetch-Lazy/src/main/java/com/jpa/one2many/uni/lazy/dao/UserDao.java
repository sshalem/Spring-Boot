package com.jpa.one2many.uni.lazy.dao;

import java.util.List;

import com.jpa.one2many.uni.lazy.dto.UserDto;
import com.jpa.one2many.uni.lazy.entity.RoleEntity;
import com.jpa.one2many.uni.lazy.entity.UserEntity;

public interface UserDao {
	
	UserDto createUser(UserEntity userEntity);	
	
	UserDto getUserById(long id);
	
	UserDto getUserByPid(long id);
	
	UserDto getUserByName(String name);
	
	UserDto getUserByEmail(String email);
	
	List<UserDto> getAllUsers();
	
	void removeUserByPid(long pid);
	
	UserDto addRoleToUser(long userPid, RoleEntity roleEntity);

	UserEntity removeRoleFromUser(long userPid, String role);

}
