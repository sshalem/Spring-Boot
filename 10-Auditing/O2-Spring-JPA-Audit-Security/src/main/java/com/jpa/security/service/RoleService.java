package com.jpa.security.service;

import java.util.List;
import java.util.Set;

import com.jpa.security.entity.RoleEntity;
import com.jpa.security.entity.UserEntity;

public interface RoleService {

	/***********
	 * Create
	 ***********/
	RoleEntity createRole(RoleEntity roleEntity);

	/***********
	 * Read
	 ***********/
	RoleEntity getRoleByRolename(String role);

	List<RoleEntity> gettAllRoles();

	List<UserEntity> getUsersWhoHasRole(String role);

	/************
	 * Update
	 ************/
	RoleEntity updateRoleDetails(RoleEntity roleEntity);

	UserEntity addRoleToUser(String email, String role);

	/***********
	 * Delete
	 ***********/
	void deleteRoleByRoleName(String role);

	UserEntity removeRoleFromUserByRoleName(String email, String role);

	Set<RoleEntity> removeAllRolesFromUser(String email);

	void deleteAllRoles();

}
