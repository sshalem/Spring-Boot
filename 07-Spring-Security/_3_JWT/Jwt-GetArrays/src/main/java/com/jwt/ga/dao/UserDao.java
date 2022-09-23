package com.jwt.ga.dao;

import java.util.List;

import com.jwt.ga.domain.Role;
import com.jwt.ga.domain.User;

public interface UserDao {

	User saveUser(User user);

	Role saveRole(Role role);

	void addRoleToUser(String username, String role);

	User getUser(String username);

	List<User> getUsers();
}
