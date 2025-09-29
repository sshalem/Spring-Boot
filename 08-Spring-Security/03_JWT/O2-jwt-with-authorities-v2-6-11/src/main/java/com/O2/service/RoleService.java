package com.O2.service;

import java.util.List;
import java.util.Set;

import com.O2.entity.RoleEntity;
import com.O2.entity.UserEntity;

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
