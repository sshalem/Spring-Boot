<img src="https://img.shields.io/badge/-AOP  Aspect Oriented Programming %20-blue" height=70px>

###### _

|     | Subject                                              |
| :-: | :--------------------------------------------------- |
|     | [Problem Statement](#Problem_Statement)             |
|     | [AOP Introduction](#AOP_Introduction)            |
|  1  | [One2Many_Bi_Lazy](#2_One2Many_Bi_Lazy)              |


###### Problem_Statement

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

Before I dif into AOP let's first describe what issue it came to solve. </br>

Suppose we have a code the following code:

```java
	@Override
	public UserEntity createUser(UserEntity userEntity) {		
		UserEntity _userEntity = userRepository.save(userEntity);		
		return _userEntity;
	}	
```
Requests that start to come:
* The manager wants to `add code for logging` 
* After few hours , the manager wants to `add code for logging` , to all `DAO layer`
* The next day , the manager wants to `add code for logging` for all controller and service layer 
* Also to `add code for security` to all layers
* Now it's late ,4pm and better to cpy paste (But is it a good solution)

this creates 2 main problems:
1. code Tangling
2. code Scattering - change and update all classes

This is where [`AOP - Aspect Oriented Programming`](#-) comes into place 

## great explanation from [javatpoint](https://www.javatpoint.com/spring-boot-aop)

The application is generally developed with multiple layers. A typical Java application has the following layers:
* [Web Layer:](#-) It exposes the services using the REST or web application.
* [Business Layer:](#-) It implements the business logic of an application.
* [Data Layer:](#-) It implements the persistence logic of the application.

The responsibility of each layer is different, but there are a few common aspects that apply to all layers are **Logging, Security, validation, caching, etc.** </br>
These common aspects are called **cross-cutting concerns.**  </br>

If we implement these concerns in each layer separately, the code becomes more **difficult to maintain**. </br>
To overcome this problem, **Aspect-Oriented Programming (AOP)** provides a solution to implement cross-cutting concerns.

1. Implement the cross-cutting concern as an aspect.
2. Define pointcuts to indicate where the aspect has to be applied.

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

###### AOP_Introduction

<img src="https://img.shields.io/badge/- AOP_Introduction %20-blue" height=40px>

#### [`AOP - Aspect Oriented Programming`](#-)
1. programming technique based on concept of an Aspect
2. Aspect encapsulates `cross-cutting logic` (sometimes Cross-cutting concerns). "Concern" means logic/functionality
3. Aspect can be reused at multiple locations
4. Same aspect/class ... applied based on configuration


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

######

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

######

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

######

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

######

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---
