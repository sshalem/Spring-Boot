package com.jpa.one2many.bi.eager.service;

import java.util.List;

import com.jpa.one2many.bi.eager.entity.RoleEntity;
import com.jpa.one2many.bi.eager.entity.UserEntity;

public interface RoleService {

	List<RoleEntity> getRoleById(long id);

	List<UserEntity> getUsersWithRoleName(String role);

	List<RoleEntity> getRoleByPid(long pid);

	List<RoleEntity> getAllRoles();
}
