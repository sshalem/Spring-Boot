package com.jwt.URA.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.jwt.URA.dto.UserDto;

public interface UserService extends UserDetailsService {

	UserDto createUser(UserDto user) throws RuntimeException;

	UserDto getUser(String email);

	UserDto getUserByUserId(String userId);

	UserDto updateUser(String userId, UserDto user);

	void deleteUser(String userId);

	List<UserDto> getUsers(int page, int limit);
}
