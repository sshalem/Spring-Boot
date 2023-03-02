<img src="https://img.shields.io/badge/- SSE & WebSocket %20- blue" height=70px>

###### \_

|     | Subject                                              |
| :-: | :--------------------------------------------------- |
|  1  | [SSE Server Send Event](#1_SSE_Server_Send_Event)             |
|     | [1.1. links](#1_1_links)             |
|  2  | [ServerSentEvent simple project](#2_ServerSentEvent_simple_project)             |
|     | [2.1. BackEnd](#2_1_BackEnd)             |
|     | [2.2. FrontEnd](#2_2_FrontEnd)             |
|     | [2.3. test](#2_3_test)             |
|  3  | [WebSocket](#3_WebSocket)       |
|  4  | [WebSocket Simple project](#4_WebSocket_Simple_project)       |
|     | [4.1. BackEnd](#4_1_BackEnd)             |
|     | [4.2. FrontEnd](#4_2_FrontEnd)             |
|     | [4.3. test](#4_3_test)             |
|  9  | [Chat App with ReactJS](#9_Chat_App_with_ReactJS)             |
|     | [3.1. ](#3_1_)             |


---------------------------------------------------------------------------------------------------


###### 1_SSE_Server_Send_Event

<img src="https://img.shields.io/badge/- 1_SSE_Server_Send_Event %20-blue" height=40px>

### [`Server Sent Events (SSE)`](#-) 
* is an [`HTTP`](#-) standart that provides the capability to servers to push streaming data to client. </br>
* The flow is unidirectional from server to client and client receives updates when the server pushes some data.

<p align="center">
	<img src="https://user-images.githubusercontent.com/36256986/218595266-c53003dc-c890-48f2-a521-8982672dc85e.png" width=600 height=400 />
</p>


How it works?
1. First connection request is send by Client
2. First Connection response is send back
3. Then the connection is established and is Open
4. We can send from server as much events as we want.
5. We can have multiple clients.


<p align="center">
	<img src="https://user-images.githubusercontent.com/36256986/218595226-fa4cf004-c088-4eb8-a5c8-5ca797bb8ede.png" width=600 height=400 />
</p>

### [handle on client side](#-)

`SSE` has an `EventSource` interface with a straightforward API in the client side:

```js
const eventSource = new EventSource("http://localhost:8080/api");
eventSource.onmessage = function (event) {
  console.log(event.data);
};
```




###### 1_1_links

<img src="https://img.shields.io/badge/- 1_1_links %20- green" height=30px>

### [Video's](#-)

1. [SSE , great explanation of WebFlux and reactive programming](https://www.youtube.com/watch?v=M3jNn3HMeWg&ab_channel=DefogTech)
2. [SSE and WebFlux - Geomapping](https://www.youtube.com/watch?v=lQCkZA-wE4U&ab_channel=ShaneLee)
3. [SSE with Flux (Stock price)](https://www.youtube.com/watch?v=CPXWzdk5OQE)
4. []()

### [Articles](#-)

1. [demo-spring-sse](https://github.com/aliakh/demo-spring-sse)
2. [SSE with WebFlux by mkyong](https://mkyong.com/spring-boot/spring-boot-webflux-server-sent-events-example/)
3. [SSE-spring-push-notifications](https://roytuts.com/server-sent-events-spring-push-notifications/)
4. [SSE with Spring from golb.hplar](https://golb.hplar.ch/2017/03/Server-Sent-Events-with-Spring.html)
5. [SSE spring boot + ReactJs](https://turkogluc.com/server-sent-events-with-spring-boot-and-reactjs/)
6. [SSE tutorial](https://medium.com/@mohitsinha.it/spring-boot-server-sent-events-tutorial-fb94a77db8a7)
7. [SSE part 1](https://www.youtube.com/watch?v=T_JZzdPCkOU&ab_channel=JavaGrowth)
8. [SSE part 2](https://www.youtube.com/watch?v=HoxPgU4lFGE&ab_channel=JavaGrowth)


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------------


###### 2_ServerSentEvent_simple_project

<img src="https://img.shields.io/badge/- 2_ServerSentEvent_simple_project %20-blue" height=40px>

In this project I will show how SSE works

###### 2_1_BackEnd

<img src="https://img.shields.io/badge/- 2_1_BackEnd %20- green" height=30px>

### [Dependencies](#-)

For backend prject jsut add following dependencies:

1. Spring boot project 2.7.8 
2. Web
3. Dev-Tools

### [Controller](#-)

Controller need to have the following:
1. a `GET` method to open a connection with Client
2. a method to dispatch the changes to client

### [1. Controller with implemented client side `message` event handler](#-)

```java
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.SseEventBuilder;

@RestController
public class NewsController {

	/**
	 * I create this List<SseEmitter> because I can have multiple browsers sending
	 * connection requests
	 * 
	 * @create a connection request (the line below of JavaScript):
	 *         const eventSource = new EventSource("http://localhost:8080/createConnection");
	 * 
	 * @CopyOnWriteArrayList is synchronized, thread safe , But is slower than ArrayList..
	 */
	public List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

	/**
	 * method for client subscription, Establishes the Connection with Client.
	 * 
	 * @I must consume MediaType.ALL_VALUE
	 */
	@CrossOrigin
	@GetMapping(path = "/createConnection", produces = MediaType.TEXT_EVENT_STREAM_VALUE)	
	public SseEmitter createConnection() {

		// I add here the Long timeout value Long.MAX_VALUE 
		SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);
		try {
			// First : send a connection request message to the client, to established connection 
			SseEventBuilder sseEventBuilder = SseEmitter
					.event()
					.id(UUID.randomUUID().toString().substring(0, 8))
					.name("this is the type field")
					.data("connecting to server");			
			
			sseEmitter.send(sseEventBuilder);	
		} catch (IOException e) {
			e.printStackTrace();
		}

		// I need to add this line of code 
		// Otherwise I will get error of :
		// "java.lang.IllegalStateException: ResponseBodyEmitter is already set complete" 
		// This code also handles the warning of "Async request timed out"         
		sseEmitter.onCompletion(()-> emitters.remove(sseEmitter));
		sseEmitter.onError((e) -> emitters.remove(sseEmitter));
		sseEmitter.onTimeout(() -> emitters.remove(sseEmitter));
		emitters.add(sseEmitter);
		return sseEmitter;
	}

	// method for dispatching events to all clients
	@PostMapping("/event")
	public void dispatchEventsToClients(@RequestBody Object freshNews) {

				
		// here I loop all over the emitters 
		// and send event (push events) to all clients
		for(SseEmitter emitter : emitters) {
			try {
				// SInce I use event Handler on my front end
				// Thus I need to :
				// 1. define the  ---> name("message")
				// 2. on frontEnd I use ---> eventSource.onmessage = function (event)
				emitter.send(SseEmitter.event().name("message").data(freshNews));
			} catch (IOException e) {    
				// Got error with below code
				// e.printStackTrace();
				// Thus had to modify the code as follows:
				// We need to remove the emitter from the list if it's not found
				emitters.remove(emitter);
			}
		}
	}
	
	static class FreshNews {
		private String freshNews;

		public FreshNews() {
			super();		
		}		

		public String getFreshNews() {
			return freshNews;
		}

		public void setFreshNews(String freshNews) {
			this.freshNews = freshNews;
		}		
	}
}
```

### [2. Controller with implemented client side `message` eventListener](#-)

Only the dispatchEventsToClients is need to be as shown below:

```java
	// method for dispatching events to all clients
	@PostMapping("/event")
	public void dispatchEventsToClients(@RequestBody Object freshNews) {
				
		// here I loop all over the emitters 
		// and send event (push events) to all clients
		for(SseEmitter emitter : emitters) {
			try {
				// Since I use eventListener in my front end
				// I need to define the same name in the listener here and in my backend
				// BackEnd ---> "latestNews"
				// FrontEnd eventListener ---> "message"
				emitter.send(SseEmitter.event().name("message").data(freshNews));				
			} catch (IOException e) {    
				// Got error with below code
				// e.printStackTrace();
				// Thus had to modify the code as follows:
				// We need to remove the emitter from the list if it's not found
				emitters.remove(emitter);
			}
		}
	}
```

### [3. Controller with implemented client side `newsFresh` custom eventListener](#-)

In dispatchEventsToClients method , we can see that the `.name("latestNews")` is not `.name("message")` , thus , my client side also is modified

```java
	// method for dispatching events to all clients
	@PostMapping("/event")
	public void dispatchEventsToClients(@RequestBody Object freshNews) {
				
		// here I loop all over the emitters 
		// and send event (push events) to all clients
		for(SseEmitter emitter : emitters) {
			try {
				// SInce I use eventListener in my front end
				// I need to define the same name in the listener here and in my backend
				// BackEnd ---> custom name "latestNews" (and not message)
				// FrontEnd eventListener ---> "latestNews"
				emitter.send(SseEmitter.event().name("latestNews").data(freshNews));				
			} catch (IOException e) {    
				// Got error with below code
				// e.printStackTrace();
				// Thus had to modify the code as follows:
				// We need to remove the emitter from the list if it's not found
				emitters.remove(emitter);
			}
		}
	}
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)


###### 2_2_FrontEnd

<img src="https://img.shields.io/badge/- 2_2_FrontEnd %20- green" height=30px>

On the fronEnd side I need to do the follwoing in order to be able to listen to the Evenets From Server:
1. create new Object of [`EventSource`] and instanciate it with the url of server
2. create 3 methods , which are :
	* `eventSource.onopen`
	* `eventSource.onerror`
	* `eventSource.onmessage` (can be also eventListener , I will show implementaion with both cases)

### [1. FornEnd with `message` event handler](#-)

```js
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1.0" />
    <title>Document</title>
  </head>
  <body>
    <h3>events</h3>
    <ul id="events"></ul>
    <script>
      const eventList = document.getElementById('events');

      function addEvent(text) {
        const newElement = document.createElement('li');
        newElement.textContent = text;
        eventList.appendChild(newElement);
      }

      function initialize() {
        const eventSource = new EventSource('http://localhost:8080/createConnection');

        eventSource.onopen = (event) => {
          addEvent('Connected and subscribed');
          console.log('open');
        };

        eventSource.onerror = (e) => {
          if (e.readyState == EventSource.CLOSED) {
            console.log('close');
          } else {
            console.log(e);
          }
        };

        // there are 2 ways to read the data from the Server:

        // In this project I use the event Handler `onmessage`
        // In the other project I sue `eventListener` 
        // If using the onmessage event handler ,
        // I need to use the name "message" when I send the emitter in my backend:
        eventSource.onmessage = function (event) {
          console.log(event);
          console.log('Im onmessage');
          const message = JSON.parse(event.data);
          addEvent(`Message : ${message.freshNews}`);
        };

      }

      window.onload = initialize;
    </script>
  </body>
</html>
```

### [2. FornEnd with `message` eventListener](#-)

```js
        // (2) second way:
        // I used eventListener which listens to the "message" coming from the server
        eventSource.addEventListener('message', function (event) {
          console.log(event);
          const message = JSON.parse(event.data);
          addEvent(`Message : ${message.freshNews}`);
        });
```


### [3. FornEnd with `latestNews` custom eventListener](#-)

```js
        // (2) custom eventListener second way:
        // I used eventListener which listens to the "latestNews" coming from the server
        eventSource.addEventListener('latestNews', function (event) {
          console.log(event);
          const message = JSON.parse(event.data);
          addEvent(`Message : ${message.freshNews}`);
        });
```


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)


###### 2_3_test

<img src="https://img.shields.io/badge/- 2_3_test %20- green" height=30px>

Here run only project `01-SSE-custom-eventListener` (No need to run others).
1. browse to localhost:8080. </br>
2. Open developer tools, goto to network tab
3. we can see , on left side, we have a `createConnection` - which is the url `GET` path of our Rest API 
4. Click on `EventStream` as shown below , we can see the initial data sent from server when a connection is created.


![image](https://user-images.githubusercontent.com/36256986/219902224-eefda340-6920-4900-aa66-87278512c8c5.png)

5. Open 2 different browers (EDGE and Chrome)
6. send via Postman , a `Post` request to the server, and lets see how the browsers react.

![image](https://user-images.githubusercontent.com/36256986/219902422-f62fe502-9a47-43f1-bd4d-854459700125.png)

7. This is what we see in both browsers:

### [Chrome](#-)

![image](https://user-images.githubusercontent.com/36256986/219902500-5e8847e2-9fc3-49c7-bdc7-3588edf299c5.png)

### [Edge](#-)

![image](https://user-images.githubusercontent.com/36256986/219902509-fa60b010-c07b-4c56-beb0-934e2ead068e.png)

8. Both get the same data , with same type
9. They Have differnet Id
10. We can implement a code , to send only to a specific connection the data , By using `Map` and not `List`


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------------



###### 3_WebSocket

<img src="https://img.shields.io/badge/- 3_WebSocket %20-blue" height=40px>

#### [Articles](#-)

* [WebSockest from Spring io](https://docs.spring.io/spring-framework/docs/5.3.25/reference/html/web.html#websocket) </br>
* [Example : getting-started-with-spring-websockets](https://www.section.io/engineering-education/getting-started-with-spring-websockets/)
* [Example : refactorfirst - websockets-stomp-notifications](https://refactorfirst.com/spring-boot-websockets-stomp-notifications)
* [spring io example](https://spring.io/guides/gs/messaging-stomp-websocket/)
* [Sent message to specific user](https://www.toptal.com/java/stomp-spring-boot-websocket)
* [spring-session-stomp-websocket](https://www.devglan.com/spring-boot-tutorial/spring-session-stomp-websocket)
* [HandShakeInterceptor](https://stackoverflow.com/questions/31669927/spring-websockets-stomp-get-client-ip-address/31706435#31706435)
* [what is ChannelInterceptor]()

#### [Video's](#-)

* [Daily Code Buffer](https://www.youtube.com/watch?v=n6ZqOwreFTA&ab_channel=DailyCodeBuffer) </br>
* [Spring Boot Using Websockets and STOMP - Great Link](https://www.youtube.com/watch?v=QNMItUBPxaI&ab_channel=RefactorFirst)</br>
* [Java Techie example](https://www.youtube.com/watch?v=4Hyv4M1kFeM&ab_channel=JavaTechie)


### [What is WebSocket](https://docs.spring.io/spring-framework/docs/5.3.25/reference/html/web.html#websocket)

see explanations from [Spring io - websocket](https://docs.spring.io/spring-framework/docs/5.3.25/reference/html/web.html#websocket)

The WebSocket protocol, RFC 6455, provides a standardized way to establish a [`full-duplex, two-way communication channel`](#-) between client and server over a single [`TCP connection`](#-). </br>
Before a client and server can exchange data, they must use the [`TCP`](#-) (Transport Control Protocol) layer to establish the connection. </br>
It is a different TCP protocol from HTTP but is designed to work over HTTP, using ports 80 and 443 and allowing re-use of existing firewall rules.

A WebSocket interaction begins with an HTTP request that uses the HTTP Upgrade header to upgrade or, in this case, to switch to the WebSocket protocol.</br>
The following example shows such an interaction:

![image](https://user-images.githubusercontent.com/36256986/220074666-d059d100-1d38-40f5-958d-bdba127c4131.png)

1. The Upgrade header.
2. Using the Upgrade connection.

Instead of the usual 200 status code, a server with WebSocket support returns output similar to the following:

![image](https://user-images.githubusercontent.com/36256986/220074783-1d2062f3-f967-4e69-b770-0ce7a5bdcc9a.png)

1. Protocol switch

We can use 2 wyas to config WebSocket:
1. implement the interface of `WebSocketConfigurer` 
2. implement the interface of `WebSocketMessageBrokerConfigurer` - this way we use a Message Broker for (STOMP or any other Broker like RabbitMQ) 

### [When to use WebSocket](#-)

the usage of WebSocket is in :
* Social feeds
* News feeds
* chat's
* Multiplayer games
* Sports updates
* Location-based apps
* Online education

### [What is SockJS](https://docs.spring.io/spring-framework/docs/5.3.25/reference/html/web.html#websocket-fallback)

[SockJS - a websocket-fallback](https://docs.spring.io/spring-framework/docs/5.3.25/reference/html/web.html#websocket-fallback)

Over the public Internet, restrictive proxies outside your control may preclude WebSocket interactions, either because they are not configured to pass on the Upgrade header or because they close long-lived connections that appear to be idle.

The solution to this problem is WebSocket emulation — that is, attempting to use WebSocket first and then falling back on HTTP-based techniques that emulate a WebSocket interaction and expose the same application-level API.

On the Servlet stack, the Spring Framework provides both server (and also client) support for the [`SockJS protocol`](#-).

The goal of [`SockJS`](#-) is to let applications use a [`WebSocket API`](#-)  ,but fall back to non-WebSocket alternatives when necessary at runtime, without the need to change application code.



### [STOMP - Simple Text Oriented Messaging Protocol](#-)

[STOMP - websocket - sockJS](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#websocket-stomp-enable)

STOMP over WebSocket support is available in the spring-messaging and spring-websocket modules. Once you have those dependencies, you can expose a STOMP endpoints, over WebSocket with SockJS Fallback, 

### [sending-stomp-messages-over-a-websocket-in-spring-boot](https://www.continuum.be/en/blog/sending-stomp-messages-over-a-websocket-in-spring-boot-2/)

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)


---------------------------------------------------------------------------------------------------

###### 4_WebSocket_Simple_project

<img src="https://img.shields.io/badge/- 4_WebSocket_Simple_project %20-blue" height=40px>

In this Project I will show the following:
1. Implement the BackEnd configuration for WebSocket, using `STOMP`
2. Implement the FrontEnd 
3. Test the app 


###### 4_1_BackEnd

<img src="https://img.shields.io/badge/- 4.1. BackEnd %20- green" height=30px>

### [Dependencies](#-)

For backend prject jsut add following dependencies:

1. Spring boot project 2.7.8 
2. Spring boot starter WebSocket
3. Dev-Tools

![image](https://user-images.githubusercontent.com/36256986/220960634-22e464d1-7842-4dc9-8260-8b4c8d0243bb.png)

### [Message Entity](#-)

```java
public class Message implements Serializable {

	private static final long serialVersionUID = 1736015187911517445L;
	private String senderName;
	private String receiverName;
	private String message;

	Ctor/ G/ S / ToString
}
```

### [Configuration with WebSocketConfig](#-)

In this configuration I have 2 methods I implement:
1. `registerStompEndpoints` - define the EndPoint to connect with the FrontEnd stomp implementation.
2. `configureMessageBroker` 
	* define a prefix for the url --> `/app`
	* define a path url , which client will send to server  --> `/all`

```java
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker("/all");
		registry.setApplicationDestinationPrefixes("/app");
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		// withSockJS() - means that a Fallback to SockJS is possible
		registry.addEndpoint("/ws-stomp-endpoint").withSockJS();		
	}
}
```

### [Controller](#-)

In this Implementation :
1. I hava a `@Controller` annotation
2. `@MessageMapping("/application")` - which client sends 


```java
@Controller
public class MessageController {

	/**
	 * @MessageMapping annotation ensures that, if a message is sent to the
	 *                 "/app/application" (/`@prefix from WebSocketConfig
	 *                 class`/application) destination, the sendMessage() method is
	 *                 called. Note the Spring adds the `/app` prefix for us
	 * 
	 *                 the sendMessage() method creates a Message object and returns
	 *                 it. The return value is broadcast to all subscribers of
	 *                 "/all/messages, as specified in the @SendTo annotation
	 * 
	 *                 We add this to tell Spring to send the return value to the
	 *                 given endpoint. All we are doing here is taking messages sent
	 *                 from one endpoint and redirecting to another
	 * 
	 *                 our method receives messages from /app/application. We also
	 *                 have the @SendTo annotation with the value /all/messages. 
	 *		   this `/all` comes from the configuration class `registry.enableSimpleBroker("/all")` 
	 */

	// Mapped as /app/application
	@MessageMapping("/application")
	@SendTo("/all/messages")
	public Message sendMessage(@Payload Message message, StompHeaderAccessor stompHeaderAccessor) throws Exception {
		
		// This command gets the header `send-Header` of the `stompClient.send` , and browser shows in console
		// >>>SEND  the header is send
		Object addressNativeHeader = stompHeaderAccessor.getFirstNativeHeader("send-Header");
		System.out.println(addressNativeHeader);
	
		// This headers are made by the client each message he sends
		MessageHeaders messageHeaders = stompHeaderAccessor.getMessageHeaders();
		System.out.println(messageHeaders);	
		
		return message;
	}
}
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)


###### 4_2_FrontEnd

<img src="https://img.shields.io/badge/- 4.2. FrontEnd %20- green" height=30px>

This is how the frontend code looks. </br>
1. in `index.html` I add the cdn for `SockJS` and `Stomp` since I use those Libraries
2. I used Bootstrap for design CSS

### [index.html](#-)

```html
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Hello WebSocket</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <link rel="stylesheet" href="style.css" />

    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.6.1/sockjs.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.js"></script>
  </head>
  <body>
    <div class="container">
      <!-- Start Header Section -->
      <header class="col-md-9">
        <h1>WebSocket connection</h1>
      </header>
      <!-- End Header Section -->
      <!-- Start Connect Section -->
      <section class="col-md-9" id="connect-section">
        <br />
        <div class="form-group">
          <label for="username-input">Write a Name</label>
          <input type="text" id="username-input" class="form-control" placeholder="Write a name here..." />
        </div>
        <div id="connect-btn" class="btn btn-success">Connect</div>
      </section>
      <section class="col-md-9">
        <div id="disconnect" class="btn btn-danger hide">Disconnect</div>
      </section>
      <!-- End Connect Section -->
      <!-- Start write Message Section -->
      <section class="col-md-9">
        <br />
        <div class="form-group">
          <label for="message-input">Write a message</label>
          <input type="text" id="message-input" class="form-control" placeholder="message here..." />
        </div>
        <div id="sendMessage" class="btn btn-primary">Send</div>
      </section>
      <br />
      <!-- End write Message Section -->
      <!-- Start Show Messages Section -->
      <section class="col-md-9">
        <br />
        <div class="form-group" id="messages"></div>
      </section>
      <!-- End Show Messages Section -->
    </div>
    <script src="./app.js"></script>
  </body>
</html>

```

### [app.js](#-)

in the app.js this is what we have:
1. (1) set up a connection to server with WebSocket 
2. (2) create a stomp client (since we use a STOMP in our server)
3. (3) connect to server with method of `stompClient.connect` , which listens (subscribe) to messages coming from server (depends on Url ,can be for all or for private user)
4. (4) eventListener (of `click` to send messages) that takes the types message and sends it to server `stompClient.send`

```js
const connectSection = document.getElementById('connect-section');
const connectBtn = document.getElementById('connect-btn');
const disconnect = document.getElementById('disconnect');
const sendMessage = document.getElementById('sendMessage');
const conversation = document.getElementById('conversation');
const usernameInput = document.getElementById('username-input');
const displayMessages = document.getElementById('messages');

// Globaly define this variable
let stompClient = null;

/*******************
 * Event Listener
 ******************/
connectBtn.addEventListener('click', (e) => {
  if (usernameInput.value === '') {
    alert('must write a name here before connecting');
  } else {
    // (1) Try to set up WebSocket connection with the handshake at "http://localhost:8080/ws-stomp-endpoint"
    let socket = new SockJS('http://localhost:8080/ws-stomp-endpoint');

    // (2) Create a new StompClient object with the WebSocket endpoint
    stompClient = Stomp.over(socket);

    // (3) in this function, we connect with STOMP , this will start:
    //     (a) connecting for establishing communications
    //     (b) Listening for url of '/all/messages'
    //     (c) Provide a callback , when the CONNECT frame arrives.
    //     (d) this is the format of connect: stompClient.connect(header, onConnected, onError);
    stompClient.connect(
      { 'connection-Header': 'connection-Header' },
      function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/all/messages', (result) => {
          console.log(result);
          displayResult(JSON.parse(result.body));
        });
      },
      function (error) {
        console.error(error);
      }
    );
  }
});

/*****************
 * Event Listener
 *****************/
// (4) Take the value in the 'message-input' text field and send it to the server.
sendMessage.addEventListener('click', (e) => {
  let messageInput = document.getElementById('message-input');
  const messageToSend = {
    senderName: usernameInput.value,
    message: messageInput.value,
  };

  // this is the format of send:
  // stompClient.send(destination, callback, headers)
  stompClient.send('/app/application', { 'send-Header': 'send-Header' }, JSON.stringify(messageToSend));

  messageInput.value = '';
});

/*****************
 * Event Listener : This is for sending the message when pressing the Enter Key
 *****************/
document.getElementById('message-input').addEventListener('keyup', (e) => {
  let messageInput = document.getElementById('message-input');
  const messageToSend = {
    senderName: usernameInput.value,
    message: messageInput.value,
  };

  if (e.key === 'Enter' && messageInput !== '') {
    stompClient.send('/app/application', { 'send-Header': 'send-Header' }, JSON.stringify(messageToSend));
    messageInput.value = '';
  }
});

/******************
 * Event Listener
 ******************/
disconnect.addEventListener('click', (e) => {
  if (stompClient !== null) {
    stompClient.disconnect();
  }
  usernameInput.value = '';
  displayMessages.innerHTML = '';
  setConnected(false);
});

/**********************************************************************
 *                Helper Methods
 **********************************************************************/
function displayResult(msg) {
  console.log(msg);
  const p = document.createElement('p');
  p.innerHTML = `
      <label style="margin-right:2rem; font-size:1.7rem">${msg.senderName} : </label>  ${msg.message}`;
  displayMessages.appendChild(p);
}

function setConnected(connected) {
  if (connected) {
    disconnect.classList.toggle('hide');
    connectSection.classList.toggle('hide');
  } else {
    disconnect.classList.toggle('hide');
    connectSection.classList.toggle('hide');
  }
}
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)



###### 4_3_test

<img src="https://img.shields.io/badge/- 4.3. test %20- green" height=30px>

Lets run project `02-websocket-stomp` and see the results.








[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------------


###### 9_Chat_App_with_ReactJS

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

Follow the link from YouTube [https://www.youtube.com/watch?v=o_IjEDAuo8Y&ab_channel=InvolveInInnovation](https://www.youtube.com/watch?v=o_IjEDAuo8Y&ab_channel=InvolveInInnovation)

git hub link for the project [https://github.com/JayaramachandranAugustin/ChatApplication](https://github.com/JayaramachandranAugustin/ChatApplication)

###### x_

<img src="https://img.shields.io/badge/- X %20- green" height=30px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)


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



