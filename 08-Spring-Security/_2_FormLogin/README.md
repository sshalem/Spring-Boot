###### _

[```SO```](#-) Same Origin </br>
[```CLL```](#-)- Custom Login Logout


<img src="https://img.shields.io/badge/-Form Login (session based) %20-brightgreen" height=70px>

|     |  Subject           |
|:---:|:------------------------------| 
|  1  |[Form Login Introduction](#FormLogin_Authentication_Introduction)   | 
|  2  |[FormLogin Filter Chain](#FormLogin_Filter_Chain)  |   
|  3  |[How Spring Security Processes FormLogin Authentication Requests](#How_Spring_Security_Processes_FormLogin_Authentication_Requests)  |   
|  4  |[FormLogin with cors, csrf , POST PUT DELETE OPTIONS methods](#FormLogin_with_cors_csrf_POST_PUT_DELETE_OPTIONS_methods)  |   
|  5  |[Form Login Authentication flow](#Authentication_flow)  
|  6  |[FormLogin Issue when CORS and CSRF together](#FormLogin_Issue_when_CORS_and_CSRF_together)  

###### FormLogin_Authentication_Introduction

<img src="https://img.shields.io/badge/-Form Login Introduction  %20-blue" height=40px>

### [_Form Login_](#-) is [_session_](#-) based.

1. Client sends a request for a url of /Home to server
2. If user not authenticated , the Server :
    * will [redirected to login](#-) page.
    * Generate a [CSRF token](#-) and send it as hidden input in the body of the login page
3. Once user and password are typed , and user click on login (to submit the form) The form is POSTED (submitted to server). With the submitted form , also the [csrf token](#-) is added.
4. If Ok (Credentials are validated) , a new [Cookie](#-) of [JSESSIONID](#-) (unique session Id)  created by Server and send the cookie to client.
5. Assume a client sends another request to GET the **/reports/all** resources and because we have a [cookie](#-) now , The client will also sends the authentication [cookie](#-) back to the server.
6. The Server will [validate the SESSION ID](#-) .The authentication cookie contains: The [JSESSIONID](#-). That [JSESSIONID](#-) from the client is checked against the actual list of valid id's in the server and if it's not expired Then the server will respond a message of 200 OK.
7. Let's say a user wants to logout
8. So when clicking on the logout icon , this sends a request to logout, then the [JSESSIONID](#-) is **Invalidated on the server** , the server redirects to the login page (Or any other url page we give). 
9. The Exact same process happens when the session expires.	Session id by default exists only [15min](#-) . After 15 min of inactivity the session automatically becomes [**_invalid_**](#-), and then when user tries to login, even if he has the [cookie](#-)  , the [session id](#-) is no longer exists.

![image](https://user-images.githubusercontent.com/36256986/175877600-3144c9e3-6375-45e2-ab2f-3689ee3313f1.png)


![image](https://user-images.githubusercontent.com/36256986/175833796-ed39d178-5763-43d7-8aeb-cf4b2ff46f17.png)

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------

###### FormLogin_Filter_Chain

<img src="https://img.shields.io/badge/-FormLogin Filter Chain %20-blue" height=40px>

## [Default FormLogin Filter chain With Spring Security](#-)

When Default FormLogin is configured , the following filters are invoked :

![image](https://user-images.githubusercontent.com/36256986/175789938-7ddf7599-0248-4abb-8d3f-e89c40968c90.png)

## [Custom FormLogin Filter chain With Spring Security](#-)

When Custom FormLogin is configured , the following filters are invoked :

![image](https://user-images.githubusercontent.com/36256986/175802167-4404531c-2fbd-4004-91ca-27c941ac2216.png)

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------

###### How_Spring_Security_Processes_FormLogin_Authentication_Requests

<img src="https://img.shields.io/badge/-How Spring Security Processes FormLogin Authentication Requests %20-blue" height=40px>

When we use the [FormLogin()](#-) , Spring Security's [UsernamePasswordAuthenticationFilter](#-) which extends the [AbstractAuthenticationProcessingFilter](#-) comes into the picture , along side with [LogoutFilter](#-) and [DefaultLoginPageGeneratingFilter](#-) , (And additional Filters as in previous section).

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------

###### FormLogin_with_cors_csrf_POST_PUT_DELETE_OPTIONS_methods

<img src="https://img.shields.io/badge/-FormLogin with cors, csrf , POST PUT DELETE OPTIONS methods %20-blue" height=40px>

With Spring Security we have additional things that matters :
* [cors](#-) Cross Origin
* [csrf](#-) Cross Site Forgery Request
* Sending POST PUT DELETE 

As we mentioned above:
* When sending only GET request , sign in will pop-up. we enter credentials and server sends the data.
* But when we try to send POST PUT DELETE requests , we will get status 403 "Forbbiden". This is because CSRF token needs to sent as well.
* Later on , I show how to work with [csrf enabled](#-) using [FormLogin](#-). 

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------

###### Authentication_flow

<img src="https://img.shields.io/badge/-X.  %20-blue" height=40px>

### Flow of Authentication

In the diagram we can see the [UsernamePasswordAuthenticationFilter](#-) which extends the [AbstractAuthenticationProcessingFilter](#-)</br>
Inside of this filter class all the authentication is done.
Then the we return to the filter chain from [AbstractAuthenticationProcessingFilter](#-).

![image](https://user-images.githubusercontent.com/36256986/175788956-0524efc6-b866-4e7f-b843-3b091bf4f23b.png)

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------

###### FormLogin_Issue_when_CORS_and_CSRF_together

<img src="https://img.shields.io/badge/-6. CSRF  %20-blue" height=40px>

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

###### 

<img src="https://img.shields.io/badge/-X.  %20-blue" height=40px>


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------
