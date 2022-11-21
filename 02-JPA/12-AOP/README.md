<img src="https://img.shields.io/badge/-AOP %20-blue" height=70px>

###### _

|     | Subject                                              |
| :-: | :--------------------------------------------------- |
|     | [Problem Statement](#Problem_Statement)             |
|     | [AOP Introduction](#AOP_Introduction)            |
|  1  | [One2Many_Bi_Lazy](#2_One2Many_Bi_Lazy)              |


###### Problem_Statement

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

Before I dif into AOP let's first describe what issue it came to solve. </br>

Suppose we have a code the following code:

```java
	@Override
	public UserEntity createUser(UserEntity userEntity) {		
		UserEntity _userEntity = userRepository.save(userEntity);		
		return _userEntity;
	}	
```
Requests that start to come:
* The manager wants to `add code for logging` 
* After few hours , the manager wants to `add code for logging` , to all `DAO layer`
* The next day , the manager wants to `add code for logging` for all controller and service layer 
* Also to `add code for security` to all layers
* Now it's late ,4pm and better to cpy paste (But is it a good solution)

this creates 2 main problems:
1. code Tangling
2. code Scattering - change and update all classes

This is where [`AOP - Aspect Object Programming`](#-) comes into place 

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

######

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

######

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

######

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

######

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

######

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---
