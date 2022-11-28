###### _

<img src="https://img.shields.io/badge/- BA_5_cors_CorsConfigurationSorce_CSRF_disabled %20-brightgreen" height=70px>

|     |  Subject           |
|:---:|:------------------------------| 
|  1  |[BA-5](#)  |   
|  2  |[BackEnd](#backend)  |  
|  3  |[FrontEnd](#frontend)  |  
|  4  |[Run Full Stack Applicaiton](#fullstack)  |  

## [In this project I use:](#-)
1. Basic Authentication with Spring Security
2. CSRF is disabled (So we can send POST, PUT , DELETE Requests w/o sending CSRF token).
3. CORS is configured using CorsConfigurationSorce Bean


###### backend

<img src="https://img.shields.io/badge/-2. BackEnd  %20-blue" height=40px>

### [Dependencies](#-)

![image](https://user-images.githubusercontent.com/36256986/174488491-47df3c06-b413-4149-bf2f-73e9da2290c2.png)

### [Implementation](#-)

1. I also set ```allowCredentials = "true"``` because we are setting CORS , otherwise we will get [401, "Unauthorized"](#-)
2. We must set also the credentials at ForntEnd when we sent AJAX

### [code of SecurityConfig class with CorsConfigurationSource](#-)

```java
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Bean
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(userDetailsServiceImpl).passwordEncoder(getPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()			
//			.antMatchers("/","/index.html","/css/*","/js/*","/images/*").permitAll()
			.antMatchers("/","/index.html","/css/*","/js/*","/images/*","/h2**/**").permitAll()
			.antMatchers("/api/app/**").hasRole("SUPERADMIN")
			.antMatchers("/api/**").hasAnyRole("ADMIN","SUPERADMIN")			
			.anyRequest()
			.authenticated()
		.and()
			.httpBasic()
		.and()
			// enable cross origin requests
			.cors()
		.and()			
			// I disable csrf() at this moment , will explain in next projects
			.csrf().disable();

		// Enable H2 console during development 
		http.headers().frameOptions().disable();
	}
	
	@Bean
    	public CorsConfigurationSource corsConfigurationSource() {
        	final CorsConfiguration configuration = new CorsConfiguration();
        	configuration.setAllowedOrigins(Arrays.asList("http://localhost:8081"));
        	configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
        	configuration.setAllowCredentials(true);
        	// setAllowedHeaders is important! Without it, OPTIONS preflight request
        	// will fail with 403 Invalid CORS request
        	configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type", "xsrf-token"));
        	configuration.setExposedHeaders(Arrays.asList("xsrf-token"));
	        
	        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        	source.registerCorsConfiguration("/**", configuration);
        	return source;
    	}
}
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------


###### frontend

<img src="https://img.shields.io/badge/-3. FrontEnd  %20-blue" height=40px>

Same as BA-4-FrontEnd project

- Since we config CORS (Because we have BackEnd and FrontEnd on different Doamins) ,we must to set credentials as true when send GET, POST, PUT, DELETE requests, otherwise we will get a 401, "Unauthorized".

```javascript
function executePostPutDelete(url, request, user) {
    const options = {
        credentials: 'include',
        method: `${request}`,
        headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(user),
    };

    fetch(url, options)
        .then((res) => res.json())
        .then((data) => {
            if (data.status == 500 || data.status == 403) {
                console.log(data);
                logTraceErrorMessageFromServer(data);
            } else {
                console.log(data);
                executeGetMethod(API_URL_GET_ALL);
            }
        });
}
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------

###### fullstack

<img src="https://img.shields.io/badge/-Run Full Stack Applicaiton %20-blue" height=40px>

Let's run both apps :
1. Backend runs on port 8080
2. FrontEnd runs on port 8081


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------



###### 

<img src="https://img.shields.io/badge/-X.  %20-blue" height=40px>


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------
