package com.backend.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.config.SecurityConstants;
import com.backend.jwt.JwtTokenUtil;
import com.backend.jwt.JwtUserDetails;
import com.backend.jwt.JwtUserDetailsService;
import com.backend.model.JwtTokenLoginRequest;
import com.backend.model.JwtTokenResponse;
import com.backend.model.UserRegisterRequest;
import com.backend.model.UserRegisterResponse;
import com.backend.service.RefreshTokenServiceImpl;
import com.backend.service.UserServiceImpl;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/auth")
public class JwtAuthenticationController {

	private static Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationController.class);

	private final AuthenticationManager authenticationManager;
	private final RefreshTokenServiceImpl refreshTokenServiceImpl;
	private final JwtUserDetailsService jwtUserDetailsService;
	private final UserServiceImpl userServiceImpl;
	private final JwtTokenUtil jwtTokenUtil;

	public JwtAuthenticationController(AuthenticationManager authenticationManager, RefreshTokenServiceImpl refreshTokenServiceImpl,
			JwtUserDetailsService jwtUserDetailsService, UserServiceImpl userServiceImpl, JwtTokenUtil jwtTokenUtil) {
		this.authenticationManager = authenticationManager;
		this.refreshTokenServiceImpl = refreshTokenServiceImpl;
		this.jwtUserDetailsService = jwtUserDetailsService;
		this.userServiceImpl = userServiceImpl;
		this.jwtTokenUtil = jwtTokenUtil;
	}

	/************************
	 * Login Request
	 ***********************/
	@PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtTokenLoginRequest authLoginReq) throws Exception {

		Authentication authenticate;

		try {
			authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authLoginReq.getEmail(), authLoginReq.getPassword()));
		} catch (BadCredentialsException e) {
			LOGGER.error(e.getMessage());
			LOGGER.error("Authentication failed, throwing BadCredentialsException");
			throw new BadCredentialsException(e.getMessage());
		}

		/**
		 * 🔑 Why I do (JwtUserDetails) authenticate.getPrincipal()? 
		 * ✅ No extra DB call — I already have the authenticated JwtUserDetails inside the Authentication object. 
		 * ✅ Standard Spring Security way (this is why the Principal exists). 
		 * 
		 * 🔑 Then Why, During request filtering (JWT validation), I call jwtUserDetailsService.loadUserByUsername(email) again? 
		 * ✅ It's because I only have the JWT’s subject (username) and need to reconstruct UserDetails for the SecurityContext.
		 */
				
		final JwtUserDetails jwtUserDetails = (JwtUserDetails) authenticate.getPrincipal();
		final String name = jwtUserDetails.getUsername();
		final String accessToken = jwtTokenUtil.generateAccessToken(jwtUserDetails);
		final String refreshToken = refreshTokenServiceImpl.generateRefreshToken(jwtUserDetails.getUsername(), SecurityConstants.INVOKED_LOGIN_URL, null);
		
		ResponseCookie responseCookie = ResponseCookie.from("refreshToken",refreshToken)
			.httpOnly(true)
			.secure(true)
			.path("/auth/refreshToken")
			.maxAge(SecurityConstants.REFRESH_TOKEN_EXPIRATION_TIME_ms)
			.sameSite("Strict")
			.build();
		
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, responseCookie.toString()).body(new JwtTokenResponse(name, accessToken));
	}


	/*
	 * ✔ On logout: 
	 * Client deletes accessToken
	 * Client deletes refreshToken
	 * Server does nothing
	 * → Since tokens are stateless, server cannot invalidate them anyway.
	 * 
	 * ✔ Works fine if:
	 * Short-lived access token (5–15 minutes)
	 * Refresh token expiration is reasonable (7–30 days)
	 * 🔧 Logout = clear tokens on the FrontEnd
	 */
	
	/**
	 * logout Request
	 */
	@GetMapping(path = "/logout", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> logout(HttpServletRequest request) {

		final String authorizationHeader = request.getHeader(SecurityConstants.AUTHORIZATION);

		if (authorizationHeader != null && authorizationHeader.startsWith(SecurityConstants.REFRESH_TOKEN_PREFIX)) {
			String _refreshToken = authorizationHeader.substring(14);
			refreshTokenServiceImpl.deleteRefreshToken(_refreshToken);
		}
		LOGGER.info("User logged out ---  Succeeded");
		return ResponseEntity.ok(Map.of("message","User Logged Out"));
	}
		
	
	@PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> signUp(@RequestBody UserRegisterRequest userRegisterRequest) {

		UserRegisterResponse userRegisterResponse = userServiceImpl.createUser(userRegisterRequest);
		LOGGER.info("User registration Succeeded");
		return ResponseEntity.ok(userRegisterResponse);
	}
	
	
	@GetMapping(path = "/refreshToken", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> refreshtoken(@CookieValue(name = "refreshToken", required = false) String refreshToken) throws Exception {
		
	    if (refreshToken == null) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	    }
		
	    try {
			refreshTokenServiceImpl.validateRefreshToken(refreshToken);				
			String email = refreshTokenServiceImpl.getUserByRefreshToken(refreshToken).getEmail();				
			UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(email);
					
			final String name = userDetails.getUsername();
			final String newAccessToken = jwtTokenUtil.generateAccessToken(userDetails);
			final String newRefreshToken = refreshTokenServiceImpl.generateRefreshToken(
					userDetails.getUsername(),
					SecurityConstants.INVOKED_REFRESH_URL, 
					refreshToken);
							
			ResponseCookie responseCookie = ResponseCookie.from("refreshToken", newRefreshToken)
					.httpOnly(true)
					.secure(true)
					.path("/auth/refreshToken")
					.maxAge(SecurityConstants.REFRESH_TOKEN_EXPIRATION_TIME_ms)
					.sameSite("Strict")
					.build();
				
			return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, responseCookie.toString()).body(new JwtTokenResponse(name, newAccessToken));			
			
		} catch (Exception ex) {					
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("error", ex.getMessage()));				
		}      
	}		
	
}
