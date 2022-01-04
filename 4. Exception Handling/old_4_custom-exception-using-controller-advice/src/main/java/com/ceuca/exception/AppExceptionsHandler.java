package com.ceuca.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppExceptionsHandler {

	@ExceptionHandler(value = { UserServiceException.class })
	public ResponseEntity<Object> handleUserServiceException(UserServiceException ex) {

		ExceptionErrorMessage errorMessage = new ExceptionErrorMessage();
		errorMessage.setTimestamp(new Date());
		errorMessage.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorMessage.setError(HttpStatus.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()).getReasonPhrase());
		errorMessage.setException(UserServiceException.class.getName());
		errorMessage.setMessage(ex.getMessage());

		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> handleOtherExceptions(Exception ex) {

		ExceptionErrorMessage errorMessage = new ExceptionErrorMessage();

		errorMessage.setTimestamp(new Date());
		errorMessage.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorMessage.setError(HttpStatus.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()).getReasonPhrase());
		errorMessage.setException(UserServiceException.class.getName());
		errorMessage.setMessage(ex.getMessage());

		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
