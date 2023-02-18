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
|  3  | [](#3_)       |
|  4  | [](#4_)             |
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



[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)


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

