package com.in28min.basic.auth.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in28min.basic.auth.entity.AuthenticationBean;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BasicAuthenticationController {

	@GetMapping(path = "/basicAuth")
	public AuthenticationBean getAuthentication() {
//		this was to check that error message is printed when doing AJAX from Angualr 
//		by using Observable and Subscribe
//		throw new RuntimeException("Some Error Happened ***-***");
		return new AuthenticationBean("You are Authenticated");
	}
}
