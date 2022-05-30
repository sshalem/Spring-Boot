package com.jwt.URA.security;

import com.jwt.URA.SpringApplicationContext;

public class SecurityConstants {

	public static final long EXPIRATION_TIME = 864_000_000; // 10 days
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "/users/signup";
	public static final String H2_CONSOLE = "/h2-console/**";
//	public static final String TOKEN_SECRET = "wqduwaoch832i823nd023rndw8cwnsjbrls9y96";

	public static String getTokenSecret() {
		AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("appProperties");
		return appProperties.getTokenSecret();
	}
}
