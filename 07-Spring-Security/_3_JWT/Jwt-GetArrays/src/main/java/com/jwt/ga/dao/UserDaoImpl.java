package com.jwt.ga.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jwt.ga.domain.Role;
import com.jwt.ga.domain.User;
import com.jwt.ga.repository.RoleRepository;
import com.jwt.ga.repository.UserRepository;

@Service
@Transactional
public class UserDaoImpl implements UserDao {

	private Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

	private final UserRepository userRepo;
	private final RoleRepository roleRepo;

	@Autowired
	public UserDaoImpl(UserRepository userRepo, RoleRepository roleRepo) {
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
	}

	@Override
	public User saveUser(User user) {
		LOGGER.info("Saving new user {} to the database", user.getName());
		return userRepo.save(user);
	}

	@Override
	public Role saveRole(Role role) {
		LOGGER.info("Saving new role {} to the database", role.getRole());
		return roleRepo.save(role);
	}

	@Override
	public void addRoleToUser(String username, String role) {
		LOGGER.info("Adding role {} to user {}", role, username);
		User user = userRepo.findByUsername(username);
		Role roleName = roleRepo.findByRole(role);
		user.getRoles().add(roleName);
	}

	@Override
	public User getUser(String username) {
		LOGGER.info("Fetching user {}", username);
		return userRepo.findByUsername(username);
	}

	@Override
	public List<User> getUsers() {
		LOGGER.info("Fetching all users");
		return userRepo.findAll();
	}

}
