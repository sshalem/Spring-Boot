package com.basic.auth.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.basic.auth.dao.UserDaoImpl;
import com.basic.auth.entity.UserEntity;

@Component
public class DBinit implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserDaoImpl userDaoImpl;

	@Override
	public void run(String... args) throws Exception {

		List<UserEntity> users = userDaoImpl.getAllUsers();
		users.forEach(user -> {
			String encode = passwordEncoder.encode("123");
			user.setPassword(encode);
			userDaoImpl.updateUser(user);
		});
	}

}
