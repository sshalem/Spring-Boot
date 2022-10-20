package com.jpa.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpa.entity.RoleEntity;
import com.jpa.entity.UserEntity;
import com.jpa.repository.RoleRepository;
import com.jpa.repository.UserRepository;

@Service
public class RoleDaoImpl implements RoleDao{

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<RoleEntity> getRoleById(long id) {
		return roleRepository.findById(id);
	}

	@Override
	public List<UserEntity> getUsersWithRoleName(String role) {
//		return roleRepository.jpqlFindUsersWithRoleName(role);
		return userRepository.nativeFindUsersWithRoleName(role);
	}
 
	@Override
	public List<RoleEntity> getRoleByPid(long pid) {
		return roleRepository.findByPid(pid);
	}

	@Override
	public List<RoleEntity> getAllRoles() {
		return roleRepository.findAll();
	}

}
