<img src="https://img.shields.io/badge/-RabbitMQ  %20-blue" height=70px>

###### _

|     | Subject                                              |
| :-: | :--------------------------------------------------- |
|     | [Links](#Links)             |
|  1  | [RabbitMQ Introduction](#1_RabbitMQ_Introduction)             |
|     | [1.1. What is a Message Queueu](#1_1_What_is_a_Message_Queueu)             |
|     | [1.2. What is a RabbitMQ](#1_2_What_is_a_RabbitMQ)             |
|     | [1.3. What is a RabbitMQ](#1_3_What_is_a_RabbitMQ)             |
|     | [1.4. What is a RabbitMQ](#1_4_What_is_a_RabbitMQ)             |
|     | [1.5. What is a RabbitMQ](#1_5_What_is_a_RabbitMQ)             |
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


Lets' learn the follwoing concepts:
* Producer
* consumer
* queue
* exchange
* binding
* Routing key
* message

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

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)


###### x_

<img src="https://img.shields.io/badge/- X %20- green" height=30px>

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
