package com.es.mail;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

	@Autowired
	private EmailService emailService;

	@PostMapping("/sendEmail")
	public ResponseEntity<String> sendEmail(@RequestBody Mail mail)
			throws UnsupportedEncodingException, MessagingException {

		emailService.sendSimpleMessage(mail.getRecipient(), mail.getSubject(), mail.getMessage());
		return new ResponseEntity<>("Mail send successfully", HttpStatus.OK);
	}
}
