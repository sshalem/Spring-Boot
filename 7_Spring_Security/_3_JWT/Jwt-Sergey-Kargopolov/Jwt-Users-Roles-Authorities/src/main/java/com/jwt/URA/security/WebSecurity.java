package com.jwt.URA.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.jwt.URA.repository.UserRepository;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	private final UserDetailsService userDetailsService;
	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;

	public WebSecurity(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder,UserRepository userRepository) {
		super();
		this.userDetailsService = userDetailsService;
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL).permitAll()
			.antMatchers(SecurityConstants.H2_CONSOLE).permitAll()
			.antMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
			.anyRequest().authenticated()
			.and()
			.addFilter(new AuthenticationFilter(authenticationManager()))
//			we can use the line below which will use the method getAuthenticationFilter()
//			to have a customized url
//			.addFilter(getAuthenticationFilter())
			.addFilter(new AuthorizationFilter(authenticationManager(),userRepository))
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);	

		/**
		 * In order to work with H2-Inmemory ,We need to disable the frame options 
		 * To make it possible for H2-console to open in browser
		 */
		 http
		 	.headers()
		 	.frameOptions()
		 	.disable();
		 
		 /**
		  * Another way to of configuring :
		  * http
		  * 	.headers()
		  * 	.frameOptions()
		  * 	.sameOrigin() // H2 Console Needs this setting
		  *     .cacheControl(); // disable caching
		  */

	}
	
	/**
	 * this method we can use if we want to have a customized
	 * url for login , for example: instead of http://localhost:8080/login
	 * we can modify the url to http://localhost:8080/users/login
	 * @throws Exception 
	 */
	
	public AuthenticationFilter getAuthenticationFilter() throws Exception {
		AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
		filter.setFilterProcessesUrl("/users/login");
		return filter;
	}
}
