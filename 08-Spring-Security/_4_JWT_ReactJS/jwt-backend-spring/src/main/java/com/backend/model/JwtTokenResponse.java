package com.backend.model;

import java.io.Serializable;

public class JwtTokenResponse implements Serializable {

	private static final long serialVersionUID = -5884238416098732332L;

	private UserLoginDetails userLoginDetails;
	private String jwtToken;

	// Need default constructor
	// for serialization (for JSON Parsing)
	public JwtTokenResponse() {
		super();
	}

	public JwtTokenResponse(UserLoginDetails user, String jwtToken) {
		super();
		this.userLoginDetails = user;
		this.jwtToken = jwtToken;

	}

	public UserLoginDetails getUserLoginDetails() {
		return userLoginDetails;
	}

	public void setUserLoginDetails(UserLoginDetails userLoginDetails) {
		this.userLoginDetails = userLoginDetails;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	/*
	 * Once I get the JwtBuilder instance I can access the methods
	 */
	public static JwtBuilder user(UserLoginDetails userLoginDetails) {
		return new JwtBuilder(userLoginDetails);
	}

	public static class JwtBuilder {

		private final JwtTokenResponse jwtTokenResponse = new JwtTokenResponse();

		public JwtBuilder(UserLoginDetails userLoginDetails) {
			jwtTokenResponse.setUserLoginDetails(userLoginDetails);
		}

		public JwtBuilder token(String token) {
			jwtTokenResponse.setJwtToken(token);
			return this;
		}

		public JwtTokenResponse build() {
			return jwtTokenResponse;
		}
	}
}
