package com.in28min.Jwt.H2.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:4200")
public class HelloWorldController {

	@GetMapping(path = "/hello")
	public HelloWorldBean getHello() {
//		this was to check that error message is printed when doing AJAX from ANgualr 
//		by using Observable and Subscribe
//		throw new RuntimeException("Some Error Happend ***-***");
		return new HelloWorldBean("hello world from Server");
	}
}
