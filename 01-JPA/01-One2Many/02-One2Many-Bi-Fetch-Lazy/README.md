###### _

<img src="https://img.shields.io/badge/-One2Many Bi Direc Lazy Loading %20-blue" height=70px>

|     |  Subject           |
|:---:|:------------------------------| 
|  1  |[One2Many-Bi-Lazy Loading](#1_Bi_directional_Lazy_Loading)  | 
|  2  |[OSIV Open Session In View](#2_OSIV_Open_Session_In_View)  | 




--------------------------------------------------------------------------------------------------

###### 1_Bi_directional_Lazy_Loading

<img src="https://img.shields.io/badge/-1. Bi directional Lazy Loading %20-blue" height=40px>

## [General Note](#-)

In the following examples I define my entities as follows:
* [```UserEntity```](#-) - Is parent Entity
* [```RoleEntity```](#-) - Is Child Entity

How to define the Entity's

### [UserEntity ](#-) 

In the Parent Entity I add the :
1. `mappedBy` - need to add the filed name of the cahild entity
2. `cascade` 
3. `fetch` - By default in One2Many the Fetch is Lazy
4. `orphanRemoval` - need to set to `true` so we can remove child Entity from Parent Entity
5. `@JsonIgnore` - With Lazy Laoding approach , need add it to Parent entity, because we Might Get LazyLoading Exception , because it accnot hnalde Entity when session is closed
6. Add 2 methods 
	* `addRole`
	* `removeRole`


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

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JsonIgnore
	private Set<RoleEntity> roles;
	
	// Default Constructor , Getters / Setters
	// toString: don't add the associated entity in the toString method 
	// This will cause ```ERROR```
	.
	.
	.
	
	public void addRole(RoleEntity role) {
		if (this.roles == null) {
			this.roles = new HashSet<>();
		}
		this.roles.add(role);
		role.setUser(this);
	}

	public void removeRole(RoleEntity role) {
		this.roles.remove(role);
		// role.setUser(null);
	}
```

### [RoleEntity ](#-)

In the Child Entity I add the :
1. `@ManyToOne` 
2. `@JoinColumn(name = "user_id")` - thats the foreign key from UserEntity 
3. `fetch` - set Fetch.LAZY (By default in Many2One the Fetch is Eager)
4. `@JsonIgnore` - must add it to child entity, otherwise we will have a `stack overflow` error.

```java
@Entity
@Table(name = "ROLES_TB")
public class RoleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String role;
	private long pid;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private UserEntity user;
	
	// Default Constructor , Getters / Setters
	// toString: don't add the associated entity in the toString method 
	// This will cause ```ERROR```
	.
	.
	.	
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------

###### 2_OSIV_Open_Session_In_View

<img src="https://img.shields.io/badge/-2. OSIV Open Session In View %20-blue" height=40px>

In application.properties file I define the property of: [`spring.jpa.open-in-view=false`](#-) </br>
Becuase of following error:

	  WARN 13496 --- [  restartedMain] JpaBaseConfiguration$JpaWebConfiguration : spring.jpa.open-in-view is enabled by default.
          Therefore, database queries may be performed during view rendering.
          Explicitly configure spring.jpa.open-in-view to disable this warning
 
 BUT,
 when using this parameter as set to `false`, and we are using `FetchType.LAZY`, it will throw Lazy fetch error.</br>
 So , 2 options are :
 1. No to add it at all (this way we still see the warning but it won't affect the lazy loading)
 2. add this : [`spring.jpa.open-in-view=true`](#-) and set it to true, this will make  the warining disappear and lazy loading still works as expected.
 
 Note: </br>
 accordint the following link: [spring.jpa.open-in-view=true](https://stackoverflow.com/questions/30549489/what-is-this-spring-jpa-open-in-view-true-property-in-spring-boot) , Unfortunately, OSIV (Open Session in View) is `enabled by default in Spring Boot`, and OSIV is really a bad idea from a performance and scalability perspective.

So, make sure that in the application.properties configuration file, you have the following entry set as false: </br>
* [`spring.jpa.open-in-view=false`](#-) </br>

This will disable OSIV so that you can handle the `LazyInitializationException` the right way. </br>
Anyway, DO NOT use the following Anti-Patterns as suggested by some of the answers: </br>
1. `Open Session in View (OSIV)` or `hibernate.enable_lazy_load_no_trans`. </br>
2. Sometimes, a DTO projection is a better choice than fetching entities, and this way, you won't get any `LazyInitializationException`.

* Question: </br>
How to handle the LazyInitializationException the right way?
* Answer:</br>
see in the link [handle LazyInitializationException](https://www.youtube.com/watch?v=6p-fuwVxryg&ab_channel=ThorbenJanssen)

 Since I'm using JPQL, 
 better use JOIN FETCH is the easiest way in the CustomerRepository 

 ```java
@Query("SELECT c FROM Customer c JOIN FETCH c.phoneNumbers")
Customer findWithJoinFetchFirstName(String firstname);
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

