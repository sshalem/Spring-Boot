package com.jpa.security.service;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jpa.security.entity.RoleEntity;
import com.jpa.security.entity.UserEntity;
import com.jpa.security.exceptions.ResourceNotFoundException;
import com.jpa.security.repository.RoleRepository;
import com.jpa.security.repository.UserRepository;

@Service
public class RoleServiceImpl implements RoleService {

	private final static Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	/***********************
	 * CREATE
	 ***********************/
	@Override
	@Transactional
	public RoleEntity createRole(RoleEntity roleEntity) {
		LOGGER.info("invoke createRole() ");

		RoleEntity _roleEntityByRole = roleRepository.findByRole(roleEntity.getRole());

		if (_roleEntityByRole != null)
			throw new DuplicateKeyException("Role with name : " + roleEntity.getRole() + " , already Exist");
		return roleRepository.save(roleEntity);
	}

	/****************
	 * GET
	 ***************/

	@Override
	public RoleEntity getRoleByRolename(String role) {
		RoleEntity _roleEntity = roleRepository.findByRole(role);

		if (_roleEntity == null)
			throw new ResourceNotFoundException("Role : " + role + " , NOT Exist");
		return _roleEntity;
	}

	@Override
	public List<RoleEntity> gettAllRoles() {
		return roleRepository.findAll();
	}

	@Override
	public List<UserEntity> getUsersWhoHasRole(String role) {
		List<UserEntity> _users = roleRepository.jpqlFindUsersWithRole(role);
		return _users;
	}

	/****************
	 * Update
	 ***************/

	@Override
	@Transactional
	public RoleEntity updateRoleDetails(RoleEntity roleEntity) {
		RoleEntity _roleEntity = roleRepository.findByRole(roleEntity.getRole());

		if (_roleEntity == null)
			throw new ResourceNotFoundException(" Role " + roleEntity.getRole() + " Not Found");

		_roleEntity.setRole(roleEntity.getRole());

		return roleRepository.save(_roleEntity);
	}

	@Override
	@Transactional
	public UserEntity addRoleToUser(String email, String role) {
		UserEntity _userEntity = userRepository.findByEmail(email);

		if (_userEntity == null)
			throw new NullPointerException("User with Email : " + email + " , Not Exist");

		RoleEntity _roleEntity = roleRepository.findByRole(role);
		
		if(_roleEntity == null)	
			throw new ResourceNotFoundException("Role : " + role + " Not Exist");

		boolean contains = _userEntity.getRoles().contains(_roleEntity);

		if (contains)
			throw new DuplicateKeyException("User already has role: " + role);

		_userEntity.addRole(_roleEntity);
		UserEntity returnedValue = userRepository.save(_userEntity);
		return returnedValue;
	}

	/****************
	 * Delete
	 ***************/
	@Override
	@Transactional
	public void deleteRoleByRoleName(String role) {

		List<UserEntity> _users = userRepository.findAll();
		RoleEntity _roleEntity = roleRepository.findByRole(role);

		for (UserEntity userEntity : _users) {
			boolean contains = userEntity.getRoles().contains(_roleEntity);
			if (contains) {
				userEntity.removeRole(_roleEntity);
				userRepository.save(userEntity);
			}
		}
		roleRepository.delete(_roleEntity);
	}

	@Override
	@Transactional
	public UserEntity removeRoleFromUserByRoleName(String email, String role) {
		UserEntity _userEntity = userRepository.findByEmail(email);

		if (_userEntity == null)
			throw new NullPointerException("User with Email: " + email + " , Not Exist");

		RoleEntity _roleEntity = roleRepository.findByRole(role);
		_userEntity.removeRole(_roleEntity);
		return userRepository.save(_userEntity);
	}

	@Override
	@Transactional
	public Set<RoleEntity> removeAllRolesFromUser(String email) {
		List<RoleEntity> _roles = roleRepository.jpqlFindRolesOfUserByEmail(email);

		UserEntity _userEntity = userRepository.findByEmail(email);

		for (RoleEntity roleEntity : _roles) {
			_userEntity.removeRole(roleEntity);
			userRepository.save(_userEntity);
		}
		return _userEntity.getRoles();
	}

	@Override
	@Transactional
	public void deleteAllRoles() {
		List<RoleEntity> _roles = roleRepository.findAll();

		List<UserEntity> _users = userRepository.findAll();

		for (RoleEntity roleEntity : _roles) {
			for (UserEntity userEntity : _users) {
				userEntity.removeRole(roleEntity);
				userRepository.save(userEntity);
			}
		}
		roleRepository.deleteAll();
	}

}
