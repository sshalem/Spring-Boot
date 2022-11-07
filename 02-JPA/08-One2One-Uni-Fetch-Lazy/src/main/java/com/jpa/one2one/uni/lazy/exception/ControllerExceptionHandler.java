package com.jpa.one2one.uni.lazy.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ErrorMessage resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {

		ErrorMessage message = new ErrorMessage();

		message.setTimestamp(new Date());
		message.setStatusCode(HttpStatus.NOT_FOUND.value());
		message.setError(HttpStatus.valueOf(HttpStatus.NOT_FOUND.value()).getReasonPhrase());
		message.setException(ex.getClass().getCanonicalName());
		message.setMessage(ex.getMessage());
		message.setUriDescription(request.getDescription(false));

		return message;
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorMessage globalExceptionHandler(Exception ex, WebRequest request) {

		ErrorMessage message = new ErrorMessage();

		message.setTimestamp(new Date());
		message.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		message.setError(HttpStatus.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()).getReasonPhrase());
		message.setException(ex.getClass().getCanonicalName());
		message.setMessage(ex.getMessage());
		message.setUriDescription(request.getDescription(false));

		return message;
	}
}