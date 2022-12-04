package com.cachem.service;

import org.springframework.stereotype.Component;

import com.cachem.entity.UserEntity;
import com.cachem.exception.ResourceNotFoundException;

@Component
public class CustomerService {

	private final String NAME = "karin";

	public String getName(String name) {
		if (!name.equals(this.NAME))
			throw new ResourceNotFoundException("name " + name + " not exist");
		return name;
	}

	public UserEntity createUser(UserEntity userEntity) {
		if (!userEntity.getFirstName().equals("karin")) {
			throw new ResourceNotFoundException("Name " + userEntity.getFirstName() + " not found");
		}
		return userEntity;
	}
}
