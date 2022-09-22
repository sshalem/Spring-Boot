package com.form.login.security;

import org.springframework.beans.factory.annotation.Autowired;
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
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(userDetailsServiceImpl).passwordEncoder(getPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		/**
		 * In these matchers antMatchers("/css/*", "/js/*", "/images/*").permitAll()
		 * I don't have ("/" , "/index.html"). 
		 * I removed it , so User access to those links w/o login authentication.  
		 */	
		
		http		
			.authorizeRequests()
			.antMatchers("/h2**/**").permitAll()
			.antMatchers("/css/*", "/js/*", "/images/*").permitAll()
			.antMatchers("/api/app/**").hasRole("SUPERADMIN")
			.antMatchers("/api/**").hasAnyRole("ADMIN","SUPERADMIN")			
			.anyRequest()
			.authenticated()
		.and()
			.formLogin()
		.and()
			.csrf().disable();
			// I disable csrf() at this moment , To be able to make POST PUT DELETE request
		
		// Enable H2 console during development, To enable H2 console w/o the need go via login page
		http.headers().frameOptions().disable();
		// Another way to config H2
		// http.headers().frameOptions().sameOrigin().cacheControl();
		
	}

}
