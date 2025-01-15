```java
spring.jpa.show-sql=true & logging.level.org.hibernate.SQL=debug 
Do the same , but with difference?
```

```java
spring.jpa.show-sql=true
# show the the logging w/o the the time stamp and the package name 
Hibernate: drop table if exists customer

logging.level.org.hibernate.SQL=debug
# shows the logging as below: with the time stamp and the package name 
2021-04-21 12:02:27.553 DEBUG 496 --- [  restartedMain] org.hibernate.SQL   : drop table if exists customer
```
