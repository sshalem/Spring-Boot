<img src="https://img.shields.io/badge/-JWT spring boot 3 update%20-brightgreen" height=70px>

###### \_

|     | Subject                                                                                |
| :-: | :------------------------------------------------------------------------------------- |
|     | [links](#Links)                                                                        |
|     | [Authentication flow](#Authentication_flow)                                            |
|  I  | [JWT Introduction](#1_JWT_Introduction)                                                |
|     | I.1. [Session-based vs Token-based](#1_1_SessionBased_vs_TokenBased)                   |
|     | I.2. [How to Create JWT](#1_2_How_to_Create_JWT)                                       |
|     | I.3. [How JWT secures our data](#1_3_How_JWT_secures_our_data)                         |
|     | I.4. [How Server validates JWT from Client](#1_4_How_Server_validates_JWT_from_Client) |
|  1  | [01-jwt-spring-boot-3-update](#01_jwt_spring_boot_3_update)               |               
|  2  | [](#)    


https://www.bezkoder.com/spring-boot-refresh-token-jwt/

###### Links

<img src="https://img.shields.io/badge/- links %20-blue" height=40px>

https://www.youtube.com/watch?v=pnLcbNUOZvU&ab_channel=TheDevWorld-bySergioLema

GREAT LINK (Well Explained) https://www.toptal.com/spring/spring-security-tutorial

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)
---

###### Authentication_flow

<img src="https://img.shields.io/badge/- Authentication_flow %20-blue" height=40px>

![image](https://user-images.githubusercontent.com/36256986/212500141-fdeb0aec-36d5-4051-9130-7a9083fb8355.png)

During JWT Authentication, These filters are working in the `FilterChainProxy` :

![image](https://user-images.githubusercontent.com/36256986/212500214-58133f2e-f31e-466c-a50b-30a5f0b374c8.png)

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

###### 1_JWT_Introduction

<img src="https://img.shields.io/badge/- 1. JWT_Introduction %20-blue" height=40px>

### [What is JWT?](#-)

[JWT](#-) - Json Web Token

one of the best ways to secure ways to communicate between Client and Server. </br>
The Advantage of Using JWT , because it completely follows stateless authentication mechanism. </br>
Stateless Authentication Mechanism means , all the users input or user state, is never saved in server memory or cookies.

- With JWT we don't need a FormLogin at the Security Config

From <https://golb.hplar.ch/2019/05/stateless.html#stateless-1> </br>
If we are using JWT , [We disable CSRF since we don't need a session anymore](#-)

### [Why JWT is Stateless](#-)

First, we set the session creation policy to STATELESS. This does not disable session management in the underlying web server; instead, it instructs Spring Security to no longer create or use an HTTP session for storing the authentication object.

<p align="center">
  <img src=""/>
</p>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### 1_1_SessionBased_vs_TokenBased

<img src="https://img.shields.io/badge/- 1.1. SessionBased_vs_TokenBased %20- green" height=30px>

link [Link from Bezkoder Session-Based_vs_Token-Based](https://www.bezkoder.com/jwt-json-web-token/)

### [Session-based Authentication](#-)

First, we’re gonna take a look at a simple method that popular websites used in the past:

In the image below, when a user logs into a website :

- the Server will generate a Session for that user and store it (in Memory or Database).
- Server also returns a SessionId for the Client to save it in Browser Cookie.

The Session on Server has an expiration time. </br>
After that time, this Session has expired and the user must re-login to create another Session. </br>
If the user has logged in and the Session has not expired yet, the Cookie (including SessionId) always gos with all HTTP Request to Server. </br>
Server will compare this SessionId with stored Session to authenticate and return corresponding Response.

<p align="center">
  <img src="https://user-images.githubusercontent.com/36256986/210515440-bbc5e69b-57e9-479c-bc06-1c7963c44307.png"/>
</p>

### [JWT Token-based Authentication](#-)

With JWT , the user login state is encoded into a JSON Web Token (JWT) by the Server and send to the Client. </br>

#### [How JWT works](#-)

Instead of creating a Session, the Server generated a JWT from user login data and send it to the Client. </br>
The Client saves the JWT and from now, every Request from Client should be attached that JWT (commonly at header). </br>
The Server will validate the JWT and return the Response.

For storing JWT on Client side, it depends on the platform you use:

- Browser: Local Storage
- IOS: Keychain
- Android: SharedPreferences

<p align="center">
  <img src="https://user-images.githubusercontent.com/36256986/210516583-16f8147c-d0cb-4782-930d-87094c76308a.png"/>
</p>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### 1_2_How_to_Create_JWT

<img src="https://img.shields.io/badge/- 1.2. How to Create JWT %20- green" height=30px>

https://jwt.io/

JWT is composite of 3 parts:

1. Header
2. Payload
3. Signature

#### [Header](#-)

The Header answers the question: How will we calculate JWT? </br>
Now look at an example of header, it’s a JSON object like this: </br>

```java
{
  "typ": "JWT",
  "alg": "HS256"
}
```

- typ is ‘type’, indicates that Token type here is JWT. </br>
- alg stands for ‘algorithm’ which is a hash algorithm for generating Token signature. In the code above, HS256 is HMAC-SHA256 – the algorithm which uses Secret Key.

#### [Payload (Claim)](#-)

The Payload (Claim) helps us to answer: What do we want to store in JWT? </br>
This is a payload sample: </br>

```java
{
  "userId": "abcd12345ghijk",
  "username": "bezkoder",
  "email": "contact@bezkoder.com",
  // standard fields
  "iss": "zKoder, author of bezkoder.com",
  "iat": 1570238918,
  "exp": 1570238992
}
```

In the JSON object above, we store 3 user fields: userId, username, email.
We also have some Standart Fields. They are optional.

- iss (Issuer): who issues the JWT
- iat (Issued at): time the JWT was issued at
- exp (Expiration Time): JWT expiration time

You can see more Standard Fields at: [JWT Standard_fields](https://en.wikipedia.org/wiki/JSON_Web_Token#Standard_fields)

#### [Signature](#-)

This part is where we use the Hash Algorithm that I told you above.

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### 1_3_How_JWT_secures_our_data

<img src="https://img.shields.io/badge/- 1.3. How_JWT_secures_our_data %20- green" height=30px>

### [`JWT does NOT secure your data`](#-).

JWT :

1. does not hide
2. obscure
3. secure data at all.

You can see that the process of generating JWT (Header, Payload, Signature) only encode & hash data, not encrypt data. </br>
The purpose of JWT is to prove that the data is generated by an authentic source. </br>
So, what if there is a Man-in-the-middle attack that can get JWT, then decode user information? Yes, that is possible, so:

- always make sure that your application has the HTTPS encryption.

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### 1_4_How_Server_validates_JWT_from_Client

<img src="https://img.shields.io/badge/- 1.4. How_Server_validates_JWT_from_Client %20- green" height=30px>

In previous section, we use a Secret string to create Signature. </br>
This Secret string is unique for every Application and must be stored securely in the server side. </br>
When receiving JWT from Client, the Server get the Signature, verify that the Signature is correctly hashed by the same algorithm and Secret string as above. </br>
If it matches the Server’s signature, the JWT is valid.

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

------------------------------------------------------------------------------------------


###### 01_jwt_spring_boot_3_update

<img src="https://img.shields.io/badge/- 1. jwt spring boot 3 update %20-blue" height=40px>

This is the link from Bezkoder , how to config security with spring boot 3 

link from bezkoder : https://www.bezkoder.com/websecurityconfigureradapter-deprecated-spring-boot/ </br>
From Spring Boot 2.7, WebSecurityConfigurerAdapter is deprecated. In this tutorial, I will show you how to update your Web Security Config class in Spring Security without the WebSecurityConfigurerAdapter example. </br>


	/**
	 * Update Security Configuration Class with newer version of Spring Security:
	 * If you use Spring Boot 2.7.0 that comes with Spring Security 5.7.1 or newer,
	 * you should code the security configuration class as follows.
	 * See Link from www.bezkoder.com
	 * https://www.bezkoder.com/websecurityconfigureradapter-deprecated-spring-boot/
	 */




[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

------------------------------------------------------------------------------------------

###### 

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

<img src="https://img.shields.io/badge/- X %20- green" height=30px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

######

<img src="https://img.shields.io/badge/- X %20- green" height=30px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

######

<img src="https://img.shields.io/badge/- X %20- green" height=30px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

######

<img src="https://img.shields.io/badge/- X %20- green" height=30px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

------------------------------------------------------

######

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

<img src="https://img.shields.io/badge/- X %20- green" height=30px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

------------------------------------------------

