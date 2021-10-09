When using java version JDK8 use with mysql dialect :

	org.hibernate.dialect.MySQL5Dialect
	
When using java version JDK11 use with mysql dialect :

	org.hibernate.dialect.MySQL8Dialect
	

## DATA SOURCE
```java
spring.datasource.url=jdbc:mysql://localhost:3306/{the_name_of_Schema_in_DB}?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=root
```
```java
# JPA / HIBERNATE 
# Spring will create a schema
spring.jpa.hibernate.ddl-auto=create
spring.jpa.generate-ddl=true
```
```java
### spring.jpa.show-sql=true & 
### logging.level.org.hibernate.SQL=debug 
### Do the same , but with difference
```
```java
spring.jpa.show-sql=true
# show the the logging w/o the the time stamp and the package name 
# Hibernate: drop table if exists customer

logging.level.org.hibernate.SQL=debug
# shows the logging as below: with the time satamp and the package name 
# 2021-04-21 12:02:27.553 DEBUG 496 --- [  restartedMain] org.hibernate.SQL   : drop table if exists customer
```
```java
# shows the statistics regarding the SQL queries
spring.jpa.properties.hibernate.generate_statistics=true
```
```java
# Regarding this property
# see the project of LAZY Loading, I explain it there
spring.jpa.open-in-view=false
spring.jpa.properties.hibernate.format_sql=true
```

```java
# MySql Dialect for to use with MySql 8 on JDK8 is MySQL5Dialect
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect
```

```java
spring.jpa.hibernate.naming-strategy = org.hibernate.cfg.ImprovedNamingStrategy
```

```java
# ===========================================
# 	dev-tools (Need to add dependency)
# ===========================================
spring.devtools.restart.enabled=true
```

```java
# =================================================================================
# to Initialize DB by using a file of "data.sql" 
# need to set the following features
# =================================================================================
spring.datasource.platform=mysql
spring.datasource.initialization-mode=always
```

```java
# ==========================================
# format the returned error format 
# when exception is thrown back to client
# ==========================================

server.error.path=true
server.error.include-binding-errors=always
server.error.include-exception=true
server.error.include-message=always
server.error.include-stacktrace=never
server.error.whitelabel.enabled=true
```
