package com.O2.service;

import java.util.List;
import java.util.UUID;

import com.O2.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.O2.entity.RoleEntity;
import com.O2.exceptions.EmailAlreadyExistException;
import com.O2.exceptions.ResourceNotFoundException;
import com.O2.model.UserRegisterRequest;
import com.O2.model.UserRegisterResponse;
import com.O2.repository.RoleRepository;
import com.O2.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

	private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/***********************
	 * CREATE
	 ***********************/
	@Override
	@Transactional
	public UserRegisterResponse createUser(UserRegisterRequest userRegisterRequest) {

		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(userRegisterRequest, userEntity);

		if (userRepository.findByEmail(userEntity.getEmail()) != null)
			throw new EmailAlreadyExistException("Email Already Exist");

		userEntity.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));

		UserEntity createdUser = userRepository.save(userEntity);

		UserRegisterResponse userRegisterResponse = new UserRegisterResponse();
		BeanUtils.copyProperties(createdUser, userRegisterResponse);

		userRegisterResponse.setId(UUID.randomUUID());

		return userRegisterResponse;
	}

	/****************
	 * Read
	 ***************/

	@Override
	public String getUserName(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);
		return userEntity.getName();
	}

	@Override
	public List<UserEntity> getUserByName(String name) {
		List<UserEntity> _users = userRepository.findByName(name);
		
		if (_users.isEmpty())
			throw new ResourceNotFoundException("User with name : " + name + " , Not Exist");
		return _users;
	}

	@Override
	@Transactional
	public UserEntity getUserByEmail(String email) {
		LOGGER.info("invoke getUserByEmail()");

		UserEntity _userEntity = userRepository.findByEmail(email);

		if (_userEntity == null)
			throw new ResourceNotFoundException("User with Email : " + email + " , Not Exist");
		return _userEntity;
	}

	@Override
	public List<UserEntity> getUsersWithRole(String role) {
		return userRepository.jpqlFindUsersWithRole(role);
	}

	@Override
	public List<RoleEntity> getAllRolesOfUserByEmail(String email) {
		return userRepository.jpqlFindAllRolesOfUserByEmail(email);
	}

	@Override
	public List<UserEntity> getAllUsers() {
		return userRepository.findAll();
	}

	/****************
	 * Update
	 ***************/

	@Override
	@Transactional
	public UserEntity updateUserDetails(String email, UserEntity userEntity) {
		UserEntity _userEntity = this.getUserByEmail(email);
		_userEntity.setName(userEntity.getName());
		_userEntity.setEmail(userEntity.getEmail());
		_userEntity.setPassword(userEntity.getPassword());
		return userRepository.save(_userEntity);
	}

	/****************
	 * Delete
	 ***************/

	@Override
	@Transactional
	public void deleteUserByEmail(String email) {
		UserEntity _userEntity = this.getUserByEmail(email);
		userRepository.delete(_userEntity);
	}

	@Override
	@Transactional
	public void removeAllUsersFromRole(String role) {
		List<UserEntity> _users = userRepository.jpqlFindUsersWithRole(role);
		RoleEntity roleEntity = roleRepository.findByRole(role);

		for (UserEntity _userEntity : _users) {
			_userEntity.removeRole(roleEntity);
			userRepository.save(_userEntity);
		}
	}

	@Override
	@Transactional
	public void deleteAllUsers() {
		userRepository.deleteAll();
	}

}
