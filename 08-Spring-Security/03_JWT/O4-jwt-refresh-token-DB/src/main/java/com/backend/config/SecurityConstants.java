package com.backend.config;

// 3_600_000 = 60min
// 1_800_000 = 30min
// 60_000 = 1min
// 5_000 = 5sec
// 1_000 = 1sec

public class SecurityConstants {

//	public static final long JWT_EXPIRATION_TIME_ms = 5_000; 

//	public static final long REFRESH_TOKEN_EXPIRATION_TIME_ms = 3_600_000;	// 60 min 
	public static final long REFRESH_TOKEN_EXPIRATION_TIME_ms = 1000 * 60 * 60; // 60min

	public static final String INVOKED_LOGIN_URL = "loginUrlInvoked";
	public static final String INVOKED_REFRESH_URL = "refreshUrlInvoked";

//  Don't forget to add white space after Bearer prefix
//	public static final String BEARER_PREFIX = "Bearer "; 
//	public static final String REFRESH_TOKEN_PREFIX = "Refresh_token "; 
//	public static final String AUTHORIZATION = "Authorization";
//	public static final String REGISTER_URL = "/register";

}
