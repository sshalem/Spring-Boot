package com.form.login.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
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
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/css/*", "/js/*", "/images/*").permitAll()
			// Secure Rest Controllers End Points
			.antMatchers("/api/*").hasAnyRole("ADMIN","MANAGER","STUDENT")
			.antMatchers("/api/app/*").hasRole("ADMIN")
			.anyRequest()
			.authenticated()
		.and()			
			.formLogin();
	}	
}
