<img src="https://img.shields.io/badge/-schema and data initialization%20-blue" height=40px>

###### _



|     |  Subject           |
|:---:|:------------------------------| 
|  1  |[SLF4J or Logback](#Logback_SLF4J)   | 
|  2  |[Log4j2](#2_log4j2)  |  
|  3  |[Log.java](#log)  |  
|  4  |[Spring logging file on linux server](#4_logging_file_on_linux_server)  |  

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
6. [Spring Doc 2.7.6 - Logging](https://docs.spring.io/spring-boot/docs/2.7.6/reference/htmlsingle/#features.logging)
7. [Logback HTML layout](https://howtodoinjava.com/logback/logback-html-layout/)
8. [Logback Video](https://www.youtube.com/watch?v=ZmTNAKuTyVg&ab_channel=CodeJava)

![image](https://user-images.githubusercontent.com/36256986/207047146-1ce874a2-efa1-4722-8f45-8d7c11ecaa24.png)

![image](https://user-images.githubusercontent.com/36256986/207047240-6e00595a-98d4-4f88-b4b1-44dae51f8c8d.png)

![image](https://user-images.githubusercontent.com/36256986/207047414-faa73920-55ca-4541-8c53-5265c76a3c9d.png)

![image](https://user-images.githubusercontent.com/36256986/207050632-7a7afe33-bd72-44a4-9cf5-f05b64d3b9b5.png)



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

```js
%clr(%d{${LOG_DATEFORMAT_PATTERN:-yyyy-MM-dd HH:mm:ss.SSS}}){faint}  -    Date and Time: Millisecond precision and easily sortable.
%clr(${LOG_LEVEL_PATTERN:-%5p}) -     Log Level: ERROR, WARN, INFO, DEBUG, or TRACE.
%clr(${PID:- }){magenta} -    Process ID.
%clr(---){faint} -     A --- separator to distinguish the start of actual log messages.
%clr([%15.15t]){faint} -    Thread name: Enclosed in square brackets (may be truncated for console output).
%clr(%-40.40logger{39}){cyan} -    Logger name: This is usually the source class name (often abbreviated).
%clr(:){faint} %m%n -   The log message. (and goes to next line)
```

As shown in the link above : </br>
https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#application-properties.core.logging.pattern.console

```sql
logging.pattern.console=%clr(%d{${LOG_DATEFORMAT_PATTERN:-yyyy-MM-dd HH:mm:ss.SSS}}){faint} %clr(${LOG_LEVEL_PATTERN:-%5p}) %clr(${PID:- }){magenta} %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %m%n
```

http://logback.qos.ch/manual/layouts.html#conversionWord

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

------------------------------------------------------------------------------------


###### 2_log4j2

<img src="https://img.shields.io/badge/- 2. Log4j2 %20-blue" height=40px>

Links

|  2  |[Log4j2 well explained](https://stackify.com/log4j2-java/)  |  
|     |2.1. [Log4j2 home page](https://logging.apache.org/log4j/2.x/index.html)  |  
|     |2.2. [Log4j2 config with xml](https://howtodoinjava.com/log4j2/log4j2-tutorial/)  |  
|     |2.3. [Log4j2 config with xml](https://howtodoinjava.com/spring-boot/spring-boot-log4j2-config/)  |  
|     |2.4. [Log4j2 config with properties](https://howtodoinjava.com/log4j2/log4j2-properties-example/)  |  
|     |2.5. [Log4j2 config with xml geeks4geeks](https://www.geeksforgeeks.org/how-to-configure-log4j-2-logging-in-spring-boot/)  |  
|     |2.6. [Log4j2 spring boot 3 check dependencies too add](https://medium.com/@bishalf98/log4j2-in-springboot3-095ab6f15763)  |  



[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)


------------------------------------------------------------------------------------

###### log

<img src="https://img.shields.io/badge/- Log class %20-blue" height=40px>



```java
package com.excel.utils;

import org.slf4j.Logger;

public class Log {

	/**
	 * ANSI can refer to the "American National Standards Institute" ANSI Colors
	 * code
	 * https://stackoverflow.com/questions/67241111/python-colored-text-to-the-terminal
	 * 
	 * https://www.w3schools.blog/ansi-colors-java
	 */

//	public final static String ESC_START = "\033[";
//	public final static String ESC_END = "m";
//	public final static String REGULAR = "0;";
//	public final static String BOLD = "1;";
//	public final static String UNDERLINE = "4;";

	// public final static String BLACK_FG = "30";
//	public final static String RED_FG = "31";
//	public final static String GREEN_FG = "32";
//	public final static String YELLOW_FG = "33";
//	public final static String BLUE_FG = "34";
//	public final static String MAGENTA_FG = "35";
//	public final static String CYAN_FG = "36";
//	public final static String WHITE_FG = "37";
//	public final static String DEFAULT_FG = "39";

	// Reset
	private static final String RESET = "\033[0m"; // Text Reset

	/**
	 * Code with pattern of \033[X;XXm
	 */
	// Regular Colors
	private static final String BLACK = "\033[0;30m"; // BLACK
	private static final String RED = "\033[0;31m"; // RED
	private static final String GREEN = "\033[0;32m"; // GREEN
	private static final String YELLOW = "\033[0;33m"; // YELLOW
	private static final String BLUE = "\033[0;34m"; // BLUE
	private static final String PURPLE = "\033[0;35m"; // PURPLE
	private static final String CYAN = "\033[0;36m"; // CYAN
	private static final String WHITE = "\033[0;37m"; // WHITE

	// Bold
	private static final String BLACK_BOLD = "\033[1;30m"; // BLACK
	private static final String RED_BOLD = "\033[1;31m"; // RED
	private static final String GREEN_BOLD = "\033[1;32m"; // GREEN
	private static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
	private static final String BLUE_BOLD = "\033[1;34m"; // BLUE
	private static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
	private static final String CYAN_BOLD = "\033[1;36m"; // CYAN
	private static final String WHITE_BOLD = "\033[1;37m"; // WHITE

	// Underline
	private static final String BLACK_UNDERLINED = "\033[4;30m"; // BLACK
	private static final String RED_UNDERLINED = "\033[4;31m"; // RED
	private static final String GREEN_UNDERLINED = "\033[4;32m"; // GREEN
	private static final String YELLOW_UNDERLINED = "\033[4;33m"; // YELLOW
	private static final String BLUE_UNDERLINED = "\033[4;34m"; // BLUE
	private static final String PURPLE_UNDERLINED = "\033[4;35m"; // PURPLE
	private static final String CYAN_UNDERLINED = "\033[4;36m"; // CYAN

	@SuppressWarnings("unused")
	private static final String WHITE_UNDERLINED = "\033[4;37m"; // WHITE

	// High Intensity
	private static final String BLACK_BRIGHT = "\033[0;90m"; // BLACK
	private static final String RED_BRIGHT = "\033[0;91m"; // RED
	private static final String GREEN_BRIGHT = "\033[0;92m"; // GREEN
	private static final String YELLOW_BRIGHT = "\033[0;93m"; // YELLOW
	private static final String BLUE_BRIGHT = "\033[0;94m"; // BLUE
	private static final String PURPLE_BRIGHT = "\033[0;95m"; // PURPLE
	private static final String CYAN_BRIGHT = "\033[0;96m"; // CYAN
	private static final String WHITE_BRIGHT = "\033[0;97m"; // WHITE

	// Bold High Intensity
	private static final String BLACK_BOLD_BRIGHT = "\033[1;90m"; // BLACK
	private static final String RED_BOLD_BRIGHT = "\033[1;91m"; // RED
	private static final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
	private static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
	private static final String BLUE_BOLD_BRIGHT = "\033[1;94m"; // BLUE
	private static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
	private static final String CYAN_BOLD_BRIGHT = "\033[1;96m"; // CYAN
	private static final String WHITE_BOLD_BRIGHT = "\033[1;97m"; // WHITE
	/**
	 * Background Colors
	 * 
	 */
	// Background
	private static final String BLACK_BACKGROUND = "\033[40m"; // BLACK
	private static final String RED_BACKGROUND = "\033[41m"; // RED
	private static final String GREEN_BACKGROUND = "\033[42m"; // GREEN
	private static final String YELLOW_BACKGROUND = "\033[43m"; // YELLOW
	private static final String BLUE_BACKGROUND = "\033[44m"; // BLUE
	private static final String PURPLE_BACKGROUND = "\033[45m"; // PURPLE
	private static final String CYAN_BACKGROUND = "\033[46m"; // CYAN
	private static final String WHITE_BACKGROUND = "\033[47m"; // WHITE

	// High Intensity backgrounds
	private static final String BLACK_BACKGROUND_BRIGHT = "\033[0;100m";// BLACK
	private static final String RED_BACKGROUND_BRIGHT = "\033[0;101m";// RED
	private static final String GREEN_BACKGROUND_BRIGHT = "\033[0;102m";// GREEN
	private static final String YELLOW_BACKGROUND_BRIGHT = "\033[0;103m";// YELLOW
	private static final String BLUE_BACKGROUND_BRIGHT = "\033[0;104m";// BLUE
	private static final String PURPLE_BACKGROUND_BRIGHT = "\033[0;105m"; // PURPLE
	private static final String CYAN_BACKGROUND_BRIGHT = "\033[0;106m"; // CYAN
	private static final String WHITE_BACKGROUND_BRIGHT = "\033[0;107m"; // WHITE

	/******************
	 * Regular colors
	 ******************/
	public static void black(Object message) {
		System.out.println(BLACK + message + RESET);
	}

	public static void red(Object message) {
		System.out.println(RED + message + RESET);
	}

	public static void green(Object message) {
		System.out.println(GREEN + message + RESET);
	}

	public static void yellow(Object message) {
		System.out.println(YELLOW + message + RESET);
	}

	public static void blue(Object message) {
		System.out.println(BLUE + message + RESET);
	}

	public static void purple(Object message) {
		System.out.println(PURPLE + message + RESET);
	}

	public static void cyan(Object message) {
		System.out.println(CYAN + message + RESET);
	}

	public static void white(Object message) {
		System.out.println(WHITE + message + RESET);
	}

	/******************
	 * Bold colors
	 ******************/

	public static void blackBold(Object message) {
		System.out.println(BLACK_BOLD + message + RESET);
	}

	public static void redBold(Object message) {
		System.out.println(RED_BOLD + message + RESET);
	}

	public static void greenBold(Object message) {
		System.out.println(GREEN_BOLD + message + RESET);
	}

	public static void yellowBold(Object message) {
		System.out.println(YELLOW_BOLD + message + RESET);
	}

	public static void blueBold(Object message) {
		System.out.println(BLUE_BOLD + message + RESET);
	}

	public static void purpleBold(Object message) {
		System.out.println(PURPLE_BOLD + message + RESET);
	}

	public static void cyanBold(Object message) {
		System.out.println(CYAN_BOLD + message + RESET);
	}

	public static void whiteBold(Object message) {
		System.out.println(WHITE_BOLD + message + RESET);
	}

	/******************
	 * Underline colors
	 ******************/
	public static void blackUnderlined(Object message) {
		System.out.println(BLACK_UNDERLINED + message + RESET);
	}

	public static void redUnderlined(Object message) {
		System.out.println(RED_UNDERLINED + message + RESET);
	}

	public static void greenUnderlined(Object message) {
		System.out.println(GREEN_UNDERLINED + message + RESET);
	}

	public static void yellowUnderlined(Object message) {
		System.out.println(YELLOW_UNDERLINED + message + RESET);
	}

	public static void blueUnderlined(Object message) {
		System.out.println(BLUE_UNDERLINED + message + RESET);
	}

	public static void purpleUnderlined(Object message) {
		System.out.println(PURPLE_UNDERLINED + message + RESET);
	}

	public static void cyanUnderlined(Object message) {
		System.out.println(CYAN_UNDERLINED + message + RESET);
	}

	public static void whiteUnderlined(Object message) {
		System.out.println(WHITE + message + RESET);
	}

	/************************************
	 * High Intensity (Bright) colors
	 ************************************/

	public static void blackBright(Object message) {
		System.out.println(BLACK_BRIGHT + message + RESET);
	}

	public static void redBright(Object message) {
		System.out.println(RED_BRIGHT + message + RESET);
	}

	public static void greenBright(Object message) {
		System.out.println(GREEN_BRIGHT + message + RESET);
	}

	public static void yellowBright(Object message) {
		System.out.println(YELLOW_BRIGHT + message + RESET);
	}

	public static void blueBright(Object message) {
		System.out.println(BLUE_BRIGHT + message + RESET);
	}

	public static void purpleBright(Object message) {
		System.out.println(PURPLE_BRIGHT + message + RESET);
	}

	public static void cyanBright(Object message) {
		System.out.println(CYAN_BRIGHT + message + RESET);
	}

	public static void whiteBright(Object message) {
		System.out.println(WHITE_BRIGHT + message + RESET);
	}

	/************************************
	 * Bold High Intensity (Bright) colors
	 ************************************/

	public static void blackBoldBright(Object message) {
		System.out.println(BLACK_BOLD_BRIGHT + message + RESET);
	}

	public static void redBoldBright(Object message) {
		System.out.println(RED_BOLD_BRIGHT + message + RESET);
	}

	public static void greenBoldBright(Object message) {
		System.out.println(GREEN_BOLD_BRIGHT + message + RESET);
	}

	public static void yellowBoldBright(Object message) {
		System.out.println(YELLOW_BOLD_BRIGHT + message + RESET);
	}

	public static void blueBoldBright(Object message) {
		System.out.println(BLUE_BOLD_BRIGHT + message + RESET);
	}

	public static void purpleBoldBright(Object message) {
		System.out.println(PURPLE_BOLD_BRIGHT + message + RESET);
	}

	public static void cyanBoldBright(Object message) {
		System.out.println(CYAN_BOLD_BRIGHT + message + RESET);
	}

	public static void whiteBoldBright(Object message) {
		System.out.println(WHITE_BOLD_BRIGHT + message + RESET);
	}

	/******************
	 * Background colors
	 ******************/
	public static void blackBackground(Object message) {
		System.out.println(BLACK_BACKGROUND + message + RESET);
	}

	public static void redBackground(Object message) {
		System.out.println(RED_BACKGROUND + message + RESET);
	}

	public static void greenBackground(Object message) {
		System.out.println(GREEN_BACKGROUND + message + RESET);
	}

	public static void yellowBackground(Object message) {
		System.out.println(YELLOW_BACKGROUND + message + RESET);
	}

	public static void blueBackground(Object message) {
		System.out.println(BLUE_BACKGROUND + message + RESET);
	}

	public static void purpleBackground(Object message) {
		System.out.println(PURPLE_BACKGROUND + message + RESET);
	}

	public static void cyanBackground(Object message) {
		System.out.println(CYAN_BACKGROUND + message + RESET);
	}

	public static void whiteBackground(Object message) {
		System.out.println(WHITE_BACKGROUND + message + RESET);
	}

	/******************************
	 * Background Bright colors
	 ******************************/
	public static void blackBackgroundBright(Object message) {
		System.out.println(BLACK_BACKGROUND_BRIGHT + message + RESET);
	}

	public static void redBackgroundBright(Object message) {
		System.out.println(RED_BACKGROUND_BRIGHT + message + RESET);
	}

	public static void greenBackgroundBright(Object message) {
		System.out.println(GREEN_BACKGROUND_BRIGHT + message + RESET);
	}

	public static void yellowBackgroundBright(Object message) {
		System.out.println(YELLOW_BACKGROUND_BRIGHT + message + RESET);
	}

	public static void blueBackgroundBright(Object message) {
		System.out.println(BLUE_BACKGROUND_BRIGHT + message + RESET);
	}

	public static void purpleBackgroundBright(Object message) {
		System.out.println(PURPLE_BACKGROUND_BRIGHT + message + RESET);
	}

	public static void cyanBackgroundBright(Object message) {
		System.out.println(CYAN_BACKGROUND_BRIGHT + message + RESET);
	}

	public static void whiteBackgroundBright(Object message) {
		System.out.println(WHITE_BACKGROUND_BRIGHT + message + RESET);
	}

	
	/******************************
	 * SLF4J info Logger colors
	 ******************************/

	public static void infoRed(Logger LOGGER, Object message) {
		LOGGER.info(RED + message + RESET);
	}

	public static void infoRedBackground(Logger LOGGER, Object message) {
		LOGGER.info(RED_BACKGROUND + message + RESET);
	}

	public static void infoRedBackgroundBright(Logger LOGGER, Object message) {
		LOGGER.info(RED_BACKGROUND_BRIGHT + message + RESET);
	}

	// -------------------------------------------------------------

	public static void infoGreen(Logger LOGGER, Object message) {
		LOGGER.info(GREEN + message + RESET);
	}

	public static void infowarnGreenBackground(Logger LOGGER, Object message) {
		LOGGER.info(GREEN_BACKGROUND + message + RESET);
	}

	public static void infoGreenBackgroundBright(Logger LOGGER, Object message) {
		LOGGER.info(GREEN_BACKGROUND_BRIGHT + message + RESET);
	}

	// -------------------------------------------------------------

	public static void infoYellow(Logger LOGGER, Object message) {
		LOGGER.info(YELLOW + message + RESET);
	}

	public static void infoYellowBackground(Logger LOGGER, Object message) {
		LOGGER.info(YELLOW_BACKGROUND + message + RESET);
	}

	public static void infoYellowBackgroundBright(Logger LOGGER, Object message) {
		LOGGER.info(YELLOW_BACKGROUND_BRIGHT + message + RESET);
	}

	// -------------------------------------------------------------

	public static void infoBlue(Logger LOGGER, Object message) {
		LOGGER.info(BLUE + message + RESET);
	}

	public static void infoBlueBackground(Logger LOGGER, Object message) {
		LOGGER.info(BLUE_BACKGROUND + message + RESET);
	}

	public static void infoBlueBackgroundBright(Logger LOGGER, Object message) {
		LOGGER.info(BLUE_BACKGROUND_BRIGHT + message + RESET);
	}

	// -------------------------------------------------------------

	public static void infoPurple(Logger LOGGER, Object message) {
		LOGGER.info(PURPLE + message + RESET);
	}

	public static void infoPurpleBackground(Logger LOGGER, Object message) {
		LOGGER.info(PURPLE_BACKGROUND + message + RESET);
	}

	public static void infoPurpleBackgroundBright(Logger LOGGER, Object message) {
		LOGGER.info(PURPLE_BACKGROUND_BRIGHT + message + RESET);
	}

	
	/******************************
	 * SLF4J Warning Logger colors
	 ******************************/

	public static void warnRed(Logger LOGGER, Object message) {
		LOGGER.warn(RED + message + RESET);
	}

	public static void warnRedBackground(Logger LOGGER, Object message) {
		LOGGER.warn(RED_BACKGROUND + message + RESET);
	}

	public static void warnRedBackgroundBright(Logger LOGGER, Object message) {
		LOGGER.warn(RED_BACKGROUND_BRIGHT + message + RESET);
	}

	// -------------------------------------------------------------

	public static void warnGreen(Logger LOGGER, Object message) {
		LOGGER.warn(GREEN + message + RESET);
	}

	public static void warnGreenBackground(Logger LOGGER, Object message) {
		LOGGER.warn(GREEN_BACKGROUND + message + RESET);
	}

	public static void warnGreenBackgroundBright(Logger LOGGER, Object message) {
		LOGGER.warn(GREEN_BACKGROUND_BRIGHT + message + RESET);
	}

	// -------------------------------------------------------------

	public static void warnYellow(Logger LOGGER, Object message) {
		LOGGER.warn(YELLOW + message + RESET);
	}

	public static void warnYellowBackground(Logger LOGGER, Object message) {
		LOGGER.warn(YELLOW_BACKGROUND + message + RESET);
	}

	public static void warnYellowBackgroundBright(Logger LOGGER, Object message) {
		LOGGER.warn(YELLOW_BACKGROUND_BRIGHT + message + RESET);
	}

	// -------------------------------------------------------------

	public static void warnBlue(Logger LOGGER, Object message) {
		LOGGER.warn(BLUE + message + RESET);
	}

	public static void warnBlueBackground(Logger LOGGER, Object message) {
		LOGGER.warn(BLUE_BACKGROUND + message + RESET);
	}

	public static void warnBlueBackgroundBright(Logger LOGGER, Object message) {
		LOGGER.warn(BLUE_BACKGROUND_BRIGHT + message + RESET);
	}

	// -------------------------------------------------------------

	public static void warnPurple(Logger LOGGER, Object message) {
		LOGGER.warn(PURPLE + message + RESET);
	}

	public static void warnPurpleBackground(Logger LOGGER, Object message) {
		LOGGER.warn(PURPLE_BACKGROUND + message + RESET);
	}

	public static void warnPurpleBackgroundBright(Logger LOGGER, Object message) {
		LOGGER.warn(PURPLE_BACKGROUND_BRIGHT + message + RESET);
	}

	/******************************
	 * SLF4J Error Logger colors
	 ******************************/

	public static void errorRed(Logger LOGGER, Object message) {
		LOGGER.error(RED + message + RESET);
	}

	public static void errorRedBackground(Logger LOGGER, Object message) {
		LOGGER.error(RED_BACKGROUND + message + RESET);
	}

	public static void errorRedBackgroundBright(Logger LOGGER, Object message) {
		LOGGER.error(RED_BACKGROUND_BRIGHT + message + RESET);
	}

	// -------------------------------------------------------------

	public static void errorGreen(Logger LOGGER, Object message) {
		LOGGER.error(GREEN + message + RESET);
	}

	public static void errorGreenBackground(Logger LOGGER, Object message) {
		LOGGER.error(GREEN_BACKGROUND + message + RESET);
	}

	public static void errorGreenBackgroundBright(Logger LOGGER, Object message) {
		LOGGER.error(GREEN_BACKGROUND_BRIGHT + message + RESET);
	}

	// -------------------------------------------------------------

	public static void errorYellow(Logger LOGGER, Object message) {
		LOGGER.error(YELLOW + message + RESET);
	}

	public static void errorYellowBackground(Logger LOGGER, Object message) {
		LOGGER.error(YELLOW_BACKGROUND + message + RESET);
	}

	public static void errorYellowBackgroundBright(Logger LOGGER, Object message) {
		LOGGER.error(YELLOW_BACKGROUND_BRIGHT + message + RESET);
	}

	// -------------------------------------------------------------

	public static void errorBlue(Logger LOGGER, Object message) {
		LOGGER.error(BLUE + message + RESET);
	}

	public static void errorBlueBackground(Logger LOGGER, Object message) {
		LOGGER.error(BLUE_BACKGROUND + message + RESET);
	}

	public static void errorBlueBackgroundBright(Logger LOGGER, Object message) {
		LOGGER.error(BLUE_BACKGROUND_BRIGHT + message + RESET);
	}

	// -------------------------------------------------------------

	public static void errorPurple(Logger LOGGER, Object message) {
		LOGGER.error(PURPLE + message + RESET);
	}

	public static void errorPurpleBackground(Logger LOGGER, Object message) {
		LOGGER.error(PURPLE_BACKGROUND + message + RESET);
	}

	public static void errorPurpleBackgroundBright(Logger LOGGER, Object message) {
		LOGGER.error(PURPLE_BACKGROUND_BRIGHT + message + RESET);
	}
}
```


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

------------------------------------------------------------------------------------


###### 4_logging_file_on_linux_server

<img src="https://img.shields.io/badge/- 4. Spring boot logging file on linux server %20-blue" height=40px>

### [File Output](#-)

[refernce - logging spring boot app 3.3.2](https://docs.spring.io/spring-boot/reference/features/logging.html)

By default, Spring Boot logs only to the console and does not write log files.</br>
If you want to write log files in addition to the console output, you need to set a :
- `logging.file.name`
- or `logging.file.path` property (for example, in your application.properties).

If both properties are set:
- `logging.file.path` is ignored
- and only `logging.file.name` is used.

Thus, In order to add logging file in Spring boot app ,so I could diagnose/analyse logging of my app .</br>

There are 4 possible options with logging file on production server

![image](https://github.com/user-attachments/assets/a251b3a9-895a-4ca2-9a4a-5df3c0b284be)


### [scenario 1: `none`](#-) 

If I don't define a logging file in `application.properties` the logging will be logged only to console. </br>

### Question : </br>
If I upload the app to production server, In this situation , where can I find the console log of the app

### Answer : </br>
I can find it in the file `catalina.out` located in directory `/opt/tomcat/logs` 

### [scenario 2: logging.file.name only](#-) 

Importnat note, there are several options where to find the `log` file:
1. If Using `Jenkins` to upload Spring boot app (`War file`) , thus file will be located at jenkins directory

```js
logging.file.name=__Shabtay_Shalem.log
```

I had some problems to find the file this way , thus , I used the command in linux server:
 
```js
find / -iname '__Shabtay*'
```

Eventually I got the path of the file ,

![image](https://github.com/user-attachments/assets/3d019efe-d8d3-4d16-ab06-8b9b1088879d)


### [scenario 2: logging.file.name only with relative/absolute ](#-) 

```js
logging.file.name=/home/logs/CiCD.log
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)
