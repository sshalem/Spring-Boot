###### _

<img src="https://img.shields.io/badge/-1. One2Many Bi directional EAGER Loading %20-blue" height=40px>

|     |  Subject           |
|:---:|:------------------------------| 
|     |[links](#links)  | 
|  1  |[One2Many-Bi-EAGER Loading](#1_Bi_directional_EAGER_Loading)  | 
|  2  |[](#)  | 
|  3  |[Code](#3_code) |   
|     |3.1  [dependencies](#3_1_dependencies) | 
|     |3.2  [Entities](#3_2_Entitiy) | 
|     |3.3  [Repository](#3_3_Repository) | 
|     |3.4  [Dao](#3_4_Dao) | 
|     |3.5  [Dao_Impl](#3_5_Dao_Impl) | 


--------------------------------------------------------------------------------------------------

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
2. [LazyLoadin another good explained](https://www.linkedin.com/pulse/spring-boot-lazy-initialized-entities-ashley-shookhye?trk=articles_directory)



[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------

###### 1_Bi_directional_EAGER_Loading

<img src="https://img.shields.io/badge/- 1. Bi directional EAGER Loading %20-blue" height=40px>

## [General Note](#-)

In the following examples I define my entities as follows:
* [```UserEntity```](#-) - Is parent Entity
* [```RoleEntity```](#-) - Is Child Entity

How to define the Entity's

### [UserEntity ](#-) 

In the Parent Entity I add the :
1. `mappedBy` - need to add the filed name of the cahild entity
2. `cascade` 
3. `fetch` - `FetchType.EAGER` , By default in One2Many the Fetch is Lazy
4. `orphanRemoval` - need to set to `true` so we can remove child Entity from Parent Entity
5. Add 2 methods 
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

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
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
3. `fetch` - By default in Many2One the Fetch is Eager
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
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------

###### 

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

