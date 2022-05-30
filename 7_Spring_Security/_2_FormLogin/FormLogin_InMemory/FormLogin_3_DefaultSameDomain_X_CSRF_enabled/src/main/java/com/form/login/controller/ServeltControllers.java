package com.form.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServeltControllers {

	@GetMapping("/landing-page")
	public String landingPage(HttpServletRequest request, HttpServletResponse response) {
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		
		System.out.println(token.getToken());
		
		response.addHeader("X-CSRF-TOKEN", token.getToken());
		return "landing-page.html";
	}

}

