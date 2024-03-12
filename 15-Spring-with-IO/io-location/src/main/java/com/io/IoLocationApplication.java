package com.io;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.io.service.IoOperations;

@SpringBootApplication
public class IoLocationApplication implements CommandLineRunner {

	@Autowired
	private IoOperations ioOperations;

	public static void main(String[] args) {
		SpringApplication.run(IoLocationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ioOperations.filePath_1();
		ioOperations.filePath_2();
		ioOperations.filePath_3();
		ioOperations.filePath_4();
		ioOperations.filePath_5();
	}

}
