package com.backend.config;

// [ms] * [sec] * [min]	
// 1_000 * 60 * 60 = 3_600_000 = 60min 
// 1_000 * 60 * 30 = 1_800_000 = 30min 
// 1_000 * 60 * 1 = 1min
// 1_000 * 5 = 5_000 = 5sec
// 1_000 = 1sec

public class SecurityConstants {

	/******************
	 * JWT AccessToken
	 *****************/
	public static final long JWT_EXPIRATION_TIME_ms = 1_000 * 10;
//	public static final long JWT_EXPIRATION_TIME_ms = 1_000 * 10;
//	public static final long JWT_EXPIRATION_TIME_ms = 1_000 * 200;
//  public static final long JWT_EXPIRATION_TIME_ms = 1_000 * 60 * 15;

	/********************
	 * RefreshToken
	 ********************/
	
//	public static final long REFRESH_TOKEN_EXPIRATION_TIME_ms = 3_600_000;
	public static final long REFRESH_TOKEN_EXPIRATION_TIME_ms = 1_000 * 20;

	public static final String INVOKED_LOGIN_URL = "loginUrlInvoked";
	public static final String INVOKED_REFRESH_URL = "refreshUrlInvoked";

	/*******************
	 *    General
	 *******************/
	
//  Don't forget to add white space after Bearer prefix
//	public static final String BEARER_PREFIX = "Bearer "; 
	public static final String REFRESH_TOKEN_PREFIX = "Refresh_token "; 
	public static final String AUTHORIZATION = "Authorization";

}
