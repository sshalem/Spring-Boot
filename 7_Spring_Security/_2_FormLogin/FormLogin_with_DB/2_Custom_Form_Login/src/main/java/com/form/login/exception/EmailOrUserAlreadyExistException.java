package com.form.login.exception;

public class EmailOrUserAlreadyExistException extends Exception {

	private static final long serialVersionUID = 4698002809962640521L;

	public EmailOrUserAlreadyExistException(String message) {
		super(message);
	}
}
