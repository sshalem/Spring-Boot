<img src="https://img.shields.io/badge/- Reactive WebFlux RxJava %20- blue" height=70px>

###### \_

|     | Subject                                              |
| :-: | :--------------------------------------------------- |
|     | [links](#links)             |
|  1  | [Reactive WebFlux Blocking/Non-Blocking](#1_Reactive_WebFlux_Blocking_NonBlocking)             |
|     | [1.1. Blocking vs Non-blocking](#1_1_Blocking_vs_NonBlocking)             |
|     | [1.2. Reactive Streams API](#1_2_Reactive_Streams_API)             |
|  2  | [Spring_WebFlux](#2_Spring_WebFlux)       |
|     | [2.1. Spring WebFlux work flow](#2_1_Spring_WebFlux_work_flow)             |
|  3  | [Spring boot Starter R2DBC](#3_Spring_boot_Starter_R2DBC)       |
|  4  | [](#4_)             |
|     | [4.1. ](#4_1_)             |

---------------------------------------------------------------------------------------------------

###### links

<img src="https://img.shields.io/badge/- 1_1_links %20- green" height=30px>

### [Video's](#-)

1. [Spring Webflux & WebClient Tutorial 2022 with code](https://www.youtube.com/watch?v=PecY7og5KyI&ab_channel=Stack%7BDev%7D) + [github SpringWebFlux-WebClient](https://github.com/nyakaz73/SpringWebFlux-WebClient)
2. [Excellent Video - for Reactive Tutorial](https://www.youtube.com/watch?v=IK26KdGRl48&list=PLnXn1AViWyL70R5GuXt_nIDZytYBnvBdd&ab_channel=CodeWithDilip)
3. []()
4. []()

### [Articles](#-)

1. [spring io/spring-boot/docs/2.7.8 web.reactive](https://docs.spring.io/spring-boot/docs/2.7.8/reference/htmlsingle/#web.reactive)
2. [spring io web-reactive](https://docs.spring.io/spring-framework/docs/5.3.25/reference/html/web-reactive.html#webflux-fn)
3. [spring-webflux-tutorial](https://www.devglan.com/spring-boot/spring-webflux-reactive-rest-api)
4. [spring-webflux-reactive-rest-api](https://www.devglan.com/spring-boot/spring-webflux-reactive-rest-api)
5. [spring-webflux-flux-how-to-publish-dynamically](https://stackoverflow.com/questions/51370463/spring-webflux-flux-how-to-publish-dynamically)
6. [Project Reactor](https://projectreactor.io/)
7. [spring io spring-data-r2dbc-docs](https://docs.spring.io/spring-data/r2dbc/docs/current/reference/html/)
8. [spring.io projects spring-data-r2dbc](https://spring.io/projects/spring-data-r2dbc)
9. [spring-boot-r2dbc okta.com](https://developer.okta.com/blog/2021/05/12/spring-boot-r2dbc)
10. [r2dbc.io](https://r2dbc.io/)



[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------------


###### 1_Reactive_WebFlux_Blocking_NonBlocking

<img src="https://img.shields.io/badge/- 1_Reactive_WebFlux_Blocking_NonBlocking %20-blue" height=40px>

let us understand the difference between blocking and non-blocking request processing.

see link i used for explanation [spring-webflux-tutorial](https://howtodoinjava.com/spring-webflux/spring-webflux-tutorial/)

###### 1_1_Blocking_vs_NonBlocking

<img src="https://img.shields.io/badge/- 1_1_Blocking_vs_NonBlocking  %20- green" height=30px>

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

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)


###### 1_2_Reactive_Streams_API

<img src="https://img.shields.io/badge/- 1_2_Reactive_Streams_API %20- green" height=30px>



### [What is Reactive?](#-)

`Reactive Programming` is a programming paradigm that promotes an asynchronous, non-blocking, event-driven approach to data processing. </br>
Reactive programming involves modeling data and events as observable data streams and implementing data processing routines to react to the changes in those streams.

### [Reactor Core](#-)


`Reactor Core` is a `Java 8 library` that implements the reactive programming model. </br>
It's built on top of the Reactive Streams specification, a standard for building reactive applications.

From the background of non-reactive Java development, going reactive can be quite a steep learning curve. This becomes more challenging when comparing it to the Java 8 Stream API, as they could be mistaken for being the same high-level abstractions.


### [Project Reactor - Reactive Streams API](#-)

The new Reactive Streams API was created by engineers from Netflix, Pivotal, Lightbend, RedHat, Twitter, and Oracle, among others and are now part of Java 9. </br>
Two popular implementations of reactive streams are:
* RxJava (https://github.com/ReactiveX/RxJava) 
* [`Project Reactor`](https://projectreactor.io/).


### [Reactive Streams API defines four interfaces:](#-)


#### [`1. Publisher`](#-)

Emits a sequence of events to subscribers according to the demand received from its subscribers. </br>
A publisher can serve multiple subscribers.

```java
Publisher.javapublic interface Publisher<T> {
  public void subscribe(Subscriber<? super T> s);
}
```

#### [`2. Subscriber`](#-)

Receives and processes events emitted by a Publisher. </br>
Please note that no notifications will be received until Subscription#request(long) is called to signal the demand.</br>
It has four methods to handle various kinds of responses received.

```java
public interface Subscriber<T> {
  public void onSubscribe(Subscription s);
  public void onNext(T t);
  public void onError(Throwable t);
  public void onComplete();
}
```

#### [`3. Subscription`](#-)

Defines a one-to-one relationship between a Publisher and a Subscriber. </br>
It can only be used once by a single Subscriber.</br>
It is used to both signal desire for data and cancels demand (and allow resource cleanup).

```java
public interface Subscription<T> {
  public void request(long n);
  public void cancel();
}
```

#### [`4. Processor`](#-)

Represents a processing stage consisting of both a Subscriber and a Publisher and obeys both contracts.

```java
public interface Processor<T, R> extends Subscriber<T>, Publisher<R> {
}
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------------

###### 2_Spring_WebFlux

<img src="https://img.shields.io/badge/- 2_Spring_WebFlux %20-blue" height=40px>

see more data in link from [spring io WebFlux](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html)

### [Spring WebFlux?](#-)

Spring WebFlux is a `parallel version` of Spring MVC and supports fully `non-blocking reactive streams`. </br>
It supports the `back pressure` concept and uses : 
* [`Netty as the inbuilt server to run reactive applications`](#-).  </br>

`Spring webflux` uses project reactor as the reactive library. </br>
Reactor is a Reactive Streams library; therefore, all of its operators support non-blocking back pressure.</br>

### [Dependencies](#-)

With Spring boot, when we add the starter for `Spring-Reactive-Web` it adds the following dependencies:

```sql
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-webflux</artifactId>
</dependency>
  
 <!-- for Testing the Reactive  -->
<dependency>
	<groupId>io.projectreactor</groupId>
	<artifactId>reactor-test</artifactId>
	<scope>test</scope>
</dependency>
```

### [WebFlux Publishers](#-)

[`Mono publisher`](#-) Returns 0 or 1 element.

```java
Mono<String> monoS = Mono.just("Alex");
Mono<String> monoE = Mono.empty();

//To subscribe call method
 
mono.subscribe();
```

[`Flux publisher`](#-) Returns 0…N elements. </br>
A Flux can be endless, meaning that it can keep emitting elements forever. </br>
Also it can return a sequence of elements and then send a completion notification when it has returned all of its elements.

```java
Flux<String> flux = Flux.just("A", "B", "C");
Flux<String> flux = Flux.fromArray(new String[]{"A", "B", "C"});
Flux<String> flux = Flux.fromIterable(Arrays.asList("A", "B", "C"));
 
//To subscribe call method
 
flux.subscribe();
```

In Spring WebFlux, we call reactive APIs/functions that return `Monos and Fluxes` and your controllers will return `monos and fluxes`. </br>
When you invoke an API that returns a mono or a flux, it will return immediately.</br>
The function call results will be delivered to you through the mono or flux when they become available.

### [Backpressure](#-)

[Back pressure from Baeldung](https://www.baeldung.com/reactor-core) </br>.
The next thing we should consider is backpressure. </br>
In our example, the subscriber is telling the producer to push every single element at once.</br>
This could end up becoming overwhelming for the subscriber, consuming all of its resources.

`Backpressure` is when a downstream (Database) can tell an upstream (Java Application) to send him less data , in order to prevent it from being overwhelmed.

* `Backpressure` - The ability of the Subscriber to let the Publisher how many requests can it handle at the time. </br>
So it's the Subscriber that is responsible for the flow of the data, not the Publisher as it just provides the data.


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### 2_1_Spring_WebFlux_work_flow

<img src="https://img.shields.io/badge/- 2_1_Spring_WebFlux_work_flow %20- green" height=30px>

### [Spring_WebFlux_work_flow](#-)

[Links form JavTechie](#-) </br>
[Spring_WebFlux_work_flow - JavaTechie 2 hours](https://www.youtube.com/watch?v=bXcFCgQsvAE&ab_channel=JavaTechie) </br>
[Spring_WebFlux_work_flow - JavaTechie part 1](https://www.youtube.com/watch?v=ckfqcfzCg3w&ab_channel=JavaTechie) </br>
[Spring_WebFlux_work_flow - JavaTechie part 2](https://www.youtube.com/watch?v=9x7G4f3o90Q&ab_channel=JavaTechie) </br>
[Spring_WebFlux_work_flow - JavaTechie part 3](https://www.youtube.com/watch?v=UZEQiaRhB9A&ab_channel=JavaTechie) </br>


Let's look at the flow Diagram with the code below:

```java
@Test
public void testFlux() {
	// Update the Publisher
	Flux<?> fluxString = Flux.just("Flux test", "Spring test", "Hibernate test").log();
		
	// Publisher send subscribe
	fluxString.subscribe(System.out::println);
}
```

1. Subscriber send a `subscribe()` to Publisher
2. Publisher sends a `subscription()` to Subscriber
3. Subscriber sends a `request` which he takes from the `subscription` 
4. 

![image](https://user-images.githubusercontent.com/36256986/218877883-868477d8-5580-46ac-adc9-e792b63a2f1d.png)

![image](https://user-images.githubusercontent.com/36256986/218879568-8c6681f7-1caf-4f14-a8b6-f2de6bee34d7.png)

![image](https://user-images.githubusercontent.com/36256986/218879655-83b7df07-3a03-4519-99af-a8b10b14b246.png)

![image](https://user-images.githubusercontent.com/36256986/218879715-5044c014-2d16-44c7-a7cd-836c5bf5654f.png)

![image](https://user-images.githubusercontent.com/36256986/218879948-a44d21ab-7fa4-4f78-9c26-cd1aa47b77fb.png)

![image](https://user-images.githubusercontent.com/36256986/218880011-d4740777-d9d6-4b0e-8cb0-dc77082b256b.png)

![image](https://user-images.githubusercontent.com/36256986/218880275-5bbddeb9-dd07-45e8-a849-ab938f7e00ad.png)

![image](https://user-images.githubusercontent.com/36256986/218880319-9da684dd-b116-4815-af09-ec3021dff7a3.png)

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)


---------------------------------------------------------------------------------------------------

###### 3_Spring_boot_Starter_R2DBC

<img src="https://img.shields.io/badge/- 3_Spring_boot_Starter_R2DBC %20-blue" height=40px>

[reactive-programming-with-spring-data-r2dbc](https://medium.com/pictet-technologies-blog/reactive-programming-with-spring-data-r2dbc-ee9f1c24848b)

![image](https://user-images.githubusercontent.com/36256986/218781799-df5d2184-41b5-467e-8e5a-0f7b47982ae7.png)




[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------------


######

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

###### x_

<img src="https://img.shields.io/badge/- X %20- green" height=30px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------------

![image](https://user-images.githubusercontent.com/36256986/218877883-868477d8-5580-46ac-adc9-e792b63a2f1d.png)

![image](https://user-images.githubusercontent.com/36256986/218879568-8c6681f7-1caf-4f14-a8b6-f2de6bee34d7.png)

![image](https://user-images.githubusercontent.com/36256986/218879655-83b7df07-3a03-4519-99af-a8b10b14b246.png)

![image](https://user-images.githubusercontent.com/36256986/218879715-5044c014-2d16-44c7-a7cd-836c5bf5654f.png)

![image](https://user-images.githubusercontent.com/36256986/218879948-a44d21ab-7fa4-4f78-9c26-cd1aa47b77fb.png)

![image](https://user-images.githubusercontent.com/36256986/218880011-d4740777-d9d6-4b0e-8cb0-dc77082b256b.png)

![image](https://user-images.githubusercontent.com/36256986/218880275-5bbddeb9-dd07-45e8-a849-ab938f7e00ad.png)

![image](https://user-images.githubusercontent.com/36256986/218880319-9da684dd-b116-4815-af09-ec3021dff7a3.png)
