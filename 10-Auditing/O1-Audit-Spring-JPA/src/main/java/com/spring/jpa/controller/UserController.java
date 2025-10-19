package com.spring.jpa.controller;

import org.springframework.beans.BeanUtils;
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

import com.spring.jpa.dto.UserDto;
import com.spring.jpa.entity.RoleEntity;
import com.spring.jpa.entity.UserEntity;
import com.spring.jpa.service.UserServiceImpl;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserServiceImpl userServiceImpl;

	/**
	 * Here I return UserDto object,
	 * Why UserDto and Not UserEntity?
	 * because If I return UserEntity from RestController , I get a error that Session proxy not possible with Lazy Loading.
	 * Thus , In service layer I return a DTO
	 */
	
	// **************************************
	// ***** Post Methods ***
	// **************************************
	
	@PostMapping("/create")
	public ResponseEntity<?> createUser(@RequestBody UserEntity userEntity) {
		return new ResponseEntity<Object>(userServiceImpl.createUser(userEntity), null, HttpStatus.CREATED);
	}

	@PostMapping("/createUserWithRoles")
	public ResponseEntity<?> createUserWithRoles(@RequestBody UserEntity userEntity) {
		UserDto returnedValue = new UserDto();
		UserEntity user = userServiceImpl.createUserWithRoles(userEntity);
		BeanUtils.copyProperties(user,returnedValue);
		return new ResponseEntity<Object>(returnedValue, null, HttpStatus.CREATED);
	}

	// *********************
	// ***** Get Methods ***
	// *********************
	@GetMapping("/getUserById/{id}")
	public ResponseEntity<?> getUserById(@PathVariable("id") long id) {
		return new ResponseEntity<Object>(userServiceImpl.getUserById(id), null, HttpStatus.FOUND);
	}
	
	@GetMapping("/getUserByPid/{pid}")
	public ResponseEntity<UserDto> getUserByPid(@PathVariable("pid") long pid) {
		return new ResponseEntity<UserDto>(userServiceImpl.getUserByPid(pid), null, HttpStatus.FOUND);
	}
	 
	@GetMapping("/getUserByName/{name}")
	public ResponseEntity<?> getUserByName(@PathVariable("name") String name) {
		return new ResponseEntity<Object>(userServiceImpl.getUserByName(name), null, HttpStatus.FOUND);
	}

	@GetMapping("/getUserByEmail/{email}")
	public ResponseEntity<?> getUserByEmail(@PathVariable("email") String email) {
		return new ResponseEntity<Object>(userServiceImpl.getUserByEmail(email), null, HttpStatus.FOUND);
	}
		
	@GetMapping("/allUsers")
	public ResponseEntity<?> getAlltUser() {
		return new ResponseEntity<Object>(userServiceImpl.getAllUsers(), null, HttpStatus.FOUND);
	}

	
	// **************************************
	// ***** Put Methods ***
	// **************************************
	
	@PutMapping("/addRole/{userPid}")
	public ResponseEntity<?> addRoleToUser(@RequestBody RoleEntity roleEntity, @PathVariable("userPid") long userPid) {
		UserDto returnedValue = userServiceImpl.addRoleToUser(userPid, roleEntity);
		return new ResponseEntity<Object>(returnedValue, null, HttpStatus.CREATED);
	}

	@PutMapping("/addRoleUpdateUser/{userPid}")
	public ResponseEntity<?> addRoleUpdateUser(@RequestBody UserEntity userEntity, @PathVariable("userPid") long userPid) {
		UserEntity updateUser = userServiceImpl.addRoleUpdateUser(userPid, userEntity);
		UserDto returnedValue = new UserDto();
		BeanUtils.copyProperties(updateUser, returnedValue);
		return new ResponseEntity<Object>(returnedValue, null, HttpStatus.CREATED);
	}

	// **************************************
	// ***** Delete Methods ***
	// **************************************
	
	@DeleteMapping("/removeRole/{userPid}/{role}")
	public ResponseEntity<?> removeRoleFromUser(@PathVariable("role") String role, @PathVariable("userPid") long userPid) {		
		UserEntity returnedValue = userServiceImpl.removeRoleFromUser(userPid, role);
		return new ResponseEntity<Object>(returnedValue, null, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/removeUser/{userPid}")
	public ResponseEntity<?> removeUser(@PathVariable("userPid") long userPid) {		
		userServiceImpl.removeUserByPid(userPid);
		return new ResponseEntity<Object>(userServiceImpl.getAllUsers(), null, HttpStatus.ACCEPTED);
	}

}
