package com.auth02.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private final Logger LOGGER = Logger.getLogger(this.getClass());

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		LOGGER.warn("Getting -> " + HttpServletResponse.SC_FORBIDDEN
				+ " You would need to provide the Jwt Token to Access This resource");
		response.sendError(HttpServletResponse.SC_FORBIDDEN,
				"---> 403 403 403 You would need to provide the Jwt Token to Access This resource 403 403 403 <---");
	}

}
