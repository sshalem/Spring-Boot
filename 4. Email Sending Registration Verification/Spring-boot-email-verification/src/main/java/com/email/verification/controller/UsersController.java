package com.email.verification.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.email.verification.entity.User;
import com.email.verification.service.UserServices;

@RestController
@RequestMapping("/api")
public class UsersController {

	@Autowired
	private UserServices userServices;

	@GetMapping
	public List<User> listOfUsers() {
		return userServices.getListOfUsers();
	}
}
