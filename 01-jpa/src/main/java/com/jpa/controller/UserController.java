package com.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@CrossOrigin("*")
//@Transactional
public class UserController {

	@Autowired
	private UserDaoImpl userDaoImpl;

	@PostMapping("/create")
	public ResponseEntity<?> createUser(@RequestBody UserEntity userEntity) {
		return new ResponseEntity<Object>(userDaoImpl.createUser(userEntity), null, HttpStatus.CREATED);
	}

	@PostMapping("/addRole/{userPid}")
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
