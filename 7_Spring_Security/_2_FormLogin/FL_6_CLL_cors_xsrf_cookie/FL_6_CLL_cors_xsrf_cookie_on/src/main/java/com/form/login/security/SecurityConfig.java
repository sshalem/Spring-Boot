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
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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
		 * In these matchers antMatchers("/css/*", "/js/*", "/images/*" ,"/index", "/").permitAll()
		 * I add  ("/" , "/index.html"). 
		 * Why?
		 * Index - is the welcome page I want all to get access to it.
		 * Login - all have access to it
		 * Home - after successful login , user will be redirected to Home url.  
		 */	
		
		http		
			.authorizeRequests()
			.antMatchers("/h2**/**").permitAll()
			.antMatchers("/css/*", "/js/*", "/images/*", "/index", "/").permitAll()
			.antMatchers("/csrf").permitAll()
			.antMatchers("/api/app/**").hasRole("SUPERADMIN")
			.antMatchers("/api/**").hasAnyRole("ADMIN","SUPERADMIN")		
			.anyRequest()
			.authenticated()
		.and()
			.formLogin()
			.loginPage("/login")
			.failureUrl("http://localhost:8081/login.html?error=true")
			.defaultSuccessUrl("/home", true)
			.permitAll()
		.and()
			.logout()
			.invalidateHttpSession(true)
			.clearAuthentication(true)
			.deleteCookies("JSESSIONID")
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST"))
			.logoutSuccessUrl("http://localhost:8081/")
		.and()
			.cors()
		.and()
			.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
		
		// Enable H2 console during development, To enable H2 console w/o the need go via login page
		http.headers().frameOptions().disable();
		// Another way to config H2
		// http.headers().frameOptions().sameOrigin().cacheControl();
		
	}

}
