package com.jpa.one2many.bi.lazy.dao;

import java.util.List;

import com.jpa.one2many.bi.lazy.dto.RoleDto;
import com.jpa.one2many.bi.lazy.dto.UserDto;

public interface RoleDao {

	List<RoleDto> getRoleById(long id);

	List<UserDto> getUsersWithRoleName(String role);

	List<RoleDto> getRoleByPid(long pid);

	List<RoleDto> getAllRoles();
}
