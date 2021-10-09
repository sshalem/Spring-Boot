# PostgreSql
```java
# ===============================
# = DATA SOURCE
# ===============================
 
spring.datasource.url=jdbc:postgresql://localhost/{the name of Schema in DB}
spring.datasource.username=postgres
spring.datasource.password=admin
```
``` 
# ===============================
# = JPA / HIBERNATE
# ===============================
```

### Spring will create the tables in the schema 
```java
spring.jpa.hibernate.ddl-auto = create
spring.jpa.generate-ddl=true
spring.jpa.show-sql=true
```

 
### if this is not set to false i get a warning
```java
spring.jpa.open-in-view=false
```
### if this is not set to false i get 
### java.lang.reflect.InvocationTargetException: null - when I play the project
```java
spring.jpa.properties.hibernate.temp.use_jdbc_metadata_defaults = false
```

### show the SQl queries in a vertical way
```java
spring.jpa.properties.hibernate.format_sql=true 
```

### PostGreSql Dialect
```
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.PostgreSQL95Dialect
```

```java
# ===========================================
# 	dev-tools (Need to add dependency)
# ===========================================
spring.devtools.restart.enabled=true
```
