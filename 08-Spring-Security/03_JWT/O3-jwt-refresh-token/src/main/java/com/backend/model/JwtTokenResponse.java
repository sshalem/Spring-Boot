package com.backend.model;

import java.io.Serializable;

public class JwtTokenResponse implements Serializable {

	private static final long serialVersionUID = -5884238416098732332L;

	private String name;
	private String accessToken;
	private String refreshToken;

	public JwtTokenResponse() {
		super();
	}

	public JwtTokenResponse(String name, String accessToken, String refreshToken) {
		super();
		this.name = name;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	/*
	 * Once I get the JwtBuilder instance I can access the methods
	 */
	public static JwtBuilder name(String name) {
		return new JwtBuilder(name);
	}

	public static class JwtBuilder {

		private final JwtTokenResponse jwtTokenResponse = new JwtTokenResponse();

		public JwtBuilder(String name) {
			jwtTokenResponse.setName(name);
		}

		public JwtBuilder accessToken(String token) {
			jwtTokenResponse.setAccessToken(token);
			return this;
		}

		public JwtBuilder refreshToken(String token) {
			jwtTokenResponse.setRefreshToken(token);
			return this;
		}

		public JwtTokenResponse build() {
			return jwtTokenResponse;
		}
	}
}
