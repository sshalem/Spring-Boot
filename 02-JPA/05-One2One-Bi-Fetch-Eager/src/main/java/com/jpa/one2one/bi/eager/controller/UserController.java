package com.jpa.one2one.bi.eager.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.one2one.bi.eager.dao.UserDaoImpl;
import com.jpa.one2one.bi.eager.entity.UserEntity;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserDaoImpl userDaoImpl;

	
	// **************************************
	// ***** Post Methods ***
	// **************************************
	@PostMapping(value = "/users")
	public ResponseEntity<?> createUser(@RequestBody UserEntity userEntity) {
		return new ResponseEntity<Object>(userDaoImpl.createUser(userEntity), HttpStatus.CREATED);
	}

	
	// *********************
	// ***** Get Methods ***
	// *********************
	@GetMapping("/users/{id}")
	public ResponseEntity<UserEntity> getUserById(@PathVariable("id") long id) {
		return new ResponseEntity<>(userDaoImpl.getUserById(id), HttpStatus.OK);
	}

	@GetMapping("/users")
	public ResponseEntity<List<UserEntity>> getAllUsers(@RequestParam(required = false) String name) {

		List<UserEntity> users = new ArrayList<UserEntity>();

		if (name == null) {
			userDaoImpl.getAllUsers().forEach(users::add);
		} else {
			userDaoImpl.getUsersByNameContaining(name).forEach(users::add);
		}

		if (users.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping("/users/published")
	public ResponseEntity<List<UserEntity>> getUsersByPublished(@RequestParam(required = false, defaultValue = "true") boolean isPublished) {

		List<UserEntity> users = userDaoImpl.getUsersByPublished(isPublished);

		if (users.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(users, HttpStatus.OK);
	}


	// **************************************
	// ***** Put Methods ***
	// **************************************

	@PutMapping("/users/{id}")
	public ResponseEntity<UserEntity> updateUser(@PathVariable("id") long id, @RequestBody UserEntity user) {
		return new ResponseEntity<>(userDaoImpl.updateUser(id, user), HttpStatus.OK);
	}
	
	// **************************************
	// ***** Delete Methods ***
	// **************************************
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<HttpStatus> deleteUserById(@PathVariable("id") long id) {
		
		/**
		 * NO Need to remove Address , before removing user.
		 * We can Immediately remove a User 
		 */
//		if (addressRepository.existsById(id)) {
//			addressRepository.deleteById(id);
//		}
		userDaoImpl.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/users/delete/all")
	public ResponseEntity<HttpStatus> deleteAllUsers() {
		
		userDaoImpl.deleteAllUsers();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
