package com.backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.config.SecurityConstants;
import com.backend.dao.UserDaoImpl;
import com.backend.exceptions.EmailAlreadyExistException;
import com.backend.model.JwtTokenRequest;
import com.backend.model.JwtTokenResponse;
import com.backend.model.UserRegisterRequest;
import com.backend.model.UserRegisterResponse;
import com.backend.service.JwtUserDetailsService;
import com.backend.util.JwtTokenUtil;

@RestController
@RequestMapping(path = "/auth")
public class JwtAuthenticationController {

	private static Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationController.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	@Autowired
	private UserDaoImpl userDaoImpl;

	/**
	 * Login Request
	 */
	@PostMapping(path = "/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtTokenRequest authReq) throws Exception {

		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(authReq.getEmail(), authReq.getPassword()));
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException(e.getMessage());
		}

		final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authReq.getEmail());

		final String name = userDaoImpl.getUserName(authReq.getEmail());

		final String token = jwtTokenUtil.generateToken(userDetails);

		// JwtTokenResponse jwtTokenResponse =
		// JwtTokenResponse.name(name).token(token).build();
		// System.out.println(jwtTokenResponse);
		// return ResponseEntity.ok(jwtTokenResponse);

		return ResponseEntity.ok(new JwtTokenResponse(name, token));

	}

	/**
	 * Register Request
	 * 
	 * @throws EmailAlreadyExistException
	 */
	@PostMapping(path = SecurityConstants.REGISTER_URL)
	public ResponseEntity<?> signUp(@RequestBody UserRegisterRequest userRegisterRequest) {

		try {
			UserRegisterResponse userRegisterResponse = userDaoImpl.createUser(userRegisterRequest);
			LOGGER.info("User registration Succeeded");
			return ResponseEntity.ok(userRegisterResponse);
		} catch (Exception em) {
			LOGGER.error("User registration failed : " + em.getMessage());
			return new ResponseEntity<Object>(em.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}

	}

}
