package com.hem.service;

import org.springframework.stereotype.Component;

import com.hem.exception.NameAlreadyExistException;

@Component
public class CustomerService {

	private final String NAME = "karin";

	public String testMethod(String name) {
		if (name.equals(this.NAME))
			throw new NameAlreadyExistException("Name " + this.NAME + " already exist");
		return name;
	}
}
