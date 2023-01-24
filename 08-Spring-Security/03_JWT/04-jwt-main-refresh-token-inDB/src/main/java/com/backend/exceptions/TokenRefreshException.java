package com.backend.exceptions;

public class TokenRefreshException extends RuntimeException {

	private static final long serialVersionUID = -4745011122427234886L;

	public TokenRefreshException(String msg) {
		super(msg);
	}
}
