<img src="https://img.shields.io/badge/-Exception Handling%20-blue" height=40px>

###### _

|     |  Subject           |
|:---:|:------------------------------| 
|  A  |[Handle RuntimeException](#__)   | 
|  B  |[config ExceptionMessage_applicationProperties](#___)   | 
|  C  |[config ExceptionMessageAtController](#____)   | 
|  D  |[Custom ExceptionMessageError](#_____)   | 
|  E  |[ControllerAdvice Custom ExceptionMessageError](#______) |  



###### __
<img src="https://img.shields.io/badge/-(A) Handle RuntimeException%20-red" height=30px>

[<img src="https://img.shields.io/badge/-Back to top%20-9cf" height=20px>](#_)

### Exception

```java
public class NameAlreadyExistException extends RuntimeException {
	private static final long serialVersionUID = -6209521422884301225L;

	public NameAlreadyExistException(String msg) {
		super(msg);
	}
}
```
### Service

```java
import com.hre.exception.NameAlreadyExistException;

@Component
public class CustomerService {

	private final String NAME = "karin";

	public String testMethod(String name) {
		if (name.equals(this.NAME))
			throw new NameAlreadyExistException("Name " + this.NAME + " already exist");
		return name;
	}
}
```

### Controller

```java
@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping("/{name}")
	public String getName(@PathVariable("name") String name) {
		return customerService.testMethod(name);
	}
}
```

### Sending Request via Postman gives back following result:

![A_ExceptionMessage](https://user-images.githubusercontent.com/36256986/150680152-bf30bb1f-61ba-4e21-b80c-7866dcee16bc.PNG)
     
[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=20px>](#_)


----------------------------------------------------------------------------------------------------------

###### ___
<img src="https://img.shields.io/badge/-(B) config ExceptionMessage_applicationProperties%20-red" height=30px>

[<img src="https://img.shields.io/badge/-Back to top%20-blue" height=20px>](#_)

In this (B) Project I am using the same Code from (A) project (Service , Exception, Controller).</br>
I only add the following configuration in the **_application.properties_** file</br>

```java
# ==========================================
# format the returned error format 
# when exception is thrown back to client
# ==========================================

# Don't use this , since it makes problems when using servelts :
# the server.error.path=/

server.error.include-binding-errors=always
server.error.include-exception=true
server.error.include-message=always
server.error.include-stacktrace=never
server.error.whitelabel.enabled=true
```

### Sending Request via Postman gives back following result:

- We can see that the message is more clear w/o the stacktrace (I disable it in the config file)

![B_ExceptionMessage](https://user-images.githubusercontent.com/36256986/150680528-6ab71b23-1f9a-4ea1-bb83-3eccbbad9227.PNG)



[<img src="https://img.shields.io/badge/-Back to top%20-blue" height=20px>](#_)
----------------------------------------------------------------------------------------------------------

###### ____
<img src="https://img.shields.io/badge/-(C) config ExceptionMessageAtController%20-red" height=30px>

[<img src="https://img.shields.io/badge/-Back to top%20-blue" height=20px>](#_)

In this project (C) I added to the **service** and **Controller** layers a new method:

### Service

```java
@Component
public class CustomerService {

	private final String NAME = "karin";

	public String getName(String name) {
		if (name.equals(this.NAME))
			throw new NameAlreadyExistException("Name " + this.NAME + " already exist");
		return name;
	}

	public UserEntity createUser(UserEntity userEntity) {
		if (userEntity.getFirstName().equals("karin")) {
			throw new NameAlreadyExistException("Name " + userEntity.getFirstName() + " already Exist");
		}
		return userEntity;
	}
}
```

### Controller

```java
/**
* In this exapmle I config ResponseEntity
* to return the exception message
* we have here a try/catch clause
*/
@PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<Object> createUser(@RequestBody UserEntity userEntity) {
	try {
		return new ResponseEntity<Object>(customerService.createUser(userEntity), new HttpHeaders(), HttpStatus.OK);
	} catch (Exception em) {
		return new ResponseEntity<Object>(em.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
```

### Sending Post Request via Postman gives back following result:

We get 500 response back, and the message (em.getMessage()).

![C_ExceptionMessage](https://user-images.githubusercontent.com/36256986/150680927-c7655830-d3c1-465d-a412-3b15ca43ea5c.PNG)

[<img src="https://img.shields.io/badge/-Back to top%20-blue" height=20px>](#_)

----------------------------------------------------------------------------------------------------------

###### _____
<img src="https://img.shields.io/badge/-(D) Custom ExceptionMessageError%20-red" height=30px>

[<img src="https://img.shields.io/badge/-Back to top%20-blue" height=20px>](#_)

In this project (D) I added to the ENUM of **ErrorMessages** , **ExceptionErrorMessage**.</br>
In mY controller , I changed the code to return my custom message.

### ENUM ErrorMessages

```java
public enum ErrorMessages {

	MISSING_REQUIRED_FIELD("Missing required field. Please check documentation for required fields"),
	RECORD_ALREADY_EXISTS("Record already exists"), 
	INTERNAL_SERVER_ERROR("Internal server error"),
	NO_RECORD_FOUND("Record with provided id is not found"), 
	AUTHENTICATION_FAILED("Authentication failed"),
	COULD_NOT_UPDATE_RECORD("Could not update record"), 
	COULD_NOT_DELETE_RECORD("Could not delete record"),
	EMAIL_ADDRESS_NOT_VERIFIED("Email address could not be verified");

	private String errorMessage;

	private ErrorMessages(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}
```

### Entity ExceptionErrorMessage

```java
	private Date timestamp;
	private int statusCode;
	private String error;
	private String exception;
	private String message;
	private String uriDescription;

	public ExceptionErrorMessage() {
		super();
	}
	G/S
}
```

### Controller

```java
/**
 * In this example I send custom ExceptionMessage created by me
 * 
 */
@PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<Object> createUser(@RequestBody UserEntity userEntity, WebRequest request) {
	try {
		return new ResponseEntity<Object>(customerService.createUser(userEntity), new HttpHeaders(), HttpStatus.OK);
	} catch (Exception em) {
		
		ExceptionErrorMessage errorMessage = new ExceptionErrorMessage();
		
		errorMessage.setTimestamp(new Date());
		errorMessage.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorMessage.setError(HttpStatus.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()).getReasonPhrase());
		errorMessage.setException(NameAlreadyExistException.class.getName());
		errorMessage.setMessage(em.getMessage());
		errorMessage.setUriDescription(request.getDescription(false));
		
		return new ResponseEntity<Object>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
```

### Sending Post Request via Postman gives back following result:

we see the difference in the response between (C) and (D) . With D we have more details about the error message.</br>
We still using try/catch clause in the controller. </br>
In next project (E) we will see how to centrolize all exceptions in one class.


![D_ExceptionMessage](https://user-images.githubusercontent.com/36256986/200419668-d3d1b405-7089-4765-b236-84e82751d238.png)


[<img src="https://img.shields.io/badge/-Back to top%20-blue" height=20px>](#_)

----------------------------------------------------------------------------------------------------------

###### ______
<img src="https://img.shields.io/badge/-(E) ControllerAdvice Custom ExceptionMessageError%20-red" height=30px>

[<img src="https://img.shields.io/badge/-Back to top%20-blue" height=20px>](#_)

In this project (E) we will see how to centrolize all exceptions in one class.</br>
1. For that I created a new Class named it **_AppExceptionsHandler_** (see the annotations I added to the class </br>
2. I remove the try/catch clause in my controller , because it will be handled by the **_AppExceptionsHandler_**

### _AppExceptionsHandler_

```java
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppExceptionsHandler {

	@ExceptionHandler(value = { NameAlreadyExistException.class })
	public ResponseEntity<Object> handleUserServiceException(NameAlreadyExistException ex) {

		ExceptionErrorMessage errorMessage = new ExceptionErrorMessage();
		
		errorMessage.setTimestamp(new Date());
		errorMessage.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorMessage.setError(HttpStatus.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()).getReasonPhrase());
		errorMessage.setException(NameAlreadyExistException.class.getName());
		errorMessage.setMessage(ex.getMessage());
		errorMessage.setUriDescription(request.getDescription(false));

		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> handleOtherExceptions(Exception ex) {

		ExceptionErrorMessage errorMessage = new ExceptionErrorMessage();

		errorMessage.setTimestamp(new Date());
		errorMessage.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorMessage.setError(HttpStatus.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()).getReasonPhrase());
		errorMessage.setException(ex.getClass().getCanonicalName());
		errorMessage.setMessage(ex.getMessage());		
		errorMessage.setUriDescription(request.getDescription(false));
		
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
```

### _Controller w/o try/catch clause_

```java
/**
 * Since I have AppExceptionsHandler who handles all Exceptions for my Rest API
 * Thus code doesn't need to have a try/catch clause
 */
@PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<Object> createUser(@RequestBody UserEntity userEntity) {
	return new ResponseEntity<Object>(customerService.createUser(userEntity), new HttpHeaders(), HttpStatus.OK);
}
```

### Sending POST or GET Request via Postman gives back following result:

* this is because both methods throw at service layer an exception (Same exception)
* Thus same Exception will be throw same exception if the logic demands that

![E_ExceptionMessage](https://user-images.githubusercontent.com/36256986/200419668-d3d1b405-7089-4765-b236-84e82751d238.png)


[<img src="https://img.shields.io/badge/-Back to top%20-blue" height=20px>](#_)




----------------------------------------------------------------------------------------------------------
