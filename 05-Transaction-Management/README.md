<img src="https://img.shields.io/badge/-Transaction Management  %20- blue" height=70px>

###### _

|     | Subject                                              |
| :-: | :--------------------------------------------------- |
|  1  | [Transaction Management in Depth](#1_Transaction_Management_in_Depth)             |
|     | 1.1. [Types of read](#Types_of_read)             |
|     | 1.2. [Transaction Isolation Levels](#Transaction_Isolation_Levels)             |
|     | 1.3. [Transaction_Propagation_Levels](#Transaction_Propagation_Levels)             |
|     | 1.4. [Proxy_with_Transaction](#Proxy_with_Transaction)             |
|  2  | [Code with Transaction Management](#2_Code_with_Transaction_Management)             |
|     | 2.1. [Test app w/o Trasnaction Management](#2_1_Test_app_without_Trasnaction_Management)             |
|     | 2.2. [Test app w/o Trasnaction Management and throws exception](#2_2_Test_app_without_Trasnaction_Management_and_throws_exception)             |
|     | 2.3. [Test app with Trasnactional](#2_3_Test_app_with_Trasnactional)             |
|  3  | [Code with Transaction Propagation](#3_Code_with_Transaction_Propagation)             |
|     | 3.1. [Test app with Propagation.REQUIRED](#3_1_Test_app_with_Propagation_REQUIRED)             |
|     | 3.2. [Test app with Propagation.REQUIRED](#)             |
|     | 3.3. [Test app with Propagation.REQUIRED](#)             |
|     | 3.4. [Test app with Propagation.REQUIRED](#)             |
|     | 3.5. [Test app with Propagation.REQUIRED](#)             |
|     | 3.6. [Test app with Propagation.REQUIRED](#)             |





###### 1_Transaction_Management_in_Depth

<img src="https://img.shields.io/badge/- 1. Transaction_Management_in_Depth %20-blue" height=40px>

1. [spring-transaction-management-transactional-in-depth](https://www.marcobehler.com/guides/spring-transaction-management-transactional-in-depth)
2. [Great Link well explained from Tutorial Point](https://www.tutorialspoint.com/spring/spring_transaction_management.htm)
3. [Explain Isolation and Propagation](https://www.youtube.com/watch?v=1fQtFALX80w&list=PLzS3AYzXBoj-H1SJxp2RuMMS4xUWrPV_3&ab_channel=KKJavaTutorials)
4. [Video Explanation](https://www.youtube.com/watch?v=XL0EROsn5Yc&list=PL12XW6i6zqKv4YBdBPMkUYTjL-ARQ8sd9&ab_channel=JavaCodeHouse)
5. [Video ThorbenJanssen](https://www.youtube.com/watch?v=SUQxXg229Xg&ab_channel=ThorbenJanssen)
6. [javaInUse - transaction tutorial](https://www.javainuse.com/spring/springtrans)
7. [javaInUse - transaction management](https://www.javainuse.com/spring/boot-transaction)
8. [javaInUse - transaction-propagation](https://www.javainuse.com/spring/boot-transaction-propagation)
9. [javaInUse - transaction-rollback](https://www.javainuse.com/spring/boot-rollback)
10. [javaInUse - transaction-isolation](https://www.javainuse.com/spring/boot-transaction-isolation)
11. [Spring DOC](https://docs.spring.io/spring-framework/docs/4.2.x/spring-framework-reference/html/transaction.html)

* https://medium.com/javarevisited/spring-transactional-mistakes-everyone-did-31418e5a6d6b
* https://www.concretepage.com/spring/spring-transactional

### [What are Database Transactions?](#-)

A database transaction is a sequence of actions that are treated as a single unit of work , which accesses and possibly modifies the contents of a database.</br>
These actions should either [Successful - complete entirely](#-) or [Failure - take no effect at all](#-)

<p align="center">
  <img src="https://user-images.githubusercontent.com/36256986/207462692-f41337cf-1b28-4802-bcb3-1bdf7e02dbd1.png">
</p>

### [explain with MySql DB](#-)

By default the transactions are [`autocommit`](#-) for mysql database. </br>
To disable [`autocommit`](#-) in MySql, use following command- [`SET autocommit = 0`](#-) </br>

See the explanation in [javaInUse - transaction management](https://www.javainuse.com/spring/boot-transaction)



This diagram shows what happens when a method is transactinoal. </br>

<p align="center">
  <img src="https://user-images.githubusercontent.com/36256986/205864267-19cd0e02-ae7d-4088-99e2-67253b5f5c0b.png">
</p>


* Spring supports a comprehansive Transaction Management supprt
* Below is a simplified overview to show how it works. 
* Spring transaction support is enabled via `AOP Proxy`. 
* The caller of the method invokes the proxy (and NOT the Target) ,and on this point the transaction is created and the Traget method is invoked. 
* Spring boot implicitly creates Proxy for the transaction annotated methods. So, for such methods the proxy acts like a wrapper which takes care of creating :
	* A transaction at the beggining of the method call
	* After method got executed , the Proxy either `commits` the transaction or `rolls it back` as a Runtime Exception
* On the way back, either the transaction is Commited or rolled back on the way out.

<p align="center">
  <img src="https://user-images.githubusercontent.com/36256986/205865904-50a8e439-4b6d-4fc0-8f45-f68eb1a7e1a3.png">
</p>



### [Enable Spring Transaction Management](#-)

* Typically it is enabled using annotation of `@EnableTransactionManagement` (Or via XML as well)
* But in Spring Boot , Most of the configuration is done for us, 
* Because we have Spring data library in the class path, Transaction Management is enabled by the Framework , So no need to do anything to enable it.
* In order to apply transaction management , we just need to add annotation of `@Transactional`.
* Spring boot implicitly creates Proxy for the transaction annotated methods. So, for such methods the proxy acts like a wrapper which takes care of creating :
	* A transaction at the beggining of the method call
	* After method got executed , the Proxy either `commits` the transaction or `rolls it back` as a Runtime Exception

### [Difference between javax Transaction to Springframwork Transaction](#-)

The prefered way is to use the `Transactionl` from Spring framwork (and not from JAVAX) </br>
following video explains difference between both [Video Explanation](https://www.youtube.com/watch?v=XL0EROsn5Yc&list=PL12XW6i6zqKv4YBdBPMkUYTjL-ARQ8sd9&ab_channel=JavaCodeHouse)


### [Under the hood with `@Transactional`](#-)

* Spring boot implicitly creates Proxy for the transaction annotated methods. So, for such methods the proxy acts like a wrapper which takes care of creating :
	* A transaction at the beggining of the method call
	* After method got executed , the Proxy either `commits` the transaction or `rolls it back` as a Runtime Exception
* When `@Transactional` is present , Spring creates a Proxy which will stand between the caller and the target.
* Thus, external invocation will always call the method in Proxy , then the Proxy will invoke the actual method in the Target.
* Once method invocation is finished on the target, the Transaction will be commited or rolled back

<p align="center">
  <img src="https://user-images.githubusercontent.com/36256986/205876696-0801ca77-fa1c-43cb-8895-73afe24a0a62.png">	
</p>

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

link to : [transaction-isolation-levels](https://learn.microsoft.com/en-us/sql/odbc/reference/develop-app/transaction-isolation-levels?view=sql-server-ver16)

1. Dirty Reads
2. non-repeatable reads
3. phantom reads

* [`Dirty Reads`](#-) - A dirty read occurs when a transaction reads data that has not yet been committed. </br>
**For example:** </br>
suppose transaction 1 updates a row. </br>
Transaction 2 reads the updated row before transaction 1 commits the update. </br>
If transaction 1 rolls back the change, transaction 2 will have read data that is considered never to have existed.

* [`Nonrepeatable Reads`](#-) A nonrepeatable read occurs when a transaction reads the same row twice but gets different data each time. </br>
**For example:** </br>
suppose transaction 1 reads a row. </br>
Transaction 2 updates or deletes that row and commits the update or delete. </br>
If transaction 1 rereads the row, it retrieves different row values or discovers that the row has been deleted.

* [`Phantoms`](#-) - A phantom is a row that matches the search criteria but is not initially seen. </br>
**For example:** </br>
suppose transaction 1 reads a set of rows that satisfy some search criteria. </br>
Transaction 2 generates a new row (through either an update or an insert) that matches the search criteria for transaction 1. </br>
If transaction 1 reexecutes the statement that reads the rows, it gets a different set of rows.

<p align="center">
  <img src="https://user-images.githubusercontent.com/36256986/205519631-5720174e-5b07-4aa6-95d7-e978bb3dbffd.png">	
</p>

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
1. ISOLATION_READ_UNCOMMITTED - Indicates that `dirty reads, non-repeatable reads, and phantom reads can occur`.
2. ISOLATION_READ_COMMITTED -   Indicates that [`dirty reads are prevented`](#-); `non-repeatable reads` and `phantom reads` can occur.
3. ISOLATION_REPEATABLE_READ -  Indicates that [`dirty reads and non-repeatable reads are prevented`](#-); `phantom reads can occur`.
4. ISOLATION_SERIALIZABLE -     Indicates that [`dirty reads, non-repeatable reads, and phantom reads are prevented`](#-).

<p align="center">
  <img src="https://user-images.githubusercontent.com/36256986/207327567-0d5d804c-1e70-4863-ae3e-86dbcfb92e4a.png">	
</p>

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

###### Proxy_with_Transaction

<img src="https://img.shields.io/badge/- 1.4. Proxy_with_Transaction %20-greenyellow" height=30px>

Spring boot implicitly creates **Proxy for the transaction annotated methods.** </br>
So, for such methods the proxy acts like a wrapper which takes care of creating :
1. A transaction at the beggining of the method call
2. commiting the trnsaction after the method executes.



[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

###### 2_Code_with_Transaction_Management

<img src="https://img.shields.io/badge/- 2. Code with Transaction Management %20-blue" height=40px>

This diagram describes the flow of the request. </br>
Once we get a Http Request to `joinOrganization` , it will be implemented in the `OrganzationServiceImpl`. </br>
`joinOrganization()` method then will invoke 2 different methods , from difereent services:
1. `employeeService.addEmployee(employee)` from `EmployeeServiceImpl`
2. `healthInsuranceService.registerEmployeeHealthInsurance(employeeHealthInsurance)` from `HealthInsuranceServiceImpl`

In this example I will show how Transaction Management works , step by step (3  sub sections that describs this 2.1. till 2.3) </br>
If a `addEmployee(employee)` is exeuted </br>
**BUT** , `registerEmployeeHealthInsurance(employeeHealthInsurance)` is not executed due to an error or exception.
**AND** , the `addEmployee(employee)` will be rolled back , and won't be commited to DB.

### [Flow Diagram](#-)

![image](https://user-images.githubusercontent.com/36256986/208326914-7ae59887-e9b6-46f1-ada8-213f4df05bd5.png)

### [Maven Project Layout](#-)

<p>
  <img src="https://user-images.githubusercontent.com/36256986/208327645-6a6f6d9e-931e-499a-b905-4c8bef65500d.png">	  
</p>
<p align="center">  
  <img src="https://user-images.githubusercontent.com/36256986/208526272-34b08ad1-7da3-4699-a0af-23c5f6cfcf8a.png">	
</p>

### [Entity](#-)

```java
@Entity
@Table(name = "employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long empId;
	private String empName;
	
	C/G/T
}
```

```java
@Entity
@Table(name = "employeeHealthInsurance")
public class EmployeeHealthInsurance {

	/**
	 * I DON'T add the GeneratedValue 
	 * Because i want to be able to setEmpId() by myself
	 */
	@Id
	private long empId;
	private String healthInsuranceSchemeName;
	private int coverageAmount;
		
	C/G/T
}
```

### [Repository & Service](#-)

```java
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee addEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public void deleteEmpolyee(long empid) {
		employeeRepository.deleteById(empid);
	}
}
```

```java
@Repository
public interface HealthInsuraceRepository extends JpaRepository<EmployeeHealthInsurance, Long> {
}

@Service
public class HealthInsuranceServiceImpl implements HealthInsuranceService {

	@Autowired
	private HealthInsuraceRepository healthInsuraceRepository;

	@Override
	public void registerEmployeeHealthInsurance(EmployeeHealthInsurance employeeHealthInsurance) {
		healthInsuraceRepository.save(employeeHealthInsurance);
	}

	@Override
	public void deleteEmployeeHealthInsuranceById(long empid) {
		healthInsuraceRepository.deleteById(empid);
	}
}
```

```java
@Service
public class OrganzationServiceImpl implements OrganizationService {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private HealthInsuranceService healthInsuranceService;

	@Override
	public void joinOrganization(Employee employee, EmployeeHealthInsurance employeeHealthInsurance) {		
		Employee _employee = employeeService.addEmployee(employee);		
		employeeHealthInsurance.setEmpId(_employee.getEmpId());		
		healthInsuranceService.registerEmployeeHealthInsurance(employeeHealthInsurance);
	}

	@Override
	public void leaveOrganization(Employee employee, EmployeeHealthInsurance employeeHealthInsurance) {
		employeeService.deleteEmpolyee(employee.getEmpId());
		healthInsuranceService.deleteEmployeeHealthInsuranceById(employeeHealthInsurance.getEmpId());
	}
}
```

### [Dto](#-)

```
public class OrganizationDto {

	private Employee employee;
	private EmployeeHealthInsurance employeeHealthInsurance;

	C/G/S/T.S
}
```

### [Controller](#-)

```java
@RestController
@RequestMapping("/transaction-management")
public class TransactionManagementController {

	@Autowired
	private OrganzationServiceImpl organzationServiceImpl;

	@PostMapping(path = "/joinOrganization")
	public String joinOrganization(@RequestBody OrganizationDto organizationDto) {

		Employee emp = organizationDto.getEmployee();
		EmployeeHealthInsurance employeeHealthInsurance = organizationDto.getEmployeeHealthInsurance();
		organzationServiceImpl.joinOrganization(emp, employeeHealthInsurance);
		return "joinOrganization successful";
	}
}
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### 2_1_Test_app_without_Trasnaction_Management

<img src="https://img.shields.io/badge/- 2.1. Test_app_without_Trasnaction_Management %20- green" height=30px>

Let's run the app, and send via postmand a request to url of `localhost:8080/transaction-management/joinOrganization` . </br>
DB shows all executed w/n issue

![image](https://user-images.githubusercontent.com/36256986/208527502-7f209dd4-76ef-4134-b73c-416f2f81c537.png)

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)


###### 2_2_Test_app_without_Trasnaction_Management_and_throws_exception

<img src="https://img.shields.io/badge/- 2.2. Test_app_without_Trasnaction_Management_and_throws_exception %20- green" height=30px>

In this example I modifyed the code in  `OrganizationServiceImpl` as follows:

```java
@Service
public class OrganzationServiceImpl implements OrganizationService {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private HealthInsuranceService healthInsuranceService;

	@Override
	public void joinOrganization(Employee employee, EmployeeHealthInsurance employeeHealthInsurance) {

		// Proxy begin Transaction Statement
		Employee _employee = employeeService.addEmployee(employee);

		if (_employee.getEmpName().equals("shabtay")) {
			throw new RuntimeException("throwing exception to test transaction rollback");
		}

		employeeHealthInsurance.setEmpId(_employee.getEmpId());
		healthInsuranceService.registerEmployeeHealthInsurance(employeeHealthInsurance);

		// commit Transaction
	}

	

	@Override
	public void leaveOrganization(Employee employee, EmployeeHealthInsurance employeeHealthInsurance) {
		employeeService.deleteEmpolyee(employee.getEmpId());
		healthInsuranceService.deleteEmployeeHealthInsuranceById(employeeHealthInsurance.getEmpId());
	}
}
```

Let's run the app, and send via postmand a request to url of `localhost:8080/transaction-management/joinOrganization` . </br>
DB shows :
* In EMPLOYEE TB - employeewas inserted even though Exception is throw.
* IN EMPLOYEE_HEALTH_INSURANCE - no records

![image](https://user-images.githubusercontent.com/36256986/208528976-21448c6c-9f3e-469e-92d4-c591bdfd3955.png)


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)


###### 2_3_Test_app_with_Trasnactional

<img src="https://img.shields.io/badge/- 2.3. Test_app_with_Trasnactional %20- green" height=30px>

Continue from previous section 2.2 where we DIDN'T had `Transactionl` annotation. </br>
Let's add `Transactionl` annotation ad method level code in  `OrganizationServiceImpl` as follows:

```java
@Service
public class OrganzationServiceImpl implements OrganizationService {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private HealthInsuranceService healthInsuranceService;

	@Override
	@Transactional
	public void joinOrganization(Employee employee, EmployeeHealthInsurance employeeHealthInsurance) {

		// Proxy begin Transaction Statement
		Employee _employee = employeeService.addEmployee(employee);

		if (_employee.getEmpName().equals("shabtay")) {
			throw new RuntimeException("throwing exception to test transaction rollback");
		}

		employeeHealthInsurance.setEmpId(_employee.getEmpId());
		healthInsuranceService.registerEmployeeHealthInsurance(employeeHealthInsurance);

		// commit Transaction
	}

	@Override
	public void leaveOrganization(Employee employee, EmployeeHealthInsurance employeeHealthInsurance) {
		employeeService.deleteEmpolyee(employee.getEmpId());
		healthInsuranceService.deleteEmployeeHealthInsuranceById(employeeHealthInsurance.getEmpId());
	}
}
```

Let's run the app, and send via postmand a request to url of `localhost:8080/transaction-management/joinOrganization` . </br>
DB shows :
* In EMPLOYEE TB - no records.
* IN EMPLOYEE_HEALTH_INSURANCE  - no records

If we now check the EMPLOYEE and the EMPLOYEE_HEALTH_INSURANCE table there are no records in both so our records are getting roll backed correctly. </br>
This is how trnsactionl annotation works:  </br>
If error occurs or Exception is thrown, any updates occured in current Transaction Session that is open , will be rolled back. </br>
Thats why we don't see the update on adding the Employee `Employee _employee = employeeService.addEmployee(employee);`

![image](https://user-images.githubusercontent.com/36256986/208529854-b1bfc582-9a5a-4483-a78d-751cf80e8121.png)

Spring Boot implicitly creates a proxy for the transaction annotated methods. </br>
So for such methods the proxy acts like a wrapper which takes care of creating a transaction at the beginning of the method call and committing the transaction after the method is executed.

![image](https://user-images.githubusercontent.com/36256986/208530535-88450b9f-d449-4fc3-954b-d8607f1792f9.png)


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------------

###### 3_Code_with_Transaction_Propagation

<img src="https://img.shields.io/badge/- 3. Code_with_Transaction_Propagation %20-blue" height=40px>

What is Transaction Propagation? </br>
Any application involves a number of services or components making a call to other services or components. </br>
Transaction Propagation indicates if any component or service will or will not participate in transaction and how will it behave if the calling calling component/service already has or does not have a transaction created already.

(I will using the same code from section 2, each step I will modify the code and explain it with example)

Suppose the user wants to call the Employee Service in both ways i.e. :
1. Call using Organization service
2. Call the the Employee Service `directly`.

![image](https://user-images.githubusercontent.com/36256986/208536445-6af41dae-b764-427a-9ec6-19f492325c11.png)

As the Employee Service may also be called directly we will need to use `Transaction annotation` with Employee Service as well.</br>
So both the services :
* Organization Service 
* and the Employee Service

will be using `Transaction annotation`. </br>
We will be looking at the various `propagation scenarios` by observing the behaviour of the **Organization and Employee service**.  </br>

There are six types of Transaction Propagations:
1. REQUIRED
2. SUPPORTS
3. NOT_SUPPORTED
4. REQUIRES_NEW
5. NEVER
6. MANDATORY

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### 3_1_Test_app_with_Propagation_REQUIRED

<img src="https://img.shields.io/badge/- 3.1. Test_app_with_Propagation_REQUIRED %20- green" height=30px>

If only annotate the method with `@Transactional` w/o specifing propagation type, by default it is **Propagation.REQUIRED** </br>

### [Calling addEmployee() Directly](#-)

if the `addEmployee()`  method is called directly , it creates it's own new Transaction.

![image](https://user-images.githubusercontent.com/36256986/208774985-56e6fefe-421e-4264-95a1-5bd1e3ecf54f.png)

### [Calling addEmployee() from another service](#-)

if the `addEmployee()`  method is called **from another service (OrganizationServiceImpl)**
1. If the calling service has a @Transaction then **method uses the existing transaction** .
2. If the calling service DOES NOT have a @Transaction then the **method creates new transaction** 

So, in case of `REQUIRED` the **addEmployee()** method makes use of the calling service transaction if it **exists**, </br>
Else, creates its own

![image](https://user-images.githubusercontent.com/36256986/208775458-4318b2aa-c611-4a99-aa74-3d987c669616.png)

### Code 

#### from class OrganzationServiceImpl

```java
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void joinOrganization(Employee employee, EmployeeHealthInsurance employeeHealthInsurance) {

		// Proxy begin Transaction Statement
		Employee _employee = employeeService.addEmployee(employee);

		if (_employee.getEmpName().equals("shabtay")) {
			throw new RuntimeException("throwing exception to test transaction rollback");
		}

		employeeHealthInsurance.setEmpId(_employee.getEmpId());
		healthInsuranceService.registerEmployeeHealthInsurance(employeeHealthInsurance);

		// commit Transaction
	}
```

#### from class EmployeeServiceImpl

```java
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Employee addEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
```

#### from class TransactionManagementController

I have 2 methods in the controller:
1. **joinOrganization** - which then Invokes the addEmployee() method from OragnizationService.
2. Invoke directly the addEmployee() method

```java
	@PostMapping(path = "/joinOrganization")
	public String joinOrganization(@RequestBody OrganizationDto organizationDto) {

		Employee emp = organizationDto.getEmployee();
		EmployeeHealthInsurance employeeHealthInsurance = organizationDto.getEmployeeHealthInsurance();
		organzationServiceImpl.joinOrganization(emp, employeeHealthInsurance);
		return "Testing Transaction Management";
	}

	@PostMapping(path = "/addEmployee")
	public String addEmployee(@RequestBody Employee employee) {

		employeeServiceImpl.addEmployee(employee);
		return "Testing Transaction Management";
	}
```

### [Test the App](#-)

Lets run the app `02-transaction-management-propagation` , and sent vis postman the requests to URL of :

![image](https://user-images.githubusercontent.com/36256986/208779306-53ca5f57-47a4-417d-83eb-1b459562b9a1.png)


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### 3_

<img src="https://img.shields.io/badge/- 2.3. Test_app_with_Trasnactional %20- green" height=30px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### 3_

<img src="https://img.shields.io/badge/- 2.3. Test_app_with_Trasnactional %20- green" height=30px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### 3_

<img src="https://img.shields.io/badge/- 2.3. Test_app_with_Trasnactional %20- green" height=30px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### 3_

<img src="https://img.shields.io/badge/- 2.3. Test_app_with_Trasnactional %20- green" height=30px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------------

######

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

###### x_

<img src="https://img.shields.io/badge/- 2.3. Test_app_with_Trasnactional %20- green" height=30px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------------

######

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------------

######

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------------

######

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------------

######

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------------

######

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

###### x_

<img src="https://img.shields.io/badge/- 2.3. Test_app_with_Trasnactional %20- green" height=30px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------------
