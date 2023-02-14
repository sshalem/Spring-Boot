<img src="https://img.shields.io/badge/- Reactive WebFlux RxJava %20- blue" height=70px>

###### \_

|     | Subject                                              |
| :-: | :--------------------------------------------------- |
|     | [links](#links)             |
|  1  | [Reactive WebFlux Blocking/Non-Blocking](#1_Reactive_WebFlux_Blocking_NonBlocking)             |
|     | [1.1. links](#1_1_links)             |
|  2  | [](#2_)       |
|  3  | [](#3_)             |
|     | [3.1. ](#3_1_)             |

---------------------------------------------------------------------------------------------------

###### links

<img src="https://img.shields.io/badge/- 1_1_links %20- green" height=30px>

### [Video's](#-)

1. []()
2. []()
3. []()
4. []()

### [Articles](#-)

1. [spring.io/spring-boot/docs/2.7.8 web.reactive](https://docs.spring.io/spring-boot/docs/2.7.8/reference/htmlsingle/#web.reactive)
2. [spring io web-reactive](https://docs.spring.io/spring-framework/docs/5.3.25/reference/html/web-reactive.html#webflux-fn)
3. [spring-webflux-tutorial](https://www.devglan.com/spring-boot/spring-webflux-reactive-rest-api)
4. [spring-webflux-reactive-rest-api](https://www.devglan.com/spring-boot/spring-webflux-reactive-rest-api)
5. [spring-webflux-flux-how-to-publish-dynamically](https://stackoverflow.com/questions/51370463/spring-webflux-flux-how-to-publish-dynamically)
6. []()
7. []()
8. []()
9. []()



[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------------


###### 1_Reactive_WebFlux_Blocking_NonBlocking

<img src="https://img.shields.io/badge/- 1_Reactive_WebFlux_Blocking_NonBlocking %20-blue" height=40px>

let us understand the difference between blocking and non-blocking request processing.

see link i used for explanation [spring-webflux-tutorial](https://howtodoinjava.com/spring-webflux/spring-webflux-tutorial/)

### [1. Blocking vs Non-blocking (Async) Request Processing?](#-)

#### [1.1. Blocking Request Processing](#-)

In traditional MVC applications, a new servlet thread is created when a request comes to the server. </br>
It delegates the request to worker threads for I/O operations such as database access etc. </br>
During the time worker threads are busy, the servlet thread (request thread) remains in `waiting status`, and thus `it is blocked`. </br>
It is also called synchronous request processing.</br>
As a server can have some finite number of request threads, it limits the server’s capability to process that number of requests at maximum server load.</br>
It may hamper the performance and limit the full utilization of server capability.

<p align=center>
  <img src="https://user-images.githubusercontent.com/36256986/218669639-eeb1a063-15c2-4a41-bac3-09319f18f3d8.png" />  
</p>

#### [1.2. Non-blocking Request Processing](#-)

In `non-blocking or asynchronous` request processing, no thread is in waiting state. </br>
There is generally only one request thread receiving the request.</br>
All incoming requests come with an event handler and callback information. </br>
Request thread delegates the incoming requests to a thread pool (generally a small number of threads) which delegates the request to its handler function and immediately starts processing other incoming requests from the request thread. </br>
When the handler function is complete, one thread from the pool collects the response and passes it to the call back function. </br>
`Non-blocking` nature of threads helps in scaling the performance of the application. </br>
A small number of threads means less memory utilization and less context switching.

<p align=center>
  <img src="https://user-images.githubusercontent.com/36256986/218670632-bcbb2a1a-45db-480d-ad15-38e9f5a5f31f.png" />  
</p>

### [2. What is Reactive?](#-)

`Reactive Programming` is a programming paradigm that promotes an asynchronous, non-blocking, event-driven approach to data processing. </br>
Reactive programming involves modeling data and events as observable data streams and implementing data processing routines to react to the changes in those streams.

### [2. Reactive Streams API](#-)

The new Reactive Streams API was created by engineers from Netflix, Pivotal, Lightbend, RedHat, Twitter, and Oracle, among others and are now part of Java 9. </br>
It defines four interfaces:



###### 

<img src="https://img.shields.io/badge/-  %20- green" height=30px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------------


######

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

###### x_

<img src="https://img.shields.io/badge/- X %20- green" height=30px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------------



######

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------------


######

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

###### x_

<img src="https://img.shields.io/badge/- X %20- green" height=30px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------------
