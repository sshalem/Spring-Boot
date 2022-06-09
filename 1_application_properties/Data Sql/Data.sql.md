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

```sql
# =================================================================================
# From Spring boot version 2.5.0 amd above 
# to create data.sql file need to add following properties
# from Baeldung https://www.baeldung.com/spring-boot-data-sql-and-schema-sql
# script-based initialization is performed by default only for embedded databases, to always initialize a database using scripts, we'll have to use:
# spring.sql.init.mode=always
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
