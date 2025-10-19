package com.jpa.one2many.bi.lazy.service;

import java.util.List;

import com.jpa.one2many.bi.lazy.dto.UserDto;
import com.jpa.one2many.bi.lazy.entity.RoleEntity;
import com.jpa.one2many.bi.lazy.entity.UserEntity;

public interface UserService {

	UserDto createUser(UserEntity userEntity);

    UserEntity createUserWithRoles(UserEntity userEntity);

    UserDto getUserById(long id);

	UserDto getUserByPid(long id);

	UserDto getUserByName(String name);

	UserDto getUserByEmail(String email);

	List<UserDto> getAllUsers();

	void removeUserByPid(long pid);

	UserDto addRoleToUser(long userPid, RoleEntity roleEntity);

	UserEntity addRoleUpdateUser(long userPid, UserEntity userEntity);

	UserEntity removeRoleFromUser(long userPid, String role);

}
