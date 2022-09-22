###### _

<img src="https://img.shields.io/badge/- BA_6_cors_config_csrf_enbaled %20-brightgreen" height=55px>

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
|  1  |[BA-6](#)  |   
|  2  |[BackEnd](#backend)  |  
|  3  |[FrontEnd](#frontend)  |  
|  4  |[Run Full Stack Applicaiton](#fullstack)  |  



## [In this project I use:](#-)
1. Basic Authentication with Spring Security
2. CORS is configured using WebMvcConfigurer
3. CSRF is enabled (in SecurityConfig Class).
4. In this project , I tak the csrf token on the RestController Level
5. CSRF token is created in the [CsrfFilter](#-) class , then the filter update the HttpServletRequest Object. Once HttpServletRequest arrives to RestController, I get the attribute from it. The [CsrfTokenRepository](#-) (Implemented withclass [HttpSessionCsrfTokenRepository](#-)) generates the token. From that class I how to get the csrf token using the ["_csrf"](#-) parameter.

###### backend

<img src="https://img.shields.io/badge/-2. BackEnd  %20-blue" height=40px>

### [Dependencies](#-)

![image](https://user-images.githubusercontent.com/36256986/174488491-47df3c06-b413-4149-bf2f-73e9da2290c2.png)

### [Implementation](#-)

1. I also set ```allowCredentials = "true"``` because we are setting CORS , otherwise we will get [401, "Unauthorized"](#-)
2. We must set also the credentials at ForntEnd when we sent AJAX

### [code of SecurityConfig class with CorsConfigurationSource](#-)

```java
	@GetMapping("/users/getAllUsers")
	public ResponseEntity<List<UserEntity>> getAllUsers(HttpServletRequest request, HttpServletResponse response) {

		// The _csrf Attribute is set in the CsrfFilter
		// If there is no csrf token , the filter will create it
		// So , before the request Arrives to the Controller , it goes via CsrfFilter ,
		// which generates this '_csrf'
		// Using the HttpSessionCsrfTokenRepository class Implementation
		// Thus we can see in the Request this attribute
		CsrfToken csrfTokenCSRF = (CsrfToken) request.getAttribute("_csrf");

		//		Header Name -> X-CSRF-TOKEN
		//		System.out.println("Header Name -> " + csrfTokenCSRF.getHeaderName()); 
		//	    parameter Name -> _csrf
		//		System.out.println("parameter Name -> " + csrfTokenCSRF.getParameterName()); 

		response.addHeader("X-CSRF-TOKEN", csrfTokenCSRF.getToken());

		List<UserEntity> returnedValue = userDaoImpl.getAllUsers();
		return new ResponseEntity<List<UserEntity>>(returnedValue, HttpStatus.ACCEPTED);
	}
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------


###### frontend

<img src="https://img.shields.io/badge/-3. FrontEnd  %20-blue" height=40px>

- Since we config CORS (Because we have BackEnd and FrontEnd on different Doamins) ,we must to set credentials as true when send GET, POST, PUT, DELETE requests, otherwise we will get a 401, "Unauthorized".

- Since we enable CSRF , we need to sent the CSRF token with every POST PUT DELETE request.
- First . we extract the CSRF token from the header (When sending GET requet)
- Second , we add the CSRF token to the POST PUT DELETE request.

```javascript
let csrf = '';

function executeGetMethod(url) {
    const options = {
        credentials: 'include',
    };

    fetch(url, options)
        .then((res) => {
            for (let header of res.headers.entries()) {
                if (header[0] === 'x-csrf-token') {
                    console.log(header);
                    csrf = header[1];
                }
            }
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
            'X-CSRF-TOKEN': `${csrf}`,
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
