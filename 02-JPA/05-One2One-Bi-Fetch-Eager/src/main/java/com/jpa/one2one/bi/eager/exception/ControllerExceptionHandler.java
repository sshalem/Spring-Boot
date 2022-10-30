package com.jpa.one2one.bi.eager.exception;

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
		
		ErrorMessage message = new ErrorMessage(
				new Date(),
				HttpStatus.NOT_FOUND.value(),
				HttpStatus.valueOf(HttpStatus.NOT_FOUND.value()).getReasonPhrase(),
				Exception.class.getName(),
				ex.getMessage(),
				request.getDescription(false));

		return message;
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorMessage globalExceptionHandler(Exception ex, WebRequest request) {
		
		ErrorMessage message = new ErrorMessage(
				new Date(), 
				HttpStatus.INTERNAL_SERVER_ERROR.value(), 
				HttpStatus.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()).getReasonPhrase(),
				Exception.class.getName(),
				ex.getMessage(),
				request.getDescription(false));

		return message;
	}
}