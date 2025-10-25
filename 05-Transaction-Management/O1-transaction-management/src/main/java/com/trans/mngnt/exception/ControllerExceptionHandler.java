package com.trans.mngnt.exception;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ControllerExceptionHandler {

	private String getCurrentTimestamp() {
		ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Jerusalem")); // or systemDefault()
		return now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss z"));
	}

	@ExceptionHandler(value = { ResourceNotFoundException.class })
	public ResponseEntity<Object> handleUserServiceException(ResourceNotFoundException ex, WebRequest request) {
		Map<String, Object> response = new HashMap<>();
		response.put("timestamp", getCurrentTimestamp());
		response.put("statusCode", HttpStatus.NOT_FOUND.value());
		response.put("error", HttpStatus.valueOf(HttpStatus.NOT_FOUND.value()).getReasonPhrase());
		response.put("exception", ResourceNotFoundException.class.getName());
		response.put("message", ex.getMessage());
		response.put("uriDescription", request.getDescription(false));
		return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> handleOtherExceptions(Exception ex, WebRequest request) {
		Map<String, Object> response = new HashMap<>();
		response.put("timestamp", getCurrentTimestamp());
		response.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.put("error", HttpStatus.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()).getReasonPhrase());
		response.put("exception", ex.getClass().getCanonicalName());
		response.put("message", ex.getMessage());
		response.put("uriDescription", request.getDescription(false));
		return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}