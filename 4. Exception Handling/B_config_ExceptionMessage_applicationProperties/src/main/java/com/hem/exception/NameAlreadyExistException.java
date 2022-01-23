package com.hem.exception;

public class NameAlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = -6209521422884301225L;

	public NameAlreadyExistException(String msg) {
		super(msg);
	}
}
