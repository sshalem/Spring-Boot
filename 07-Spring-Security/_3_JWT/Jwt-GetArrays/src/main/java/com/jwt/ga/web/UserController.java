package com.jwt.ga.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.ga.dao.UserDaoImpl;
import com.jwt.ga.domain.User;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserDaoImpl userDaoImpl;

	@GetMapping
	public ResponseEntity<List<User>> getUsers() {
		return ResponseEntity.ok().body(userDaoImpl.getUsers());
	}
}
