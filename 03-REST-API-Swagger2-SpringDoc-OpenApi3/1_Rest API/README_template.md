<img src="https://img.shields.io/badge/-Projects%20-brightgreen" height=70px>

* In this repository I have several projects that I build step by step **_MICRO-SERVICE ARCHITECTURE_** .</br>
the first project starts with 2 simple microservices : </br>
[1. customer-service]() </br>
[2. order-service]()

with each project, I add another service (Or modify cetain properties) and show how Architecture works at that point.

###### _

 <img src="https://img.shields.io/badge/- Table of contents%20-brightgreen" height=50px> 

|     |  Subject           |
|:---:|:------------------------------| 
|  1  |[Services: C-O](#__)    | 
|  2  |[Services: C-O-E](#___)  |   
|  3  |[Services: C-O-E-G-Multiple-Service-Instances](#____) |   
|  3a |[Services: C-O-E-G-Multiple-Service-Instances-AutoPort](#_____) |   
|  4  |[Services: C-O-E-G-MIAP_Resiliance4J](#______) |  


- ![#c5f015](https://via.placeholder.com/10/c5f015/000000?text=+) C-Customer
- ![#c5f015](https://via.placeholder.com/10/c5f015/000000?text=+) O-Order
- ![#c5f015](https://via.placeholder.com/10/c5f015/000000?text=+) E-Eureka
- ![#c5f015](https://via.placeholder.com/10/c5f015/000000?text=+) G-Gateway 
- ![#c5f015](https://via.placeholder.com/10/c5f015/000000?text=+) MI-Multiple Instances 
- ![#c5f015](https://via.placeholder.com/10/c5f015/000000?text=+) AP-Auto Port 

###### __
<img src="https://img.shields.io/badge/-(1) Services: Customer, Order%20-orange" height=40px>

[<img src="https://img.shields.io/badge/-Back to top%20-blue" height=20px>](#_)

In this project I have 2 microservices:

|     |  micro-services           |
|:---:|:------------------------------| 
|  1  |[customer-service](#)    | 
|  2  |[order-service](#) |  

- They Communicate between them using **_RestTemplate_** Bean

[<img src="https://img.shields.io/badge/-Back to top%20-blue" height=20px>](#_)


----------------------------------------------------------------------------------------------------------

###### ___
<img src="https://img.shields.io/badge/-(2) Services: Customer, Order, Eureka%20-orange" height=40px>

[<img src="https://img.shields.io/badge/-Back to top%20-blue" height=20px>](#_)

In this project I have 3 microservices:

|     |  micro-services           |
|:---:|:------------------------------| 
|  1  |[customer-service](#)    | 
|  2  |[order-service](#) |  
|  3  |[eureka-discovery-server-service](#) |  

- I also use ```@LoadBalanced``` annotation thus, when using the RestTemplate: </br>
    -- Instead of Using url as http://localhost:8081 , i use the application name of it http://ORDER-SERVICE </br>
    -- Otherwise i will get follwoing error:
 ```
"at org.springframework.cloud.loadbalancer.blocking.client.BlockingLoadBalancerClient.execute(BlockingLoadBalancerClient.java:79)"
 ```

![image](https://user-images.githubusercontent.com/36256986/147967247-19e1aa36-91c2-43f0-9284-5261fb0a896d.png)

[<img src="https://img.shields.io/badge/-Back to top%20-blue" height=20px>](#_)
----------------------------------------------------------------------------------------------------------

###### ____
<img src="https://img.shields.io/badge/-(3) Services: Customer, Order, Eureka ,Gateway Multiple Instances%20-orange" height=40px>

[<img src="https://img.shields.io/badge/-Back to top%20-blue" height=20px>](#_)

In this project I have 4 microservices:

|     |  micro-services           |
|:---:|:------------------------------| 
|  1  |[customer-service](#)    | 
|  2  |[order-service](#) |  
|  3  |[eureka-discovery-server-service](#) |
|  4  |[spring-cloud-gateway](#) |

**_Spring-Cloud-Gateway_** is responsible for:
	1. routing
	2. load balancing

I create 3 instances for **customer MS** and 2 instances for **order MS**. </br>
I added to **CustomerController** and **OrderController** the following lines ,in order to see that Load Blanaced is working.</br>
This way we can see which instance is processing the Request.</br>

```java
	private static final Logger log = LoggerFactory.getLogger(OrderController.class);
	
	@Value("${server.port}")
	int port;
	
	@PostMapping(path = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CustomerEntity> createCustomer(@RequestBody CustomerEntity customerEntity) {

		log.info("port number : --> " + port);
		CustomerEntity returnedValue = customerDaoImpl.createCustomer(customerEntity);
		return ResponseEntity.status(HttpStatus.CREATED).body(returnedValue);
	}
```

### Via Boot Dashboard we can see the number of instances running:

![Boot_Dashboard](https://user-images.githubusercontent.com/36256986/148125783-4f1e40e1-4d95-49ce-aeda-4c390785225c.PNG)

#### If we take a look at browse to **_erueka url_** we see all instaces created.
#### we can see that Port number is Shown 
![Eureka_assigned_Ports](https://user-images.githubusercontent.com/36256986/148130279-c8423d76-9775-4cc9-b38e-b6797e265596.PNG)


(I show how to use ZUUL gateway in another project)
**_ZUUL_** and **_Spring-Cloud-Gateway_** do the same , but there is difference between them:

**_NOTE_**:

	##### Zuul

		Zuul is built on servlet 2.5 (works with 3.x), using blocking APIs. </br>
		It doesn't support any long lived connections, like websockets.</br>
		From <https://stackoverflow.com/questions/47092048/how-is-spring-cloud-gateway-different-from-zuul> </br>

	##### Spring Cloud Routing (gateway) 

		Gateway is built on Spring Framework 5, Project Reactor and Spring Boot 2 using non-blocking APIs. </br>
		Websockets are supported and it's a much better developer experience since it's tightly integrated with Spring.</br>

		From <https://stackoverflow.com/questions/47092048/how-is-spring-cloud-gateway-different-from-zuul> </br>



[<img src="https://img.shields.io/badge/-Back to top%20-blue" height=20px>](#_)

----------------------------------------------------------------------------------------------------------

###### _____
<img src="https://img.shields.io/badge/-(3a) Services: Customer, Order, Eureka ,Gateway Multiple Instances Auto Port%20-orange" height=40px>

[<img src="https://img.shields.io/badge/-Back to top%20-blue" height=20px>](#_)

This is same project as in section 3, but with slight differnce.</br>
In order to make MS to generate the port number automatically , I added 2 properties to make it possible: </br>

1. ```server.port=0```
2. ```eureka.instance.instance-id=${spring.application.name}:${spring.application.instance_id:${random.value}}```

Why assigning 0 ? </br>
it means that for every instance , a port will be assigned automatically.</br>
```java
server.port=0
```

I saw we can also define it as below: </br>
```java
server.port=${PORT:0}
```

every time we make a new instance it will  have the same name "**CUSTOMER-SERVICE**"</br>
but it will have a different instance id</br>
in the line below it will be randomized for us</br>
```java
eureka.instance.instance-id=${spring.application.name}:${spring.application.instance_id:${random.value}}
```

### Via Boot Dashboard we can see the number of instances running:
![Boot_dashboard_Auto_Gen_Port](https://user-images.githubusercontent.com/36256986/148130821-f822380a-cc2e-4d81-8277-ba5e62687d66.PNG)



#### If we take a look at browse to **_erueka url_** we see all instaces created.
#### BUT : the Port number is not shown
![Eureka_Auto_Gen_Port](https://user-images.githubusercontent.com/36256986/148128499-d86a4b92-0f7e-4fc5-9f7a-dc48e5019548.PNG)

[<img src="https://img.shields.io/badge/-Back to top%20-blue" height=20px>](#_)

----------------------------------------------------------------------------------------------------------

###### ______
<img src="https://img.shields.io/badge/-(4) Services: Customer, Order, Eureka ,Gateway MIAP, Resiliance4J%20-orange" height=40px>

[<img src="https://img.shields.io/badge/-Back to top%20-blue" height=20px>](#_)

[<img src="https://img.shields.io/badge/-Back to top%20-blue" height=20px>](#_)



----------------------------------------------------------------------------------------------------------
