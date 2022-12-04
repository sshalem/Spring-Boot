package com.cachem.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class AppExceptionsHandler {

	@ExceptionHandler(value = { ResourceNotFoundException.class })
	public ResponseEntity<Object> handleUserServiceException(ResourceNotFoundException ex, WebRequest request) {

		ExceptionErrorMessage errorMessage = new ExceptionErrorMessage();

		errorMessage.setTimestamp(new Date());
		errorMessage.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorMessage.setError(HttpStatus.valueOf(HttpStatus.NOT_FOUND.value()).getReasonPhrase());
		errorMessage.setException(ResourceNotFoundException.class.getName());
		errorMessage.setMessage(ex.getMessage());
		errorMessage.setUriDescription(request.getDescription(false));

		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> handleOtherExceptions(Exception ex, WebRequest request) {

		ExceptionErrorMessage errorMessage = new ExceptionErrorMessage();

		errorMessage.setTimestamp(new Date());
		errorMessage.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorMessage.setError(HttpStatus.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()).getReasonPhrase());
		errorMessage.setException(ex.getClass().getCanonicalName());
		errorMessage.setMessage(ex.getMessage());
		errorMessage.setUriDescription(request.getDescription(false));

		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * @ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ErrorMessage resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {

		ErrorMessage message = new ErrorMessage();

		message.setTimestamp(new Date());
		message.setStatusCode(HttpStatus.NOT_FOUND.value());
		message.setError(HttpStatus.valueOf(HttpStatus.NOT_FOUND.value()).getReasonPhrase());
		message.setException(ResourceNotFoundException.class.getName());
		message.setMessage(ex.getMessage());
		message.setUriDescription(request.getDescription(false));

		return message;
	}

	@ExceptionHandler({ Exception.class })
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
	 */
}
