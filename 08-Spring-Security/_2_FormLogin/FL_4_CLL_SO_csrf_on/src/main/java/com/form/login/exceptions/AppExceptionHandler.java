package com.form.login.exceptions;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler(value = { ObjectNotFoundException.class })
	public ResponseEntity<Object> handleUserNotFoundException(ObjectNotFoundException ex, HttpServletRequest request) {

		ErrorMessage em = new ErrorMessage();

		em.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()); // 'error: "Internal Server Error"'
		em.setException(ex.getClass().getName()); // 'exception: "com.form.login.exception.UserNotFoundException"'
		em.setMessage(ex.getMessage()); // 'message: "User Id '1' Not Found exception...."'
		em.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value()); // example : 'status: 500'
		em.setTimestamp(new Date()); // 'timestamp: "2021-11-10T12:54:09.487+00:00"'
		em.setPath(request.getContextPath()); // 'path: "/users/get/userId/1"'

		return new ResponseEntity<Object>(em, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
