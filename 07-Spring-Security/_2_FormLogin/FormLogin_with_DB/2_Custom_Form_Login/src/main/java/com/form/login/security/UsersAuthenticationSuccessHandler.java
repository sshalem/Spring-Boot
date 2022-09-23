package com.form.login.security;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class UsersAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		authorities.forEach(auth -> {
			if (auth.getAuthority().equals("ROLE_SUPER_ADMIN") || auth.getAuthority().equals("ROLE_ADMIN")) {
				try {
					response.sendRedirect("/admin");
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (auth.getAuthority().equals("ROLE_USER")) {
				try {
					response.sendRedirect("/book");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

	}

}
