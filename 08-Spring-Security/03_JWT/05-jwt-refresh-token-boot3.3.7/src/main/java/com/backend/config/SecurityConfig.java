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
import com.backend.service.JwtUserDetailsService;

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
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(jwtUserDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf(csrf ->csrf.disable())
			.cors(Customizer.withDefaults())
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.exceptionHandling(handling ->handling.authenticationEntryPoint(jwtAuthenticationEntryPoint))
			.authorizeHttpRequests(requests -> 
				requests
					.requestMatchers("/*", "/assets/*", "/css/*", "/js/*").permitAll()
					.requestMatchers(PathRequest.toH2Console()).permitAll()					
					.requestMatchers("/auth/**").permitAll()
					.requestMatchers("/api/users/**").hasAnyRole("SUPER-ADMIN", "ADMIN")
					.requestMatchers("/api/roles/**").hasAnyRole("SUPER-ADMIN", "ADMIN")
					.requestMatchers("/api/book/**").authenticated()
					.anyRequest().authenticated());
			
		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		// fix H2 database console:
		// Refused to display ' in a frame because it set 'X-Frame-Options' to 'deny'
		http.headers(headers -> headers
				.frameOptions(options -> options.sameOrigin()) // This so embedded frames in h2-console are working
				.cacheControl(Customizer.withDefaults()));
		
		return http.build();
				
	}
}
