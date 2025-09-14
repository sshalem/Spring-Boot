package com.rcac.exception;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -6209521422884301225L;

	public ResourceNotFoundException(String msg) {
		super(msg);
	}
}
