package com.auth02.model;

import java.io.Serializable;

public class JwtTokenResponse implements Serializable {

	private static final long serialVersionUID = -5884238416098732332L;

	private String jwtToken;

	// Need default constructor
	// for serialization (for JSON Parsing)
	public JwtTokenResponse() {
		super();
	}

	public JwtTokenResponse(String token) {
		this.jwtToken = token;
	}

	public String getToken() {
		return this.jwtToken;
	}
}
