package com.form.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServeltControllers {

	@GetMapping("/landing-page")
	public String landingPage() {
		return "landing-page.html";
	}

	@GetMapping("/403")
	public String accessDenidePage() {
		return "access-denied.html";
	}

}
