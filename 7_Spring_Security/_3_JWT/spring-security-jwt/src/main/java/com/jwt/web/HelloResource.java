package com.jwt.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloResource {

	@RequestMapping("/hello")
	public String hello() {
		return "hello world";
	}
//	Before we had JWT we used Form based login with user details  
//	 * that we initiated in the class of DBInit.
	
	/* First thing we need to do is	create and authenticate endpoint
	 * with JWT, we don't need a user id and password Form based login anymore, 
	 * What we need is some API where we can Post user Id & password .	 * 
	 * That API endpoint returns a JWT and a payload.
	 * 1. Authenticate API endpoint
	 * 		- Accepts user ID and password
	 * 		- Returns JWT as response 
	 * 
	 * 2. the client holds that JWT and sends it in a subsequent request , in the header
	 * 
	 */
}
