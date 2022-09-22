see the answer from stack overflow https://stackoverflow.com/questions/38040572/spring-boot-loading-initial-data:

If you're using Spring Boot 2, database initialization only works for embedded databases (H2, HSQLDB, ...). </br>
If you want to use it for other databases as well, you need to change the initialization mode property: 

#### [Spring Boot < v2.5.0](#-)
```sql
spring.datasource.initialization-mode=always 
```

#### [Spring Boot >= v2.5.0](#-)
```sql 
spring.sql.init.mode=always # 
```

From spring-boot 2.5.0 and up use the following config from Spring docs:

https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto.data-initialization.using-basic-sql-scripts

* By default, SQL database initialization is only performed when using an embedded in-memory database. </br>
* To always initialize an SQL database, irrespective of its type, set [```spring.sql.init.mode=always```](#-) </br>
* If you want script-based ```DataSource``` initialization to be able to build upon the schema creation performed by Hibernate, </br> 
set [```spring.jpa.defer-datasource-initialization=true```](#-) </br>


### Example: 
* if Using H2 DB for developement [**NO NEED**](#-) to add the feature of [```spring.sql.init.mode=always```](#-)
* if Using MySql, PostgreSql we must add [```spring.sql.init.mode=always```](#-) , otherwise we won't be able to Init DB with [```data.sql```](#-)

```sql
spring.datasource.url=jdbc:mysql://localhost:3306/<database_name>?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=root

# this ensures script-based initialization is performed using schema.sql and data.sql directly.
spring.jpa.hibernate.ddl-auto=none
# spring.jpa.hibernate.ddl-auto=create

spring.jpa.generate-ddl=true

spring.sql.init.mode=always
spring.jpa.defer-datasource-initialization=true
spring.data.jpa.repositories.bootstrap-mode=default
```
