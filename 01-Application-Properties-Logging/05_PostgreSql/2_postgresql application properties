# ===============================
# = DATA SOURCE
# ===============================
 
spring.datasource.url=jdbc:postgresql://localhost/{the name of Schema in DB}
spring.datasource.username=postgres
spring.datasource.password=admin
 
 
# ===============================
# = JPA / HIBERNATE
# ===============================

#Spring will create a schema 
spring.jpa.hibernate.ddl-auto=create
spring.jpa.generate-ddl=true
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.generate_statistics=true
spring.jpa.open-in-view=false
spring.jpa.properties.hibernate.format_sql=true
 
# if this is not set to false i get 
# java.lang.reflect.InvocationTargetException: null - when I play the project
spring.jpa.properties.hibernate.temp.use_jdbc_metadata_defaults=false
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQL95Dialect

# ===========================================
# 	dev-tools (Need to add dependency)
# ===========================================
spring.devtools.restart.enabled=true

# ==========================================
# format the returned error format when exception is thrown back to client
# ==========================================
server.error.path=true
server.error.include-binding-errors=always
server.error.include-exception=true
server.error.include-message=always
server.error.include-stacktrace=never
server.error.whitelabel.enabled=true
