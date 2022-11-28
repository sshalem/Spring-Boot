package com.backend.exceptions;

public class EmailAlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = -505185660787820336L;

	public EmailAlreadyExistException(String msg) {
		super(msg);
	}

}
