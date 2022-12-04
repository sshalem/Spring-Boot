<img src="https://img.shields.io/badge/-Transaction Management  %20-blue" height=70px>

###### _

|     | Subject                                              |
| :-: | :--------------------------------------------------- |
|  1  | [Transaction Management](#1_Transaction_Management)             |



###### 1_Transaction_Management

<img src="https://img.shields.io/badge/- 1. Transaction_Management Introduction %20-blue" height=40px>

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

@Transactional, meaning that any failure causes the entire operation to roll back to its previous state and to re-throw the original exception. </br>


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

