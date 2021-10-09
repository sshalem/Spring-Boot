package com.cem.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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

import com.cem.entity.UserEntity;
import com.cem.exception.ExceptionErrorMessage;
import com.cem.exception.UserServiceException;
import com.cem.service.UserServiceImpl;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserServiceImpl userServiceImpl;

	@PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createUser(@RequestBody UserEntity userEntity) {

		try {
			return new ResponseEntity<Object>(userServiceImpl.createUser(userEntity), new HttpHeaders(),
					HttpStatus.ACCEPTED);
		} catch (Exception em) {
			
			ExceptionErrorMessage errorMessage = new ExceptionErrorMessage();
			
			errorMessage.setTimestamp(new Date());
			errorMessage.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			errorMessage.setError(HttpStatus.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()).getReasonPhrase());
			errorMessage.setException(UserServiceException.class.getName());
			errorMessage.setMessage(em.getMessage());

			return new ResponseEntity<Object>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
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
