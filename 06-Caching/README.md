<img src="https://img.shields.io/badge/-Caching  %20-blue" height=70px>

###### _

|     | Subject                                              |
| :-: | :--------------------------------------------------- |
|  1  | [Caching Introduction](#1_Caching_Introduction)             |
|  2  | [Redis Cache vs Redis DATA](#2_Redis_Cache_vs_Redis_DATA)             |



###### 1_Caching_Introduction

<img src="https://img.shields.io/badge/- 1. Caching Introduction %20-blue" height=40px>

### [Caching](#-)

Caching is a part of temporary memory (RAM). </br>
A Cache is :
* any temporary storage location 
* lies between the application and persistence database 
* or a third-party application that stores the most frequently or recently accessed data </br>
so that future requests for that data can be served faster. 

It lies between the application and persistence database. </br>
It stores the recently used data that reduces the number of database hits as much as possible. </br>
In other words, caching is to store data for future reference.

It increases data retrieval performance by reducing the need to access the underlying slower storage layer. Data access from memory is always faster in comparison to fetching data from the database. Caching keeps frequently accessed objects, images, and data closer to where you need them, speeding up access by not hitting the database or any third-party application multiple times for the same data and saving monetary costs. Data that does not change frequently can be cached.


The main Purpose of caching is to improve performance. </br>


Hibernate supports caching in 2 levels:
1. level 1 - hibernate session level (Enabled by Default) 
2. level 2 - Session Factory cache, we need to configure it, in order to be able to use multiple session for the same cache.

So , `sessionfactory` and `session` are the low level hibernate objects which are internally used by hibernate. </br>
(If we don't use JPA, We didn't have spring data , we used to directly use these in our applicaitons now we no longer use them). </br>

But internally if we use Level 1 cache (which is free ,always there ,by default enabled) it happens at the session level , </br>
But , if we configure level 2 caching (**which needs additional steps**) need to do at the session factory level . </br>
A `Sessionfactory` is used to create multiple hibernate sessions.

### [Level 1 caching](#-) </br>

Level one cache comes for free. 
For example when client one accesses our application internally the hibernate session is used by JPA (by spring data). 
The very first time the data is loaded from the database , its put into the cache. </br>
The next time the client accesses the same data the session will fetch it from the cache instead of going against the database. </br>
If another client accesses the application and if a different hibernates session is used , this cache will not be referred to. </br>
It will have its own cache and the next time the client accesses it ,this session will check against that particular cache and not the earlier cache. </br>

### [Level 2 caching](#-) </br>
so if we use Level 2 caching, objects will be cached at the session factory level. </br>
They are shared across hibernate sessions. </br>
So, if multiple users are accessing our application and we are using multiple hibernate sessions , the cached objects will be shared across those user sessions as well. </br>

SessionFactory is responsible for creating different hibernates sessions and it will have a common cache. </br>
So all the sessions will share this cache when this client access our application. </br>
The session loads, executes a select query,loads the object and it will store it in the cache and then it will send it back to the client. </br>
WHen another client access our application , a different session is used but that session will first check to see if the data is there in the Level 2 cache (Because That is how we configure it) </br>
And if that data is there then there will be no database queries , that data will be sent back to the client. </br>

So, Level 2 cache is very powerful because data here is cached across sessions.
But level two cache needs some additional work.
Hibernate `DOES NOT` have in built support for it We use caching providers such as :
* ehCache which is very popular
* Swaram cache 
* Jboss tree cache
* OS cache 
* Tangosol cache etc. 
 
but ehCache is the :
* most popular 
* very easy to configure 
* very powerful 

which it will be configuring as Level 2 cache for hibernate.


Good link from youtube : https://www.youtube.com/watch?v=oUDpmINwJ5g&ab_channel=Saggu

Supporting caching providers:

* Generic
* JCache (JSR-107) (EhCache 3, Hazelcast, Infinispan, and others)
* EhCache 2.x
* Hazelcast
* Infinispan
* Couchbase
* Redis
* Caffeine
* Simple

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

###### 2_Redis_Cache_vs_Redis_DATA

<img src="https://img.shields.io/badge/- 2_Redis_Cache %20-blue" height=40px>

### [What is REDIS data?](#-)

Spring Data REDIS - https://www.youtube.com/watch?v=oRGqCz8OLcM&ab_channel=JavaTechie

### [What is REDIS cachce?](#-)

Spring Redis Cache - https://www.youtube.com/watch?v=vpe4aDu5ixI&ab_channel=JavaTechie

several ways How to Install Redis on windows 10 

1. https://dev.to/divshekhar/how-to-install-redis-on-windows-10-3e99  </br>
2. https://www.youtube.com/watch?v=6mtu_dFxm28&ab_channel=TechNuggets
3. https://redis.io/docs/getting-started/installation/install-redis-on-windows/   - Since Redis only works on Linux , Redis offical doc says to install WSL

### How to use REDIS as Cache






[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

######

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

