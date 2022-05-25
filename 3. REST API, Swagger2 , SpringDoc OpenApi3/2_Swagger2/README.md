<img src="https://img.shields.io/badge/-Swagger2 (springfox) %20-blue" height=50px>

###### _

|     |  Subject           |
|:---:|:------------------------------| 
|     |[What is Swagger2](#swagger2_introduction)   | 
|  1  |[Spring Boot project](#Spring_Boot_project)  |   
|  2  |[Add Swagger2 to Spring Boot Project](#Add_Swagger2_to_Spring_Boot_Project) | 
|     |2.1. [Add Swagger2 dependency](#add_Swagger2_dependency) |   
|     |2.2. [Config Swagger2](#config_swagger2) |   
|     |2.3. [Run App Test API with Swagger UI](#Run_App_Test_API_with_Swagger_UI) |  
|  2  |[Reconfig Swagger2](#Reconfig_Swagger2)    | 



--------------------------------------------------------------------------------------------------

###### swagger2_introduction

<img src="https://img.shields.io/badge/- What is Swagger %20-blue" height=40px>


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------

###### Spring_Boot_project

<img src="https://img.shields.io/badge/-1. Spring Boot project %20-blue" height=40px>

### [Dependencies](#-)

![image](https://user-images.githubusercontent.com/36256986/170337226-3eec299f-9836-4df3-b312-bf9ad43dd2b7.png)

### [POM](#-)

```sql
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.5.8</version>
		<relativePath /> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.api.test</groupId>
	<artifactId>swagger2-test-api</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>swagger2-test-api</name>
	<description>project Swagger 2 Api Test</description>
	<properties>
		<java.version>1.8</java.version>
	</properties>
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
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>
</project>
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### application_properties

```sql
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

# Enabling H2 Console
spring.h2.console.enabled=true

# Custom H2 Console URL from /h2-console to /h2
spring.h2.console.path=/h2
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------

###### package_layout

<img src="https://img.shields.io/badge/-2.3. package layout   %20-blue" height=40px>

![image](https://user-images.githubusercontent.com/36256986/170337728-3392b5f7-a77b-40ed-88d4-5faa6cf192ed.png)

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------

###### Add_Swagger2_to_Spring_Boot_Project

<img src="https://img.shields.io/badge/-2. Add Swagger2 to Spring Boot Project %20- blue" height=40px>

###### add_Swagger2_dependency

<img src="https://img.shields.io/badge/-2.1. Add Swagger2 dependency %20-yellow" height=35px>

I'll be using the following dependency to add Swagger2. </br>
It includes the ```<artifactId>springfox-swagger2</artifactId>``` and also ```<artifactId>springfox-swagger-ui</artifactId>```

```sql
<!-- https://mvnrepository.com/artifact/io.springfox/springfox-boot-starter -->
<dependency>
	<groupId>io.springfox</groupId>
	<artifactId>springfox-boot-starter</artifactId>
	<version>3.0.0</version>
</dependency>
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------


###### 

<img src="https://img.shields.io/badge/-X.  %20-yellow" height=40px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------

###### 

<img src="https://img.shields.io/badge/-X.  %20-yellow" height=40px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------

###### 

<img src="https://img.shields.io/badge/-X.  %20-yellow" height=40px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------

###### 

<img src="https://img.shields.io/badge/-X.  %20-yellow" height=40px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------
---------------------------------------------------------
---------------------------------------------------------
---------------------------------------------------------
--------------------------------------------------------------------------------------------------

###### 

<img src="https://img.shields.io/badge/-X.  %20-blue" height=40px>


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

###### 

<img src="https://img.shields.io/badge/-X.  %20-blue" height=40px>


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------

###### 

<img src="https://img.shields.io/badge/-X.  %20-blue" height=40px>


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------

###### Customer_Service

<img src="https://img.shields.io/badge/-2. Customer Service  %20-yellow" height=45px>

###### Customer_Dependencies_POM_file

### [Dependencies](#-)

### [POM](#-)

```sql
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------

###### Customer_application_properties

<img src="https://img.shields.io/badge/-2.2. Customer application.properties   %20-blue" height=40px>

```sql
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------

###### Customer_package_layout

<img src="https://img.shields.io/badge/-2.3. Customer package layout   %20-blue" height=40px>

### [Expand pakcages to see Classes in each package](#-)

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------
