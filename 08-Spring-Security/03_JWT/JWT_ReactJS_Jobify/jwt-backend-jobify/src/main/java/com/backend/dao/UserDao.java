package com.backend.dao;

import com.backend.model.UserRegisterRequest;
import com.backend.model.UserRegisterResponse;
import com.backend.model.UserLoginDetails;

public interface UserDao {

	UserRegisterResponse createUser(UserRegisterRequest userRegisterRequest);
	
	String getUserName(String email);

	UserLoginDetails getUserLoginDetailsByEmail(String email); 
		
	UserLoginDetails updateUserDetails(UserLoginDetails userLoginDetails);
}
