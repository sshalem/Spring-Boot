package com.chem.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chem.entity.UserEntity;
import com.chem.exception.ExceptionErrorMessage;
import com.chem.exception.NameAlreadyExistException;
import com.chem.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping("/{name}")
	public String getName(@PathVariable("name") String name) {
		return customerService.getName(name);
	}

	/**
	 * In this example I send custom ExceptionMessage created by me
	 * 
	 */
	@PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createUser(@RequestBody UserEntity userEntity) {
		try {
			return new ResponseEntity<Object>(customerService.createUser(userEntity), new HttpHeaders(), HttpStatus.OK);
		} catch (Exception em) {

			ExceptionErrorMessage errorMessage = new ExceptionErrorMessage();

			errorMessage.setTimestamp(new Date());
			errorMessage.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			errorMessage.setError(HttpStatus.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()).getReasonPhrase());
			errorMessage.setException(NameAlreadyExistException.class.getName());
			errorMessage.setMessage(em.getMessage());

			return new ResponseEntity<Object>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
