package com.backend.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.config.SecurityConstants;
import com.backend.dao.RefreshTokenDaoImpl;
import com.backend.dao.UserDaoImpl;
import com.backend.jwt.JwtTokenUtil;
import com.backend.model.JwtTokenLoginRequest;
import com.backend.model.JwtTokenResponse;
import com.backend.model.LogoutResponse;
import com.backend.model.UserRegisterRequest;
import com.backend.model.UserRegisterResponse;
import com.backend.service.JwtUserDetails;
import com.backend.service.JwtUserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	@Autowired
	private RefreshTokenDaoImpl refreshTokenDaoImpl;

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

		final JwtUserDetails jwtUserDetails = (JwtUserDetails) authenticate.getPrincipal();
		final String name = jwtUserDetails.getUsername();
		final String accessToken = jwtTokenUtil.generateToken(jwtUserDetails);
		final String refreshToken = refreshTokenDaoImpl.generateRefreshToken(jwtUserDetails.getUsername(), SecurityConstants.INVOKED_LOGIN_URL, null);

		return ResponseEntity.status(HttpStatus.CREATED).body(new JwtTokenResponse(name, accessToken, refreshToken));
	}

	/**
	 * logout Request
	 */
	@GetMapping(path = SecurityConstants.LOGOUT_URL, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LogoutResponse> logout(HttpServletRequest request) {

		final String authorizationHeader = request.getHeader(SecurityConstants.AUTHORIZATION);

		if (authorizationHeader != null && authorizationHeader.startsWith(SecurityConstants.REFRESH_TOKEN_PREFIX)) {
			String _refreshToken = authorizationHeader.substring(14);
			refreshTokenDaoImpl.deleteRefreshToken(_refreshToken);
		}
		LOGGER.info("User logged out Succeeded");
		return ResponseEntity.ok(new LogoutResponse("User Logged Out"));
	}

	/**
	 * Register Request
	 */
	@PostMapping(path = SecurityConstants.REGISTER_URL, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> signUp(@RequestBody UserRegisterRequest userRegisterRequest) {

		UserRegisterResponse userRegisterResponse = userDaoImpl.createUser(userRegisterRequest);
		LOGGER.info("User registration Succeeded");
		return ResponseEntity.ok(userRegisterResponse);
	}

	/**
	 * RefreshToken Request
	 */
	@GetMapping(path = "/refreshToken", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception {

		final String authorizationHeader = request.getHeader(SecurityConstants.AUTHORIZATION);

		if (authorizationHeader != null && authorizationHeader.startsWith(SecurityConstants.REFRESH_TOKEN_PREFIX)) {
			String _refreshToken = authorizationHeader.substring(14);

			try {
				refreshTokenDaoImpl.validateRefreshToken(_refreshToken);

				String email = refreshTokenDaoImpl.getUserByRefreshToken(_refreshToken).getEmail();

				UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(email);

				final String name = userDetails.getUsername();
				final String accessToken = jwtTokenUtil.generateToken(userDetails);
				final String refreshToken = refreshTokenDaoImpl.generateRefreshToken(userDetails.getUsername(),
						SecurityConstants.INVOKED_REFRESH_URL, _refreshToken);

				return ResponseEntity.status(HttpStatus.CREATED)
						.body(new JwtTokenResponse(name, accessToken, refreshToken));

			} catch (Exception ex) {

				Map<String, String> errorResponse = new HashMap<>();
				errorResponse.put("error", ex.getMessage());

				response.setHeader("error", ex.getMessage());
				response.setStatus(HttpStatus.FORBIDDEN.value());
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);

				new ObjectMapper().writeValue(response.getOutputStream(), errorResponse);
			}
		}
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Refresh Token Failed");
	}
}
