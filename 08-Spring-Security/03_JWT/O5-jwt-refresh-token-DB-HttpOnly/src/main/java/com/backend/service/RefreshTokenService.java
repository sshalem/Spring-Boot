package com.backend.service;

import com.backend.entity.RefreshTokenEntity;
import com.backend.entity.UserEntity;

public interface RefreshTokenService {

	String generateRefreshToken(String email, String invokedMethod, String refreshToken);

	RefreshTokenEntity validateRefreshToken(String refreshToken);

	UserEntity getUserByRefreshToken(String refreshToken);

	void deleteRefreshToken(String refreshToken);

	void scheduledRefreshTokenCleanup();
}
