package com.form.login.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.form.login.entity.UserEntity;
import com.form.login.exception.ObjectNotFoundException;
import com.form.login.service.UserDaoImpl;

@Controller
public class ServletController {

	@Autowired
	private UserDaoImpl userDaoImpl;

	@GetMapping("/login")
	public String loginPage() {
		return "login.html";
	}
	
	@GetMapping("/")
	public String index() {
		return "index.html";
	}

	@GetMapping("/admin")
	public String adminPage(HttpServletRequest req, HttpServletResponse res) throws ObjectNotFoundException {
		String username = req.getUserPrincipal().getName();
		UserEntity user = userDaoImpl.getByUsername(username);

		res.addHeader("userId", Long.toString(user.getUserId()));
		return "admin.html";
	}

	@GetMapping("/book")
	public String bookPage(HttpServletRequest req, HttpServletResponse res) throws ObjectNotFoundException {
		String username = req.getUserPrincipal().getName();
		UserEntity user = userDaoImpl.getByUsername(username);

		res.addHeader("userId", Long.toString(user.getUserId()));
		return "book.html";
	}
	
}
