package com.backend.model;

import java.io.Serializable;

public class JwtTokenResponse implements Serializable {

	private static final long serialVersionUID = -5884238416098732332L;

	private String name;
	private String jwtToken;

	// Need default constructor
	// for serialization (for JSON Parsing)
	public JwtTokenResponse() {
		super();
	}

	public JwtTokenResponse(String name, String jwtToken) {
		super();
		this.name = name;
		this.jwtToken = jwtToken;

	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "JwtTokenResponse [name=" + name + ", jwtToken=" + jwtToken + "]";
	}

	/*
	 *  Once I get the JwtBuilder instance I can access the methods
	 */
	public static JwtBuilder name(String name) {
		return new JwtBuilder(name);
	}

	public static class JwtBuilder {

		private final JwtTokenResponse jwtTokenResponse = new JwtTokenResponse();

		public JwtBuilder(String name) {
			jwtTokenResponse.setName(name);
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
