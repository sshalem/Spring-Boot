package com.jpa.dao;

import java.util.List;

import com.jpa.entity.RoleEntity;
import com.jpa.entity.UserEntity;

public interface RoleDao {

	List<RoleEntity> getRoleById(long id);

	List<UserEntity> getUsersWithRoleName(String role);

	List<RoleEntity> getRoleByPid(long pid);

	List<RoleEntity> getAllRoles();
}
