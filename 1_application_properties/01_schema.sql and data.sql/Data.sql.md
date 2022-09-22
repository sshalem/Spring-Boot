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


# [h2 database ](#-)

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

---------------------------------------------------------------------------------------------------

# [mysql database ](#-)

If using during dev [mysql DB](#-) , and we want to use [```schema.sql```](#-) and [```data.sql```](#-) , do the following:

### 1. create new Spring-Boot app version 2.5 and up with following dependencies (w/o any java code):

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
spring.datasource.url=jdbc:mysql://localhost:3306/jpa?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=root

# this ensures script-based initialization is performed using schema.sql and data.sql directly.
spring.jpa.hibernate.ddl-auto=none

spring.jpa.generate-ddl=true
spring.jpa.show-sql=true
spring.jpa.open-in-view=false
spring.jpa.properties.hibernate.format_sql=true 


# we must add this config as well for schema.sql and data.sql could work
spring.sql.init.mode=always
spring.jpa.defer-datasource-initialization=true
#spring.data.jpa.repositories.bootstrap-mode=default
```

### 4. run the app and check musql database :

1. DB created 
2. Tables cretaed
3. Data inserted to tables
