package com.auth02.config;

public class SecurityConstants {

	public static final long EXPIRATION_TIME = 120_000; // this is in milli second
	public static final String BEARER_PREFIX = "Bearer "; // Don't forget to add white space after Bearer prefix 
	public static final String AUTHORIZATION = "Authorization";
	public static final String SIGN_UP_URL = "/signup";

}
