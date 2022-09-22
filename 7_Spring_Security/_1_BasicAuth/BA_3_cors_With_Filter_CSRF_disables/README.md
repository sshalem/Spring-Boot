###### _

<img src="https://img.shields.io/badge/- BA_3_cors_With_Filter_CSRF_disables %20-brightgreen" height=70px>

|     |  Subject           |
|:---:|:------------------------------| 
|  1  |[BA-3](#)  |   
|  2  |[BackEnd](#backend)  |  
|  3  |[FrontEnd](#frontend)  |  
|  4  |[Run Full Stack Applicaiton](#fullstack)  |  

## [In this project I use:](#-)
1. Basic Authentication with Spring Security
2. CSRF is disabled (So we can send POST, PUT , DELETE Requests w/o sending CSRF token).
3. CORS is configured using a Filter , Also Config in SecurityConfig class


###### backend

<img src="https://img.shields.io/badge/-2. BackEnd  %20-blue" height=40px>

### [Dependencies](#-)

![image](https://user-images.githubusercontent.com/36256986/174488491-47df3c06-b413-4149-bf2f-73e9da2290c2.png)

### [Implementation](#-)

Here I Implement CORS by using a filter.

1. I remove the config of Cors in the Controller level (Since I uSe a filter)
2. I create a CorsFilterImpl class that extends OncePerRequestFilter
3. I also set ```allowCredentials = "true"``` because we are setting CORS , otherwise we will get [401, "Unauthorized"](#-)
4. We must set also the credentials at ForntEnd when we sent AJAX

```java
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class CorsFilterImpl extends OncePerRequestFilter {

	/**
	 * Because I implement a filter , no need to add the CORS at the Controller
	 * level Every thing is define here in the filter
	 */

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:8081");
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "authorization, content-type, xsrf-token");
		response.addHeader("Access-Control-Expose-Headers", "xsrf-token");
		
		if ("OPTIONS".equals(request.getMethod())) {
			System.out.println("in if");
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			System.out.println("in else");
			filterChain.doFilter(request, response);
		}
	}
}
```

3. I add in the class of SecurityConfig the following line  

```java
.addFilterBefore(new CorsFilterImpl(), CorsFilter.class)
```

code of SecurityConfig class

```java
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
			.addFilterBefore(new CorsFilterImpl(), CorsFilter.class)
			// I disable csrf() at this moment , will explain in next projects
			.csrf().disable();

		// Enable H2 console during development 
		http.headers().frameOptions().disable();
	}
```

When app runs, it will run the **_CorsFilterImpl_** before **_CorsFilter.class_** (we can test it in debug mode)

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------


###### frontend

<img src="https://img.shields.io/badge/-3. FrontEnd  %20-blue" height=40px>

###### frontend

<img src="https://img.shields.io/badge/-3. FrontEnd  %20-blue" height=40px>

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

In order to be able to get 


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------



###### 

<img src="https://img.shields.io/badge/-X.  %20-blue" height=40px>


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------
