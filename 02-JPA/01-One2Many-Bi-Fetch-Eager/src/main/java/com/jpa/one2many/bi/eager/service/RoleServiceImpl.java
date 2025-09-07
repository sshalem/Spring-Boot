package com.jpa.one2many.bi.eager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpa.one2many.bi.eager.entity.RoleEntity;
import com.jpa.one2many.bi.eager.entity.UserEntity;
import com.jpa.one2many.bi.eager.repository.RoleRepository;
import com.jpa.one2many.bi.eager.repository.UserRepository;

@Service
public class RoleServiceImpl implements RoleService {

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
