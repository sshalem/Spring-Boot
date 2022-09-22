package com.form.login.security;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class CorsConfig implements WebMvcConfigurer{

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		
	    registry
	      .addMapping("/**")
	      .allowCredentials(true)
	      .allowedOrigins("http://localhost:8081")
	      .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
	      .allowedHeaders("authorization", "Cache-Control", "Content-Type", "X-XSRF-TOKEN")
	      .maxAge(3600)
	      .exposedHeaders("X-XSRF-TOKEN");
	}
}
