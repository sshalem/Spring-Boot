This is  ony work on Sprig-boot version up to 2.4.12
```sql
# =================================================================================
# to Initialize DB by using a file of "data.sql" 
# need to set the following features
# on Spring Boot vewrsion 2.4.12 it worked as expected
# when tried on Spring boot version 2.5.8 -> did not work as expected , alot of problems created
# =================================================================================
spring.datasource.platform=mysql
spring.datasource.initialization-mode=always
```
see the answer fro stack overflow https://stackoverflow.com/questions/38040572/spring-boot-loading-initial-data:

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

By default, SQL database initialization is only performed when using an embedded in-memory database. </br>
To always initialize an SQL database, irrespective of its type, set [```spring.sql.init.mode=always```](#-) </br>
If you want script-based ```DataSource``` initialization to be able to build upon the schema creation performed by Hibernate, </br> 
set [```spring.jpa.defer-datasource-initialization=true```](#-) </br>



```sql
# =================================================================================
# From Spring boot version 2.5.0 amd above 
# to create data.sql file need to add following properties
# from Baeldung https://www.baeldung.com/spring-boot-data-sql-and-schema-sql
# script-based initialization is performed by default only for embedded databases, 
# to always initialize a database using scripts, we'll have to use:
#           spring.sql.init.mode=always
# Example: 
#          if Using H2 DB for developement no need to add the feature of spring.sql.init.mode=always
#          if Using MySql, PostgreSql we must add spring.sql.init.mode=always , otherwise we won't be able to Init DB with data.sql  
# =================================================================================

spring.datasource.url=jdbc:mysql://localhost:3306/<database_name>?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=root

spring.jpa.hibernate.ddl-auto=create
spring.jpa.generate-ddl=true

spring.sql.init.mode=always
spring.jpa.defer-datasource-initialization=true
spring.data.jpa.repositories.bootstrap-mode=default
```
