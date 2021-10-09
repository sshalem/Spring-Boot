package com.email.verification.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginFailureHandler.class);

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		String redirectURL = "/login?error=true";

		String errorMessage = exception.getMessage();

		LOGGER.info(errorMessage);

		if (errorMessage.contains("Bad credentials")) {
			redirectURL = redirectURL + "&BadCredentials";
			LOGGER.info(redirectURL);
		}
		if (errorMessage.contains("disable")) {
			redirectURL = redirectURL + "&UserDisabled";
			LOGGER.info(redirectURL);
		}
		if (errorMessage.contains("locked")) {
			redirectURL = redirectURL + "&UserLocked";
			LOGGER.info(redirectURL);
		}
		if (errorMessage.contains("expired")) {
			redirectURL = redirectURL + "&UserAccountExpired";
			LOGGER.info(redirectURL);
		}
		super.setDefaultFailureUrl(redirectURL);
		super.onAuthenticationFailure(request, response, exception);
	}

}
