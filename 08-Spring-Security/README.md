<img src="https://img.shields.io/badge/-Spring Security%20-brightgreen" height=70px>

###### _

|     |  Subject           |
|:---:|:------------------------------| 
|     |[Links](#Links)   | 
|     |--- [Basic Security Flow Diagram](#Basic_Security_Flow_Diagram)   | 
|     |--- [Security with Web Application](#Security_with_Web_Application)   | 
|     |--- [Security Filters](#Security_Filters)   | 
|     |--- [Most Common way to Authenticate](#Most_Common_way_to_Authenticate)   | 
|  1  |[Basic Authentication](https://github.com/sshalem/spring-security/tree/main/01_BasicAuth)   | 
|  2  |[Form Login](https://github.com/sshalem/spring-security/tree/main/02_FormLogin)  |   
|  3  |[JWT](https://github.com/sshalem/spring-security/tree/main/03_JWT)  |   
|  4  |[OAuth](https://github.com/sshalem/spring-security/tree/main/04_OAuth)  |   


---------------------------------------------------------------------------------------------

###### Links

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

Videos:
1. [YouTube - Architecture Deep Dive in Spring Security](https://www.youtube.com/watch?v=AdsnM6OTepc&ab_channel=SpringDeveloper)
2. [YouTube - Architecture Deep Dive in Spring Security - Joe Grandja @ Spring I/O 2017](https://www.youtube.com/watch?v=8rnOsF3RVQc&ab_channel=SpringI%2FO)
3. [YouTube - Spring security filter chain explained | Architecture](https://www.youtube.com/watch?v=b-MbhgPmHjE&ab_channel=stackfortech)
4. [YouTube - What are spring security filters?](https://www.youtube.com/watch?v=dJ1ORGD22eM&ab_channel=JavaDevelopmentJournal)
5. [YouTube - Filter Chain](https://www.youtube.com/watch?v=FZ5kB7rV_xg&ab_channel=PacktVideo)

Articles:
1. [Spring Security Architecture](https://spring.io/guides/topicals/spring-security-architecture)
2. [how-spring-security-filter-chain-works](https://stackoverflow.com/questions/41480102/how-spring-security-filter-chain-works)
3. [Spring Security Filters Chain](https://www.javadevjournal.com/spring-security/spring-security-filters/)
4. [Security Filters Chain](https://www.marcobehler.com/guides/spring-security)




[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------

###### Basic_Security_Flow_Diagram

<img src="https://img.shields.io/badge/- Basic_Security_Flow_Diagram %20-blue" height=40px>

### [Basic Flow diagram (high level explained)](#-)

Spring Security is installed as a single Filter in the chain, and its concrete type is [`FilterChainProxy`](#-) , covered later (See articles and videos). </br>
In a Spring Boot app, the security filter is a [`@Bean in the ApplicationContext`](#-), and installed by default so that it is applied to every request. </br>
It is installed at a position defined by : 
* [`SecurityProperties.DEFAULT_FILTER_ORDER`](#-) 

which in turn is anchored by [`FilterRegistrationBean.REQUEST_WRAPPER_FILTER_MAX_ORDER`](#-) . </br>
(the maximum order that a Spring Boot application expects filters to have if they wrap the request, modifying its behavior). 

There is more to it than that, though: </br>
* From the point of view of the `container`, Spring Security is a single filter
* `BUT`, inside of it, there are additional filters, each playing a special role. 

![image](https://user-images.githubusercontent.com/36256986/212496180-31ba8d06-6d51-4d3c-bb49-4ba4b1234683.png)

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------

###### Security_with_Web_Application

<img src="https://img.shields.io/badge/- Security_Filters_FilterChainProxy %20-blue" height=40px>

Let's create a simple Spring boot app with following sependencies:

![image](https://user-images.githubusercontent.com/36256986/212496709-2b1ecf41-85c1-4ca3-ab76-f1b243eded63.png)

Let's take a deeper look on the Filter chain of Spring Security
1. I've created a simple app with only one controller 

```java
@RestController
@RequestMapping("/home")
public class HomeController {
 
	@GetMapping
	public String home() {
		return "hello home";
	}
}
```

In the Application.properties file I added the following line to display the log:

```sql
logging.level.org.springframework.security.web.FilterChainProxy=TRACE
```

* I didn't config the security for the app , thus by default it will give a From Login authentication.

### [Explanation](#-)

Spring Security is installed as a single Filter in the chain, and its concrete type is
* [`FilterChainProxy`](#-) , covered later (See articles and videos). </br>

In a Spring Boot app, the security filter is a [`@Bean in the ApplicationContext`](#-), and installed by default so that it is applied to every request. </br>
It is installed at a position defined by : 
* [`SecurityProperties.DEFAULT_FILTER_ORDER`](#-) 

which in turn is anchored by [`FilterRegistrationBean.REQUEST_WRAPPER_FILTER_MAX_ORDER`](#-) . </br>
(the maximum order that a Spring Boot application expects filters to have if they wrap the request, modifying its behavior). 

There is more to it than that, though: </br>
* From the point of view of the `container`, Spring Security is a single filter
* `BUT`, inside of it, there are additional filters, each playing a special role. 

The following image shows this relationship:

<p align="center">
	<img src="https://user-images.githubusercontent.com/36256986/212496959-4e414dfa-28b7-49ea-992a-d99eb0904d9f.png" width=500px height=300px />
</p>


### [Run App and Check Logging](#-)

Let's run the APP and browse to localhost:8080 , and check the console .

These filters automatically configured by Spring security and executing on each incoming Request. </br>
There are few important points to remember:
1. Filters are executing in a specific order (Look at the number). The filters can change based on how we configure the modules.:  </br>
	a. [`Basic Auth`](#-)  </br>
	b. [`Form Login (Custom Login page)`](#-)  </br>
	c. [`JWT (stateless session)`](#-)  </br>

2. Each incoming request will go through all these filters (total 15 in our case) following a specific order.

![image](https://user-images.githubusercontent.com/36256986/212497278-fd4fabd3-6c55-435e-9c9f-595a347fdb38.png)

## [`Security Web Application`](#-)

For a web application using Spring security, all incoming HttpServletRequest goes through the `spring security filters chain` before it reaches to the Spring MVC controller.

Links:
1. [spring-security-filters](https://www.javadevjournal.com/spring-security/spring-security-filters/)
2. [YouTube - video](https://www.youtube.com/watch?v=EeXFwR21J1A&ab_channel=LaurentiuSpilca)

### [How Security Filters Works](#-)

We got the basics about the Spring security and it’s workflow. </br>
Spring security performs most of its core logic using servlet filters, and it’s very important that we understand the workflow. </br>
This can help us debug or customize the security behaviour. Let’s look at the entire filters stack closely:


<p align="center">
	<img src="https://user-images.githubusercontent.com/36256986/212498150-e270ec30-1036-4dd6-a563-e17aed10e1d1.png" />
</p>

### [DelegatingFilterProxy](#-)

The [`DelegatingFilterProxy is a filter`](#-) which works as a bridge between Servlet container’s life-cycle and Spring’s Application Context. </br>
Servlet container does not have any information about the Spring’s application context, but spring security needs security filters to execute the task.. </br>
* Since DelegatingFilterProxy is a `servlet filter`, the application server register it as a normal filter in the context. </br>
* The DelegatingFilterProxy as name suggests, delegates the work to the spring bean to start the security flow.

### [FilterChainProxy](#-)

This filter contains all the details about the different security filters available through the security filter chain. </br>
Let’s understand few important points about the FilterChainProxy:

1. The `FilterChainProxy` contains information about the `different security filter chains` and it [`delegates`](#-) the task to the chain based on the [`URI’s mapping or using the RequestMatcher interface`](#-).
2. It’s not executed directly but started by the `DelegatingFilterProxy filter`

### [SecurityFilterChain](#-)

We can have multiple `SecurityFilterChain` configured in our application. </br>
1. Filter chain for our REST API starting with /api/v2/**.
2. Filter chain for the internal communication /enterprise/**.
3. Security chain for other applications /**.
4. Spring security filter chain can contain multiple filters and registered with the FilterChainProxy.
5. Each security filter can be configured uniquely.

The `FilterChainProxy` determines which `SecurityFilterChain` will be invoked for an incoming request.</br>
There are several benefits of this architecture, I will highlight few advantages of this workflow:
1. It provides a centralized approach to start the process. There are no multiple entry points to the security stack.
2. Make it more easy to debug an application. We know where to start (FilterChainProxy is a prominent place to start).
3. Security is not an optional task, it’s a central point to start the process.
	* Clearing out the security context on logout is not optional and having one entry point make it easy to execute it.


we invoke servlet filter based on the given URL only, but spring security filter chain provides more flexibility:
1. Start security filters for given URL’s
2. Use the `RequestMatcher` to create more powerful rules to start the security filter chain (e.g. Based on the certain request header etc.)

### [Multiple Filter Chains](#-)

Think about FilterChainProxy as a core module. </br>
The `FilterChainProxy` decides which `SecurityFilterChain` should be used. </br>
It matches all incoming requests with the security filter chains and the first matched filter chain will execute.

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------

###### Security_Filters

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>


Some explanations for filters:

1. [`WebAsyncManagerIntegrationFilter`](#-) - Provides integration between the SecurityContext and Spring Web's WebAsyncManager.
2. [`SecurityContextPersistenceFilter`](#-) - This filter will only execute once per request, Populates the SecurityContextHolder with information obtained from the configured SecurityContextRepository prior to the request and stores it back in the repository once the request has completed and clearing the context holder.
Request is checked for existing session. If new request, SecurityContext will be created else if request has session then existing security-context will be obtained from respository.
3. [`HeaderWriterFilter`](#-) - Filter implementation to add headers to the current response
4. [`LogoutFilter`](#-) - If request url is /logout(for default configuration) or if request url matches RequestMatcher configured in LogoutConfigurer then
	* clears security context.
	* invalidates the session
	* deletes all the cookies with cookie names configured in LogoutConfigurer
	* Redirects to default logout success url / or logout success url configured or invokes logoutSuccessHandler configured.
5. [`UsernamePasswordAuthenticationFilter`](#-) see in [link](https://stackoverflow.com/questions/41480102/how-spring-security-filter-chain-works)
6. [`SecurityContextHolderAwareRequestFilter`](#-) - if you are using it to install a Spring Security aware HttpServletRequestWrapper into your servlet container
7. [`AnonymousAuthenticationFilter`](#-) - Detects if there is no Authentication object in the SecurityContextHolder, if no authentication object found, creates Authentication object (AnonymousAuthenticationToken) with granted authority ROLE_ANONYMOUS. Here AnonymousAuthenticationToken facilitates identifying un-authenticated users subsequent requests.
8. [`ExceptionTranslationFilter`](#-) - to catch any Spring Security exceptions so that either an HTTP error response can be returned or an appropriate AuthenticationEntryPoint can be launched
9. [`FilterSecurityInterceptor`](#-) - see in [link](https://stackoverflow.com/questions/41480102/how-spring-security-filter-chain-works)


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------


###### Most_Common_way_to_Authenticate

<img src="https://img.shields.io/badge/- Most_Common_way_to_Authenticate %20-blue" height=40px>

See link from stack overflow [link](https://stackoverflow.com/questions/58339005/what-is-the-most-common-way-to-authenticate-a-modern-web-app/58404641#58404641)

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------

###### 

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

<img src="https://img.shields.io/badge/- X %20- green" height=30px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------

###### 

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------

