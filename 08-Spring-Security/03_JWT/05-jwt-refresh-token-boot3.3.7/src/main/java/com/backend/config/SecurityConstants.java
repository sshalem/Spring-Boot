package com.backend.config;

public class SecurityConstants {

	// 3_600_000 = 60min
	// 1_800_000 = 30min
	// 60_000 = 1min
	// 5_000 = 5sec
	// 1_000 = 1sec
	public static final long JWT_EXPIRATION_TIME_ms = 60_000; 
	
	public static final long REFRESH_TOKEN_EXPIRATION_TIME_ms = 600_000;
	
	public static final String BEARER_PREFIX = "Bearer "; // Don't forget to add white space after Bearer prefix
	public static final String REFRESH_TOKEN_PREFIX = "Refresh_token "; // Don't forget to add white space after 'Refresh_token' prefix
	public static final String AUTHORIZATION = "Authorization";
	public static final String INVOKED_LOGIN_URL = "loginUrlInvoked";
	public static final String INVOKED_REFRESH_URL = "refreshUrlInvoked";
	public static final String REGISTER_URL = "/register";
	public static final String LOGOUT_URL = "/logout";	
}

