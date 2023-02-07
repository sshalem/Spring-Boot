package com.eh107.cache.exception;

import java.util.Date;

public class ErrorMessage {

	private Date timestamp;
	private int statusCode;
	private String error;
	private String exception;
	private String message;
	private String uriDescription;

	public ErrorMessage() {
		super();
	}

	public ErrorMessage(Date timestamp, int statusCode, String error, String exception, String message,
			String uriDescription) {
		super();
		this.timestamp = timestamp;
		this.statusCode = statusCode;
		this.error = error;
		this.exception = exception;
		this.message = message;
		this.uriDescription = uriDescription;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUriDescription() {
		return uriDescription;
	}

	public void setUriDescription(String uriDescription) {
		this.uriDescription = uriDescription;
	}

}
