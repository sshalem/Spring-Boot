package com.jpa.one2many.uni.eager.dao;

import java.util.List;

import com.jpa.one2many.uni.eager.entity.RoleEntity;
import com.jpa.one2many.uni.eager.entity.UserEntity;

public interface RoleDao {

	List<RoleEntity> getRoleById(long id);

	List<UserEntity> getUsersWithRoleName(String role);

	List<RoleEntity> getRoleByPid(long pid);

	List<RoleEntity> getAllRoles();
}
