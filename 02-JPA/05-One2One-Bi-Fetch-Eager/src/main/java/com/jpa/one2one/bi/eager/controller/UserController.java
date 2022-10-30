package com.jpa.one2one.bi.eager.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.one2one.bi.eager.dao.UserDaoImpl;
import com.jpa.one2one.bi.eager.entity.AddressEntity;
import com.jpa.one2one.bi.eager.entity.UserEntity;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserDaoImpl userDaoImpl;

	// *********************
	// ***** Get Methods ***
	// *********************
	@GetMapping(value = "/getByName/{name}")
	public ResponseEntity<?> getUserByName(@PathVariable("name") String name) {
		return new ResponseEntity<Object>(userDaoImpl.getUserByName(name), HttpStatus.CREATED);
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
	// ***** Post Methods ***
	// **************************************
	@PostMapping(value = "/create")
	public ResponseEntity<?> createUser(@RequestBody UserEntity userEntity) {
		return new ResponseEntity<Object>(userDaoImpl.createUser(userEntity), HttpStatus.CREATED);
	}

	@PostMapping(value = "/addAddress/{name}")
	public ResponseEntity<?> addAddressToUser(@RequestBody AddressEntity addressEntity,
			@PathVariable("name") String name) {
		return new ResponseEntity<Object>(userDaoImpl.addAddressToUser(addressEntity, name), HttpStatus.CREATED);
	}

	// **************************************
	// ***** Put Methods ***
	// **************************************

	// **************************************
	// ***** Delete Methods ***
	// **************************************

}
