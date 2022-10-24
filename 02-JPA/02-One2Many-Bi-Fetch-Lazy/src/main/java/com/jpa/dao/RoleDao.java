package com.jpa.dao;

import java.util.List;

import com.jpa.dto.RoleDto;
import com.jpa.dto.UserDto;

public interface RoleDao {

	List<RoleDto> getRoleById(long id);

	List<UserDto> getUsersWithRoleName(String role);

	List<RoleDto> getRoleByPid(long pid);

	List<RoleDto> getAllRoles();
}
