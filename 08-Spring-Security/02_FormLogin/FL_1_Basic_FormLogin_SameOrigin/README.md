###### _

<img src="https://img.shields.io/badge/-FL_1_Basic_FormLogin_same_origin %20-blue" height=55px>

|     |  Subject           |
|:---:|:------------------------------| 
|  1  |[FL-1](#)  |   
|  2  |[BackEnd](#backend)  |  
|  3  |[FrontEnd](#frontend)  |  
|  4  |[Run Full Stack Applicaiton](#fullstack)  |  

## [In this project I use:](#-)
1. FormLogin with Spring Security
2. CSRF is disabled (So we can send POST, PUT , DELETE Requests w/o sending CSRF token).
3. CORS is NOTE configured
	
		In the matchers antMatchers("/css/*", "/js/*", "/images/*").permitAll() 
		I don't have ("/" , "/index.html"). 
		I removed those url's. 
		Why? 
		so User can't access to those links w/o login authentication.  	

###### backend

<img src="https://img.shields.io/badge/-2. BackEnd  %20-blue" height=40px>

### [Implementation](#-)

```java

```


### [code of SecurityConfig class](#-)

```java
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
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------

###### frontend

<img src="https://img.shields.io/badge/-3. FrontEnd  %20-blue" height=40px>

See implementation in the static folder. Both Fonrt and Back run on same domain.

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------

###### fullstack

<img src="https://img.shields.io/badge/-Run Full Stack Applicaiton %20-blue" height=40px>

Let's run both apps :
1. Backend runs on port 8080 (both on same port and domain)
2. FrontEnd runs on port 800 (both on same port and domain)


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------



###### 

<img src="https://img.shields.io/badge/-X.  %20-blue" height=40px>


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------
