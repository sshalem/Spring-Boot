<img src="https://img.shields.io/badge/-RabbitMQ  %20-blue" height=70px>

###### _

|     | Subject                                              |
| :-: | :--------------------------------------------------- |
|     | [Links](#Links)             |
|  1  | [RabbitMQ Introduction](#1_RabbitMQ_Introduction)             |
|     | [1.1. What is a Message Queueu](#1_1_What_is_a_Message_Queueu)             |
|     | [1.2. What is a RabbitMQ](#1_2_What_is_a_RabbitMQ)             |
|     | [1.3. RabbitMQ in Microservices](#1_3_RabbitMQ_in_microservices)             |
|     | [1.4. RabbitMQ core concepts (Prodeucer , consumer etc...](#1_4_RabbitMQ_core_concepts)             |
|     | [1.5. RabbitMQ Architecture](#1_5_RabbitMQ_architecture)             |
|     | [1.6. What is a RabbitMQ](#1_6_What_is_a_RabbitMQ)             |
|  2  | [Install RabbitMQ](#2_Install_RabbitMQ)             |
|     | [2.1. Install RabbitMQ on windows](#2_1_Install_RabbitMQ_on_Windows)             |
|     | [2.2. Install RabbitMQ docker image](#2_2_Install_RabbitMQ_docker_image)             |
|  3  | [Connect to RabbitMQ Message Broker](#3_Connect_to_RabbitMQ_Message_Broker)             |




------------------------------------------


###### Links

<img src="https://img.shields.io/badge/- Links %20-blue" height=40px>

### [Video](#-) 

* [Spring Boot RabbitMQ Tutorial](https://www.youtube.com/playlist?list=PLGRDMO4rOGcMh2fAMOnwuBMDa8PxiKWoN) 



[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)


------------------------------------------


###### 1_RabbitMQ_Introduction

<img src="https://img.shields.io/badge/- 1. Caching Introduction %20-blue" height=40px>

### [RabbitMQ core concepts](#-)

We will learn the follwoing :

1. RabbitMQ concepts (Producer , consumer, queue, exchange, binding, Routing key, message)
2. RabbitMQ Architecture
3. Spring Boot + RabbitMQ with a  using AMQP (Advanced Message Queueing Protocol) library

<p align=center>
  <img src="https://user-images.githubusercontent.com/36256986/220572000-0b9f01b9-f8d7-469c-8dd5-b0644be12ae1.png" width=500 height=200 />
</p>

4. Spring Boot + RabbitMQ flow for a [`String`](#-) message

<p align=center>
  <img src="https://user-images.githubusercontent.com/36256986/220572355-cb2498d5-fe96-43a1-bea2-54533b5e4668.png" width=600 height=200 />
</p>

5. Spring Boot + RabbitMQ flow for a [`JSON`](#-) message

<p align=center>
  <img src="https://user-images.githubusercontent.com/36256986/220572450-819bfa07-85ee-4bd4-a5d7-34265411854b.png" width=600 height=200 />
</p>

6. RabbitMQ Architecture with multiple queue's

<p align=center>
  <img src="https://user-images.githubusercontent.com/36256986/220572839-88e97143-5525-4984-bc9d-7f18ea1c71f1.png" width=500 height=200 />
</p>


###### 1_1_What_is_a_Message_Queueu

<img src="https://img.shields.io/badge/- 1_1_What_is_a_Message_Queueu %20- green" height=30px>

The message Queue provides [`temporary message storage`](#-) when the destination program is :
* busy 
* or not connected 

A message queue is made up of a:
1. produver
2. a broker (the message queue software)
3. and a consumenr

A message queue provides an [`asynchronous`](#-) communication between applications.


<p align=center>
  <img src="https://user-images.githubusercontent.com/36256986/220581787-026385f3-21d7-4ec5-b402-cfa633b5ffb4.png" width=600 height=100 />
</p>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)


###### 1_2_What_is_a_RabbitMQ

<img src="https://img.shields.io/badge/- 1_2_What_is_a_RabbitMQ %20- green" height=30px>

RabbitMQ is a message queue software (message broker/queue manager) that acts as [`ibtermediary`](#-) platform where different applications can send and receive messages. </br>
RabbitMQ originally implements the [`AMQP`](#-) Advance Message Queueing Protocol. </br>
But now RabbitMQ also supports several other API protocols , such as:
* [`STOMP`](#-)
* [`MQTT`](#-)
* [`HTTP`](#-)

[Prodcer](#-) is an application that `sends` messages to the RabbitMQ broker. </br>
[Consumer](#-) is an application that `reads` messages from the RabbitMQ broker.

<p align=center>
  <img src="https://user-images.githubusercontent.com/36256986/220585195-5d39c879-eb0f-4ddb-babc-fe0e8e00c70e.png" width=600 height=200 />
</p>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)


###### 1_3_RabbitMQ_in_microservices

<img src="https://img.shields.io/badge/- 1_3_RabbitMQ_in_microservices %20- green" height=30px>

RabbitMQ is one of the simplest freely available options for implementing messaging queues in microservices architecture.

<p align=center>
  <img src="https://user-images.githubusercontent.com/36256986/220586740-b43e57d5-9327-42a6-b42e-ba11ba0e9f07.png" width=600 height=300 />
</p>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)


###### 1_4_RabbitMQ_core_concepts

<img src="https://img.shields.io/badge/- 1_4_RabbitMQ_core_concepts %20- green" height=30px>

Lets' learn the follwoing concepts:
1. [Prodcer](#-) is an application that sends messages only to the RabbitMQ broker.
2. [Consumer](#-) is an application that reads messages from the RabbitMQ broker. there can be multiple consumenrs that can subscribe to RabbitMQ broker.
3. [Queue](#-) is a buffer or a storage in a RabbitMQ broker to store the messages. </br> The message is read , it is consumened and removed from the queue.</br> A message can thus only be processed exactly once.</br> We can do any number of queues in a broker.
4. [Message](#-) is the information sent from producer to consumer via RabbitMQ. (can be String, JSON, Byte Array, plain text ,HTML)

<p align=center>
  <img src="https://user-images.githubusercontent.com/36256986/220589155-968f9a48-a937-48e5-a915-0aed9336df30.png" width=600 height=200 />
</p>


5. [Exchange](#-) acts as an intermediary between the producer and the queue. </br> Instead of sending messages directly to a queue, a producer can send them to an exachange.</br> The exachange then sends those messages to one or more queues following a specified set of rules.</br> Thus, the producer does not need to know the queues that eventually receive those messages.

6. [Routing Key](#-) is a key that the Exchange looks at to decide how to route the message to queues. </br> The Routing Key is like an address for the message.
7. [Binding](#-) a Binding is a link between a queue and an exachange.

<p align=center>
  <img src="https://user-images.githubusercontent.com/36256986/220593190-b22a55b7-cf31-46c8-86db-29eecdb804ac.png" width=600 height=200 />
</p>





[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)


###### 1_5_RabbitMQ_architecture

<img src="https://img.shields.io/badge/- 1_5_RabbitMQ_architecture %20- green" height=30px>

Simple RabbitMQ architecure

<p align=center>
  <img src="https://user-images.githubusercontent.com/36256986/220596226-32b7350e-c395-477e-bc24-bba80647648a.png" width=600 height=100 />
</p>

High level RabbitMQ Architecture with multiple queues, in a complex Architecture.</br>
The important component here , is the [Exchange](#-). </br>
In this architecture 
1. the Producer sends the message with a Routing key to Exchange. 
2. The Exchange sends the message to Queue according to the Routing Key , SO now it's Binding Exchange to Queue

<p align=center>
  <img src="https://user-images.githubusercontent.com/36256986/220596473-9a260db8-64b1-42b6-a36f-0a72809935c5.png" width=600 height=400 />
</p>




[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)


------------------------------------------

###### 2_Install_RabbitMQ

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

There are several ways to Install RabbitMQ:
1. Install as server windows
2. Install server with Docker image on windows


###### 2_1_Install_RabbitMQ_on_Windows

<img src="https://img.shields.io/badge/- 2_1_Install_RabbitMQ_on_Windows %20- green" height=30px>
 
To install RabbitMQ on windows do the following:

1. Go to [RabbitMQ home page](https://www.rabbitmq.com/)
2. Click on Get Started 

![image](https://user-images.githubusercontent.com/36256986/220632096-b08ba0f6-245f-4d40-9bbc-caf5b748cc06.png)

3. Click on DownLoad and Installation

![image](https://user-images.githubusercontent.com/36256986/220632247-e5663785-622b-4246-baab-6486162bd747.png)

4. The latest release of RabbitMQ on 22-02-2023 was 3.11.9. 
5. On the page of `Downloading and Installing RabbitMQ`  click on `windows Installer` 

![image](https://user-images.githubusercontent.com/36256986/220633773-362e7dc3-682c-48ec-9d1b-a8c2daa0b746.png)

6. Click on `Using the officail Installer`

![image](https://user-images.githubusercontent.com/36256986/220638363-b6b55bcb-09fa-41f8-b31a-e0b0ab4882e8.png)

7. There must be only one `Erlang` version installed at a time. Erlang must be installed using an administrative account.
8. got To [https://www.erlang.org/](https://www.erlang.org/) home page
9. Download the latest version that compatibalibe with RabbitMQ version. </br> 
10. Download the latest version of RabbitMQ

* Check the Dependencies that ErLang and RabbitMQ , compatible with each other.

![image](https://user-images.githubusercontent.com/36256986/220640207-95d48c9f-981e-4912-8cef-e8e1f23de59f.png)

11. These are the version I've downloaded that are compatible with each Other.

![image](https://user-images.githubusercontent.com/36256986/220639727-018c3a71-de13-44e9-a1fa-fb7248d3888f.png)

12. Install Erlang (Just CLick on next w/o changing anything)
13. Install RabbitMQ (Just CLick on next w/o changing anything)


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

------------------------------------------

###### 3_Connect_to_RabbitMQ_Message_Broker

1. Go to url of : http://localhost:15672/
2. We get a `This site can’t be reached`














[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------------


###### 

<img src="https://img.shields.io/badge/- X  %20-blue" height=40px>

###### x_

<img src="https://img.shields.io/badge/- X %20- green" height=30px>

<p align=center>
  <img src="" width=600 height=100 />
</p>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------------
