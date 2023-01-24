package com.backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.config.SecurityConstants;
import com.backend.dao.UserDaoImpl;
import com.backend.exceptions.EmailAlreadyExistException;
import com.backend.jwt.JwtTokenUtil;
import com.backend.model.JwtTokenRequest;
import com.backend.model.JwtTokenResponse;
import com.backend.model.UserLoginDetails;
import com.backend.model.UserRegisterRequest;
import com.backend.model.UserRegisterResponse;
import com.backend.service.JwtUserDetails;
import com.backend.service.JwtUserDetailsService;

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
		
		Authentication authenticate;
		
		try {
			authenticate = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(authReq.getEmail(), authReq.getPassword()));
		} catch (BadCredentialsException e) {
			LOGGER.error("********  Login Failed ******** ");
			throw new BadCredentialsException(e.getMessage());
		}

		JwtUserDetails jwtUserDetails = (JwtUserDetails) ((Authentication) authenticate).getPrincipal();
		
		// The user-name is the email address (check the method)
		String email = jwtUserDetails.getUsername();
		final UserLoginDetails userLoginDetails = userDaoImpl.getUserLoginDetailsByEmail(email);
		final String token = jwtTokenUtil.generateToken(jwtUserDetails);
		
		/**
		 * This is also a way to get the user-name since we are already authenticated if no exception is thrown, 
		 */
//		final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authReq.getEmail());
//		final UserLoginDetails userLoginDetails = userDaoImpl.getUserLoginDetailsByEmail(authReq.getEmail());
//		final String token = jwtTokenUtil.generateToken(userDetails);

		// JwtTokenResponse jwtTokenResponse =
		// JwtTokenResponse.name(name).token(token).build();
		// System.out.println(jwtTokenResponse);
		// return ResponseEntity.ok(jwtTokenResponse);

		LOGGER.info(" -------- User Login Succeeded --------");
		return ResponseEntity.ok(new JwtTokenResponse(userLoginDetails, token));

	}

	/**
	 * Register Request
	 * 
	 * @throws EmailAlreadyExistException
	 */
	@PostMapping(path = SecurityConstants.REGISTER_URL)
	public ResponseEntity<?> signUp(@RequestBody UserRegisterRequest userRegisterRequest) {

		UserRegisterResponse userRegisterResponse = userDaoImpl.createUser(userRegisterRequest);
		LOGGER.info(" -------- User registration Succeeded --------");
		return ResponseEntity.ok(userRegisterResponse);
	}

	/**
	 * Register Request
	 * 
	 * @throws EmailAlreadyExistException
	 */
	@PutMapping(path = SecurityConstants.UPDATE_USER_URL)
	public ResponseEntity<?> updateUser(@RequestBody UserLoginDetails userLoginDetails) {

		UserLoginDetails userLoginDetailsupdate = userDaoImpl.updateUserDetails(userLoginDetails);
		LOGGER.info(" -------- User Details updated Succeeded --------");
		return ResponseEntity.ok(userLoginDetailsupdate);
	}
}
