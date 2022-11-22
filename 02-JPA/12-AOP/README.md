<img src="https://img.shields.io/badge/-AOP  Aspect Oriented Programming %20-blue" height=70px>

###### _

|     | Subject                                              |
| :-: | :--------------------------------------------------- |
|     | [Problem Statement](#Problem_Statement)             |
|     | [AOP Introduction](#AOP_Introduction)               |
|     | [AOP Terminology](#AOP_Terminology)                 |
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

## great explanation from [javatpoint](https://www.javatpoint.com/spring-boot-aop)  https://www.javatpoint.com/spring-boot-aop

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

- programming technique based on concept of an Aspect
- Aspect encapsulates `cross-cutting logic` (sometimes Cross-cutting concerns). "Concern" means logic/functionality
- Aspect can be reused at multiple locations
- Same aspect/class ... applied based on configuration

## great explanation from [javatpoint](https://www.javatpoint.com/spring-boot-aop)  https://www.javatpoint.com/spring-boot-aop

[`AOP - Aspect Oriented Programming`](#-) </br>
is a programming pattern that increases modularity by allowing the separation of the **cross-cutting concern**. </br>
These **cross-cutting concerns** are different from the main business logic. </br>
We can add additional behavior to existing code without modification of the code itself. </br>

Spring's **AOP** framework helps us to implement these cross-cutting concerns.

Using **AOP**, we define common functionality in one place. </br>
We are free to define how and where this functionality is applied without modifying the class to which we are applying the new feature. </br>
The **cross-cutting concern** can now be modularized into special classes, called **aspect**.</br>

[There are two benefits of aspects:](#-)
1. First, the logic for each concern is now in one place instead of scattered all over the codebase.
2. Second, the business modules only contain code for their primary concern. The secondary concern has been moved to the aspect.
The aspects have the responsibility that is to be implemented, called advice. We can implement an aspect's functionality into a program at one or more join points.

[Benefits of AOP](#-) </br>
* It is implemented in pure Java.
* There is no requirement for a special compilation process.
* It supports only method execution Join points.
* Only run time weaving is available.
* Two types of [AOP proxy](#-) is available: [JDK dynamic proxy](#-) and [CGLIB proxy](#-).

1. Code for Aspect is defined in a singlae class
2. Promotes code reuse and easier change
3. Business code in app is cleaner (Apply to business funcionality like addAccount)
4. Reduce code complexity
5. Based on configuration 
6. Apply Aspects selectively do different parts of app
7. No need to make changes to main application code 

[Additional AOP use cases](#-)
* **Most Common** : Logging,  Security, Transactions
* **Audit logging** : who, what, when , where
* **Exception Handling** : log exception and notify **DevOps** team via SMS / email
* **API management** : 
	* how many times has a method been called a user
	* analytics : what are peak times ? what is average load? who is top user?



[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

###### AOP_Terminology

<img src="https://img.shields.io/badge/- AOP Terminology %20-blue" height=40px>

[Aspect](#-) </br>
An aspect is a module that encapsulates **_advice_** and **_pointcuts_** and provides **_cross-cutting_** An application can have any number of aspects. We can implement an aspect using regular class annotated with **_@Aspect annotation._** </br>

[Pointcut](#-) </br>
A pointcut is an expression that selects one or more join points where advice is executed. We can define pointcuts using **_expressions_** or **_patterns_**. It uses different kinds of expressions that matched with the join points. In Spring Framework, **_AspectJ_** pointcut expression language is used.

[Join point](#-) </br>
A join point is a point in the application where we apply an AOP aspect. Or it is a specific execution instance of an advice. In AOP, join point can be a method execution, exception handling, changing object variable value, etc.

[Advice](#-) </br>
The advice is an action that we take either before or after the method execution. The action is a piece of code that invokes during the program execution. There are five types of advices in the Spring AOP framework: **_before, after, after-returning, after-throwing, and around advice_**. Advices are taken for a particular join point. We will discuss these advices further in this section.

[Target object](#-) </br>
An object on which advices are applied, is called the target object. Target objects are always a proxied It means a subclass is created at run time in which the target method is overridden, and advices are included based on their configuration.

[Weaving](#-) </br>
It is a process of linking aspects with other application types. We can perform weaving at run time, load time, and compile time.

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
