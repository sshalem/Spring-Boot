###### _

<img src="https://img.shields.io/badge/-FL_6_CustomLoginLogout_cors_xsrf_cookie_Enable %20-blue" height=55px>

|     |  Subject           |
|:---:|:------------------------------| 
|  1  |[FL-5](#)  |   
|  2  |[BackEnd](#backend)  |  
|  3  |[FrontEnd](#frontend)  |  
|  4  |[Run Full Stack Applicaiton](#fullstack)  |  
|  5  |[FormLogin_Issue_when_CORS_and_CSRF_together](#FormLogin_Issue_when_CORS_and_CSRF_together)  | 


## [In this project I use:](#-)

1. FormLogin with Spring Security
2. Both Backend & FrontEnd on SAME ORIGIN.
3. I make a Custom Form to Login
4. I Customize the way I Logout
5. CSRF is Enabled as cookie thus we Send [X-XSRF-TOKEN](#-) (and not X-CSRF)  token, I will show how to send XSRF token , So we can send POST, PUT , DELETE .
6. CORS is configured (We are in the Different Domains)		

## [Changes made in BackEnd/FrontEnd](#-)

I make the following changes in [backend](#-):
- Config SecurityConfig Class to enable [csrf()](#-)
- Create new Package for for **_Servlet Controller_**
- Create new Class for **_ViewController_** to return pages from server (Login page , and Home page)
- Unlike with CSRF send with header , with XSRF it is send as COOKIE , thus no need to send with VIEW of loginPage and homePage [CsrfToken csrfToken](#-). </br>
- Since we use ```.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())``` , thus we should extract the XSRF from the cookie.
- Because we set with HTTP false, we will be able to take the cookie with JavaScript, and add it to :
1. Login 
2. Logout
3. POST PUT DELETE requests.


### XSRF token will be used for :
1. login - We must sent with the form of the login page , the XSRF token , otherwise we will never get access.
2. Sending POST PUT DELETE -  We must add the XSRF token to the rewuest header , when we sent AJAX of POST PUT DELETE.
3. Logout - XSRF token must add to logout form , otherwise we will be routed to 404 page


I make the following changes in [frontend](#-):
- create new pages **_Home.html_** (Which was our Index.html in previous projects)
- Create new Index.html pgae , which will be the page where user gets at first , before trying to login.
- From Index.html, when user will click on home link, he will be redirected to **_Login.html_** page

###### backend

<img src="https://img.shields.io/badge/-2. BackEnd  %20-blue" height=40px>


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
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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
		 * In these matchers antMatchers("/css/*", "/js/*", "/images/*" ,"/index", "/").permitAll()
		 * I add  ("/" , "/index.html"). 
		 * Why?
		 * Index - is the welcome page I want all to get access to it.
		 * Login - all have access to it
		 * Home - after successful login , user will be redirected to Home url.  
		 */	
		
		http		
			.authorizeRequests()
			.antMatchers("/h2**/**").permitAll()
			.antMatchers("/css/*", "/js/*", "/images/*", "/index", "/").permitAll()
			.antMatchers("/csrf").permitAll()
			.antMatchers("/api/app/**").hasRole("SUPERADMIN")
			.antMatchers("/api/**").hasAnyRole("ADMIN","SUPERADMIN")		
			.anyRequest()
			.authenticated()
		.and()
			.formLogin()
			.loginPage("/login")
			.failureUrl("/login.html?error=true")
			.defaultSuccessUrl("/home", true)
			.permitAll()
		.and()
			.logout()
			.invalidateHttpSession(true)
			.clearAuthentication(true)
			.deleteCookies("JSESSIONID")
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST"))
			.logoutSuccessUrl("/")
		.and()
			.cors()
		.and()
			.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
		
		// Enable H2 console during development, To enable H2 console w/o the need go via login page
		http.headers().frameOptions().disable();
		// Another way to config H2
		// http.headers().frameOptions().sameOrigin().cacheControl();		
	}
}
```

### [code of ViewController class](#-)

The VIEW's (pages)  that are returned , are not been redirected (Since we are in the SAME ORIGIN). </br>
I could redirect, but the , I won't be able to sent the RESPONSE header , because header will be lost , when I redirect to a page.


```java
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

	@GetMapping("/login")
	public String loginPage() {		
		return "login.html";
	}

	@GetMapping("/home")
	public String homePage() {		
		return "home.html";
	}
}
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------

###### frontend

<img src="https://img.shields.io/badge/-3. FrontEnd  %20-blue" height=40px>

See implementation in the static folder. </br>
Both Front and Back run on same domain.

## [index.html](#-)

```sql
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Document</title>
        <link rel="shortcut icon" href="./images/favicon.png" type="image/x-icon" />
        <link rel="stylesheet" href="./css/index.css" />
    </head>
    <body>
        <div class="container">
            <header><h1>Welcome Page</h1></header>
            <div class="seperator"></div>
            <div class="home-link">
                <a href="http://localhost:8080/home">link to home page</a>
            </div>
        </div>
    </body>
</html>
```

## [login.html](#-)

Using Javascript, when I am at the login.html page, My Server sends in a header the X-CSRF-TOKEN.</br>
I get this token from the header , and sent it , when I submit the form in a hidden attribute.

```<input name="_csrf" type="hidden" value="" id="csrfId" />```


```sql
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <title>login</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
        <script src="https://kit.fontawesome.com/c3d49f7cef.js" crossorigin="anonymous"></script>
    </head>

    <body>
        <div class="container">
            <div style="width: 400px; margin-left: auto; margin-right: auto; margin-top: 24px; padding: 24px; text-align: left">
                <div class="card">
                    <div class="card-header" style="background-color: #1e90ff"><i class="fa fa-user" style="font-size: 24px"></i> Sign In</div>
                    <div class="card-block" style="padding: 24px">
                        <form class="form-signin" method="post" action="http://localhost:8080/login">
                            <div class="form-group">
                                <label for="username">username</label>
                                <input type="text" id="username" name="username" class="form-control" placeholder="username" required="" autofocus="" />
                            </div>
                            <div class="form-group">
                                <label for="password">password</label>
                                <input type="password" id="password" name="password" class="form-control" placeholder="password" required="" />
                            </div>
                            <input name="_csrf" type="hidden" value="" id="csrfId" />
                            <div class="form-actions" style="margin-top: 12px">
                                <button type="submit" class="btn btn-success btn-block" style="background-color: #1e90ff">login</button>
                            </div>
                            <br />
                            <div id="err"></div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <script src="./js/login.js"></script>
    </body>
</html>
```

## [login.js](#-)

```javascript
const csrfId = document.getElementById('csrfId');
let csrf = null;

/**********************************************
 * Once Page loads , I check Cookies
 * and looking for the X-XSRF-TOKEN cookie ,
 * to add it to the hidden input
 **********************************************/

let ck = document.cookie;
let splitCookie = ck.split('=');
window.addEventListener('load', () => {
    // csrfAdd.innerHTML = `<input type="hidden" name="_csrf" value="${splitCookie[1]}">`;
    csrfId.setAttribute('value', `${splitCookie[1]}`);
});

/***********************************
 * Validation for user credentials
 ***********************************/
if (window.location.search != '' && window.location.search != '?') {
    document.getElementById('err').innerHTML = `
    		<div class="alert alert-danger">username / password incorrect</div>
    		`;
}
```

## [home.html](#-)

Here is we can see the logout button, and we assign to it , the csrf token , that we got from server from [homePage() method](#-).

```sql
<!-- Logout Button -->
<div class="logout">
	<form action="http://localhost:8080/logout" method="post">
		<input type="hidden" name="_csrf" value="" id="csrfId">
        <button class="logout-btn" type="submit">Logout</button>
	</form>                
</div>
```

## [home.js](#-)

- Here I Take the CSRF token from Header , and assign it to the logout attribute.
- Also, i the csrf token to the header of each POST PUT DELETE request.

```javascript

const csrfId = document.getElementById('csrfId');
let xsrf = null;

/**********************************************
 * Once Page loads , I check Cookies
 * and looking for the X-XSRF-TOKEN cookie ,
 * to add it to the hidden input
 **********************************************/

let ck = document.cookie;
let splitCookie = ck.split('=');
console.log(ck);
console.log(splitCookie);
// const csrfAdd = document.getElementById('csrfAdd');
window.addEventListener('load', () => {
    // csrfAdd.innerHTML = `<input type="hidden" name="_csrf" value="${splitCookie[1]}">`;
    xsrf = splitCookie[1];
    csrfId.setAttribute('value', `${splitCookie[1]}`);
});



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
1. Backend runs on port 8080 (both on same port and domain)
2. FrontEnd runs on port 8080 (both on same port and domain)


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------



###### FormLogin_Issue_when_CORS_and_CSRF_together

<img src="https://img.shields.io/badge/-X. FormLogin_Issue_when_CORS_and_CSRF_together  %20-blue" height=40px>

Great link well explained how to use

https://www.javadevjournal.com/spring-security/spring-security-csrf-token/

### [Issue](#-)

When I made a project where FrontEnd and BackEnd are in different Domain, the app did not react as expected with Spring Security. </br>
The issue is as follows:
* After clicking on logout , I'm redirected to the index.html page on url of "http://localhost:8081/" 
* When I click on the back button on the browser , I'm return to the /Home url , and seeing the page of HOME.html (AJAX calls are not working as expected).
* I shouldn't be able to see this page when I'm logged out.
* Didn'y find a sloution for this issue , YET.

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------
