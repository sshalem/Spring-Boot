** the following configuration configs the logging of SLF4J

```java
# ===============================
# 		Logging
# =============================== 
logging.pattern.console=%d{dd-MM-yyyy HH:mm:ss.SSS}  %clr(%5p) %clr(${PID:- }){magenta}  --- [%15.15t]  %cyan(%-40.40logger{39}) : %msg %n
```

```js
%clr(%d{${LOG_DATEFORMAT_PATTERN:-yyyy-MM-dd HH:mm:ss.SSS}}){faint}  -    Date and Time: Millisecond precision and easily sortable.

%clr(${LOG_LEVEL_PATTERN:-%5p}) -     Log Level: ERROR, WARN, INFO, DEBUG, or TRACE.

%clr(${PID:- }){magenta} -    Process ID.

%clr(---){faint} -     A --- separator to distinguish the start of actual log messages.

%clr([%15.15t]){faint} -    Thread name: Enclosed in square brackets (may be truncated for console output).

%clr(%-40.40logger{39}){cyan} -    Logger name: This is usually the source class name (often abbreviated).

%clr(:){faint} %m%n -   The log message. (and goes to next line)
```


