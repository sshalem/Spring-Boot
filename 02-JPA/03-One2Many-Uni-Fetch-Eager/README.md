###### \_

<img src="https://img.shields.io/badge/-2. One2Many Uni directional Eager Loading %20-blue" height=50px>

|     | Subject                                                        |
| :-: | :------------------------------------------------------------- |
|     | [links](#links)                                                |
|  1  | [One2Many-Uni-Eager Loading](#1_Uni_directional_Eager_Loading) |
|  2  | [OSIV Open Session In View](#2_OSIV_Open_Session_In_View)      |

---

###### links

<img src="https://img.shields.io/badge/- links %20-blue" height=40px>

### OneToMany:

1. [The best way to map a @OneToMany relationship with JPA and Hibernate](https://vladmihalcea.com/the-best-way-to-map-a-onetomany-association-with-jpa-and-hibernate/)
2. [Most efficient way to map a @OneToMany relationship with JPA and Hibernate](https://medium.com/@rajibrath20/the-best-way-to-map-a-onetomany-relationship-with-jpa-and-hibernate-dbbf6dba00d3)

### DELETE with JPA:

1. [Deleting Data in Spring Boot with JPA and Hibernate](https://hellokoding.com/deleting-data-with-jpa-hibernate/)
2. [different ways to delete a child entity in JPA/hibernate](https://fullstackdeveloper.guru/2020/08/17/what-are-the-different-ways-to-delete-a-child-entity-in-jpa-hibernate-through-spring-data/)
3. [How does orphanRemoval work with JPA and Hibernate](https://vladmihalcea.com/orphanremoval-jpa-hibernate/)
4. [JPA how to remove parent without delete children](https://itecnote.com/tecnote/jpa-how-to-remove-parent-without-delete-children/)

### Lazy Loading :

1. [Lazy Loading options](https://www.youtube.com/watch?v=XbT5oRJFp2E&ab_channel=TechnoTownTechie)
2. [Lazy Loading another good explained](https://www.linkedin.com/pulse/spring-boot-lazy-initialized-entities-ashley-shookhye?trk=articles_directory)

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

###### 1_Uni_directional_Eager_Loading

<img src="https://img.shields.io/badge/- 1. Uni directional Eager Loading %20-blue" height=40px>

## [General Note](#-)

In the following examples I define my entities as follows:

- [`UserEntity`](#-) - Is parent Entity
- [`RoleEntity`](#-) - Is Child Entity

How to define the Entity's

### [UserEntity ](#-)

In the Parent Entity I add the :

1. `cascade`
2. `fetch` - By default in One2Many the Fetch is Lazy
3. `orphanRemoval` - need to set to `true` so we can remove child Entity from Parent Entity
4. Add 2 methods
   - `addRole`
   - `removeRole`

Note: </br>
we don't add `mapped By` with Uni-direc.

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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
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
	}

	public void removeRole(RoleEntity role) {
		this.roles.remove(role);
	}
````

### [RoleEntity ](#-)

In the Child Entity I add the , I dont add a referece with User user. </br>
This is Uni-Direc One2Many

````java
@Entity
@Table(name = "ROLES_TB")
public class RoleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String role;
	private long pid;

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

1.  No to add it at all (this way we still see the warning but it won't affect the lazy loading)
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

###### x

<img src="https://img.shields.io/badge/-x. xxx %20-blue" height=40px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---
