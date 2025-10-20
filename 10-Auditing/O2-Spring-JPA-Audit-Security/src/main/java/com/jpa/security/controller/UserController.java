package com.jpa.security.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.security.entity.RoleEntity;
import com.jpa.security.entity.UserEntity;
import com.jpa.security.service.UserServiceImpl;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserServiceImpl userServiceImpl;

	// *******************************
	// GET methods
	// ********************************
	@GetMapping(path = "/getUserByName/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getUserByName(@PathVariable String name) {
		LOGGER.info("getUserByName()");		
		return new ResponseEntity<>(userServiceImpl.getUserByName(name), HttpStatus.OK);
	}

	@GetMapping(path = "/getUserByEmail/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
		return new ResponseEntity<>(userServiceImpl.getUserByEmail(email), HttpStatus.OK);
	}

	@GetMapping(path = "/getUsersWithRole/{role}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getUsersWithRole(@PathVariable String role) {

		List<UserEntity> _users = userServiceImpl.getUsersWithRole(role);
		if (_users.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(_users, HttpStatus.OK);
	}

	@GetMapping(path = "/getAllRolesOfUser/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllRolesOfUser(@PathVariable String email) {

		List<RoleEntity> _roles = userServiceImpl.getAllRolesOfUserByEmail(email);
		if (_roles.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(_roles, HttpStatus.OK);
	}

	@GetMapping(path = "/getAllUsers", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllUsers() {
		return new ResponseEntity<>(userServiceImpl.getAllUsers(), HttpStatus.OK);
	}

	// ********************************
	// UPDATE methods
	// ********************************
	@PutMapping(path = "/updateUserDetails/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateUserDetails(@PathVariable String email, @RequestBody UserEntity userEntity) {
		UserEntity _user = userServiceImpl.updateUserDetails(email, userEntity);
		return new ResponseEntity<>(_user, HttpStatus.OK);
	}

	// ********************************
	// DELETE methods
	// ********************************
	@DeleteMapping(path = "/deleteUserByEmail/{email}")
	public void deleteUserByEmail(@PathVariable String email) {
		userServiceImpl.deleteUserByEmail(email);
	}

	/**
	 * Need to check this API why its not working
	 */
	@DeleteMapping(path = "/removeAllUsersFromRole/{role}")
	public ResponseEntity<Void> removeAllUsersFromRole(@PathVariable String role) {
		userServiceImpl.removeAllUsersFromRole(role);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping(path = "/deleteAllUsers")
	public ResponseEntity<Void> deleteAllUsers() {
		userServiceImpl.deleteAllUsers();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
