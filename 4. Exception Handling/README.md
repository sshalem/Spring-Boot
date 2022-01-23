<img src="https://img.shields.io/badge/-Exception Handling%20-blue" height=40px>

###### _

|     |  Subject           |
|:---:|:------------------------------| 
|  A  |[Handle RuntimeException](#__)   | 
|  B  |[config ExceptionMessage_applicationProperties](#___)   | 
|  C  |[config ExceptionMessageAtController](#____)   | 
|  D  |[Custom ExceptionMessageError](#_____)   | 
|  E  |[ControllerAdvice Custom ExceptionMessageError](#_____) |  



###### __
<img src="https://img.shields.io/badge/-(A) Handle RuntimeException%20-red" height=30px>

[<img src="https://img.shields.io/badge/-Back to top%20-blue" height=20px>](#_)

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


[<img src="https://img.shields.io/badge/-Back to top%20-blue" height=20px>](#_)


----------------------------------------------------------------------------------------------------------

###### ___
<img src="https://img.shields.io/badge/-(B) config ExceptionMessage_applicationProperties%20-red" height=30px>

[<img src="https://img.shields.io/badge/-Back to top%20-blue" height=20px>](#_)



[<img src="https://img.shields.io/badge/-Back to top%20-blue" height=20px>](#_)
----------------------------------------------------------------------------------------------------------

###### ____
<img src="https://img.shields.io/badge/-(C) config ExceptionMessageAtController%20-red" height=30px>

[<img src="https://img.shields.io/badge/-Back to top%20-blue" height=20px>](#_)



[<img src="https://img.shields.io/badge/-Back to top%20-blue" height=20px>](#_)

----------------------------------------------------------------------------------------------------------

###### _____
<img src="https://img.shields.io/badge/-(D) Custom ExceptionMessageError%20-red" height=30px>

[<img src="https://img.shields.io/badge/-Back to top%20-blue" height=20px>](#_)

[<img src="https://img.shields.io/badge/-Back to top%20-blue" height=20px>](#_)

----------------------------------------------------------------------------------------------------------

###### ______
<img src="https://img.shields.io/badge/-(E) ControllerAdvice Custom ExceptionMessageError%20-red" height=30px>

[<img src="https://img.shields.io/badge/-Back to top%20-blue" height=20px>](#_)

[<img src="https://img.shields.io/badge/-Back to top%20-blue" height=20px>](#_)



----------------------------------------------------------------------------------------------------------
