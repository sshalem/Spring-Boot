package com.combined.service;

import java.util.List;

import com.combined.dto.RoleDto;
import com.combined.dto.UserDto;

public interface RoleService {

	List<RoleDto> getRoleById(long id);

	List<UserDto> getUsersWithRoleName(String role);

	List<RoleDto> getRoleByPid(long pid);

	List<RoleDto> getAllRoles();
}
