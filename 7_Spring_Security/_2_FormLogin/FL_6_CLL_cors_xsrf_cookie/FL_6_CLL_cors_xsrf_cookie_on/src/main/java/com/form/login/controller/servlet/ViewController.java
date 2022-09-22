package com.form.login.controller.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

	@GetMapping("/login")
	public String loginPage() {
		return "redirect:http://localhost:8081/login.html";
	}

	@GetMapping("/home")
	public String homePage() {
		return "redirect:http://localhost:8081/home.html";
	}

//	@GetMapping("/logout")
//	public String indexPage() {
//		return "redirect:http://localhost:8081/";
//	}
}
