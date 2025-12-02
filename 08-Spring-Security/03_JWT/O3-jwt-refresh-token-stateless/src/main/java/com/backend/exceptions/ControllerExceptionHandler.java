package com.backend.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(EmailAlreadyExistException.class)	
	public ErrorMessage emailAlreadyExistException(EmailAlreadyExistException ex, WebRequest request) {

		ErrorMessage message = new ErrorMessage();

		message.setTimestamp(new Date());
		message.setStatusCode(HttpStatus.CONFLICT.value());
		message.setError(HttpStatus.valueOf(HttpStatus.CONFLICT.value()).getReasonPhrase());
		message.setException(EmailAlreadyExistException.class.getName());
		message.setMessage(ex.getMessage());
		message.setUriDescription(request.getDescription(false));

		return message;
	}

	@ExceptionHandler({ Exception.class })
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