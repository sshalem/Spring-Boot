package com.delete.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.delete.entity.UserEntity;
import com.delete.service.UserService;

@RestController
@RequestMapping(path = "/api")
public class UserController {

	private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@PostMapping("/createAndDeleteById")
	public ResponseEntity<?> createAndDeleteById(@RequestBody UserEntity user) {
		try {
			UserEntity returnValue = userService.createAndDeleteById(user);
			return ResponseEntity.ok(returnValue);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return null;
		}
	}
	
	@PostMapping("/createAndDeleteByEmail")
	public ResponseEntity<?> createAndDeleteByEmail(@RequestBody UserEntity user) {
		try {
			UserEntity returnValue = userService.createAndDeleteByEmail(user);
			return ResponseEntity.ok(returnValue);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return null;
		}
	}

}
