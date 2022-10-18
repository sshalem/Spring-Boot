package com.jpa.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpa.entity.RoleEntity;
import com.jpa.repository.RoleRepository;

@Service
public class RoleDaoImpl implements RoleDao {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<RoleEntity> getRoleById(long id) {
		return roleRepository.findById(id);
	}

	@Override
	public List<RoleEntity> getRoleByName(String name) {
		return roleRepository.findByRole(name);
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
