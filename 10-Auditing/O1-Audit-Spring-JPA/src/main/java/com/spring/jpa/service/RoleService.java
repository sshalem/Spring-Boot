package com.spring.jpa.service;

import java.util.List;

import com.spring.jpa.dto.RoleDto;
import com.spring.jpa.dto.UserDto;

public interface RoleService {

	List<RoleDto> getRoleById(long id);

	List<UserDto> getUsersWithRoleName(String role);

	List<RoleDto> getRoleByPid(long pid);

	List<RoleDto> getAllRoles();
}
