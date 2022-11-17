<img src="https://img.shields.io/badge/-OpenApi3 (springfox) %20-blue" height=50px>

###### _

|     |  Subject           |
|:---:|:------------------------------| 
|     |[Difference Between Swagger and OpenAPI](#Difference_Between_Swagger_and_OpenAPI)   | 
|     |[SpringDoc or Swagger or OpenAPI](#SpringDoc_or_Swagger_or_OpenAPI)   | 
|  1  |[Spring Boot project](#1_Spring_Boot_project)  |   
|  2  |[Add SpringDoc (OpenAPi) ](#2_Add_springdoc) | 
|     |2.1. [Run App Test API with Open APi](#2_1_Run_App_Test_API_with_OpenApi) |  
|  3  |[Customize Swagger2 configuration](#Customize_Swagger2_configuration)    | 
|     |3.1. [Run App Test API with Swagger UI reconfig](#Run_App_Test_API_with_Swagger_UI_reconfig) |  
|  4  |[Add details to API](#Add_details_to_API)    | 
|     |4.1. [test App](#test_app) |  






--------------------------------------------------------------------------------------------------

###### Difference_Between_Swagger_and_OpenAPI

<img src="https://img.shields.io/badge/- Difference_Between_Swagger_and_OpenAPI  %20-blue" height=40px>

Documentation:
- https://springdoc.org/
- https://www.youtube.com/watch?v=1Vmi_5ZsyqE&ab_channel=Saggu

links: 
- https://swagger.io/blog/api-strategy/difference-between-swagger-and-openapi/
- https://www.blazemeter.com/blog/openapi-vs-swagger
- https://nordicapis.com/whats-the-difference-between-swagger-and-openapi/
- https://www.youtube.com/watch?v=2pyUYJ4NiMI&ab_channel=CodeWithPraveen

Question:
* What's the Difference Between OpenAPI and Swagger?

Answer:
* OpenAPI and Swagger used to refer to the same thing. While there are differences today (OpenAPI refers to RESTful API design and Swagger refers to a set of SmartBear tools), this blog will use the terms interchangeably. 

![image](https://user-images.githubusercontent.com/36256986/202562368-421c8ee4-3292-495d-be4b-81e60df97ffa.png)
![image](https://user-images.githubusercontent.com/36256986/202562410-77a656d0-140d-4a5f-8ea9-6569624b223a.png)


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------

###### 1_Spring_Boot_project

<img src="https://img.shields.io/badge/-1. Spring_Boot_project  %20-blue" height=40px>

Create a new Spring-Boot Project and add the follwoing dependencies:

```sql
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
		
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>
```



[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------

###### 2_Add_springdoc

<img src="https://img.shields.io/badge/- 2. Add_springdoc  %20-blue" height=40px>

Now let's also add the dependdency of OpenAPi 

```sql
<dependency>
  <groupId>org.springdoc</groupId>
	<artifactId>springdoc-openapi-ui</artifactId>
	<version>1.6.12</version>
</dependency>
```

I created the following controllers with endpoints:

### UserController

```java
@RestController
@RequestMapping("/user")
public class UserController {

	@GetMapping(path = "/get")
	public ResponseEntity<?> getUser() {
		return new ResponseEntity<>(new UserEntity("shabtay", "shalem"), HttpStatus.OK);
	}

	@PostMapping(path = "/create")
	public ResponseEntity<?> createUser(@RequestBody UserEntity userEntity) {
		System.out.println(userEntity);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
```

### RoleController

```java
@RestController
@RequestMapping("/role")
public class RoleController {

	@GetMapping(path = "/get")
	public ResponseEntity<?> getRole() {
		return new ResponseEntity<>(new RoleEntity("1234", "ADMIN"), HttpStatus.OK);
	}

	@PostMapping(path = "/create")
	public ResponseEntity<?> createRole(@RequestBody RoleEntity roleEntity) {
		System.out.println(roleEntity);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------

###### 2_1_Run_App_Test_API_with_OpenApi

<img src="https://img.shields.io/badge/-2.1. Run_App_Test_API_with_OpenApi.  %20-blue" height=40px>

Lets run the app , and browse to the following url:

- http://localhost:8080/v3/api-docs
- http://localhost:8080/swagger-ui.html

this will bring us to  a page where we see all the defaults values. </br>
We can modify these default values , I will show it later on.


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------

###### 

<img src="https://img.shields.io/badge/-X.  %20-blue" height=40px>


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------

###### 

<img src="https://img.shields.io/badge/-X.  %20-blue" height=40px>


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------
