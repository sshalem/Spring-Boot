package com.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.dao.UserDaoImpl;
import com.jpa.entity.RoleEntity;
import com.jpa.entity.UserEntity;

@RestController
@RequestMapping("/")
public class UserController {

	@Autowired
	private UserDaoImpl userDaoImpl;

	@PostMapping("/create")
	public ResponseEntity<?> createUser(@RequestBody UserEntity userEntity) {
		return new ResponseEntity<Object>(userDaoImpl.createUser(userEntity), null, HttpStatus.CREATED);
	}

	@PostMapping("/addRole/{userId}")
	public ResponseEntity<?> addRoleToUser(@RequestBody RoleEntity roleEntity , @PathVariable("userId") long id) {

		UserEntity userEntity = userDaoImpl.getUserById(id);
		
		UserEntity returnedValue = userDaoImpl.addRoleToUser(userEntity, roleEntity);
		
		return new ResponseEntity<Object>(returnedValue, null, HttpStatus.CREATED);
	}

	@PostMapping("/removeRole/{userId}")
	public ResponseEntity<?> removeRoleFromUser(@RequestBody RoleEntity roleEntity , @PathVariable("userId") long id) {

		UserEntity userEntity = userDaoImpl.getUserById(id);
		
		UserEntity returnedValue = userDaoImpl.removeRoleFromUser(userEntity, roleEntity);
		
		return new ResponseEntity<Object>(returnedValue, null, HttpStatus.CREATED);
	}
}
