package com.cachem.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppExceptionsHandler {

	@ExceptionHandler(value = { NameAlreadyExistException.class })
	public ResponseEntity<Object> handleUserServiceException(NameAlreadyExistException ex) {

		ExceptionErrorMessage errorMessage = new ExceptionErrorMessage();
		errorMessage.setTimestamp(new Date());
		errorMessage.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorMessage.setError(HttpStatus.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()).getReasonPhrase());
		errorMessage.setException(NameAlreadyExistException.class.getName());
		errorMessage.setMessage(ex.getMessage());

		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> handleOtherExceptions(Exception ex) {

		ExceptionErrorMessage errorMessage = new ExceptionErrorMessage();

		errorMessage.setTimestamp(new Date());
		errorMessage.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorMessage.setError(HttpStatus.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()).getReasonPhrase());
		errorMessage.setException(Exception.class.getName());
		errorMessage.setMessage(ex.getMessage());

		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
