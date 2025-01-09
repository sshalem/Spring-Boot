package com.backend.jwt;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {

		Exception exception = (Exception) request.getAttribute("exception");

		if (exception.getClass().getSimpleName().equals("ExpiredJwtException")) {
			/**
			 * If authentication fails ,  I send 401 un-authorized error
			 * This way I send with the response the exception.getMessage() 
			 */
//			LOGGER.error("Getting -> " + HttpServletResponse.SC_UNAUTHORIZED + " - " + exception.getMessage());
//			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, exception.getMessage().substring(0, 35) + ", " + authException.getMessage());
			
			/**
			 * This is another way ,I saw developers returning a response , 
			 * even though the one raw above does the same 
			 */
			LOGGER.error("Getting -> " + HttpServletResponse.SC_UNAUTHORIZED + " - " + exception.getMessage());
			
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

		    final Map<String, Object> body = new HashMap<>();
			body.put("timestamp", LocalDateTime.now().toString());
		    body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
		    body.put("error", "Unauthorized");
		    body.put("message", exception.getMessage() + " " + authException.getMessage());
		    body.put("path", request.getServletPath());

		    final ObjectMapper mapper = new ObjectMapper();
		    mapper.writeValue(response.getOutputStream(), body);
		}
		else {
			LOGGER.error("Getting -> " + HttpServletResponse.SC_BAD_REQUEST + " - Access Denied, Probably Bad credentials");
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "400 - Access Denied, Probably Bad credentials");
		}
	}
}
