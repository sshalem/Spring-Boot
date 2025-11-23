package com.backend.model;

import java.io.Serializable;

public class LogoutResponse implements Serializable {

	private static final long serialVersionUID = -7701900798199957161L;

	private String message;

	public LogoutResponse() {
		super();
	}

	public LogoutResponse(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
