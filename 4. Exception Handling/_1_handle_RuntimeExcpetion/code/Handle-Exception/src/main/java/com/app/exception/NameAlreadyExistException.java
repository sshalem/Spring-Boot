package com.app.exception;

public class NameAlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = -4746732915743920318L;

	public NameAlreadyExistException(String msg) {
		super(msg);
	}
}
