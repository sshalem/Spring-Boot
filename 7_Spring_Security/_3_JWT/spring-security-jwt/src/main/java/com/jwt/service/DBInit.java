package com.jwt.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.jwt.entity.Role;
import com.jwt.entity.User;
import com.jwt.repository.UserRepository;

@Component
public class DBInit implements CommandLineRunner {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {

		Set<Role> adminRoles = new HashSet<>();
		adminRoles.add(new Role("USER"));
		adminRoles.add(new Role("MANAGER"));
		adminRoles.add(new Role("ADMIN"));
		User admin = new User("admin", "admin@com.com", passwordEncoder.encode("123"), adminRoles);
		adminRoles.forEach(a -> admin.addRole(a));

		Set<Role> managerRoles = new HashSet<>();
		managerRoles.add(new Role("USER"));
		managerRoles.add(new Role("MANAGER"));
		User manager = new User("manager", "manager@com.com", passwordEncoder.encode("123"), managerRoles);
		managerRoles.forEach(m -> manager.addRole(m));

		Set<Role> userRoles = new HashSet<>();
		userRoles.add(new Role("USER"));
		User simpleUser = new User("user", "user@com.com", passwordEncoder.encode("123"), userRoles);
		userRoles.forEach(s -> simpleUser.addRole(s));

		userRepo.saveAll(Arrays.asList(admin, manager, simpleUser));

//		userRepo.save(karin);
//		userRepo.save(admin);
//		userRepo.save(manager);
//		userRepo.save(simple);

	}

}
