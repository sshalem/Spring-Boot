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

###### 

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

```sql
application.properties 

	my.greeting=Hello world
	app.name=my app
	app.description=Welcome to ${app.name}
	
	# list
	my.list.values=one,two,three
	 
	# key/value 
	db.values={connectionString:'http://',username:'foo',password:'1234'}
```

```java
	@RestController
	public class GreetingController {
	 
		@Value("${my.greeting : default value}")
		private String greetingMessage;
	 
		@Value("some static message")
		private String staticMessage;
	 
		@Value("${my.list.values}")
		private List<String> listValues;
	 
		@Value("#{${db.values}}")
		private Map<String, String> dbValues;
	 
		@GetMapping("/greeting")
		public String greeting() {
			return greetingMessage + " " + staticMessage + " " + listValues + " \n" + dbValues;
		}
	}
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

------------------------------------------------------------------------------------

###### 

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)
