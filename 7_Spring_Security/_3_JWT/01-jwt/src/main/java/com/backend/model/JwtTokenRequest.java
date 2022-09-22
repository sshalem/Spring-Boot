package com.backend.model;

import java.io.Serializable;

public class JwtTokenRequest implements Serializable {

	private static final long serialVersionUID = 7772119093834586226L;

	private String email;
	private String password;

	// Need default constructor
	// for serialization (for JSON Parsing)
	public JwtTokenRequest() {
		super();
	}

	public JwtTokenRequest(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
