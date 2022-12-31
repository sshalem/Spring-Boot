<img src="https://img.shields.io/badge/-Caching  %20- blue" height=70px>

###### _

|     | Subject                                              |
| :-: | :--------------------------------------------------- |
|  1  | [Caching Introduction](#1_Caching_Introduction)             |
|     | 1.1. [Types of Caching](#1_1_Types_of_Caching)             |
|     | 1.2. [First-Level Vs. Second-Level Cache](#1_2_First_Level_Vs_Second_Level_Cache)             |
|     | 1.3. [Compare Redis vs EhCache](#1_3_Compare_Redis_vs_EhCache)             |
|  2  | [First Level cache exmple](#2_First_Level_cache_exmple)             |
|     | 2.1. [Test level-1](#Test_level_1)             |
|  3  | [Spring-boot-cache](#3_Spring_boot_cache)             |
|     | 3.1. [Test Spring-boot-cache](#3_1_Test_Spring_boot_cache)             |
|  4  | [EhCache TTL/TTI ](#4_EhCache)             |
|     | 4.1. [Test EhCache](#4_1_Test_EhCache)             |
|  5  | [Redis Cache](#5_Redis_Cache)             |
|     | 5.1. [Test Redis Cache](#5_1_Test_Redis_Cache)             |
|     | [Redis Cache vs Redis DATA](#Redis_Cache)             |



###### 1_Caching_Introduction

<img src="https://img.shields.io/badge/- 1. Caching Introduction %20-blue" height=40px>

### [Caching](#-)

Caching is a part of temporary memory (RAM). </br>
A Cache is :
* any temporary storage location 
* lies between the application and persistence database (or a third-party application ) , that stores the most frequently or recently accessed data </br>
so that future requests for that data can be served faster. 

It increases `data retrieval performance` , by reducing the need to access the underlying slower storage layer. </br>
Data access from memory is always faster in comparison to fetching data from the database. </br>
Caching keeps frequently accessed objects, images, and data closer to where you need them, speeding up access by not hitting the database or any third-party application multiple times for the same data and saving monetary costs. </br>
Data that does not change frequently can be cached. </br>

![image](https://user-images.githubusercontent.com/36256986/209462606-6a9cdee4-3a53-421f-baa7-5585e5ef2bb6.png)


### [Why should we use the cache?](#-)

The primary reason for using cache is to make data access faster and less expensive. </br>
When the highly requested resource is requested multiple times, it is often beneficial for the developer to cache resources so that it can give responses quickly.</br> Using cache in an application enhances the performance of the application. </br>
Data access from memory is always faster in comparison to fetching data from the database. </br>
It reduces both monetary cost and opportunity cost. </br>

### [What data should be cached?](#-)

* The data that do not change frequently.
* The frequently used read query in which results does not change in each call, at least for a period.


###### 1_1_Types_of_Caching

<img src="https://img.shields.io/badge/- 1.1. Types_of_Caching %20- green" height=30px>

### [Types of Caching](#-)

There are four types of caching are as follows:
1. In-memory Caching
2. Database Caching
3. Web server Caching
4. CDN (Content Delivery Network) Caching

#### [1. In-memory Caching](#-)

**In-memory caching** increases the performance of the application. </br>
It is the area that is frequently used. </br>
[`Memcached`](#-) and [`Redis`](#-) are examples of in-memory caching. </br>
It stores key-value between application and database. </br>
Redis is an in-memory, distributed, and advanced caching tool that allows backup and restore facility. </br>
We can manage cache in distributed clusters, also. </br>

#### [2. Database Caching](#-)

Database caching is a mechanism that generates web pages on-demand (dynamically) by fetching the data from the database. </br>
It is used in a multi-tier environment that involved clients, web-application server, and database. </br>
It improves scalability and performance by distributing a query workload. </br>
The most popular **database caching** is the **first level cache of Hibernate**.  </br>
See below ,explained more detailed Level1/Level2 database caching with Hibernate.

#### [3. Web server Caching](#-)

Web server caching is a mechanism that stores data for reuse.  </br>
For example, a copy of a web page served by a web server.  </br>
It is cached for the first time when a user visits the page.  </br>
If the user requests the same next time, the cache serves a copy of the page. </br>
It avoids server form getting overloaded.  </br>
Web server caching enhances the page delivery speed and reduces the work to be done by the backend server.


#### [4. CDN (Content Delivery Network) Caching](#-)

The CDN stands for **Content Delivery Network**. </br>
It is a component used in modern web applications. </br>
It improves the delivery of the content by replicating commonly requested files (such as HTML Pages, stylesheet, JavaScript, images, videos, etc.) across a globally distributed set of caching servers. </br>
It is the reason CDN becomes more popular. </br>
The CDN reduces the load on an application origin and improves the user experience. </br>
It delivers a local copy of the content from a nearby cache edge (a cache server that is closer to the end-user), or a Point of Presence (PoP).

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

1_3_Compare_Redis_vs_EhCache

###### 1_2_First_Level_Vs_Second_Level_Cache

<img src="https://img.shields.io/badge/- 1.2. Hibernate Caching (Database Caching) : First_Level Vs Second_Level Cache %20- green" height=30px>

### [L1 caching](#-) </br>

https://dzone.com/articles/caching-in-hibernate-with-redis </br>

The first-level cache (also known as the L1 cache) is associated with Hibernate's `Session` object, which represents a connection between a Java application and a SQL database.  (Level 1 cache ,by default enabled)</br> 
This means that the first-level cache is only available for as long as the Session exists. </br>
Each first-level cache is only accessible by the Session object with which it is associated.
When an entity is queried from the database for the first time, it is stored in the first-level cache associated with that Session. </br>
Any later queries to this same entity during the same Session will retrieve the entity from the cache, instead of from the database.

### [L2 caching](#-) </br>

https://www.youtube.com/watch?v=79s1d16ZltU&ab_channel=DevTalkers  </br>
https://dzone.com/articles/caching-in-hibernate-with-redis </br>

The second-level cache (also known as the L2 cache) is disabled by default but can be enabled by modifying Hibernate's configuration settings. </br>
This cache is associated with Hibernate's `SessionFactory` object and is mainly used to store data that should persist across Sessions. </br>
Before looking in the second-level cache:
* applications will always search the first-level cache for the presence of a given entity.


So, Level 2 cache is very powerful because data here is cached across sessions.
But level two cache needs some additional work.
Hibernate `DOES NOT` have in built support for it We use caching providers such as :
* EhCache which is very popular
* Swaram cache 
* Jboss tree cache
* OS cache 
* REDIS cache
 
but ehCache is the , most popular , very easy to configure , very powerful.

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

### [Concurrency Strategies](#-)

A concurrency strategy is a mediator, which is responsible for storing items of data in the cache and retrieving them from the cache. </br>
If you are going to enable a second-level cache, you will have to decide, for each persistent class and collection, which cache concurrency strategy to use.
Hibernate cache strategies :

1. [Transactional](#-) − Use this strategy for read-mostly data where it is critical to prevent stale data in concurrent transactions, in the rare case of an update.
2. [Read-write](#-) − Again use this strategy for read-mostly data where it is critical to prevent stale data in concurrent transactions, in the rare case of an update.
3. [Nonstrict-read-write](#-) − This strategy makes no guarantee of consistency between the cache and the database. Use this strategy if data hardly ever changes and a small likelihood of stale data is not of critical concern.
4. [Read-only](#-) − A concurrency strategy suitable for data, which never changes. Use it for reference data only.

Every cache provider is not compatible with every concurrency strategy. </br>
The following compatibility matrix will help you choose an appropriate combination.

![image](https://user-images.githubusercontent.com/36256986/209463807-d7af6e4d-d4e6-49d9-b14f-7c9df792594a.png)

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)




###### 1_3_Compare_Redis_vs_EhCache

<img src="https://img.shields.io/badge/- 1.3. Compare_Redis_vs_EhCache %20- green" height=30px>

link [Redis vs EhCache](https://redisson.org/feature-comparison-redis-vs-ehcache.html)

### [What is Redis?](#-)

Redis is an :
* open-source, in-memory data structure project that can be used as a key-value database
* a cache
* and a message broker. 

It includes support for many different abstract data structures, such as strings, lists, and maps. There are a number of options, such as Redisson, for developers who want to use Redis with the Java programming language.

### [What is Ehcache?](#-)

Ehcache is an :
* open-source distributed cache in Java. 

It includes a number of useful features such as REST and SOAP APIs, as well as memory and disk stores. Ehcache is often used to integrate with other Java frameworks such as Spring, Hibernate and MyBatis.



link [Redis vs hazelcast](https://redisson.org/feature-comparison-redis-vs-hazelcast.html)

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------

###### 2_First_Level_cache_exmple

<img src="https://img.shields.io/badge/- 2. First_Level_cache_exmple %20-blue" height=40px>

https://javatute.com/hibernate/hibernate-first-level-cache-example-using-spring-boot/

Let's look in the following code and see how [session-level-1 works](#-)

#### [Package layout](#-)

![image](https://user-images.githubusercontent.com/36256986/209920370-55cb0faf-0a37-4fcd-87e1-04f47548e10f.png)

#### [Dependencies](#-)

![image](https://user-images.githubusercontent.com/36256986/209920427-31fb28b9-ba79-4f5f-9018-a7e6c5014121.png)

#### [Code](#-)

```java
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String productName;
    private int price;
    private String description;
 
    G/S/Hash/Equals
}
```

```java
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findProductByProductName(String productName);
}
```

```java
public interface ProductService {
    Product getById(long id);
    Product getProductByProductName(String productName);
}
```


In the code of `ProductServiceImpl` I have 2 2 methods:
1. `getById(long id)` - Is a CRUD repository method
2. `getProductByProductName(String productName)` - It's a find method which I wrote

I want to show the difference behaviour between the methods. (See in the Test app section)

```java
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional
    public Product getById(long id) {
	System.out.println("getById - First time - from database");
	Optional<Product> productResponse = productRepository.findById(id);
	
	System.out.println("getById - Second time - from cache");
	productResponse = productRepository.findById(id);
	
	System.out.println("getById - Third time - from cache");
	productResponse = productRepository.findById(id);
	
	System.out.println("getById - Fourth time - from cache");
	productResponse = productRepository.findById(id);
	
	Product product = productResponse.get();
	return product;
    }

    @Override
    @Transactional
    public Product getProductByProductName(String productName) {
	System.out.println("getProductByProductName - First time - from database");
	Product productResponse = productRepository.findProductByProductName(productName);
	
	System.out.println("getProductByProductName - Second time - from cache");
	productResponse = productRepository.findProductByProductName(productName);
	
	System.out.println("getProductByProductName - Third time - from cache");
	productResponse = productRepository.findProductByProductName(productName);
	
	System.out.println("getProductByProductName - Fourth time - from cache");
	productResponse = productRepository.findProductByProductName(productName);
	
	return productResponse;
    }
}
```

```java
@RestController
@RequestMapping("/level-1")
public class CacheLevel1Controller {

    @Autowired
    private ProductServiceImpl productService;

    @GetMapping(path = "/getById/{id}")
    public Product getById(@PathVariable("id") long id) {
	System.out.println("<<<<<<<<<<------------------->>>>>>>>>>> \n");
       	return productService.getById(id);
    }
    
  
    @GetMapping(path = "getProductByProductName/{productName}")
    public Product getProductByProductName(@PathVariable("productName") String productName) {
	System.out.println("<<<<<<<<<<------------------->>>>>>>>>>> \n");
	return productService.getProductByProductName(productName);
    }
}
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### Test_level_1

<img src="https://img.shields.io/badge/- 2.1. Test_level_1 %20- green" height=30px>

Let's run the code for level-1 , and sent via postman request for each of the GET method :

![image](https://user-images.githubusercontent.com/36256986/209921263-55673942-a1b7-4cc0-a966-86d904a9c226.png)

Console shows the following:

1. when we sent request to invoke the `getById()` , we can see that first level cache is working as expected
2. when we sent request to invoke the `getProductByProductName()` , It didn't work , each time a new SQL was written (Don't know why it didn't work for this method).

![image](https://user-images.githubusercontent.com/36256986/209921894-7d0817dc-3998-48a1-82a7-e117e7ef18f5.png)



[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------

###### 3_Spring_boot_cache

<img src="https://img.shields.io/badge/- 3. Spring_boot_cache  %20-blue" height=40px>

In this project we will see the usage of Spring-boot-cache. </br>
See how cache works with methods of: [`create(post)`](#-) [`read (get)`](#-) [`update(put)`](#-) [`delete`](#-)

### [Dependencies](#-)

Besides the rgular dependecies of DB's , JPA & WEB , I also add the following dependency:

![image](https://user-images.githubusercontent.com/36256986/210153185-ce06c21e-bb5b-4e2a-928f-e597e73dabea.png)

```sql
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-cache</artifactId>
</dependency>
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### 3_1_Test_Spring_boot_cache

<img src="https://img.shields.io/badge/- 3.1. Test_Spring_boot_cache %20- green" height=30px>



[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------


###### 4_EhCache

<img src="https://img.shields.io/badge/- 4. EhCache  %20-blue" height=40px>

https://www.baeldung.com/hibernate-second-level-cache

[EhCache :](#-)
1. fast and lightwight , used by most of JAVA EE apps, that use Hibernate
2. Supports both In Memory and Disk based Caching. We can store them in the RAM, or serialize them and store them on Disk

[EhCache TTL / TTI / Eviction](#-)

https://www.ehcache.org/documentation/2.7/configuration/data-life.html

[Steps for caching](#-):
1. add MAVEN dependency
2. enable cache for the application
3. create ehcache.xml 
4. make Entities cachable
5. Test Caching


###### 4_1_Test_EhCache

<img src="https://img.shields.io/badge/- 4.1. Test_EhCache %20- green" height=30px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------


###### 5_Redis_Cache

<img src="https://img.shields.io/badge/- 5. Redis_Cache %20-blue" height=40px>


###### 5_1_Test_Redis_Cache

<img src="https://img.shields.io/badge/- 5.1. Test_Redis_Cache %20- green" height=30px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------

###### 

<img src="https://img.shields.io/badge/- Redis_Cache %20-blue" height=40px>

### [What is REDIS data?](#-)

Spring Data REDIS - https://www.youtube.com/watch?v=oRGqCz8OLcM&ab_channel=JavaTechie

Good link from youtube : https://www.youtube.com/watch?v=oUDpmINwJ5g&ab_channel=Saggu

### [What is REDIS cachce?](#-)

Spring Redis Cache - https://www.youtube.com/watch?v=vpe4aDu5ixI&ab_channel=JavaTechie

several ways How to Install Redis on windows 10 

1. https://dev.to/divshekhar/how-to-install-redis-on-windows-10-3e99  </br>
2. https://www.youtube.com/watch?v=6mtu_dFxm28&ab_channel=TechNuggets
3. https://redis.io/docs/getting-started/installation/install-redis-on-windows/   - Since Redis only works on Linux , Redis offical doc says to install WSL

### How to use REDIS as Cache

https://dzone.com/articles/caching-in-hibernate-with-redis

https://dzone.com/articles/hibernate-redis-and-l2-cache-performance




[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------

###### 

<img src="https://img.shields.io/badge/- X  %20-blue" height=40px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------


######

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

###### x_

<img src="https://img.shields.io/badge/- X %20- green" height=30px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)



[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------



######

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

###### x_

<img src="https://img.shields.io/badge/- X %20- green" height=30px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)



[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------



######

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

###### x_

<img src="https://img.shields.io/badge/- X %20- green" height=30px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)



[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------



######

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

###### x_

<img src="https://img.shields.io/badge/- X %20- green" height=30px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)



[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------



######

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

###### x_

<img src="https://img.shields.io/badge/- X %20- green" height=30px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)



[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------
