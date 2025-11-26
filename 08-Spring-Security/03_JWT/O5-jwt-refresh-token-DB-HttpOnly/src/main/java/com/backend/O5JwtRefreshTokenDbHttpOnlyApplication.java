package com.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class O5JwtRefreshTokenDbHttpOnlyApplication {

	public static void main(String[] args) {
		SpringApplication.run(O5JwtRefreshTokenDbHttpOnlyApplication.class, args);
	}

}
