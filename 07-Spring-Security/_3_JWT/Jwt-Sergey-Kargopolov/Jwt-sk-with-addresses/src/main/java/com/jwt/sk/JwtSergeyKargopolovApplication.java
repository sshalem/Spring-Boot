package com.jwt.sk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class JwtSergeyKargopolovApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtSergeyKargopolovApplication.class, args);
	}

	@Bean
	public PasswordEncoder getEncryptedPassword() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SpringApplicationContext getSpringApplicationContext() {
		return new SpringApplicationContext();
	}

}
