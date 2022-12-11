<img src="https://img.shields.io/badge/-schema and data initialization%20-blue" height=40px>

###### _

|     |  Subject           |
|:---:|:------------------------------| 
|  1  |[H2](#H2_database)   | 
|  2  |[MySql](#MySql_database)  |   
|  3  |[PostgreSql](#PostgreSql_database)  |  


see the answer from stack overflow https://stackoverflow.com/questions/38040572/spring-boot-loading-initial-data:

From spring-boot 2.5.0 and up use the following config from Spring docs:

https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto.data-initialization.using-basic-sql-scripts

* By default, SQL database initialization is only performed when using an embedded in-memory database. </br>
* To always initialize an SQL database, irrespective of its type, set [```spring.sql.init.mode=always```](#-) </br>
* If you want script-based ```DataSource``` initialization to be able to build upon the schema creation performed by Hibernate, </br> 
set [```spring.jpa.defer-datasource-initialization=true```](#-).
* In other words ,if you want to use [```data.sql```](#-) to populate a [```schema```](#-) created by Hibernate, set [```spring.jpa.defer-datasource-initialization=true```](#-) </br>.


### Summary , Applicable for [Spring Boot >= v2.5.0](#-) and up : 
*  If you're using Spring Boot 2 , and Using H2 DB for developement [**NO NEED**](#-) to add the feature of [```spring.sql.init.mode=always```](#-) .database initialization only works for embedded databases (H2, HSQLDB, ...). </br>
* If you want to use it for other databases as well, you need to change the initialization mode property. If Using MySql, PostgreSql we must add [```spring.sql.init.mode=always```](#-) , otherwise we won't be able to Init DB with [```data.sql```](#-)


###### H2_database

<img src="https://img.shields.io/badge/- H2_database %20-blue" height=40px>

If using during dev [only h2 DB](#-) , and we want to use [```schema.sql```](#-) and [```data.sql```](#-) , do the following:

### 1. create new Spring-Boot app version 2.5 and up (While writing this lines the current version is 2.6.11) with following dependencies:

![image](https://user-images.githubusercontent.com/36256986/191862030-bb8986c9-808e-4ac9-82db-2283b1798095.png)

### 2. create [```schema.sql```](#-) and [```data.sql```](#-) files and place them in the folder of resources :

I didn't write java code, it's all made by [```schema.sql```](#-) and [```data.sql```](#-), all data will be present in H2 DB w/o any java code.

![image](https://user-images.githubusercontent.com/36256986/191861895-e8a213c6-20ca-4623-9654-8a8459bd62f7.png)

##### [```schema.sql```](#-)

```sql
DROP TABLE IF EXISTS user_entity;
DROP TABLE IF EXISTS role_entity;

CREATE TABLE IF NOT EXISTS user_entity(
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR (20),
email VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS role_entity(
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR (20)
);
```

##### [```data.sql```](#-)

```sql
INSERT INTO user_entity(id ,name ,email) VALUES (1, 'shabtay' , 'shabtay.shalem@gmail.com');
INSERT INTO user_entity(id ,name ,email) VALUES (2, 'karin' , 'karin.shalem@gmail.com');
INSERT INTO user_entity(id ,name ,email) VALUES (3, 'odel' , 'odel.shalem@gmail.com');
```

### 3. config [```application.properties```](#-) as follows :

##### [```application.properties```](#-)

```sql
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

# Enabling H2 Console
spring.h2.console.enabled=true

# Custom H2 Console URL from /h2-console to /h2
spring.h2.console.path=/h2
```

### 4. run the app and check h2 console that :

1. DB created 
2. Tables cretaed
3. Data inserted to tables

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

------------------------------------------------------------------------------------


###### MySql_database

<img src="https://img.shields.io/badge/- MySql_database %20-blue" height=40px>

If using during dev [mysql DB](#-) , and we want to use [```schema.sql```](#-) and [```data.sql```](#-) , do the following:

### 1. create new Spring-Boot app version 2.5 and up with following dependencies (w/o any java code):

![image](https://user-images.githubusercontent.com/36256986/191862030-bb8986c9-808e-4ac9-82db-2283b1798095.png)

### 2. create [```schema.sql```](#-) and [```data.sql```](#-) files and place them in the folder of resources :

I didn't write java code, it's all made by [```schema.sql```](#-) and [```data.sql```](#-), all data will be present in MySql DB w/o any java code.

![image](https://user-images.githubusercontent.com/36256986/191861895-e8a213c6-20ca-4623-9654-8a8459bd62f7.png)

##### [```schema.sql```](#-)

```sql
DROP TABLE IF EXISTS user_entity;
DROP TABLE IF EXISTS role_entity;

CREATE TABLE IF NOT EXISTS user_entity(
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR (20),
email VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS role_entity(
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR (20)
);
```

##### [```data.sql```](#-)

```sql
INSERT INTO user_entity(id ,name ,email) VALUES (1, 'shabtay' , 'shabtay.shalem@gmail.com');
INSERT INTO user_entity(id ,name ,email) VALUES (2, 'karin' , 'karin.shalem@gmail.com');
INSERT INTO user_entity(id ,name ,email) VALUES (3, 'odel' , 'odel.shalem@gmail.com');
```

or we can define it w/o the `id`, and let `MySql` to to it for us:

```sql
INSERT INTO user_entity(name ,email) VALUES ('shabtay' , 'shabtay.shalem@gmail.com');
INSERT INTO user_entity(name ,email) VALUES ('karin' , 'karin.shalem@gmail.com');
INSERT INTO user_entity(name ,email) VALUES ('odel' , 'odel.shalem@gmail.com');
```

### 3. config [```application.properties```](#-) as follows :

##### [```application.properties```](#-)

```sql
spring.datasource.url=jdbc:mysql://localhost:3306/jpa?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=root

# this ensures script-based initialization is performed using schema.sql and data.sql directly.
spring.jpa.hibernate.ddl-auto=none

spring.jpa.generate-ddl=true
spring.jpa.show-sql=true
spring.jpa.open-in-view=false
spring.jpa.properties.hibernate.format_sql=true 

# ==========================================
#  we must add this config as well for 
#  schema.sql and data.sql could work
# ==========================================
spring.sql.init.mode=always
spring.jpa.defer-datasource-initialization=true
#spring.data.jpa.repositories.bootstrap-mode=default

# When using java version JDK11 use with mysql dialect
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL8Dialect
```

### 4. run the app and check mysql database :

1. DB created 
2. Tables cretaed
3. Data inserted to tables

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)


------------------------------------------------------------------------------------

###### PostgreSql_database

<img src="https://img.shields.io/badge/- PostgreSql_database %20-blue" height=40px>

1. https://stackoverflow.com/questions/4448340/postgresql-duplicate-key-violates-unique-constraint
2. https://stackoverflow.com/questions/244243/how-to-reset-postgres-primary-key-sequence-when-it-falls-out-of-sync
3. https://www.youtube.com/watch?v=2gZLq_QmWk0&ab_channel=KnowledgeBase
4. https://stackoverflow.com/questions/787722/whats-the-postgresql-datatype-equivalent-to-mysql-auto-increment


![image](https://user-images.githubusercontent.com/36256986/206897660-0d237e3e-d71b-4dbc-bfa8-7d0e26d387be.png)

![image](https://user-images.githubusercontent.com/36256986/206897538-00518c8a-dfdf-48f5-a42b-fc87d671c86f.png)

###  [SERIAL or BIGSERIAL](#-)

If you’ve been using MySQL, you know that AUTO_INCREMENT is a very popular choice. </br>
When migrating to PostgreSQL, you will notice that SERIAL or BIGSERIAL column types can be used just like AUTO_INCREMENT in MySQL.
SERIAL is an `auto-incremented integer` column that takes 4 bytes while `BIGSERIAL is an auto-incremented bigint` column taking 8 bytes. </br>
Behind the scenes, PostgreSQL will use a sequence generator to generate the SERIAL column values upon inserting a new ROW.


### 2. create [```schema.sql```](#-) and [```data.sql```](#-) files and place them in the folder of resources :

I didn't write java code, it's all made by [```schema.sql```](#-) and [```data.sql```](#-), all data will be present in Postgresql DB w/o any java code.

![image](https://user-images.githubusercontent.com/36256986/191861895-e8a213c6-20ca-4623-9654-8a8459bd62f7.png)

##### [```schema.sql```](#-)

```sql
DROP TABLE IF EXISTS user_entity;
DROP TABLE IF EXISTS role_entity;

CREATE TABLE IF NOT EXISTS user_entity(
id BIGSERIAL PRIMARY KEY,
name VARCHAR (20),
email VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS role_entity(
id BIGSERIAL PRIMARY KEY,
name VARCHAR (20)
);
```

##### [```data.sql```](#-)

With Postgresql , don't add the id , other wise we will have a sync issue , and get duplications, thus put insret as follows:

```sql
INSERT INTO user_entity(name ,email) VALUES ('shabtay' , 'shabtay.shalem@gmail.com');
INSERT INTO user_entity(name ,email) VALUES ('karin' , 'karin.shalem@gmail.com');
INSERT INTO user_entity(name ,email) VALUES ('odel' , 'odel.shalem@gmail.com');
```


### 3. config [```application.properties```](#-) as follows :

##### [```application.properties```](#-)

```sql
# ===============================
# 		DATA SOURCE
# =============================== 
#spring.datasource.url=jdbc:postgresql://localhost:5342/jpa
spring.datasource.url=jdbc:postgresql://localhost/jpa
spring.datasource.username=postgres
spring.datasource.password=root

# this ensures script-based initialization is performed using schema.sql and data.sql directly.
spring.jpa.hibernate.ddl-auto=none

spring.jpa.generate-ddl=true
spring.jpa.show-sql=true
spring.jpa.open-in-view=false
spring.jpa.properties.hibernate.format_sql=true 

# ==========================================
#  we must add this config as well for 
#  schema.sql and data.sql could work
# ==========================================
spring.sql.init.mode=always
spring.jpa.defer-datasource-initialization=true
#spring.data.jpa.repositories.bootstrap-mode=default

# Naming strategy
spring.jpa.hibernate.naming.implicit-strategy=org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyHbmImpl
spring.jpa.hibernate.naming.physical-strategy=org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy

# Allows Hibernate to generate SQL optimized for a particular DBMS
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

### 4. run the app and check postgresql database :

1. DB created 
2. Tables cretaed
3. Data inserted to tables

![image](https://user-images.githubusercontent.com/36256986/206899321-4edc5faa-1af1-4034-af7c-64ff3199874d.png)


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

------------------------------------------------------------------------------------

######

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)
