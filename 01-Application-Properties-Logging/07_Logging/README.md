<img src="https://img.shields.io/badge/-schema and data initialization%20-blue" height=40px>

###### _



|     |  Subject           |
|:---:|:------------------------------| 
|  1  |[Logback (SLF4J) ](#Logback_SLF4J)   | 
|  2  |[](#)  |   
|  3  |[](#)  |  
|  4  |[](#)  |  

There are several Logging API we can use </br>
From spring 2.7.6 documentation : [spring-boot/docs/2.7.6/](https://docs.spring.io/spring-boot/docs/2.7.6/reference/htmlsingle/#features.logging)

------------------------------------------------------------------------------------

###### Logback_SLF4J

<img src="https://img.shields.io/badge/- 1. Logback_SLF4J %20-blue" height=40px>

Links with examples of how to config:

1. [DZone](https://dzone.com/articles/configuring-logback-with-spring-boot)
2. [Baeldung](https://www.baeldung.com/logback)
3. [MyKong](https://mkyong.com/spring-boot/spring-boot-slf4j-logging-example/)
4. [Configuring Logback with Spring Boot](https://www.codingame.com/playgrounds/4497/configuring-logback-with-spring-boot)
5. [Logback](https://logback.qos.ch/)  and  [logback manual](https://logback.qos.ch/manual/layouts.html#conversionWord)
6. [Logback HTML layout](https://howtodoinjava.com/logback/logback-html-layout/)

#### [POM dependency](#-)

Logback is part of spring boot so , </br>
There is no need to add any Dependency , as long as we have at least one dependency that it is part of spring boot ,

For example :

```sql
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

#### [Code example](#-)

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogbackController {
 
	private static Logger LOGGER = LoggerFactory.getLogger(LogbackController.class);

	public void getLog() {
	  LOGGER.trace("doStuff needed more information - {}");
          LOGGER.debug("doStuff needed to debug - {}");
          LOGGER.info("doStuff took input - {}");
          LOGGER.warn("doStuff needed to warn - {}");
          LOGGER.error("doStuff encountered an error with value - {}");
	}
}
```

#### [application.properties](#-)

```sql
logging.level.root=info
logging.pattern.console=%highlight(%d{dd-MM-yyyy HH:mm:ss.SSS} %n SYETEM-%-5level %n [Thread]: %thread ,  [Method]: %M , [Package + Class]: %logger %n %msg) %n %n
 
logging.path=logs
logging.file=${logging.path}/logging.log
logging.pattern.file=%d{dd-MM-yyyy HH:mm:ss.SSS} %n SYETEM-%-5level %n [Thread]: %thread ,  [Method]: %M , [Package + Class]: %logger %n %msg %n %n
```

This is how it is Viewed in Console & in log file :

```
19-03-2020 20:35:49.348 
 SYETEM-INFO  
 [Thread]: http-nio-8080-exec-1 ,  [Method]: getLog , [Package + Class]: com.log.web.LogbackController 
 doStuff took input - {} 
	 
19-03-2020 20:35:49.348 
 SYETEM-WARN  
 [Thread]: http-nio-8080-exec-1 ,  [Method]: getLog , [Package + Class]: com.log.web.LogbackController 
 doStuff needed to warn - {} 
	 
19-03-2020 20:35:49.348 
 SYETEM-ERROR 
 [Thread]: http-nio-8080-exec-1 ,  [Method]: getLog , [Package + Class]: com.log.web.LogbackController 
 doStuff encountered an error with value - {}
```

#### [More word conversions](#-)

This line of logger creates same format of logging in spring console:

```sql
logging.pattern.console=%d{dd-MM-yyyy HH:mm:ss.SSS}  %clr(%5p) %clr(${PID:- }){magenta}  --- [%15.15t]  %cyan(%-40.40logger{39}) : %msg %n
```

* logging.pattern.console= 
* `%clr(%d{${LOG_DATEFORMAT_PATTERN:-yyyy-MM-dd HH:mm:ss.SSS}}){faint}` - Date and Time: Millisecond precision and easily sortable.
* `%clr(${LOG_LEVEL_PATTERN:-%5p})` - Log Level: ERROR, WARN, INFO, DEBUG, or TRACE.
* `%clr(${PID:- }){magenta}` - Process ID.
* `%clr(---){faint} ` - A --- separator to distinguish the start of actual log messages.
* `%clr([%15.15t]){faint}` - Thread name: Enclosed in square brackets (may be truncated for console output).
* `%clr(%-40.40logger{39}){cyan}` - Logger name: This is usually the source class name (often abbreviated).
* `%clr(:){faint} %m%n`- The log message.

As shown in the link above : </br>
https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#application-properties.core.logging.pattern.console

```sql
logging.pattern.console=%clr(%d{${LOG_DATEFORMAT_PATTERN:-yyyy-MM-dd HH:mm:ss.SSS}}){faint} %clr(${LOG_LEVEL_PATTERN:-%5p}) %clr(${PID:- }){magenta} %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %m%n
```

http://logback.qos.ch/manual/layouts.html#conversionWord

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

------------------------------------------------------------------------------------

###### 

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)
