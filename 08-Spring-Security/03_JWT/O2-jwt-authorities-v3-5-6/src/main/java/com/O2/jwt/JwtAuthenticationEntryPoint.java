package com.O2.jwt;

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

//		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//
//		Exception exception = (Exception) request.getAttribute("exception");
//
//		String message;
//
//		/**
//		 * This will check the Exception that is set from the JwtAuthenticationFilter
//		 * Else 
//		 * it will check if AuthenticationException authException is thrown by DaoAuthenticationProvider
//		 */
//		if (exception != null) {
//			
//			LOGGER.error(exception.getMessage());
//			
//			if (exception.getCause() != null) {
//				message = exception.getCause().toString() + " " + exception.getMessage();
//			} else {
//				message = exception.getMessage();
//			}
//
//			byte[] body = new ObjectMapper().writeValueAsBytes(Collections.singletonMap("error", message));
//
//			response.getOutputStream().write(body);
//			
//
//		} else {
//
//			LOGGER.error(authException.getCause());
//			
//			if (authException.getCause() != null) {
//				message = authException.getCause().toString() + " " + authException.getMessage();
//			} else {
//				message = authException.getMessage();
//			}
//
//			byte[] body = new ObjectMapper().writeValueAsBytes(Collections.singletonMap("error", message));
//
//			response.getOutputStream().write(body);
//		}
	}

}
