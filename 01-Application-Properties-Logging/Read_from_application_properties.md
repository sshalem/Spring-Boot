<img src="https://img.shields.io/badge/-Read_from_application_properties %20- blue" height=40px>

###### _

|     |  Subject           |
|:---:|:------------------------------| 
|     |[Introduction](#Introduction)   | 
|  1  |[@Value](#value)   | 
|  2  |[Environment (from Spring)](#Environment_from_spring)  |   
|  3  |[@ConfigurationProperties](#ConfigurationProperties)  |  
|  4  |[Actuator expose application properties](#Multiple_data_sql)  |  
|     |[]()  


------------------------------------------------------------------------------------

###### Introduction

<img src="https://img.shields.io/badge/- Introduction %20-blue" height=40px>

In this topic we will see ,three different ways to read application properties in the Spring Boot application.
1. `@Value`
2. `Environment` Bean
3. `@ConfigurationProperties`

[reading-application-properties-spring-boot](https://www.appsdeveloperblog.com/reading-application-properties-spring-boot/)

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

------------------------------------------------------------------------------------

###### value

<img src="https://img.shields.io/badge/- value %20-blue" height=40px>

### [application.properties ](#-)

```sql
my.greeting=Hello world
app.name=my app
app.description=Welcome to ${app.name}

# list
my.list.values=one,two,three
 
# key/value 
db.values={connectionString:'http://',username:'foo',password:'1234'}
```

### [Main app Class](#-)

```java
@SpringBootApplication
public class Application implements CommandLineRunner {

    @Value("${my.greeting:default value}")
    private String greetingMessage;

    @Value("some static message")
    private String staticMessage;

    @Value("${app.name}")
    private String appName;

    @Value("${app.description}")
    private String appDescription;

    @Value("${my.list.values}")
    private List<String> listValues;

    @Value("#{${db.values}}")
    private Map<String, String> dbValues;

    public static void main(String[] args) {
	SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
	System.out.println(
		greetingMessage + "\n" + staticMessage + "\n" + appName + "\n" + appDescription + "\n" + listValues);

	dbValues.forEach((k, v) -> {
	    System.out.println("[" + k + "]" + " : " + "[" + v + "]");
	});
    }
}
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

------------------------------------------------------------------------------------

###### Environment_from_spring

<img src="https://img.shields.io/badge/- 2. Environment_from_spring %20-blue" height=40px>

Spring Boot Environment Is a bean that I can Inject and [`@Autowire`](#-) it ( From <http://zetcode.com/springboot/environment/> ) </br>
Environment is an interface representing the environment in which the current application is running.  </br>
It can be used to get profiles and properties of the application environment.


```sql
my.greeting=Hello world
app.name=my app
app.description=Welcome to ${app.name}

# list
my.list.values=one,two,three
 
# key/value 
db.values={connectionString:'http://',username:'foo',password:'1234'}
```

### [Main app Class](#-)

```java
@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private Environment env;

    public static void main(String[] args) {
	SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
	String greetingMessage = env.getProperty("my.greeting");
	String appName = env.getProperty("app.name");
	String appDescription = env.getProperty("app.description");
	String myListValues = env.getProperty("my.list.values");

	String[] splitedString = myListValues.split(",");

	System.out.print("[");
	for (String str : splitedString) {
	    System.out.print(str + ", ");
	}
	System.out.println("]");

	String dbValues = env.getProperty("db.values");
	String[] splitDbValues = dbValues.split(",");

	System.out.print("[");
	for (String str : splitDbValues) {
	    System.out.print(str + ", ");
	}
	System.out.println("]");

	System.out.println(greetingMessage + ", " + appName + ", " + appDescription);
    }
}
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

------------------------------------------------------------------------------------

###### ConfigurationProperties

<img src="https://img.shields.io/badge/- 3. ConfigurationProperties %20-blue" height=40px>

[`@ConfigurationProperties`](#-) is a specific annotaion for pulling properties from configuration file. </br>

Steps:
1. Create a class with the fields of the property names .
2. Add annotation of `@Configuration` so spring will be able to make the class as a bean
3. Add annotation of `@ConfigurationProperties`
4. Add `prefix` to annotation that starts with same prefix in `application.properties` file.
5. Now I can Autowire the Bean (Since it is 

```sql
# list
db.config.my-list-values[0]=one
db.config.my-list-values[1]=two
db.config.my-list-values[2]=three

# key/value
db.config.connection[connectionString]='http://'
db.config.connection[username]='foo'
db.config.connection[password]='1234'
db.config.host=127.0.0.1
db.config.port=2700
```

### [DBsettings config class](#-)

```java
@Configuration
@ConfigurationProperties(prefix = "db.config")
public class DBsettings {

    private List<String> myListValues;
    private Map<String, String> connection;
    private String host;
    private String port;

    Ctor/G/S/
}
```

### [Main app Class](#-)

```java
@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private DBsettings dBsettings;

    public static void main(String[] args) {
	SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
	
	List<String> myListValues = dBsettings.getMyListValues();
	Map<String,String> connection = dBsettings.getConnection();	
	String host = dBsettings.getHost();
	String port = dBsettings.getPort();
	
	System.out.println(myListValues);
	System.out.println(connection);
	System.out.println(host);
	System.out.println(port);	
    }
}
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

------------------------------------------------------------------------------------

###### 

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)
