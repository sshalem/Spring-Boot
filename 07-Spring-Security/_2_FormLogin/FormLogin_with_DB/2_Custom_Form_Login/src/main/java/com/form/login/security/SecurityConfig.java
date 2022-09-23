package com.form.login.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UsersAuthenticationSuccessHandler successHandler;
	@Override
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder);
	}
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers(SecurityConstants.H2_CONSOLE).permitAll()
			.antMatchers("/", "/css/*", "/js/*", "/images/*").permitAll()
			// Secure Pages
//			.antMatchers("/admin").hasAnyRole(SecurityConstants.ADMIN, SecurityConstants.SUPER_ADMIN)
//			.antMatchers("/book").hasAnyRole(SecurityConstants.USER, SecurityConstants.ADMIN, SecurityConstants.SUPER_ADMIN)
			// Secure Rest Controllers End Points
			.antMatchers("/api/users/**").hasAnyRole(SecurityConstants.USER, SecurityConstants.ADMIN, SecurityConstants.SUPER_ADMIN)
			.antMatchers("/api/app/superadmin/**").hasRole(SecurityConstants.SUPER_ADMIN)
			.anyRequest()
			.authenticated()
		.and()
			.formLogin()
			.loginPage("/login")
			.successHandler(successHandler)
			.failureUrl("/login.html?error=true")
			.permitAll()
			.loginPage("/login")
		.and()
			.logout()
			.invalidateHttpSession(true)
			.clearAuthentication(true)
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout","POST"))
			.logoutSuccessUrl("/")
			.deleteCookies("JSESSIONID");
		
		
		// To enable H2 console w/o the need go via login page
		http.headers().frameOptions().disable();
		// Another way to config H2
//		 http.headers().frameOptions().sameOrigin().cacheControl();
	}	
}
