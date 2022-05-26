package com.email.verification.controller;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import com.email.verification.entity.User;
import com.email.verification.excption.UserEmailAlreadyExistException;
import com.email.verification.service.UserServices;

@Controller
public class AppViewController {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(AppViewController.class);

	@Autowired
	private UserServices service;

	@GetMapping("/")
	public String viewHomePage() {
		return "index.html";
	}

	@GetMapping("/login")
	public String loginPage() {
		return "login.html";
	}

	@GetMapping("/register")
	public String showRegistrationForm() {
		return "signupForm.html";
	}

	@GetMapping("/registerSuccessful")
	public String registerSuccessfull() {
		return "redirect:registerSuccessful.html";
	}

	/**
	 * Here I'm using the RedirectView. 
	 * This way I can redirect to different Url, if Registration fails
	 */
	@RequestMapping(value = "/processRegister", method = RequestMethod.POST)
	public RedirectView processRegister(HttpServletRequest request) {

		User user = new User();

		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("password"));
		user.setFirstName(request.getParameter("firstName"));
		user.setLastName(request.getParameter("lastName"));

		// this line gives the url of 'http://localhost:8080/processRegister'
		String siteURL = request.getRequestURL().toString();
		
		// returns the path - 'processRegister'
		String curretnPathUrl = request.getServletPath(); 
		
		// modifiedSiteURL = 'http://localhost:8080'
		String modifiedSiteURL = siteURL.replace(curretnPathUrl, "");
						
		RedirectView viewUrl = new RedirectView();

		try {
			service.register(user, modifiedSiteURL);
		} catch (UserEmailAlreadyExistException e) {
			viewUrl.setUrl("http://localhost:8080/register?error");
			Properties props = new Properties();
			props.setProperty("Email", "AlreadyExist");
			viewUrl.setAttributes(props);
			return viewUrl;
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("message .....................................", e.getMessage());
		}catch (MessagingException e) {
			LOGGER.error("message .....................................", e.getMessage());
		} 
		viewUrl.setUrl("http://localhost:8080/registerSuccessful");
		return viewUrl;
	}

	@GetMapping("/users")
	public String listUsers(HttpServletRequest request, HttpServletResponse response) {
		/**
		 * users.html , is the landing page after successful sign-in.
		 * I'm adding header of 'loggedUser' in order 
		 * to display the logged in user at users.html page
		 */
		response.addHeader("loggedUser", service.loggedUser(request.getUserPrincipal().getName()));
		return "users.html";
	}

	@GetMapping("/verify")
	public String verifyUser(@Param("code") String code) {
		if (service.verify(code)) {
			return "verify_success.html";
		} else {
			return "verify_fail.html";
		}
	}

}
