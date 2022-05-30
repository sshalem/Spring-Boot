package com.security.app.config;

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
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

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
			.withUser("shabtay").password(getPasswordEncoder().encode("123")).roles("ADMIN").authorities("READ");
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
//			.antMatchers("/","index","/css/*","/js/*").permitAll()
//			.antMatchers("/api/*").hasAnyRole("ADMIN","MANAGER","STUDENT")
//			.antMatchers("/api/app/*").hasAuthority("READ")
			.anyRequest()
			.authenticated()
			.and()
//			.httpBasic()
			.formLogin();
//			.and()
//			.csrf().csrfTokenRepository(token());
//			.and()
//			.addFilterAfter(new CsrfTokenResponseHeaderBindingFilter(), CsrfFilter.class);
//			.addFilterAfter(new CsrfLoggerFilter(), CsrfFilter.class);
	}        
	
//	private CsrfTokenRepository token() {
			
//		CookieCsrfTokenRepository cookieRepo = new CookieCsrfTokenRepository();
//		HttpSessionCsrfTokenRepository sessionRepo = new HttpSessionCsrfTokenRepository();				
//		sessionRepo.setHeaderName("X-SHABTAY-TOKEN");
//		sessionRepo.setParameterName("_csrf");		
//		return cookieRepo.withHttpOnlyFalse();
//	}
}

