```java
# =================================================================================
# to Initialize DB by using a file of "data.sql" 
# need to set the following features
# on Sprng Boot vewrsion 2.4.12 it worked as expected
# when tried on Spring boot version 2.5.8 -> did not work as expected , alot of problems created
# =================================================================================
spring.datasource.platform=mysql
spring.datasource.initialization-mode=always
```
