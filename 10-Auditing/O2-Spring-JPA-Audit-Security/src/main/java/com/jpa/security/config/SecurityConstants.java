package com.jpa.security.config;

public class SecurityConstants {

//	public static final long EXPIRATION_TIME = 3_600_000; // this is in milli second = 1 hour
	public static final long EXPIRATION_TIME = 200_000; // this is 200 seconds
//	public static final long EXPIRATION_TIME = 5_000; // this is 5 seconds to check if TokenExpiredException is thrown
	public static final String BEARER_PREFIX = "Bearer "; // Don't forget to add white space after Bearer prefix 
	public static final String AUTHORIZATION = "Authorization";
}
