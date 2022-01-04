package com.emac.exception;

public class UserServiceException extends RuntimeException {

	private static final long serialVersionUID = -3207721345596414038L;

	public UserServiceException(String message) {
		super(message);
	}
}
