package com.O2.controller;

import com.O2.service.UserServiceImpl;
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

import com.O2.jwt.JwtTokenUtil;
import com.O2.jwt.JwtUserDetails;
import com.O2.model.JwtTokenLoginRequest;
import com.O2.model.JwtTokenResponse;
import com.O2.model.UserRegisterRequest;
import com.O2.model.UserRegisterResponse;

@RestController
@RequestMapping(path = "/auth")
public class JwtAuthenticationController {

	private static Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationController.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserServiceImpl userServiceImpl;

	/**
	 * Login Request
	 */
	@PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtTokenLoginRequest authLoginReq)
			throws Exception {

		Authentication authenticate;

		try {
			authenticate = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authLoginReq.getEmail(), authLoginReq.getPassword()));
		} catch (BadCredentialsException e) {
			LOGGER.error("Authentication failed, throwing BadCredentialsException");
			throw new BadCredentialsException(e.getMessage());
		}

		/**
		 * 🔑 Why I do (JwtUserDetails) authenticate.getPrincipal()? ✅ No extra DB call
		 * — I already have the authenticated JwtUserDetails inside the Authentication
		 * object. ✅ Standard Spring Security way (this is why the Principal exists). 🔑
		 * Then Why, During request filtering (JWT validation), I call
		 * jwtUserDetailsService.loadUserByUsername(email) again? ✅ It's because I only
		 * have the JWT’s subject (username) and need to reconstruct UserDetails for the
		 * SecurityContext.
		 */

		final JwtUserDetails jwtUserDetails = (JwtUserDetails) authenticate.getPrincipal();
		final String name = jwtUserDetails.getUsername();
		final String token = jwtTokenUtil.generateToken(jwtUserDetails);

		return ResponseEntity.ok(new JwtTokenResponse(name, token));
	}

	/**
	 * Register Request
	 */
	@PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> signUp(@RequestBody UserRegisterRequest userRegisterRequest) {

		UserRegisterResponse userRegisterResponse = userServiceImpl.createUser(userRegisterRequest);
		LOGGER.info("User registration Succeeded");
		return ResponseEntity.ok(userRegisterResponse);
	}

}
