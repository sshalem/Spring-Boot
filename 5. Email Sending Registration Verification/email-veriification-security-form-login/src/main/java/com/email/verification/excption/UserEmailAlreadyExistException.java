package com.email.verification.excption;

public class UserEmailAlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = -6775054231699428234L;

	public UserEmailAlreadyExistException(String message) {
		super(message);
	}
}
