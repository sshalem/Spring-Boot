# Handle Runtime Exception at RestController

### in this exapmle we have the following code :
#### this is a very simple code to show the behaviour of exception

- This is the Service login which we throw a NameAlreadyExistException (Extends Runtime Exception)
- Notice that When we throw new NameAlreadyExistException , it doesn't ask to add to the method signiture 'throws NameAlreadyExistException'
- This is because it is an UnChecked Exception (We extend form RuntiemException class)

### CustomerService
#### here we implement the service login 

```java
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

### NameAlreadyExistException class 

```java
public class NameAlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = -4746732915743920318L;

	public NameAlreadyExistException(String msg) {
		super(msg);
	}
}
```

### CustomerController
#### we call the method testMethod(name) from CustomerService class
#### we can see that we don't have "throws NameAlreadyExistException" in the method signiture
#### we don't have a try/catch block as well

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

### Once we run the code , and send request to the following url as http://localhost:8080/customer/karin 
### Postman and Spring console shows the message as follows::

![Capture](https://user-images.githubusercontent.com/36256986/147885907-2be2a2df-de43-46bd-b9d7-f0b5f3143845.PNG)

