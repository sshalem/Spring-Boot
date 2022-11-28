package com.auth02.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.auth02.config.SecurityConstants;
import com.auth02.dao.UserDaoImpl;
import com.auth02.model.JwtTokenRequest;
import com.auth02.model.JwtTokenResponse;
import com.auth02.model.UserSignUpRequest;
import com.auth02.model.UserSignUpResponse;
import com.auth02.service.JwtUserDetailsService;
import com.auth02.util.JwtTokenUtil;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	@Autowired
	private UserDaoImpl userDaoImpl;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtTokenRequest authReq) throws Exception {

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authReq.getUsername(), authReq.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID CREDENTIALS", e);
		}

		final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authReq.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtTokenResponse(token));
	}

	@RequestMapping(value = SecurityConstants.SIGN_UP_URL, method = RequestMethod.POST)
	public ResponseEntity<?> signUp(@RequestBody UserSignUpRequest userSignUpRequest) {
		UserSignUpResponse userSignUpResponse = userDaoImpl.createUser(userSignUpRequest);
		return ResponseEntity.ok(userSignUpResponse);
	}

}
