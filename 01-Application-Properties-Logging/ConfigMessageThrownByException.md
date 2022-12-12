** the following configuration can manage the way we see the message once we get Exception form server

```java
#dont include this line , gives error 
server.error.path=/

server.error.include-binding-errors=always
server.error.include-exception=true
server.error.include-message=always
server.error.include-stacktrace=never
server.error.whitelabel.enabled=true
```

