# [application.properties profiles](#-)

let's see what is a [**_profile_**](#-) in a regular Spring boot app. </br>
When spring app runs it uses [**_application.properties_**](#-) file configuration. </br>
Since we have only 1 file of [**_application.properties_**](#-) , it is named as [**_default_**](#-) profile. </br>
Here is example of a simple Spring-boot-app with RestController :

![image](https://user-images.githubusercontent.com/36256986/168445822-6726d33b-509d-48c0-96e0-b9cf05f25dc6.png)

```java
@RestController
@RequestMapping("/")
public class TestController {

	@Value("${app.message}")
	private String message ;

	@GetMapping
	public String getMessage() {
		return "hello from  :" + message;
	}
}
```

### [application.properties](#-)

```sql
server.port=8080
app.message=Default environment
```

When running the app  and sending the GET request to localhost:8080 this is what we get.
Profile is default.

![image](https://user-images.githubusercontent.com/36256986/168445781-b14483d5-e72a-4931-8e24-a3d4bd7e6373.png)

Get Requets: ```hello from :Default environment```

### [Question](#-) </br>
What if we want to have different properties file for example :
* dev environment properties
* QA environment properties
* production environment properties

Do we need to modify it every time we want to use it for certain purpose?</br>

### [Answer](#-) </br>
No, This is where profiles comes into the picture.</br>

we can make several properties file which will be assign for each environment via the main application.properties file.</br>
we need to use the following convention , in order to add new properties file:
* [application<-profileName>.properties](#-)
* [application<-profileName>.yml](#-)

### [example](#-) :

* here we have properties files.
* [application.properties](#-) - the main  file is [default](#-) one
* [application-dev.properties](#-) - development properties
* [application-qa.properties](#-) - QA properties
* [application-prod.properties](#-) - Production propperties

![image](https://user-images.githubusercontent.com/36256986/168446419-b35a584b-3778-45a6-ac0f-8436b434b34e.png)

This is how I config each properties file:

1. [application.properties](#-)

In the default properties I define who will be the profile properties that app will use, for example [**DEV**](#-) profile.

```sql
server.port=8080
app.message=Default environment
spring.profiles.active=dev
```

2. [application-dev.properties](#-)

```sql
server.port=8080
app.message=DEV
```

3. [application-qa.properties](#-)

```sql
server.port=8080
app.message=QA
```

4. [application-prod.properties](#-)

```sql
server.port=8080
app.message=PRODUCTION
```

### [Run the app ]#(-)

We have DEV profile active

![image](https://user-images.githubusercontent.com/36256986/168446806-fd68ee28-5ed4-498f-b839-a7d274af3f73.png)

Get Request ```hello from :DEV```

### combine profiles

we can more than 1 profile to run , for eaxmple DEV and QA

```sql
server.port=8080
app.message=Default environment
spring.profiles.active=dev,qa
```

![image](https://user-images.githubusercontent.com/36256986/168446867-68058331-507d-447c-9ef7-8964d5503fd0.png)

