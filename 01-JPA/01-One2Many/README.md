###### _

<img src="https://img.shields.io/badge/-One2Many Mapping%20-blue" height=70px>

|     |  Subject           |
|:---:|:------------------------------| 
|  1  |[One2Many-Bi-introduction](#introduction)  | 
|  2  |[One2Many-Bi-Eager](#)  | 
|  3  |[One2Many-Bi-Lazy](#)  | 
|  4  |[One2Many-Bi-Delete-Update](#)  | 
|  5  |[One2Many-Bi-](#)  | 
|  6  |[One2Many-Bi-](#)  | 
|  7  |[One2Many-Bi-](#)  | 
|  8  |[One2Many-Bi-](#)  | 



--------------------------------------------------------------------------------------------------

###### introduction

<img src="https://img.shields.io/badge/-1. introduction %20-blue" height=40px>

## [General Note](#-)

In the following examples I define my entities as follows:
* [UserEntity ](#-) - Is parent Entity
* [RoleEntity ](#-) - Is Child Entity

How to define the Entity's

```java
@Entity
@Table(name = "USERS_TB")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long pid;
	private String name;
	private String email;
	private String password;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private Set<RoleEntity> roles;
```

```java
@Entity
@Table(name = "ROLES_TB")
public class RoleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String role;
	private long pid;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private UserEntity user;
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------

###### x

<img src="https://img.shields.io/badge/-x. xxx %20-blue" height=40px>


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------

###### x

<img src="https://img.shields.io/badge/-x. xxx %20-blue" height=40px>


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------

###### x

<img src="https://img.shields.io/badge/-x. xxx %20-blue" height=40px>


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------

###### x

<img src="https://img.shields.io/badge/-x. xxx %20-blue" height=40px>


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------
