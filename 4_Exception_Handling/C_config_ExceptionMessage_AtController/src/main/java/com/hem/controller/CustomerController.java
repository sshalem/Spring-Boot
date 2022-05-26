package com.hem.controller;

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

import com.hem.entity.UserEntity;
import com.hem.service.CustomerService;

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
	 * In this exapmle I config ResponseEntity
	 * to return the exception message
	 * 
	 * 
	 */
	@PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createUser(@RequestBody UserEntity userEntity) {
		try {
			return new ResponseEntity<Object>(customerService.createUser(userEntity), new HttpHeaders(), HttpStatus.OK);
		} catch (Exception em) {
			return new ResponseEntity<Object>(em.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
