package com.form.login.controller.servlet;

import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

	@GetMapping("/login")
	public String loginPage(CsrfToken csrfToken, HttpServletResponse response) {
		response.setHeader("X-CSRF-TOKEN", csrfToken.getToken());
		return "login.html";
	}

	@GetMapping("/home")
	public String homePage(CsrfToken csrfToken, HttpServletResponse response) {
		response.setHeader("X-CSRF-TOKEN", csrfToken.getToken());
		return "home.html";
	}
}
