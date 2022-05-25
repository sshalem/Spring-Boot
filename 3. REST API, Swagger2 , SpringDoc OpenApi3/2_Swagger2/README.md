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
|  3  |[Customize Swagger2 configuration](#Customize_Swagger2_configuration)    | 
|     |3.1. [Run App Test API with Swagger UI reconfig](#Run_App_Test_API_with_Swagger_UI_reconfig) |  



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

###### config_swagger2

<img src="https://img.shields.io/badge/-2.2. config_swagger2  %20-yellow" height=35px>

In order to config Swagger2 with Spring boot we need to do the following:
* create config package
* Create a config class for swagger2 with annotation of ```@Configuration```
* It seems that with dependency of ```<artifactId>springfox-boot-starter</artifactId>``` we don't have to add annotation of ```@EnableSwagger2```
But I still add it.

### [Package Layout](#-)

![image](https://user-images.githubusercontent.com/36256986/170343821-2272b8b0-5171-4ba8-a440-204f74eef25f.png)

```java
package com.swagger2.config;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

} 
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------

###### Run_App_Test_API_with_Swagger_UI

<img src="https://img.shields.io/badge/-2.3. Run App Test API with SwaggerUI %20-yellow" height=35px>

1. Let's run the app.
2. Browse to url where th UI of swagger is displayed [localhost:8080/swagger-ui/](#-)

We can see in Swagger UI several things:
1. Our Controllers of Author and Book are shown. Click on them to see the API we have in the Controller
2. 'Select a Defenition' - Default , we can have several defenitions 
3. 'Servers' - inferred Url
4. Schemas (Or Models)

![image](https://user-images.githubusercontent.com/36256986/170345871-ddac3f63-f595-4f91-8c79-fd6c4bca5252.png)

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------

###### Customize_Swagger2_configuration

<img src="https://img.shields.io/badge/-3. Customize Swagger2 configuration  %20-blue" height=40px>

```java
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("shabtay")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.swagger2.controller")) 
				.paths(PathSelectors.ant("/project/**"))				
				.build()
				.useDefaultResponseMessages(false)
				.apiInfo(apiShabtayDetails());
	}

	@Bean
	public Docket apiKarin() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("karin")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.swagger2.controller")) 
				.paths(PathSelectors.ant("/project/**"))				
				.build()
				.useDefaultResponseMessages(false)
				.apiInfo(apiKarinDetails());
	}
	
	@Bean
	public Docket apiOdel() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("odel")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.swagger2.controller")) 
				.paths(PathSelectors.ant("/project/**"))				
				.build()
				.useDefaultResponseMessages(false)
				.apiInfo(apiOdelDetails());
	}
	
	private ApiInfo apiShabtayDetails() { 
		return new ApiInfo(
				"Project Author/Book", 
				"Documentation API", 
				"V1.0", 
				"Free to use",
				new Contact("shabtay shalem", "url - NA", "shabtay.shalem@gmail.com"), 
				"API licesnse",
				"license URL",				
				Collections.emptyList());		
	}
	
	private ApiInfo apiKarinDetails() { 
		return new ApiInfoBuilder()
				.title("Project Author/Book")
				.description("Documentation API")
				.version("V1.0")
				.termsOfServiceUrl("free to use")
				.contact(new Contact("Karin shalem", "url - NA", "karin.shalem@gmail.com"))
				.license("API licesnse")
				.licenseUrl("license URL")
				.extensions(Collections.emptyList())
				.build();
	}
	
	private ApiInfo apiOdelDetails() { 
		return new ApiInfoBuilder()
				.title("Project Author/Book")
				.description("Documentation API")
				.version("V1.0")
				.termsOfServiceUrl("free to use")
				.contact(new Contact("Odel shalem", "url - NA", "Odel.shalem@gmail.com"))
				.license("API licesnse")
				.licenseUrl("license URL")
				.extensions(Collections.emptyList())
				.build();
	}
}
```


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------

###### Run_App_Test_API_with_Swagger_UI_reconfig

<img src="https://img.shields.io/badge/-3.1. Run App Test API with Swagger UI reconfig  %20-blue" height=40px>

We can see , we can select from 3 different definitions.</br>
Error Controller is not shown.</br>

![image](https://user-images.githubusercontent.com/36256986/170362385-f1d24dd9-2fce-41af-bf4f-eca1fbc1086c.png)

Since we define ```useDefaultResponseMessages(false)``` thus we see response only for 200.

![image](https://user-images.githubusercontent.com/36256986/170362710-3ca995f5-f9ad-40c2-9d7d-6ea07b748f01.png)

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


