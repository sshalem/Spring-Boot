<img src="https://img.shields.io/badge/-Transaction Management  %20-blue" height=70px>

###### _

|     | Subject                                              |
| :-: | :--------------------------------------------------- |
|  1  | [Transaction Management in Depth](#1_Transaction_Management_in_Depth)             |
|     | 1.1. [Types of read](#Types_of_read)             |
|     | 1.2. [Transaction Isolation Levels](#Transaction_Isolation_Levels)             |
|     | 1.3. [Transaction_Propagation_Levels](#Transaction_Propagation_Levels)             |




###### 1_Transaction_Management_in_Depth

<img src="https://img.shields.io/badge/- 1. Transaction_Management_in_Depth %20-blue" height=40px>

1. [spring-transaction-management-transactional-in-depth](https://www.marcobehler.com/guides/spring-transaction-management-transactional-in-depth)
2. [Great Link well explained from Tutorial Point](https://www.tutorialspoint.com/spring/spring_transaction_management.htm)
3. [Great video](https://www.youtube.com/watch?v=1fQtFALX80w&list=PLzS3AYzXBoj-H1SJxp2RuMMS4xUWrPV_3&ab_channel=KKJavaTutorials)


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

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### Types_of_read

<img src="https://img.shields.io/badge/- 1.1. Types_of_read %20-yellowgreen" height=30px>

https://www.youtube.com/watch?v=UgTZ1Tun-wg&list=PLzS3AYzXBoj-H1SJxp2RuMMS4xUWrPV_3&index=2&ab_channel=KKJavaTutorials

1. Dirty Reads
2. non-repeatable reads
3. phantom reads

![image](https://user-images.githubusercontent.com/36256986/205519631-5720174e-5b07-4aa6-95d7-e978bb3dbffd.png)

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### Transaction_Isolation_Levels

<img src="https://img.shields.io/badge/- 1.2. Transaction_Isolation_Levels %20-yellow" height=30px>

https://www.youtube.com/watch?v=UgTZ1Tun-wg&list=PLzS3AYzXBoj-H1SJxp2RuMMS4xUWrPV_3&index=2&ab_channel=KKJavaTutorials

1. ISOLATION_DEFAULT 
2. ISOLATION_READ_COMMITTED - Indicates that dirty reads are prevented; non-repeatable reads and phantom reads can occur.
3. ISOLATION_READ_UNCOMMITTED - Indicates that dirty reads, non-repeatable reads, and phantom reads can occur.
4. ISOLATION_REPEATABLE_READ - Indicates that dirty reads and non-repeatable reads are prevented; phantom reads can occur.
5. ISOLATION_SERIALIZABLE - Indicates that dirty reads, non-repeatable reads, and phantom reads are prevented.

![image](https://user-images.githubusercontent.com/36256986/205519380-d43007d2-79ed-4006-9c31-07c118a87dda.png)

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### Transaction_Propagation_Levels

<img src="https://img.shields.io/badge/- 1.3. Transaction_Propagation_Levels %20-yellow" height=30px>

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

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

Let's look in the example:

### [Main Class](#-)

Here we have a method with `@Transactional(propagation=Propagation.REQUIRED)` .</br>
Inside it's method , there is a call to an inner method `innerBean.testRequired()` , which is also has `@Transactional(propagation=Propagation.REQUIRED)` </br>.
In the InnerBean it throws an Exception , thus it will RollBack to the Outer Transaction. </br>
Since Outer Trnsaction is Also `Propagation.REQUIRED` , thus Outer Transaction will also RollBack.

### Note </br>
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

#### [InnerBean class](#-)

```java
 @Override
 @Transactional(propagation=Propagation.REQUIRED)
	public void testRequired() {
   throw new RuntimeException("Rollback this transaction");
 }
```

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

