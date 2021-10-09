package com.es.mail;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendSimpleMessage(String recipient, String subject, String body)
			throws MessagingException, UnsupportedEncodingException {
		
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom("shabtay.karin.shalem@gmail.com", "Shabtay & Karin");
		helper.setTo(recipient);
		helper.setSubject(subject);
		helper.setText(body);

		javaMailSender.send(message);

		LOGGER.info("Email has sent to " + "\"" + recipient + "\"");

	}

	public void sendMessageWithAttachment(String recipient, String subject, String message, String pathToAttachment) {

	}
}