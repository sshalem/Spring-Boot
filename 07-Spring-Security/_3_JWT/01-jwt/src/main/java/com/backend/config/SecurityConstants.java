package com.backend.config;

public class SecurityConstants {

	public static final long EXPIRATION_TIME = 3_600_000; // this is in milli second
	public static final String BEARER_PREFIX = "Bearer "; // Don't forget to add white space after Bearer prefix 
	public static final String AUTHORIZATION = "Authorization";
	public static final String REGISTER_URL = "/register";

}
