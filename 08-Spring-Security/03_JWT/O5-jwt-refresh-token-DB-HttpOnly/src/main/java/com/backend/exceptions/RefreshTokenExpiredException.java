package com.backend.exceptions;

public class RefreshTokenExpiredException extends RuntimeException {

	private static final long serialVersionUID = 6936447678248440380L;

	public RefreshTokenExpiredException(String msg) {
		super(msg);
	}
}
