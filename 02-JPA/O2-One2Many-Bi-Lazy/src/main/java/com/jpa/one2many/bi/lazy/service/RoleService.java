package com.jpa.one2many.bi.lazy.service;

import java.util.List;

import com.jpa.one2many.bi.lazy.dto.RoleDto;
import com.jpa.one2many.bi.lazy.dto.UserDto;

public interface RoleService {

	List<RoleDto> getRoleById(long id);

	List<UserDto> getUsersWithRoleName(String role);

	List<RoleDto> getRoleByPid(long pid);

	List<RoleDto> getAllRoles();
}
