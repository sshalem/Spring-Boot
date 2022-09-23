package com.form.login.exception;

import java.util.Date;

public class ErrorMessage {

	private String error;
	private String exception;
	private String message;
	private int status;
	private Date timestamp;
	private String path;

	public ErrorMessage() {
		super();
	}

	public ErrorMessage(String error, String exception, String message, int status, Date timestamp, String path) {
		super();
		this.error = error;
		this.exception = exception;
		this.message = message;
		this.status = status;
		this.timestamp = timestamp;
		this.path = path;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
