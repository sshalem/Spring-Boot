package com.backend.jwt;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

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

		LOGGER.error(authException.getMessage());
		LOGGER.error(authException.getClass().getName());
		
		if (exception.getClass().getSimpleName().equals("ExpiredJwtException")) {
			LOGGER.error("Getting -> " + HttpServletResponse.SC_UNAUTHORIZED + " - " + exception.getMessage());			
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, exception.getMessage());
		}
		else {
			LOGGER.error("Getting -> " + HttpServletResponse.SC_BAD_REQUEST + " - Access Denied, Probably Bad credentials");
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "400 - Access Denied, Probably Bad credentials");
		}
	}
}
