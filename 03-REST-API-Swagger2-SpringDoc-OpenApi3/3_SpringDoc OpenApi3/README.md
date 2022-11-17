<img src="https://img.shields.io/badge/-OpenApi3 (springfox) %20-blue" height=50px>

###### _

|     |  Subject           |
|:---:|:------------------------------| 
|     |[Difference Between Swagger and OpenAPI](#Difference_Between_Swagger_and_OpenAPI)   | 
|     |[SpringDoc or Swagger or OpenAPI](#SpringDoc_or_Swagger_or_OpenAPI)   | 
|  1  |[Spring Boot project](#Spring_Boot_project)  |   
|  2  |[Add Swagger2 to Spring Boot Project](#Add_Swagger2_to_Spring_Boot_Project) | 
|     |2.1. [Add Swagger2 dependency](#add_Swagger2_dependency) |   
|     |2.2. [Config Swagger2](#config_swagger2) |   
|     |2.3. [Run App Test API with Swagger UI](#Run_App_Test_API_with_Swagger_UI) |  
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

###### Spring_Boot_project

<img src="https://img.shields.io/badge/-X.  %20-blue" height=40px>

Create a new Spring-Boot Project and add the follwoing dependencies:

```
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
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
			<groupId>com.mysql</groupId>
			<artifactId>mysql-connector-j</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>
```

Now let's also add the dependdency of OpenAPi 

```
<dependency>
  <groupId>org.springdoc</groupId>
	<artifactId>springdoc-openapi-ui</artifactId>
	<version>1.6.12</version>
</dependency>
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------


