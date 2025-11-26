package com.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class O4JwtRefreshTokenDBApplication {

	public static void main(String[] args) {
		SpringApplication.run(O4JwtRefreshTokenDBApplication.class, args);
	}

}
