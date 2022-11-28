## Links

https://www.youtube.com/watch?v=pnLcbNUOZvU&ab_channel=TheDevWorld-bySergioLema

GREAT LINK (Well Explained)  https://www.toptal.com/spring/spring-security-tutorial



### [Stateless](#-)

Now we start with the main Spring Security configuration.

```.sessionManagement(cust -> cust.sessionCreationPolicy(SessionCreationPolicy.STATELESS))```

### [SecurityConfig.java](#-)

First, we set the session creation policy to STATELESS. This does not disable session management in the underlying web server; instead, it instructs Spring Security to no longer create or use an HTTP session for storing the authentication object.

From <https://golb.hplar.ch/2019/05/stateless.html#stateless-1>  </br>
If we are using JWT , [We disable CSRF since we don't need a session anymore](#-)


# [What is JWT](#-)

JWT - Json Web Token

one of the best ways to secure ways to communicate between Client and Server. </br>
The Advantage of Using JWT , because it completely follows stateless authentication mechanism. </br>
Stateless Authentication Mechanism means , all the users input or user state, is never saved in server memory or cookies.

- With JWT we don't need a FormLogin at the Security Config
