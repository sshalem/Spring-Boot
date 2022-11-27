<img src="https://img.shields.io/badge/-AOP  Aspect Oriented Programming %20-blue" height=70px>

###### _

|     | Subject                                              |
| :-: | :--------------------------------------------------- |
|     | [Problem Statement](#Problem_Statement)             |
|     | [AOP Introduction](#AOP_Introduction)               |
|  1  | [Before Advice](#1_Before_Advice)              |
|  2  | [Pointcut Expressions](#2_Pointcut_Expressions)              |
|  3  | [Pointcut Declarations](#3_Pointcut_Declarations)              |
|  4  | [Ordering Aspects](#4_Ordering_Aspects)              |
|  5  | [Join Points](#5_JoinPoints)              |
|  6  | [After Returning Advice](#6_After_Returning_Advice)              |
|  7  | [After Throwing Advice](#7_After_Throwing_Advice)              |


###### Problem_Statement

<img src="https://img.shields.io/badge/- Problem_Statement %20-blue" height=40px>

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

## great explanation from 
1. [javatpoint](https://www.javatpoint.com/spring-boot-aop)  https://www.javatpoint.com/spring-boot-aop
2. https://reflectoring.io/aop-spring/


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

[Benefits of AOP](#-) </br>
* It is implemented in pure Java.
* There is no requirement for a special compilation process.
* It supports only method execution Join points.
* Only run time weaving is available.
* Two types of [AOP proxy](#-) is available: [JDK dynamic proxy](#-) and [CGLIB proxy](#-).


[Additional AOP use cases](#-)
* **Most Common** : Logging,  Security, Transactions
* **Audit logging** : who, what, when , where
* **Exception Handling** : log exception and notify **DevOps** team via SMS / email
* **API management** : 
	* how many times has a method been called a user
	* analytics : what are peak times ? what is average load? who is top user?



[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)


<img src="https://img.shields.io/badge/- AOP Terminology %20-blue" height=30px>

[Aspect](#-) </br>
Aspect is a class in which we define Pointcuts and Advices.

[Pointcut](#-) </br>
A Pointcut is an expression that defines at what JoinPoints a given Advice should be applied. </br>
In Spring Framework, **_AspectJ_** pointcut expression language is used.

[Join point](#-) </br> 
[When to apply code during program execution.](#-) A join point is a point in the application where we apply an **_AOP aspect_** . Or it is a specific execution instance of an advice. In AOP, join point can be a **_method execution, exception handling, changing object variable value,_**  etc.

[Advice](#-) </br>
[What action is taken and when it should be applied](#-). advice is an action that we take either **_before or after_** the method execution. The action is a piece of code that invokes during the program execution. There are five types of advices in the Spring AOP framework: **_before, after, after-returning, after-throwing, and around advice_**. Advices are taken for a particular **_join point_**. We will discuss these advices further in this section.

[Target object](#-) </br>
An object on which advices are applied, is called the **_target object_**. Target objects are always a [**_proxied_**](#-) It means a subclass is created at run time in which the target method is overridden, and advices are included based on their configuration.

[Weaving](#-) </br>
Connecting aspects to target objects to create an advised object. It is a process of **_linking aspects_** with other application types. We can perform weaving at **_run time, load time, and compile time_**.
Regarding performance : run-time Weaving is the slowest

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

<img src="https://img.shields.io/badge/- AOP_Advices_Types %20-blue" height=30px>

There are five types of AOP advices are as follows:
1. [Before Advice](#-) - run before the method . 
2. [After Returning Advice](#-) executes when a method executes [successfully](#-).
3. [After Throwing Advice](#-) executes when a join point throws an exception.
4. [After Advice](#-) / [After finally Advice](#-)  - executes after a join point. This advice is run after the method finishes running, this could be by normally returning or by throwing an exception.
5. [Around Advice](#-) executes before and after of a join point.


<img src="https://img.shields.io/badge/- Spring_AOP_vs_AspectJ %20-blue" height=30px>

Two leading AOP frameworks for JAVA
1. Spring AOP
2. AspectJ

### [Spring AOP Support](#-)

* Spring provides AOP support out of the box
* Spring Actually uses AOP in the background fro : Security , Transactiopns, Caching etc...
	* This is kind of built-in in Spring Framework automatically.
* Spring uses of run-time weaving of aspects, meaning Spring uses a [PROXY pattern](#-) to advice an object. 

![image](https://user-images.githubusercontent.com/36256986/203423020-0a9199e0-50a1-4bfb-a461-4ca8ce73d03b.png)

### [AspectJ](#-)

* Original AOP framework released in 2001
* Provides complete support for AOP
* Rich support for :
	* join points: method-level, constructor, field
	* code weaving : compile-time, post compile time and load-time


### [Spring AOP comparison Advantages/Diasadvanteges](#-)

Advanteges:
* Simpler to use the AspectJ
* Uses Proxy pattern
* Can migrate to AspectJ when using @Aspect annotation

Disadvanteges
* Only supports method-levele join 
* Can only apply aspects to beans created by Spring app context
* Minor performance cost for aspect execution (run time weaving)


### [AspectJ comparison Advantages/Diasadvanteges](#-)

Advanteges:
* Support all join points
* works with any POJO, not just beans from app context
* Faster performance compared to Spring AOP
* Copmplte AOP support

Disadvanteges
* Complie time weaving requires extra compilation step
* AspectJ pointcut syntax can become copmplex

### [AspectJ comparing Spring AOP and AspectJ](#-)

* Spring AOP is a light implementation of AOP
* Spring AOP solves most common problems in enterprise apps
* If we have very complex requirments then move to AspectJ

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

###### 1_Before_Advice

<img src="https://img.shields.io/badge/- 1. Before_Advice %20-blue" height=40px>

Lets see how AOP works, by creating a simple app with a service and controller layers. </br>

1. Add the relevent Dependency
2. Add the annotation needed in the Main App 
3. Create the Aspect class with the relevent advices.

### [step 1](#-)

I create a Spring boot app with following dependencies:

```sql
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-aop</artifactId>
	</dependency>
```

### package layout

![image](https://user-images.githubusercontent.com/36256986/203661711-826c4737-9558-4a12-b727-ccd704b1b72a.png)


### [step 2](#-)

At the main app ad the annotation of `@EnableAspectJAutoProxy`

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
```

### [step 3](#-)

Create Aspect Class in a package of aspect , and add the follolwing code below. </br>

the line below is a [`pointcut expression`](#-). In section 2 I explain on pointcuts.
```java
	@Before(value = "execution(public void com.aop.dao.AccountDao.addAccount())")
//	@Before(value = "execution(* com.aop.dao.AccountDao.addAccount())")
//	@Before(value = "execution(public void addAccount())")
```

```java
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

	@Before(value = "execution(public void com.aop.dao.AccountDao.addAccount())")
//	@Before(value = "execution(* com.aop.dao.AccountDao.addAccount())")
//	@Before(value = "execution(public void addAccount())")
//	@Before(value = "execution(void addAccount())")
//	@Before(value = "execution(public void add*())")
//	@Before(value = "execution(public * add*())")
//	@Before(value = "execution(* add*())")
	public void beforeAddAccountAdvice() {
		System.out.println("In Aspect");
	}
}
```

### [step 4](#-)

Create package of dao , and add the follolwing code:

```java
@Service
public class AccountDao {

	public void addAccount() {
		System.out.println("In AccountDao");
	}
}
```

### [step 5](#-)

Create package of controller , and add the follolwing code:

```java
@RestController
@RequestMapping("/aop")
public class AccountController {

	@Autowired
	private AccountDao accountDao;

	@PostMapping
	public void addAccount() {
		accountDao.addAccount();
	}
}
```

### [step 6](#-)

Let's test the app . Sent a Http Request to the url of localhost:8080/aop

Spring console shows as expected:

![image](https://user-images.githubusercontent.com/36256986/203662157-8a22575b-2aca-413c-90de-5093e3ac87b1.png)

When Sending Http Request , before our Target Object (**courseDao.getCourseName()**)  is invoked , </br>
the AOP Proxy (CGlib) is executed on the **Target Object**.

![image](https://user-images.githubusercontent.com/36256986/203424003-e3d3cac0-45e5-4b69-8f5c-e29b2e1aa8a3.png)

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

###### 2_Pointcut_Expressions

<img src="https://img.shields.io/badge/- 2.Pointcut_Expressions %20-blue" height=40px>

* [Pointcut](#-) : A predicate expression for for where advice should be applied. 
* Spring AOP uses AspectJ pointcut expression Language. 
* There are a lot of different pointcuts.
* In previous section I use the `execution` pointcut.

### [Match on Method names](#-)

[_execution( ModifiersPattern  **returnTypePattern**  DeclaringTypePattern  **MethodNamePattern(param-pattern)**  ThrowsPattern)_](#-)


**Modifiers-pattern** : (Optional) Spring AOP only supports public </br>
**return-type-pattern** : void, boolean ,String,List<Customer>, ... </br>
**declaring-type-pattern** : (Optional) the class name </br>
**method-name-pattern(param-pattern)** : Method name to match, also parameter types to match </br>
**throws-pattern** : (Optional) Exception types to match </br>

Let's look in the pointcut below, and break it down to pieces:

_public_ 		: Modifier
_void_   		: return type
_com.aop.dao.CourseDao_ : Declaring type
_getCourseName()_ 	: Method

```
@Before(value = "execution(public void com.aop.dao.CourseDao.addCourseName())")
```

The line below is same as above (W/o Declaring type & throws-pattern).
But this Aspect code will be executed on any class that has the `getCourseName()` method

```
@Before(value = "execution(public void addAccount())")
```

### [Match on Method names using wildcards](#-)

* Match methods **starting** with **add** in any class

```
@Before(value = "execution(public void add*())")
```

```java
	@Before(value = "execution(public void com.aop.dao.AccountDao.addAccount())")
	@Before(value = "execution(* com.aop.dao.AccountDao.addAccount())")
	@Before(value = "execution(public void addAccount())")
	@Before(value = "execution(void addAccount())")
	@Before(value = "execution(public void add*())")
	@Before(value = "execution(public * add*())")
	@Before(value = "execution(* add*())")
```

### [Match on Method return Type using wildcards](#-)

```java
	@Before(value = "execution(public * add*())")
	@Before(value = "execution(* add*())")
```

### [Match Method Parameter Types wildcards](#-)

For Param-Patterns:
1. ()   - matches a methods w/n args
2. (* ) - matches a method with one arg of any type
3. (..) - matches a method with 0 ore more args of any type

### I add the full qualified name of the Parameter [`com.aop.entity.BookEntity`](#-)

```java
	@Before(value = "execution(public void com.aop.dao.AccountDao.addBook(com.aop.entity.BookEntity))")
	@Before(value = "execution(public void com.aop.dao.*.*(*))")
	@Before(value = "execution(public void com.aop.dao.*.*(..))")
	@Before(value = "execution(public void addBook(com.aop.entity.BookEntity))")
	@Before(value = "execution(* addBook(com.aop.entity.BookEntity))")	
	@Before(value = "execution(* addBook(*))")
	@Before(value = "execution(* addBook(..))")
```

### [Match with wildcards returntype, Package, Params, Class, Method Types wildcards](#-)

The picture below shows how we can match with wildcards any of the following:
1. returntype
2. Package
3. Params
4. Class
5. Method 

![image](https://user-images.githubusercontent.com/36256986/203861380-099fb4ef-429d-4e38-a9cd-e30ddd3297dd.png)


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---


###### 3_Pointcut_Declarations

<img src="https://img.shields.io/badge/- 3.Pointcut_Declarations %20-blue" height=40px>

<img src="https://img.shields.io/badge/- 3.1. Pointcut to Multiple Advices %20-yellow" height=30px>

Problem:
* How can we reuse a pointcut expression?
* Want to apply to multiple Advices

Ideal Solution:
1. create a pointcut declaration once
2. Apply it to multiple advices

In the code below , when we run the app, when we sent a Http Request that uses a method from the class ,
then both @Before advices will run since they have the same @Pointcut declaration

```java
	/**
	 * @Pointcut - Pointcut declarations , adding the pointcut expression in it 
	 * @Before - add the Pointcut declaration , as an expression 'forDaoPackage' in the Advice  
	 */
	@Pointcut(value = "execution(* com.aop.dao.*.*(..))")
	private void forDaoPackage() {
		
	}
	
	@Before(value = "forDaoPackage()")
	public void beforeAdd() {
		System.out.println("Exceuting the @Before Advice - beforeAdd");
	}
	
	@Before(value = "forDaoPackage()")
	public void beforePerformApiAnalytics() {
		System.out.println("Exceuting the @Before Advice - beforePerformApiAnalytics");
	}
```

### [Test App](#-)

Run project `02-pointcut-to-multiple-advices` , sent Http Request for addAccount </br>
Console shows both Advices run for before method addAccount()

```
Exceuting the @Before Advice - beforeAdd
Exceuting the @Before Advice - beforePerformApiAnalytics
class com.aop.dao.AccountDao add account
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

<img src="https://img.shields.io/badge/- 3.1. Multiple Pointcuts to Single Advice %20-yellow" height=30px>

Problem:
* How to apply multiple pointcut expressions to single advice?
* Execute an advice only if vertain conditions are met
* FOr example : apply an advice to all methods in a package EXCEPT getter/setter methods

Solution:
1. Combining Pointcut expressions using logic operators : AND (&&) , OR(||) , NOT(!)
2. When we combine it works like an "if" statement

Let's look in the example :

Modified the AccountDao class by adding getter and setter methods:

```java
@Service
public class AccountDao {

	private String name;
	private String serviceCode;

	public void addAccount() {
		System.out.println(getClass() + " add account");
	}

	public void addBook(BookEntity bookEntity) {
		System.out.println(getClass() + " add Book");
	}

	public String getName() {
		System.out.println(getClass() + " getName()");
		return name;
	}

	public void setName(String name) {
		System.out.println(getClass() + " setName()");
		this.name = name;
	}

	public String getServiceCode() {
		System.out.println(getClass() + " getServiceCode()");
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		System.out.println(getClass() + " setServiceCode()");
		this.serviceCode = serviceCode;
	}
}
```

### [Aspect class](#-)

```java
	/**
	 * Example for Combining Pointcuts
	 */
	@Pointcut(value = "execution(* com.aop.dao.*.*(..))")
	private void forDaoPackage() {
	}

	@Pointcut(value = "execution(* com.aop.dao.*.get*(..))")
	private void getter() {
	}

	@Pointcut(value = "execution(* com.aop.dao.*.set*(..))")
	private void setter() {
	}

	// Combine the pointcuts : include package ... exclude getter/setter
	@Pointcut(value = "forDaoPackage() && !(getter() || setter())")
	private void forDaoPackageNoGetterSetter() {
	}

	@Before(value = "forDaoPackageNoGetterSetter()")
	public void beforeAdd() {
		System.out.println("Exceuting the @Before Advice - beforeAdd");
	}

	@Before(value = "forDaoPackageNoGetterSetter()")
	public void beforePerformApiAnalytics() {
		System.out.println("Exceuting the @Before Advice - beforePerformApiAnalytics");
	}
```

### [Test code](#-)

run project `03-multiple-pointcuts-to-single-advice` </br>
Let run the following code for testing which invoke the getter / setter and the addAccount methods:

```java
accountDao.setName("test");
accountDao.setServiceCode("123");		
accountDao.getName();
accountDao.getServiceCode();	

accountDao.addAccount();
```


Console shows the following result : </br>
* the Advice run only for the addAccount() method
* Advice didn't run for the gettres/setters

```
class com.aop.dao.AccountDao setName()
class com.aop.dao.AccountDao setServiceCode()
class com.aop.dao.AccountDao getName()
class com.aop.dao.AccountDao getServiceCode()
Exceuting the @Before Advice - beforeAdd
Exceuting the @Before Advice - beforePerformApiAnalytics
class com.aop.dao.AccountDao add account
```


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

###### 4_Ordering_Aspects

<img src="https://img.shields.io/badge/- 4. Ordering_Aspects %20-blue" height=40px>

Problem:
* How to control the order of advices being applied?
* The order is undefined, so Spring will take any Advice thtey have and run it

To Control the Order:
* Refactor : place advices in seperate Aspects class
* add `@Order` annotationto Aspects
* 

<img src="https://img.shields.io/badge/- 4.1. Code before Refactoring %20-yellow" height=30px>

```java
@Aspect
@Component
public class LoggingAspect {

	/**
	 * Example for Combining Pointcuts
	 */
	@Pointcut(value = "execution(* com.aop.dao.*.*(..))")
	private void forDaoPackage() {
	}

	@Pointcut(value = "execution(* com.aop.dao.*.get*(..))")
	private void getter() {
	}

	@Pointcut(value = "execution(* com.aop.dao.*.set*(..))")
	private void setter() {
	}

	/**
	 * Combine the pointcuts : include package ... exclude getter/setter
	 */
	@Pointcut(value = "forDaoPackage() && !(getter() || setter())")
	private void forDaoPackageNoGetterSetter() {
	}

	@Before(value = "forDaoPackageNoGetterSetter()")
	public void beforeAdd() {
		System.out.println("Exceuting the @Before Advice - beforeAdd");
	}

	@Before(value = "forDaoPackageNoGetterSetter()")
	public void beforeApiAnalytics() {
		System.out.println("Exceuting the @Before Advice - beforeApiAnalytics");
	}

	@Before(value = "forDaoPackageNoGetterSetter()")
	public void beforeLogToCloudAsync() {
		System.out.println("Exceuting the @Before Advice - beforeLogToCloudAsync");
	}
}
```

### [Test App before refactor](#-)

Console shows that the order that the advices run is not consequative. (Its random)

```
class com.aop.dao.AccountDao setName()
class com.aop.dao.AccountDao setServiceCode()
class com.aop.dao.AccountDao getName()
class com.aop.dao.AccountDao getServiceCode()
Exceuting the @Before Advice - beforeAdd
Exceuting the @Before Advice - LogToCloudAsync
Exceuting the @Before Advice - ApiAnalytics
class com.aop.dao.AccountDao add account
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

<img src="https://img.shields.io/badge/- 4.1. Code After Refactoring %20-yellow" height=30px>

In order to overcome this, what we do is create a seperate Aspect class for each Advice, </br>
and add the `@Order` annotation to each class.

In addition I created a new class for `PointcutDeclarations`

```java
@Aspect
@Component
@Order(1)
public class CloudLogAspect {

	@Before(value = "com.aop.aspect.PointcutDeclarations.forDaoPackageNoGetterSetter()")
	public void beforeLogToCloudAsync() {
		System.out.println("Exceuting the @Before Advice - LogToCloudAsync");
	}
}

@Aspect
@Component
@Order(2)
public class LoggingAspect {

	@Before(value = "com.aop.aspect.PointcutDeclarations.forDaoPackageNoGetterSetter()")
	public void beforeAdd() {
		System.out.println("Exceuting the @Before Advice - beforeAdd");
	}
}

@Aspect
@Component
@Order(3)
public class ApiAnalyticsAspect {

	@Before(value = "com.aop.aspect.PointcutDeclarations.forDaoPackageNoGetterSetter()")
	public void beforeApiAnalytics() {
		System.out.println("Exceuting the @Before Advice - ApiAnalytics");
	}
}
```

```java
@Aspect
public class PointcutDeclarations {

	/**
	 * Example for Combining Pointcuts
	 */
	@Pointcut(value = "execution(* com.aop.dao.*.*(..))")
	public void forDaoPackage() {
	}

	@Pointcut(value = "execution(* com.aop.dao.*.get*(..))")
	public void getter() {
	}

	@Pointcut(value = "execution(* com.aop.dao.*.set*(..))")
	public void setter() {
	}

	/**
	 * Combine the pointcuts : include package ... exclude getter/setter
	 */
	@Pointcut(value = "forDaoPackage() && !(getter() || setter())")
	public void forDaoPackageNoGetterSetter() {
	}
}
```

### [Test App After code refactor](#-)

Console shows that the order of the advices is the way we define it.

```
class com.aop.dao.AccountDao setName()
class com.aop.dao.AccountDao setServiceCode()
class com.aop.dao.AccountDao getName()
class com.aop.dao.AccountDao getServiceCode()
Exceuting the @Before Advice - LogToCloudAsync
Exceuting the @Before Advice - beforeAdd
Exceuting the @Before Advice - ApiAnalytics
class com.aop.dao.AccountDao add account
```


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)
---

###### 5_JoinPoints 

<img src="https://img.shields.io/badge/- 5. Join Points %20-blue" height=40px>

### Problem:
* We we are in an Aspect (for example Loggin Aspect) , how can we access method parameters?

![image](https://user-images.githubusercontent.com/36256986/204105149-8f8e380c-9358-4d4c-9208-7706ea9bc309.png)


### Answer:
* With `JoinPoints` we can read method arguments. </br>
* Access and display Method Signature
* Access and display Method Arguments.

`JoinPoint` has the metadata about method call

Let's look in the following code example:

### [Aspect](#-)

```java
@Aspect
@Component
public class LoggingAspect {

	/**
	 * @Pointcut - Pointcut declarations , adding the pointcut expression in it
	 * @Before - add the Pointcut declaration , as an expression 'forDaoPackage' in
	 *         the Advice
	 */
	@Pointcut(value = "execution(* com.aop.dao.*.*(..))")
	private void forDaoPackage() {

	}

	@Before(value = "forDaoPackage()")
	public void beforeAddBook(JoinPoint joinPoint) {
		System.out.println("Exceuting the @Before Advice - beforeAddBook");

		/**
		 * Access and Display the method signature
		 */
		System.out.println(" \n Access and Display the method signature ");
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

		System.out.println("Method modifiers: " + methodSignature.getModifiers());
		System.out.println("Method name: " + methodSignature.getName());
		System.out.println("Method declaringType: " + methodSignature.getDeclaringType());
		System.out.println("Method method: " + methodSignature.getMethod());
		System.out.println("Method returnType: " + methodSignature.getReturnType());

		/**
		 * Access and Display the method Arguments
		 */
		
		System.out.println(" \n Access and Display the method Arguments ");
		Object[] args = joinPoint.getArgs();
		for (Object obj : args) {
			System.out.println(obj);

			if (obj instanceof BookEntity) {
				BookEntity bookEntity = (BookEntity) obj;
				System.out.println(bookEntity.getName());
				System.out.println(bookEntity.getAuthor());
			}
		}
	}
}
```

### [AccountDao class](#-)

```java
@Service
public class AccountDao {

	public void addAccount() {
		System.out.println(getClass() + " add account");
	}

	public void addBook(BookEntity bookEntity) {
		System.out.println(getClass() + " add Book");
	}
}
```

### [BookEntity class](#-)

```java
public class BookEntity {

	private String name;
	private String author;

	public BookEntity() {
		super();
	}

	public BookEntity(String name, String author) {
		super();
		this.name = name;
		this.author = author;
	}
	
	G/S/ToString
}
```

### [Test the app](#-)

Run project 05-join-points and sent a POST request via POstman to url of `localhost:8080/aop/book` </br>
console shows the following :

```
Exceuting the @Before Advice - beforeAddBook
 
 Access and Display the method signature 
Method modifiers: 1
Method name: addBook
Method declaringType: class com.aop.dao.AccountDao
Method method: public void com.aop.dao.AccountDao.addBook(com.aop.entity.BookEntity)
Method returnType: void
 
 Access and Display the method Arguments 
BookEntity [name=harrie potter, author=J.K.Roling]
harrie potter
J.K.Roling
class com.aop.dao.AccountDao add Book
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

###### 6_After_Returning_Advice

<img src="https://img.shields.io/badge/- 6. After_Returning_Advice %20-blue" height=40px>

`@AfterReturning` is an advice type, which ensures that an advice runs after the method executes [successfully](#-). </br>
We can also do Post Processing data after the execution of the method, meaning we can modify the data, But not to add someting new.
Pay attention , with `@AfterReturning` method , we must retun void.

Let's look in the following code example , where I return a List<AccountEntity>:

### [Aspect](#-)

```java
@Aspect
@Component
public class LoggingAspect {

	/**
	 * @Pointcut - Pointcut declarations , adding the pointcut expression in it
	 * @Before - add the Pointcut declaration , as an expression 'forDaoPackage' in
	 *         the Advice
	 */
	@Pointcut(value = "execution(* com.aop.dao.*.*(..))")
	private void forDaoPackage() {

	}

	@Before(value = "forDaoPackage()")
	public void beforeAddBook(JoinPoint joinPoint) {
		System.out.println("Exceuting the @Before Advice - beforeAddAccount");
	}

	@AfterReturning(pointcut = "forDaoPackage()", returning = "result")
	public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<AccountEntity> result) {

		System.out.println(" \nafterReturningFindAccountsAdvice ");

		String method = joinPoint.getSignature().toShortString();
		System.out.println(method);

		/**
		 * Post Processing data
		 * We cannot Add or remove form the List , but we can modify the data in it
		 */

		result.forEach(res -> res.setLevel("UnKnown"));

	}
}
```

### [AccountDao class](#-)

```java
@Service
public class AccountDao {

	public void addAccount(AccountEntity accountEntity) {
		System.out.println(getClass() + " add Account");
	}

	public List<AccountEntity> findAccounts() {

		List<AccountEntity> accounts = Arrays.asList(
				new AccountEntity("Home", "secret"),
				new AccountEntity("School", "Top"), 
				new AccountEntity("Office", "classified"));		
		return accounts;
	}
}
```

### [Test the app](#-)

Run project `06-After-Returning-Advice` and sent a GET request via POstman to url of `localhost:8080/aop/findAccounts` </br>
console shows the following , the data returned is after it is modified in the `@AfterReturning` Advice method:

```
[AccountEntity [name=Home, level=UnKnown], AccountEntity [name=School, level=UnKnown], AccountEntity [name=Office, level=UnKnown]]
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

###### 7_After_Throwing_Advice

<img src="https://img.shields.io/badge/- 7. After_Throwing_Advice %20-blue" height=40px>

	
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
