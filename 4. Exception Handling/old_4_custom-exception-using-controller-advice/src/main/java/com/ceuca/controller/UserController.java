package com.ceuca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceuca.entity.UserEntity;
import com.ceuca.service.UserServiceImpl;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserServiceImpl userServiceImpl;

	@PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserEntity createUser(@RequestBody UserEntity userEntity) {
		return userServiceImpl.createUser(userEntity);
	}

	@GetMapping(path = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserEntity> getUser(long id) {
		return ResponseEntity.status(HttpStatus.OK).body(userServiceImpl.getUser(id));
	}

	@PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserEntity> updateUser(@RequestBody UserEntity userEntity) {
		return ResponseEntity.status(HttpStatus.OK).body(userServiceImpl.updateUser(userEntity));
	}

	@DeleteMapping(path = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteUser(@RequestBody UserEntity userEntity) {
		userServiceImpl.deleteUser(userEntity);
		return ResponseEntity.status(HttpStatus.OK).body("User Deleted");
	}
}
