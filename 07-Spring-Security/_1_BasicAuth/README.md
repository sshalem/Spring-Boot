###### _

<img src="https://img.shields.io/badge/-Basic Authentication projects%20-brightgreen" height=70px>

|     |  Subject           |
|:---:|:------------------------------| 
|  1  |[Basic Authentication Introduction](#Basic_Authentication_Introduction)   | 
|  2  |[HTTP Basic Authentication With Spring Security](#HTTP_Basic_Authentication_With_Spring_Security)  |   
|  3  |[How Spring Security Processes HTTP Basic Authentication Requests](#How_Spring_Security_Processes_HTTP_Basic_Authentication_Requests)  |   
|  4  |[Basic Auth with cors, csrf , POST PUT DELETE OPTIONS methods](#Basic_Auth_with_cors_csrf_POST_PUT_DELETE_OPTIONS_methods)  |   
|  5  |[Authentication flow](#Authentication_flow)  
|  6  |[CSRF](#csrf)  

###### Basic_Authentication_Introduction

<img src="https://img.shields.io/badge/-Basic Authentication Introduction  %20-blue" height=40px>

### [Basic Authentication Flow Diagram](#-)

![image](https://user-images.githubusercontent.com/36256986/175132036-e443d47f-e3cc-40d2-ba1b-d8643391c94a.png)

### [How Basic Auth works?](#-)
The client wants to send a request to server, </br>
but since we have configured our app with **_[basic auth](#-)_** the server will sent a [401](#-) page.


### [Question:](#-)
Why 401 Unauthorized returned ?
	
### [Answer:](#-)
since we have configured our app with basic auth, </br> 
we must send Inside the request header the user name and password as **_Basic_64_**.  
	
So, for every single request I sent to server I need to send with request header the user name and password as **_Basic 64_**.  
The server does some validation :
1. If user name exist, 
2. Check the password , if password matches to the one that server knows
3. Return to client the response of 200 with the data
 
  
### [Stuff need to know about Basic Auth](#-)</br>

1. It is very simple. 
2. Doesn't require **_cookies_** , **_session identifiers_** (Although a SESSION is created) , or **_login page_**.
3. Transmitted credentials are **_not encrypted_**. They are **_encoded with Base64_** in transit, but **_not encrypted or hashed_** in anyway.
4. Http does not provide a method for a web server to instruct the client to "Log Out"
5. This authentication mechanism is not handled by your app , but by the browser.


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------

###### HTTP_Basic_Authentication_With_Spring_Security

<img src="https://img.shields.io/badge/-HTTP Basic Authentication With Spring Security %20-blue" height=40px>

## [HTTP Basic Authentication With Spring Security](#-)

From <https://medium.com/@ashokmathankumar/http-basic-authentication-with-spring-security-57c46ffa7d86> 

How **_HTTP Basic Authentication Works_**? <br>
In case of **_HTTP Basic Authentication_**:
* User login with credentials 
* Credentials are passed on the HTTP request header, precisely “Authorization” request header. 
* This header allows you to send username and password into request headers instead of the request body. 
* This is ideal for authenticating REST clients.

1. Client sends REST request
2. If a request doesn’t have **_"Authorization"_** header then, server does 2 things:</br>
	* a. Server **_rejects_** the request with [**_401 unauthorized response_**](#-) 
	* b. Server appends header [**_“WWW-Authenticate: Basic realm”_**](#-) to instruct the client that it needs to send **_username and password_** in request header for "Authorization".
3. This header (Send from server [**_“WWW-Authenticate: Basic realm”_**](#-)) will trigger the browser to pop up a [**_sign in dialog box_**](#-)

![image](https://user-images.githubusercontent.com/36256986/174597957-e5fc440f-8d60-43fc-855c-c9de783832cf.png)

4. user enters credentials for example username & password 
5. the client creates the string “username:password” and [base 64 encode](#-) it before sending it in the "Authorization" header.
6. This string is then sent with [“Authorization”](#-) header of the request.

### [Summarize it](#-)
* After entering credentials
* The Browser creates the Authorization Header
* The Browser assigns Credentials (entered in the dialog box ) to the Authorization Header

![image](https://user-images.githubusercontent.com/36256986/174600588-0c0071aa-2c58-4bcf-9d8e-f10cbeec7bd4.png)

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------

###### How_Spring_Security_Processes_HTTP_Basic_Authentication_Requests

<img src="https://img.shields.io/badge/-How Spring Security Processes HTTP Basic Authentication Requests %20-blue" height=40px>

From link of : https://dzone.com/articles/how-does-http-basic-authentication-work-in-spring 

WHen we use the [httpBasic()](#-) Spring Security's [BasicAuthenticationFitler](#-) comes into the picture which basically checks to see if the incoming HTTP request contains the "Authorization" header or not and its value starts with "Basic."

A [BasicAuthenticationEntryPoint](#-) class strategy is also configured is startup since we chose HttpBasic().

This class adds the header ["WWW-Authenticate", "Basic realm=\"" + this.realmName + "\""](#-) to the response and then sends an HTTP status code of 401 (Unauthorized) to the client, e.g. to your browser, which knows how to handle this code and work accordingly. Because of this, it shows a dialog box prompting for username and password, as in previous paragraph.

When you put the username and password and submit the request, the request again follows the filter chain until it reaches the BasicAuthenticationFilter.

This filter checks the request headers and the location for the Authorization header, starting with "Basic." It will look something like this: Authorization: Basic CDWhZGRpbjpvcGVuc2AzYW1l .

The BasicAuthentictionFilter then extracts the content of the Authorization header and uses the Base64 algorithm to decode the login credentials to extract the username and password from the decoded string.

Once it has that information, the filter creates a UsernamePasswordAuthenticationToken object and sends it to the authentication manager for authentication in the standard way.

Basically, the [BasicAuthenticationFilter](#-) does most of the job along with [BasicAuthenticationEntryPoint](#-).

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------

###### Basic_Auth_with_cors_csrf_POST_PUT_DELETE_OPTIONS_methods

<img src="https://img.shields.io/badge/-Basic Auth with cors, csrf , POST PUT DELETE OPTIONS methods %20-blue" height=40px>

With Spring Security we have additional things that matters :
* [cors](#-) Cross Origin
* [csrf](#-) Cross Site Forgery Request
* Sending POST PUT DELETE 

As we mentioned above:
* When sending only GET request , sign in will pop-up. we enter credentials and server sends the data.
* But when we try to send POST PUT DELETE requests , we will get status 403 "Forbbiden". This is because CSRF token needs to sent as well.
* In the first projects ,I disable CSRF just to show how to work with CORS and security.
* Later on , I show how to work with [csrf enabled](#-) using [Basic Authentication](#-). This way we can sent POST PUT DELETE requests to server.

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------

###### Authentication_flow

<img src="https://img.shields.io/badge/-X.  %20-blue" height=40px>

### Flow of Authentication

![image](https://user-images.githubusercontent.com/36256986/175136726-15d34663-8406-45aa-9316-02af9b71857a.png)

In the diagram we can see the [BasicAuhtneticationFilter](#-) which inside of this filter class all the authentication is done.
Then the we return to the filter chain.

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------

###### csrf

<img src="https://img.shields.io/badge/-6. CSRF  %20-blue" height=40px>

Great link well explained how to use

https://www.javadevjournal.com/spring-security/spring-security-csrf-token/

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------

###### 

<img src="https://img.shields.io/badge/-X.  %20-blue" height=40px>


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------
