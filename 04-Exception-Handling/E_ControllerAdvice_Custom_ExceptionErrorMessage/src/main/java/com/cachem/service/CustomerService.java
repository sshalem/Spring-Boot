package com.cachem.service;

import org.springframework.stereotype.Component;

import com.cachem.entity.UserEntity;
import com.cachem.exception.ErrorMessages;
import com.cachem.exception.ResourceNotFoundException;

@Component
public class CustomerService {

	private final String NAME = "unknown";

	public String getName(String name) {
		if (!name.equals(this.NAME))
			throw new ResourceNotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		return name;
	}

	public UserEntity createUser(UserEntity userEntity) {
		if (userEntity.getFirstName().equals("karin")) {
			throw new ResourceNotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		}
		return userEntity;
	}
}
