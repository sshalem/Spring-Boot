<img src="https://img.shields.io/badge/- SSE & WebSocket %20- blue" height=70px>

###### \_

|     | Subject                                              |
| :-: | :--------------------------------------------------- |
|  1  | [SSE Server Send Event](#1_SSE_Server_Send_Event)             |
|     | [1.1. links](#1_1_links)             |
|  2  | [](#2_)       |
|  3  | [](#3_)             |
|     | [3.1. ](#3_1_)             |

in this GIT I will show the use of ServerSentEvent & WebSocket.

- For Server Sent Event :

   1. SseEmitter ([```Code not working well, need to re-write it```](#-))
   2. WebFlux

- For WebSocket to show bi-directional communication from Server to Client , and Vice Versa

---------------------------------------------------------------------------------------------------


###### 1_SSE_Server_Send_Event

<img src="https://img.shields.io/badge/- 1_SSE_Server_Send_Event %20-blue" height=40px>

[`Server Sent Events (SSE)`](#-) is an HTTP standart that provides the capability to servers to push streaming data to client. </br>
The flow is unidirectional from server to client and client receives updates when the server pushes some data.

![image](https://user-images.githubusercontent.com/36256986/218595266-c53003dc-c890-48f2-a521-8982672dc85e.png)

How it works?
1. First connection request is send by Client
2. First Connection response is send back
3. Then the connection is established and is Open
4. We can send from server as much events as we want.
5. We can have multiple clients.

![image](https://user-images.githubusercontent.com/36256986/218595226-fa4cf004-c088-4eb8-a5c8-5ca797bb8ede.png)

### [handle on client side](#-)

`SSE` has an `EventSource` interface with a straightforward API in the client side:

```js
const url = "http://localhost:8080/api";
const eventSource = new EventSource(url);



var source = new EventSource('sse-endpoint-address');
source.onmessage = function (event) {
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
