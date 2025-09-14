package com.cachem.service;

import org.springframework.stereotype.Component;

import com.cachem.entity.UserEntity;
import com.cachem.exception.ErrorMessagesEnum;
import com.cachem.exception.ResourceNotFoundException;

@Component
public class CustomerService {

	public final String NAME = "unknown";

	public String getName(String name) {
		if (!name.equals(this.NAME))
			throw new ResourceNotFoundException(ErrorMessagesEnum.NO_RECORD_FOUND.getErrorMessage());
		return name;
	}

	public UserEntity createUser(UserEntity userEntity) {
		if (userEntity.getFirstName().equals("karin")) {
			throw new NullPointerException(ErrorMessagesEnum.INTERNAL_SERVER_ERROR.getErrorMessage());
		}
		return userEntity;
	}
}
