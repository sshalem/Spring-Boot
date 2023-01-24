package com.backend.dao;

import com.backend.entity.RefreshTokenEntity;
import com.backend.entity.UserEntity;

public interface RefreshTokenDao {

	String generateRefreshToken(String email, String invokedMethod, String refreshToken);

	RefreshTokenEntity validateRefreshToken(String refreshToken);

	UserEntity getUserByRefreshToken(String refreshToken);
	
	void deleteRefreshToken(String refreshToken);
}
