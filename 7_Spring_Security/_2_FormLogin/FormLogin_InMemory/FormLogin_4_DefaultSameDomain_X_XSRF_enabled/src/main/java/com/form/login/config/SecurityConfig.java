package com.form.login.config;

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
			.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
		.and()
			.authorizeRequests()
			.antMatchers("/", "/index", "/css/*", "/js/*", "/images/*").permitAll()
			// secure Pages url's
			.antMatchers("/landing-page").authenticated()
			// Secure Rest Controllers End Points
			.antMatchers("/api/*").hasAnyRole("ADMIN","MANAGER","STUDENT")
			.antMatchers("/api/app/*").hasRole("ADMIN")
		.and()
			.formLogin()
			.permitAll()
			.defaultSuccessUrl("/landing-page",true)
		.and()
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST"))
			.invalidateHttpSession(true)
			.clearAuthentication(true)
			.deleteCookies("JSESSIONID")			
			.logoutSuccessUrl("/login");
	}	
}

//.formLogin()
//	.loginProcessingUrl("/login")       // link to submit username-password
//	.loginPage("/login")
//	.usernameParameter("username")      // username field in login form
//	.passwordParameter("password")      // password field in login form
//	.defaultSuccessUrl("/")             
//	.failureUrl("/login?error")
//.and()
//	.logout()
//	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))            
//	.logoutSuccessUrl("/login")
//	.invalidateHttpSession(true)        // set invalidation state when logout
//	.deleteCookies("JSESSIONID")        
//.and()
//	.exceptionHandling()
//	.accessDeniedPage("/403");
