<img src="https://img.shields.io/badge/-Transaction Management  %20- blue" height=70px>

###### _

|     | Subject                                              |
| :-: | :--------------------------------------------------- |
|  1  | [Transaction Management in Depth](#1_Transaction_Management_in_Depth)             |
|     | 1.1. [Types of read](#Types_of_read)             |
|     | 1.2. [Transaction Isolation Levels](#Transaction_Isolation_Levels)             |
|     | 1.3. [Transaction_Propagation_Levels](#Transaction_Propagation_Levels)             |
|  2  | [Code Example](#2_Code_Example)             |



###### 1_Transaction_Management_in_Depth

<img src="https://img.shields.io/badge/- 1. Transaction_Management_in_Depth %20-blue" height=40px>

1. [spring-transaction-management-transactional-in-depth](https://www.marcobehler.com/guides/spring-transaction-management-transactional-in-depth)
2. [Great Link well explained from Tutorial Point](https://www.tutorialspoint.com/spring/spring_transaction_management.htm)
3. [Explain Isolation and Propagation](https://www.youtube.com/watch?v=1fQtFALX80w&list=PLzS3AYzXBoj-H1SJxp2RuMMS4xUWrPV_3&ab_channel=KKJavaTutorials)
4. [Video Explanation](https://www.youtube.com/watch?v=XL0EROsn5Yc&list=PL12XW6i6zqKv4YBdBPMkUYTjL-ARQ8sd9&ab_channel=JavaCodeHouse)
5. [Video ThorbenJanssen](https://www.youtube.com/watch?v=SUQxXg229Xg&ab_channel=ThorbenJanssen)
6. [Spring DOC](https://docs.spring.io/spring-framework/docs/4.2.x/spring-framework-reference/html/transaction.html)

* https://medium.com/javarevisited/spring-transactional-mistakes-everyone-did-31418e5a6d6b
* https://www.concretepage.com/spring/spring-transactional

A database transaction is a sequence of actions that are treated as a single unit of work.</br>
These actions should either:
* [Successful - complete entirely](#-)
* [Failure - take no effect at all](#-)

This diagram shows what happens when a method is transactinoal. </br>

<p align="center">
  <img src="https://user-images.githubusercontent.com/36256986/205864267-19cd0e02-ae7d-4088-99e2-67253b5f5c0b.png">
</p>


* Spring supports a comprehansive Transaction Management supprt
* Below is a simplified overview to show how it works. 
* Spring transaction support is enabled via AOP Proxy. 
* The caller of the method invokes the proxy (and NOT the Target) , </br>
and on this point the transaction is created and the Traget method is invoked. 
* On the way back, either the transaction is Commited or rolled back on the way out.

<p align="center">
  <img src="https://user-images.githubusercontent.com/36256986/205865904-50a8e439-4b6d-4fc0-8f45-f68eb1a7e1a3.png">
</p>



### [Enable Spring Transaction Management](#-)

* Typically it is enabled using annotation of `@EnableTransactionManagement` (Or via XML as well)
* But in Spring Boot , Most of the configuration is done for us, 
* Because we have Spring data library in the class path, Transaction Management is enabled by the Framework , So no need to do anything to enable it.
* In order to apply transaction management , we just need to add annotation of `@Transactional`.

### [Difference between javax Transaction to Springframwork Transaction](#-)

The prefered way is to use the `Transactionl` from SPring framwork (and not from JAVAX)

<img src="https://user-images.githubusercontent.com/36256986/205872651-f41c00e2-8031-4106-a118-73c8e4cf16f0.png" height=300px width=500px> 

### [Under the hood with `@Transactional`](#-)

* When `@Transactional` is present , Spring creates a Proxy which will stand between the caller and the target.
* Thus, external invocation will always call the method in Proxy , then the Proxy will invoke the actual method in the Target.
* Once method invocation is finished on the target, the Transaction will be commited or rolled back

![image](https://user-images.githubusercontent.com/36256986/205876696-0801ca77-fa1c-43cb-8895-73afe24a0a62.png)

### [Default JAVA based configuration](#-)

* `proxyTargetClass` - can be false/true. 
	* the default value is false, in which JDK (interface based) proxies are created. 
	* if true then `CGLIB` proxies (class based) will be used. (with Spring boot it is set as true, `spring.aop.proxy-target-class=true` , enabling CGLIB proxy)
* `mode` -  can be proxy/aspectJ . 
	* The dafault mode value is `mode="Proxy"`, which processes the annotated bean to be proxied using the Spring AOP framework.
	* when mode is aspectJ (AspectJ Framework)

### Note : </br>
* Transaction management is an important part of RDBMS-oriented enterprise application , </br>
to ensure data integrity and consistency (Transaction is applicable for any RDBMS MySql PostgreSql Oracle etc...) .</br>
The concept of transactions can be described with the following four key properties described as ACID.

### [ACID](#-)

* [Atomicity](#-) − A transaction should be treated as a single unit of operation, which means either the entire sequence of operations is successful or unsuccessful.
* [Consistency](#-) − This represents the consistency of the referential integrity of the database, unique primary keys in tables, etc.
* [Isolation](#-) − There may be many transaction processing with the same data set at the same time. Each transaction should be isolated from others to prevent data corruption.
* [Durability](#-) − Once a transaction has completed, the results of this transaction have to be made permanent and cannot be erased from the database due to system failure.

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### Types_of_read

<img src="https://img.shields.io/badge/- 1.1. Types_of_read %20-greenyellow" height=30px>

https://www.youtube.com/watch?v=UgTZ1Tun-wg&list=PLzS3AYzXBoj-H1SJxp2RuMMS4xUWrPV_3&index=2&ab_channel=KKJavaTutorials

1. Dirty Reads
2. non-repeatable reads
3. phantom reads

![image](https://user-images.githubusercontent.com/36256986/205519631-5720174e-5b07-4aa6-95d7-e978bb3dbffd.png)

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### Transaction_Isolation_Levels

<img src="https://img.shields.io/badge/- 1.2. Transaction_Isolation_Levels %20-greenyellow" height=30px>

https://www.youtube.com/watch?v=UgTZ1Tun-wg&list=PLzS3AYzXBoj-H1SJxp2RuMMS4xUWrPV_3&index=2&ab_channel=KKJavaTutorials

See link --> [introduction-to-transaction-isolation-levels](https://fauna.com/blog/introduction-to-transaction-isolation-levels)

### [What is an “Isolation Level”?](#-)

**Database isolation** refers to the ability of a database to allow a transaction to execute as if there are no other concurrently running transactions (even though in reality there can be a large number of concurrently running transactions). The overarching goal is to prevent reads and writes of temporary, aborted, or otherwise incorrect data written by concurrent transactions.

There is such a thing as perfect isolation (we will define this below). </br>
Unfortunately, perfection usually comes at a performance cost—in terms of :
* transaction latency (how long before a transaction completes) 
* or throughput (how many transactions per second can the system complete). 

Depending on how a particular system is architected, perfect isolation becomes easier or harder to achieve. </br>
In poorly designed systems, achieving perfection comes with a prohibitive performance cost, and users of such systems will be pushed to accept guarantees significantly short of perfection. </br>
However, even in well-designed systems, there is often a non-trivial performance benefit achieved by accepting guarantees short of perfection. </br>
Therefore, isolation levels came into existence: 
* they provide the user of a system the ability to trade off isolation guarantees for improved performance.

[ISOLATION Types:](#-)
1. ISOLATION_DEFAULT 
2. ISOLATION_READ_UNCOMMITTED - Indicates that `dirty reads, non-repeatable reads, and phantom reads can occur`.
3. ISOLATION_READ_COMMITTED -   Indicates that [`dirty reads are prevented`](#-); `non-repeatable reads` and `phantom reads` can occur.
4. ISOLATION_REPEATABLE_READ -  Indicates that [`dirty reads and non-repeatable reads are prevented`](#-); `phantom reads can occur`.
5. ISOLATION_SERIALIZABLE -     Indicates that [`dirty reads, non-repeatable reads, and phantom reads are prevented`](#-).

![image](https://user-images.githubusercontent.com/36256986/207162962-4479739a-1b39-42b1-b67b-b49322a74471.png)

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### Transaction_Propagation_Levels

<img src="https://img.shields.io/badge/- 1.3. Transaction_Propagation_Levels %20-greenyellow" height=30px>

https://www.youtube.com/watch?v=oy4VFlbH1cU&list=PLzS3AYzXBoj-H1SJxp2RuMMS4xUWrPV_3&index=3&ab_channel=KKJavaTutorials

1. PROPAGATION_REQUIRED
2. PROPAGATION_REQUIRES_NEW
3. PROPAGATION_NESTED
4. PROPAGATION_MANDATORY
5. PROPAGATION_NEVER
6. PROPAGATION_NOT_SUPPORTED
7. PROPAGATION_SUPPORTS

### [1. PROPAGATION_REQUIRED](#-) 

Spring **REQUIRED** behavior means that the same transaction will be used if there is an already opened transaction in the current bean method execution context. </br> 
If there is NO existing transaction the Spring container will create a new one. </br>
If multiple methods configured as **REQUIRED** behavior are called in a nested way they will be assigned **distinct logical transactions** but they will all share the **same physical transaction**. </br>
In short this means that if an inner method causes a transaction to rollback, the outer method will fail to commit and also rollback the transaction.

Let's look in the example:

#### Main Class

Here we have a method with `@Transactional(propagation=Propagation.REQUIRED)` .</br>
So :
* Main Class has a method (Outer Method) with `@Transactional(propagation=Propagation.REQUIRED)`
* InnerBean class has Method (Inner Method since it's called from Main Class method) with also Propagation.REQUIRED -> `@Transactional(propagation=Propagation.REQUIRED)`

Inside it's method , there is a call to an inner method `innerBean.testRequired()` , which is also has `@Transactional(propagation=Propagation.REQUIRED)` </br>.
In the InnerBean it throws an Exception , thus it will RollBack to the Outer Transaction. </br>
Since Outer Trnsaction is Also `Propagation.REQUIRED` , thus Outer Transaction will also RollBack.

#### Note </br>
 * Inner Method throws **RuntimeException** and is annotated with REQUIRED behavior. This means that it will use the same transaction as the outer bean, so outer bean transaction will fail to commit and will also roll back.
 * The only exceptions that set a transaction to rollback state by default are unchecked exceptions (like RuntimeException). If we want checked exceptions to also set transactions to rollback we must configure them to do so (this is how to do that `@Transactional(rollbackFor = Exception.class)`.


```java
@Autowired
private TestDao testDao;
	
@Autowired
private InnerBean innerBean;
	
@Override
@Transactional(propagation=Propagation.REQUIRED)
    public void testRequired(User user) {
	testDao.insertUser(user);
		
	try {
		innerBean.testRequired();
	} catch (RunTimeException ex) {
			// handle excpetion
	}
}
```

#### InnerBean class

```java
 @Override
 @Transactional(propagation=Propagation.REQUIRED)
	public void testRequired() {
   throw new RuntimeException("Rollback this transaction");
 }
```

### [2. PROPAGATION_REQUIRES_NEW](#-) 

REQUIRES_NEW behavior means that a new physical transaction will **always** be created by the container. </br>
In other words the inner transaction may commit or rollback independently of the outer transaction, i.e. the outer transaction will not be affected by the inner transaction result: they will run in `distinct physical transactions`.

In the example below the :
* Main Class has a method (Outer Method) with `@Transactional(propagation=Propagation.REQUIRED)`
* InnerBean class has Method (Inner Method since it's called from Main Class method) with Propagation.REQUIRES_NEW

The Inner Method is annotated with REQUIRES_NEW and throws a **RuntimeException** so it will set its transaction to rollback but will NOT affect the outer transaction. </br>
The Outer transaction is paused when the inner transaction starts and then resumes after the inner transaction is concluded. </br>
They run independently of each other so the outer transaction may commit successfully.

#### Main Class

```java
@Autowired
private TestDao testDao;
	
@Autowired
private InnerBean innerBean;
	
@Override
@Transactional(propagation=Propagation.REQUIRED)
    public void testRequired(User user) {
	testDao.insertUser(user);
		
	try {
		innerBean.testRequired();
	} catch (RunTimeException ex) {
			// handle excpetion
	}
}
```

#### InnerBean class

```java
 @Override
 @Transactional(propagation=Propagation.REQUIRES_NEW)
	public void testRequired() {
   throw new RuntimeException("Rollback this transaction");
 }
```

### [3. NESTED](#-) 

The **NESTED** behavior makes nested spring transactions to use the same physical transaction but sets savepoints between nested invocations so inner transactions may also rollback independently of outer transactions. </br>
This may be familiar to JDBC aware developers as the savepoints are achieved with JDBC savepoints, so this behavior should only be used with Spring JDBC managed transactions.

### [4. MANDATORY](#-) 

The MANDATORY behavior states that an existing opened transaction must already exist. </br>
If not,  an exception will be thrown by the container.

### [5. NEVER](#-) 

The NEVER behavior states that an existing opened transaction must NOT already exist. </br>
If a transaction exists,  an exception will be thrown by the container.

### [6. NOT_SUPPORTED](#-) 

The NOT_SUPPORTED behavior will execute outside of the scope of any transaction. </br>
If an opened transaction already exists it will be paused.

### [7. SUPPORTS](#-) 

The SUPPORTS behavior will execute in the scope  of a transaction if an opened transaction already exists. </br>
If there isn't already opened transaction the method will exeute anyway but in a non-transactinoal way.


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

