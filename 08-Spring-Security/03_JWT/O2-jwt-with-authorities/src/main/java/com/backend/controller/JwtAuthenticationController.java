package com.backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.config.SecurityConstants;
import com.backend.exceptions.EmailAlreadyExistException;
import com.backend.jwt.JwtTokenUtil;
import com.backend.jwt.JwtUserDetails;
import com.backend.model.JwtTokenLoginRequest;
import com.backend.model.JwtTokenResponse;
import com.backend.model.UserRegisterRequest;
import com.backend.model.UserRegisterResponse;
import com.backend.service.UserServiceImpl;

@RestController
@RequestMapping(path = "/auth")
public class JwtAuthenticationController {

	private static Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationController.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

//	@Autowired
//	private JwtUserDetailsService jwtUserDetailsService;

	@Autowired
	private UserServiceImpl userServiceImpl;

	/**
	 * Login Request
	 */
	@PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtTokenLoginRequest authLoginReq) throws Exception {

		Authentication authenticate;

		try {
			authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authLoginReq.getEmail(), authLoginReq.getPassword()));
		} catch (BadCredentialsException e) {
			LOGGER.error("Authentication failed, throwing BadCredentialsException");
			throw new BadCredentialsException(e.getMessage());
		}

		/**
		 * First Way:
		 * If the authentication process is successful, we can get User’s information
		 * such as username, password, authorities from an Authentication object.
		 */
				
		final JwtUserDetails jwtUserDetails = (JwtUserDetails) authenticate.getPrincipal();
		final String name = jwtUserDetails.getUsername();
		final String token = jwtTokenUtil.generateToken(jwtUserDetails);

		return ResponseEntity.ok(new JwtTokenResponse(name, token));
		
		/**
		 * Second way:
		 * This is also a way to get the user-name since we are already authenticated,
		 * (if no exception is thrown), we can get user details from
		 * `loadUserByUsername`
		 */
		//		final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authLoginReq.getEmail());
		//		final String name = userDaoImpl.getUserName(authLoginReq.getEmail());
		//		final String token = jwtTokenUtil.generateToken(userDetails);
		//		return ResponseEntity.ok(new JwtTokenResponse(name, token));

		// JwtTokenResponse jwtTokenResponse =
		// JwtTokenResponse.name(name).token(token).build();
		// return ResponseEntity.ok(jwtTokenResponse);
	}

	/**
	 * Register Request
	 * 
	 * @throws EmailAlreadyExistException
	 */
	@PostMapping(path = SecurityConstants.REGISTER_URL, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> signUp(@RequestBody UserRegisterRequest userRegisterRequest) {

		UserRegisterResponse userRegisterResponse = userServiceImpl.createUser(userRegisterRequest);
		LOGGER.info("User registration Succeeded");
		return ResponseEntity.ok(userRegisterResponse);
	}

}
