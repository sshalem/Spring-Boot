package com.jpa.dao;

import java.util.List;

import com.jpa.entity.RoleEntity;

public interface RoleDao {

	List<RoleEntity> getRoleById(long id);

	List<RoleEntity> getRoleByName(String name);

	List<RoleEntity> getRoleByPid(long pid);

	List<RoleEntity> getAllRoles();
}
