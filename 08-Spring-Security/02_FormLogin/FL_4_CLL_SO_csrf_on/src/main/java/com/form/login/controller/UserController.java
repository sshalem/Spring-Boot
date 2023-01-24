package com.form.login.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.form.login.dao.UserDaoImpl;
import com.form.login.dto.UserRequestModel;
import com.form.login.entity.UserEntity;
import com.form.login.exceptions.EmailOrUserAlreadyExistException;
import com.form.login.exceptions.ErrorMessage;
import com.form.login.exceptions.ObjectNotExistException;
import com.form.login.shared.UserDeleteResponse;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserDaoImpl userDaoImpl;

	@GetMapping("/users/get/userId/{userid}")
	public ResponseEntity<Object> getUserById(@PathVariable("userid") long userid, HttpServletRequest request) {

		try {
			UserEntity returnedValue = userDaoImpl.getByUserid(userid);
			return new ResponseEntity<Object>(returnedValue, HttpStatus.OK);
		} catch (ObjectNotExistException e) {
			return new ResponseEntity<Object>(
					new ErrorMessage(
							HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
							e.getClass().getName(), 
							e.getMessage(), 
							HttpStatus.INTERNAL_SERVER_ERROR.value(), 
							new Date(),
							request.getRequestURI()), 
							HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/users/get/{username}_requested")
	public ResponseEntity<Object> getUserByName(@PathVariable("username") String username) throws ObjectNotExistException {

		UserEntity returnedValue = userDaoImpl.getByUsername(username);
		return ResponseEntity.status(HttpStatus.FOUND).body(returnedValue);
	}

	@GetMapping("/users/getAllUsers")
	public ResponseEntity<List<UserEntity>> getAllUsers() {

		List<UserEntity> returnedValue = userDaoImpl.getAllUsers();
		return new ResponseEntity<List<UserEntity>>(returnedValue, HttpStatus.ACCEPTED);
	}

	// Update USer
	@PutMapping("/app/superadmin/users/update")
	public ResponseEntity<UserEntity> updateUser(@RequestBody UserEntity user) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(userDaoImpl.updateUser(user));
	}

	// Create User
	@PostMapping("/app/superadmin/create")
	public ResponseEntity<UserEntity> createUser(@RequestBody UserRequestModel userRequestModel) throws ObjectNotFoundException, EmailOrUserAlreadyExistException {

		UserEntity retunredValue = userDaoImpl.createUser(userRequestModel);		
		return new ResponseEntity<UserEntity>(retunredValue, HttpStatus.CREATED);
	}

	// Delete User
	@DeleteMapping("/app/superadmin/delete/{userid}")
	public ResponseEntity<UserDeleteResponse> deleteUser(@PathVariable("userid") long userid) {
		userDaoImpl.deleteUser(userid);
		return ResponseEntity.status(HttpStatus.OK).body(new UserDeleteResponse("User Deleted Successfully"));
	}

}
