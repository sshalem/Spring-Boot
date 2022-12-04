<img src="https://img.shields.io/badge/-Transaction Management  %20-blue" height=70px>

###### _

|     | Subject                                              |
| :-: | :--------------------------------------------------- |
|  1  | [Transaction Management in Depth](#1_Transaction_Management_in_Depth)             |
|  1  | [](#)             |
|  1  | [](#)             |
|  1  | [](#)             |




###### 1_Transaction_Management_in_Depth

<img src="https://img.shields.io/badge/- 1. Transaction_Management_in_Depth %20-blue" height=40px>

1. https://www.marcobehler.com/guides/spring-transaction-management-transactional-in-depth </br>
2. (Great Linke well explained) https://www.tutorialspoint.com/spring/spring_transaction_management.htm </br> 
3. Great video https://www.youtube.com/watch?v=1fQtFALX80w&list=PLzS3AYzXBoj-H1SJxp2RuMMS4xUWrPV_3&ab_channel=KKJavaTutorials


A database transaction is a sequence of actions that are treated as a single unit of work.</br>
These actions should either:
* [complete entirely](#-)
* or [take no effect at all](#-)
 
Transaction management is an important part of RDBMS-oriented enterprise application to ensure data integrity and consistency (Transaction is applicable for any RDBMS MySql PostgreSql Oracle etc...) .</br>

The concept of transactions can be described with the following four key properties described as ACID.

### [ACID ](#-)

* [Atomicity](#-) − A transaction should be treated as a single unit of operation, which means either the entire sequence of operations is successful or unsuccessful.
* [Consistency](#-) − This represents the consistency of the referential integrity of the database, unique primary keys in tables, etc.
* [Isolation](#-) − There may be many transaction processing with the same data set at the same time. Each transaction should be isolated from others to prevent data corruption.
* [Durability](#-) − Once a transaction has completed, the results of this transaction have to be made permanent and cannot be erased from the database due to system failure.


[At a high level,](#-)
* Spring creates proxies for all the classes annotated with @Transactional, either on the class or on any of the methods. 
* The proxy allows the framework to inject transactional logic before and after the running method, mainly for starting and committing the transaction.
* What's important to keep in mind is that, if the transactional bean is implementing an interface, by default the proxy will be a Java Dynamic Proxy. </br>
  This means that only external method calls that come in through the proxy will be intercepted. 
  Any self-invocation calls will not start any transaction, even if the method has the @Transactional annotation.
* Another caveat of using proxies is that only public methods should be annotated with @Transactional.
* Methods of any other visibilities will simply ignore the annotation silently as these are not proxied.

### [Types of read](#-)

1. Dirty Reads
2. non-repeatable reads
3. phantom reads






[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---


######

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

######

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

