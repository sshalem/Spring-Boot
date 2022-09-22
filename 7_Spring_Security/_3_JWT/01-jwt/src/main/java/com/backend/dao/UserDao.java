package com.backend.dao;

import com.backend.model.UserRegisterRequest;
import com.backend.model.UserRegisterResponse;

public interface UserDao {

	UserRegisterResponse createUser(UserRegisterRequest userRegisterRequest);
	
	String getUserName(String email);
}
