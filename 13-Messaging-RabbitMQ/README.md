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


###### x_

<img src="https://img.shields.io/badge/- X %20- green" height=30px>

<p align=center>
  <img src="" width=600 height=100 />
</p>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)


###### x_

<img src="https://img.shields.io/badge/- X %20- green" height=30px>

<p align=center>
  <img src="" width=600 height=100 />
</p>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)


###### x_

<img src="https://img.shields.io/badge/- X %20- green" height=30px>

<p align=center>
  <img src="" width=600 height=100 />
</p>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)


###### x_

<img src="https://img.shields.io/badge/- X %20- green" height=30px>

<p align=center>
  <img src="" width=600 height=100 />
</p>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)



[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)


------------------------------------------

######

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

######

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

###### x_

<img src="https://img.shields.io/badge/- X %20- green" height=30px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------------
