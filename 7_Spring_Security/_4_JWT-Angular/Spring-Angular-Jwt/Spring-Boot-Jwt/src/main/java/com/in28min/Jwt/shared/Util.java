package com.in28min.Jwt.shared;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Util implements CommandLineRunner {

	@Autowired
	private PasswordEncoder pse;

	@Override
	public void run(String... args) throws Exception {
//		String encode = pse.encode("123");
//		System.out.println(encode);
	}

}
