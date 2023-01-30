<img src="https://img.shields.io/badge/-Read_from_application_properties %20- blue" height=40px>

###### _

|     |  Subject           |
|:---:|:------------------------------| 
|  1  |[@Value](#value)   | 
|  2  |[Environment (from Spring)](#Environment_from_spring)  |   
|  3  |[@ConfigurationProperties](#ConfigurationProperties)  |  
|  4  |[Actuator expose application properties](#Multiple_data_sql)  |  
|     |[]()  


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

<img src="https://img.shields.io/badge/- Environment_from_spring %20-blue" height=40px>

Spring Boot Environment Is a bean that I can Inject and [`@Autowire`](#-) it ( From <http://zetcode.com/springboot/environment/> ) </br>
Environment is an interface representing the environment in which the current application is running.  </br>
It can be used to get profiles and properties of the application environment.


```sql

```

```java
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

------------------------------------------------------------------------------------

###### 

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

------------------------------------------------------------------------------------

###### 

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)
