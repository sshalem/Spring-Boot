package com.in28min.Jwt.H2.exception;

public class AuthenticationException extends RuntimeException {

	private static final long serialVersionUID = 3364026113185200978L;

	public AuthenticationException(String message, Throwable cause) {
		super(message, cause);
	}
}