###### _

<img src="https://img.shields.io/badge/- BA_8_cors_config_xsrf_cookie %20-blue" height=55px>

### [CSRF (Cross Site Request Forgery)](#-)
Which means that you open two tabs in a browser, </br>
one of which sends a fake request by stealing another page’s cookie, </br>
because the cookie is automatically sent to the request. Server side. </br>
From <https://medium.com/@mena.meseha/how-to-defend-against-csrf-using-jwt-8adebe64824b> 

### We will see how to work with CSRF with Basic Auth:
- [CSRF is enabled by default](#-) in spring security.
- in order to be able to send POST, PUT, DELETE requests we need to perform some configuration 
- We need to add following methods to our HttpSecurity config :
○ csrf()
○ Enable 

### [When using basic auth we first need to send a **_Get request_**](#-).
1. Send an Initial GET request
2. The server will return as a response , a CSRF token (can be cookie X-XSRF-TOKEN , or Header X-CSRF-TOKEN) with the response of the GET request
3. We must to capture the csrf token in our FrontEnd , and sent the CSRF token with AJAX requests of POST PUT DELETE. otherwise we will get [403, "Forbidden"](#-)


### [2 Ways to send CSRF/XSRF](#-)
I will show how to implement it with :
1. [XSRF](#-) - Server send XSRF as cookie
2. [CSRF](#-) - Server send CSRF in the header



https://medium.com/@mena.meseha/how-to-defend-against-csrf-using-jwt-8adebe64824b


|     |  Subject           |
|:---:|:------------------------------| 
|  1  |[BA-7](#)  |   
|  2  |[BackEnd](#backend)  |  
|  3  |[FrontEnd](#frontend)  |  
|  4  |[Run Full Stack Applicaiton](#fullstack)  |  



## [In this project I use](#-)
1. Basic Authentication with Spring Security
2. CORS is configured using WebMvcConfigurer
3. I use CookieCsrfTokenRepository withHttpOnlyFalse() to genertae [**XSRF**](#-) Token
4. [**XSRF**](#-) token is generated. This configuration will set a [XSRF-TOKEN](#-) cookie to the front end, 
5. Since we set the [HTTP-only](#-) flag to ['false'](#-), the front end will be able to retrieve this cookie using JavaScript. (Other wise it won't be possible).

###### backend

<img src="https://img.shields.io/badge/-2. BackEnd  %20-blue" height=40px>

### [Dependencies](#-)

![image](https://user-images.githubusercontent.com/36256986/174488491-47df3c06-b413-4149-bf2f-73e9da2290c2.png)

### [Implementation](#-)

1. I also set ```allowCredentials = "true"``` because we are setting CORS , otherwise we will get [401, "Unauthorized"](#-)
2. We must set also the credentials at ForntEnd when we sent AJAX

### [code of SecurityConfig class](#-)

```java
@Override
protected void configure(HttpSecurity http) throws Exception {
	http
		.authorizeRequests()			
		//	.antMatchers("/","/index.html","/css/*","/js/*","/images/*").permitAll()
		.antMatchers("/","/index.html","/css/*","/js/*","/images/*","/h2**/**").permitAll()
		.antMatchers("/api/app/**").hasRole("SUPERADMIN")
		.antMatchers("/api/**").hasAnyRole("ADMIN","SUPERADMIN")			
		.anyRequest()
		.authenticated()
	.and()
		.httpBasic()
	.and()			
		.cors()
	.and()			
		.csrf()
		.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
	
	/**
	 * Here I define to get the CSRF token in a cookie.
	 * This configuration will set a XSRF-TOKEN cookie to the front end.
	 * BUT, Since we set the HTTP-only flag to 'false', 
	 * the front end will be able to retrieve this cookie using JavaScript.
	 */
		
	// Enable H2 console during development
	http.headers().frameOptions().disable();			
}	
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------


###### frontend

<img src="https://img.shields.io/badge/-3. FrontEnd  %20-blue" height=40px>

We add 2 things:
1. Since we config CORS (Because we have BackEnd and FrontEnd on different Doamins) ,we must to set credentials as true when send GET, POST, PUT, DELETE requests, otherwise we will get a 401, "Unauthorized".
2. Since we enable CSRF , we need to sent the CSRF token with every POST PUT DELETE request. First , we extract the CSRF token from the header (When sending GET requet). Second , we add the CSRF token to the POST PUT DELETE request.

```javascript

let xsrf = '';

function executeGetMethod(url) {
    const options = {
        credentials: 'include',
    };

    fetch(url, options)
        .then((res) => {
            xsrf = document.cookie.split('=')[1];
            console.log(xsrf);
            return res.json();
        })
        .then((data) => {
           .
	   .
	   .
}

function executePostPutDelete(url, request, user) {
    const options = {
        credentials: 'include',
        method: `${request}`,
        headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json',
            'X-XSRF-TOKEN': `${xsrf}`,
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
