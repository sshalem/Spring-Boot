
* Question : </br>
What is difference between [`spring.jpa.show-sql=true`](#-) & [`logging.level.org.hibernate.SQL=debug`](#-) ?

* Answer: </br>
[`spring.jpa.show-sql=true`](#-) - show the the logging w/o the the time stamp and the package name </br>
[`logging.level.org.hibernate.SQL=debug`](#-) - shows the logging as below: with the time stamp and the package name </br>
`2021-04-21 12:02:27.553 DEBUG 496 --- [  restartedMain] org.hibernate.SQL   : drop table if exists customer` 

Better to use the following  :
```java
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
spring.jpa.properties.hibernate.format_sql=true
```

```java
# ===============================
# 	 	JPA / HIBERNATE
# ===============================
 
#Spring will create a schema
spring.jpa.hibernate.ddl-auto=create
spring.jpa.generate-ddl=true

# This is OSIV , define it as false for better performance when Fetch.Lazy configured
spring.jpa.open-in-view=false

# show SQL logging
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
spring.jpa.properties.hibernate.format_sql=true

#spring.jpa.properties.hibernate.generate_statistics=true

# When using java version JDK11 use with mysql dialect
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL8Dialect
```
