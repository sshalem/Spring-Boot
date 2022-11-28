package com.auth02.dao;

import com.auth02.model.UserSignUpRequest;
import com.auth02.model.UserSignUpResponse;

public interface UserDao {

	UserSignUpResponse createUser(UserSignUpRequest userSignUpRequest);
}
