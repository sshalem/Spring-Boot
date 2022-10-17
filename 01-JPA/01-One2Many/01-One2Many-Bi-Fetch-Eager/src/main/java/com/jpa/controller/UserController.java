package com.jpa.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.jpa.dao.UserDaoImpl;
import com.jpa.entity.RoleEntity;
import com.jpa.entity.UserEntity;

@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserDaoImpl userDaoImpl;

	// *********************
	// ***** Get Methods ***
	// *********************
	@GetMapping("/getUserById/{id}")
	public ResponseEntity<?> getUserById(@PathVariable("id") long id) {
		return new ResponseEntity<Object>(userDaoImpl.getUserById(id), null, HttpStatus.FOUND);
	}
	
	@GetMapping("/getUserByPid/{pid}")
	public ResponseEntity<?> getUserByPid(@PathVariable("pid") long pid) {
		return new ResponseEntity<Object>(userDaoImpl.getUserByPid(pid), null, HttpStatus.FOUND);
	}
	 
	@GetMapping("/getUserByName/{name}")
	public ResponseEntity<?> getUserByName(@PathVariable("name") String name) {
		return new ResponseEntity<Object>(userDaoImpl.getUserByName(name), null, HttpStatus.FOUND);
	}

	@GetMapping("/getUserEmail/{email}")
	public ResponseEntity<?> getUserByEmail(@PathVariable("email") String email) {
		return new ResponseEntity<Object>(userDaoImpl.getUserByEmail(email), null, HttpStatus.FOUND);
	}
	
	// *********************
	// ***** Get Methods ***
	// *********************
	
	@PostMapping("/create")
	public ResponseEntity<?> createUser(@RequestBody UserEntity userEntity) {
		return new ResponseEntity<Object>(userDaoImpl.createUser(userEntity), null, HttpStatus.CREATED);
	}

	@PutMapping("/addRole/{userPid}")
	public ResponseEntity<?> addRoleToUser(@RequestBody RoleEntity roleEntity, @PathVariable("userPid") long userPid) {
		UserEntity returnedValue = userDaoImpl.addRoleToUser(userPid, roleEntity);
		return new ResponseEntity<Object>(returnedValue, null, HttpStatus.CREATED);
	}
 
	@DeleteMapping("/removeRole/{userPid}")
	public ResponseEntity<?> removeRoleFromUser(@RequestBody RoleEntity roleEntity, @PathVariable("userPid") long userPid) {		
		UserEntity returnedValue = userDaoImpl.removeRoleFromUser(userPid, roleEntity);
		return new ResponseEntity<Object>(returnedValue, null, HttpStatus.CREATED);
	}
}
