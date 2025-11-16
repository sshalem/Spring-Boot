package com.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.backend.jwt.JwtAuthenticationEntryPoint;
import com.backend.jwt.JwtAuthenticationFilter;
import com.backend.jwt.JwtUserDetailsService;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(jwtUserDetailsService);		
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}
	
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthenticationEntryPoint))
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.csrf(csrf -> csrf.disable()) // Disable CSRF for stateless APIs
			.cors(Customizer.withDefaults()) // turns on Spring Security’s CORS support, but does not define any rules by itself.
											// Thus, I can config my own @CrossOrigin in Controller level
			.authorizeHttpRequests(authorized -> authorized
					.requestMatchers("/*", "/assets/*", "/css/*", "/js/*").permitAll()
					.requestMatchers(PathRequest.toH2Console()).permitAll()					
					.requestMatchers("/auth/**").permitAll()
					.requestMatchers("/api/users/**").hasAnyRole("SUPER-ADMIN", "ADMIN")
					.requestMatchers("/api/roles/**").hasAnyRole("SUPER-ADMIN", "ADMIN")					
					.anyRequest().authenticated() // Protect any of the remaining end points
			);
		
		/**
		 * Per ChatGPT:
		 * Spring Boot finds UserDetailsService Bean + PasswordEncoder Bean,
		 * Thus, No need to config authenticationProvider
		 * since it will wire a provider for you automatically.
		 */
		http.authenticationProvider(authenticationProvider());
				
		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // Add JWT filter
		
		http.headers(headers -> headers
				.frameOptions(options -> options.sameOrigin()) // This so embedded frames in h2-console are working
				.cacheControl(Customizer.withDefaults()));
		
		return http.build();
	}
	
}
