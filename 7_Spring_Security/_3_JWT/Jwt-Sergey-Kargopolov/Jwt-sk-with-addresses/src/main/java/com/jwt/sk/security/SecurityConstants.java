package com.jwt.sk.security;

import com.jwt.sk.SpringApplicationContext;

public class SecurityConstants {

	public static final long EXPIRATION_TIME = 864_000_000; // 10 days
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "/users";
//	public static final String TOKEN_SECRET = "wqduwaoch832i823nd023rndw8cwnsjbrls9y96";

	public static String getTokenSecret() {
		AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("appProperties");
		return appProperties.getTokenSecret();
	}
}
