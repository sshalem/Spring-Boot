###### \_

<img src="https://img.shields.io/badge/-1. One2Many Bi directional EAGER Loading %20-blue" height=40px>

|     | Subject                                                           |
| :-: | :---------------------------------------------------------------- |
|     | [links](#links)                                                   |
|  1  | [One2Many-Bi-EAGER Loading](#1_Bi_directional_EAGER_Loading)      |
|  2  | [OSIV Open Session In View](#2_OSIV_Open_Session_In_View)         |
|  3  | [Code](#3_code)                                                   |
|     | 3.1 [dependencies](#3_1_dependencies)                             |
|     | 3.2 [Entities](#3_2_Entitiy)                                      |
|     | 3.3 [Repository](#3_3_Repository)                                 |
|     | 3.4 [Dao](#3_4_Dao)                                               |
|     | 3.5 [DaoImpl](#3_5_Dao_Impl)                                      |
|     | 3.6 [Dto](#3_6_Dto)                                               |
|     | 3.7 [FrontEnd](#3_7_FrontEnd)                                     |
|  4  | [Test App](#4_test_app)                                           |
|     | 4.1 [User API - GET, POST, PUT, DELETE](#4_1_GET_POST_PUT_DELETE) |
|     | 4.5 [Role API](#4_2_Role_API)                                     |

---

###### links

<img src="https://img.shields.io/badge/- links %20-blue" height=40px>

### OneToMany:

1. [The best way to map a @OneToMany relationship with JPA and Hibernate](https://vladmihalcea.com/the-best-way-to-map-a-onetomany-association-with-jpa-and-hibernate/)

### DELETE with JPA:

1. [Deleting Data in Spring Boot with JPA and Hibernate](https://hellokoding.com/deleting-data-with-jpa-hibernate/)
2. [different ways to delete a child entity in JPA/hibernate](https://fullstackdeveloper.guru/2020/08/17/what-are-the-different-ways-to-delete-a-child-entity-in-jpa-hibernate-through-spring-data/)
3. [How does orphanRemoval work with JPA and Hibernate](https://vladmihalcea.com/orphanremoval-jpa-hibernate/)
4. [JPA how to remove parent without delete children](https://itecnote.com/tecnote/jpa-how-to-remove-parent-without-delete-children/)

### Lazy Loading :

1. [Lazy Loading options](https://www.youtube.com/watch?v=XbT5oRJFp2E&ab_channel=TechnoTownTechie)
2. [Lazy Loading another good explained](https://www.linkedin.com/pulse/spring-boot-lazy-initialized-entities-ashley-shookhye?trk=articles_directory)
3. [Lazy JOIN FETCH - Baeldung](https://www.baeldung.com/java-jpa-lazy-collections)

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

###### 1_Bi_directional_EAGER_Loading

<img src="https://img.shields.io/badge/- 1. Bi directional EAGER Loading %20-blue" height=40px>

## [General Note](#-)

In the following examples I define my entities as follows:

- [`UserEntity`](#-) - Is parent Entity
- [`RoleEntity`](#-) - Is Child Entity

How to define the Entity's

### [UserEntity ](#-)

In the Parent Entity I add the :

1. `mappedBy` - need to add the filed name of the cahild entity
2. `cascade`
3. `fetch` - `FetchType.EAGER` , By default in One2Many the Fetch is Lazy
4. `orphanRemoval` - need to set to `true` so we can remove child Entity from Parent Entity
5. Add 2 methods
   - `addRole`
   - `removeRole`

````java
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
````

### [RoleEntity ](#-)

In the Child Entity I add the :

1. `@ManyToOne`
2. `@JoinColumn(name = "user_id")` - thats the foreign key from UserEntity
3. `fetch` - By default in Many2One the Fetch is Eager
4. `@JsonIgnore` - must add it to child entity, otherwise we will have a `stack overflow` error.

````java
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

	// Default Constructor , Getters / Setters
	// toString: don't add the associated entity in the toString method
	// This will cause ```ERROR```
	.
	.
	.
````

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

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

1.  Not to add it at all (this way we still see the warning but it won't affect the lazy loading)
2.  add this : [`spring.jpa.open-in-view=true`](#-) and set it to true, this will make the warining disappear and lazy loading still works as expected.

Note: </br>
accordint the following link: [spring.jpa.open-in-view=true](https://stackoverflow.com/questions/30549489/what-is-this-spring-jpa-open-in-view-true-property-in-spring-boot) , Unfortunately, OSIV (Open Session in View) is `enabled by default in Spring Boot`, and OSIV is really a bad idea from a performance and scalability perspective.

So, make sure that in the application.properties configuration file, you have the following entry set as false: </br>

- [`spring.jpa.open-in-view=false`](#-) </br>

This will disable OSIV so that you can handle the `LazyInitializationException` the right way. </br>
Anyway, DO NOT use the following Anti-Patterns as suggested by some of the answers: </br>

1. `Open Session in View (OSIV)` or `hibernate.enable_lazy_load_no_trans`. </br>
2. Sometimes, a DTO projection is a better choice than fetching entities, and this way, you won't get any `LazyInitializationException`.

- Question: </br>
  How to handle the LazyInitializationException the right way?
- Answer:</br>
  see in the link [handle LazyInitializationException](https://www.youtube.com/watch?v=6p-fuwVxryg&ab_channel=ThorbenJanssen)

Since I'm using JPQL,
better use JOIN FETCH is the easiest way in the CustomerRepository

```java
@Query("SELECT c FROM Customer c JOIN FETCH c.phoneNumbers")
Customer findWithJoinFetchFirstName(String firstname);
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

###### 3_code

<img src="https://img.shields.io/badge/-3. Code %20-blue" height=40px>

Spring-boot version [`2.6.11`](#-) </br>
POM file:

```sql
<dependencies>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-data-jpa</artifactId>
	</dependency>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-web</artifactId>
	</dependency>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-devtools</artifactId>
		<scope>runtime</scope>
		<optional>true</optional>
	</dependency>
	<dependency>
		<groupId>mysql</groupId>
		<artifactId>mysql-connector-java</artifactId>
		<scope>runtime</scope>
	</dependency>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-test</artifactId>
		<scope>test</scope>
	</dependency>
</dependencies>
```

Code :

1. [`dependencies`](#-)
2. [`Entity`](#-)
3. [`Repository`](#-)
4. [`Dao`](#-)
5. [`DaoImpl`](#-)

---

###### 3_1_dependencies

<img src="https://img.shields.io/badge/-3.1. dependencies  %20-yellow" height=40px>

Dependencies:

![image](https://user-images.githubusercontent.com/36256986/197414794-b5aba6e7-591a-40ee-bf84-87781f6b9876.png)

Package Layout:

![image](https://user-images.githubusercontent.com/36256986/197415424-d2565f01-f099-4022-9c17-d09832ebc4d0.png)

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

###### 3_2_Entitiy

<img src="https://img.shields.io/badge/-3.2. Entity  %20-yellow" height=40px>

### [`UserEntity`](#-)

```java
package com.jpa.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "USERS_TB")
public class UserEntity implements Serializable {

	private static final long serialVersionUID = -5199469587304114249L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long pid;
	private String name;
	private String email;
	private String password;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private Set<RoleEntity> roles;

	public UserEntity() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPid() {
		return pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleEntity> roles) {
		this.roles = roles;
	}

	public void addRole(RoleEntity role) {
		if (this.roles == null) {
			this.roles = new HashSet<>();
		}
		this.roles.add(role);
		role.setUser(this);
	}

	public void removeRole(RoleEntity role) {
		this.roles.remove(role);
//		role.setUser(null);
	}

	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", pid=" + pid + ", name=" + name + ", email=" + email + ", password="
				+ password + "]";
	}
}
```

### [`RoleEntity`](#-)

```java
package com.jpa.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ROLES_TB")
public class RoleEntity implements Serializable {

	private static final long serialVersionUID = 4547155074103443567L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String role;
	private long pid;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private UserEntity user;

	public RoleEntity() {
		super();
	}

	public RoleEntity(String role) {
		super();
		this.role = role;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public long getPid() {
		return pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "RoleEntity [id=" + id + ", role=" + role + ", pid=" + pid + "]";
	}
}
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

###### 3_3_Repository

<img src="https://img.shields.io/badge/-3.3 Repository  %20-yellow" height=40px>

### [`UserRepository`](#-)

See in the code all Queries created

### [`RoleRepository`](#-)

See in the code all Queries created

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

###### 3_4_Dao

<img src="https://img.shields.io/badge/-3.4. Dao  %20-yellow" height=40px>

### [`UserDao`](#-)

See UserDao code

### [`RoleDao`](#-)

See RoleDao code

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

###### 3_5_Dao_Impl

<img src="https://img.shields.io/badge/-3.5. DaoImpl  %20-yellow" height=40px>

### [`UserDaoImpl`](#-)

See UserDaoImpl code

### [`RoleDaoImpl`](#-)

See RoleDaoImpl code

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

###### 3_6_Dto

<img src="https://img.shields.io/badge/-3.6. Dto  %20-yellow" height=40px>

I didn't implement DTO with `Eager fetching` (I implemented it in Fetch Lazy)
Best practice is to use DTO's

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

###### 4_test_app

<img src="https://img.shields.io/badge/-4. test app %20-blue" height=40px>

Let's Run the App, adn test our API.
I have 2 Controllers, whith these Controllers I test the API's :

1. UserController API
2. RoleController API

![image](https://user-images.githubusercontent.com/36256986/197419012-317b3223-2db9-48ac-9f66-dc6b7465d760.png)

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

###### 4_1_GET_POST_PUT_DELETE

<img src="https://img.shields.io/badge/-4.1. User API GET POST PUT DELETE %20-yellow" height=40px>

Let's test each method in our UserDaoImpl and see how many queries are executed during each request, and see if [`FETCH LAZY`](#-) is created.

### [`Following Get Methods are tested`](#-)

In this Table I compare between Lazy and Eager.
![image](https://user-images.githubusercontent.com/36256986/197978707-717b7166-d019-4c29-b3a7-271d6a4a856c.png)

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### 4_5_Role_API

<img src="https://img.shields.io/badge/-4.5. Role API  %20-yellow" height=40px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

###### x

<img src="https://img.shields.io/badge/-x. xxx %20-blue" height=40px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

###### x

<img src="https://img.shields.io/badge/-x. xxx %20-blue" height=40px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---
