package com.basic.cors.Xcsrf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder(); 
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
			.withUser("odel").password(getPasswordEncoder().encode("123")).roles("STUDENT")
			.and()
			.withUser("karin").password(getPasswordEncoder().encode("123")).roles("MANAGER")
			.and()
			.withUser("shabtay").password(getPasswordEncoder().encode("123")).roles("ADMIN");
			// it was .roles("ADMIN").authorities("READ");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()			
			.antMatchers("/","/index.html","/css/*","/js/*","/images/*").permitAll()	
			.antMatchers("/api/*").hasAnyRole("ADMIN","MANAGER","STUDENT")
			//	It was as below
			//	.antMatchers("/api/app/*").hasAuthority("READ")
			// It it made some authorization issues when sending POST/ PUT request 			 
			.antMatchers("/api/app/*").hasRole("ADMIN")
			.anyRequest()
			.authenticated()
			.and()
			.httpBasic()
			.and()
			.cors();
	}	
}