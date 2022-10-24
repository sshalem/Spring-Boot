###### _

<img src="https://img.shields.io/badge/-2. One2Many Bi directional Lazy Loading %20-blue" height=50px>

|     |  Subject           |
|:---:|:------------------------------| 
|     |[links](#links)  | 
|  1  |[One2Many-Bi-Lazy Loading](#1_Bi_directional_Lazy_Loading)  | 
|  2  |[OSIV Open Session In View](#2_OSIV_Open_Session_In_View)  | 
|  3  |[Code](#3_code) |   
|     |3.1  [dependencies](#3_1_dependencies) | 
|     |3.2  [Entities](#3_2_Entitiy) | 
|     |3.3  [Repository](#3_3_Repository) | 
|     |3.4  [Dao](#3_4_Dao) | 
|     |3.5  [DaoImpl](#3_5_Dao_Impl) | 
|     |3.6  [Dto](#3_6_Dto) | 
|     |3.7  [FrontEnd](#3_7_FrontEnd) | 
|  4  |[Test App](#4_test_app) | 
|     |4.1  [User API - GET](#4_1_User_API_GET) | 
|     |4.2  [User API - POST](#4_2_User_API_POST) | 
|     |4.3  [User API - PUT](#4_3_User_API_PUT) | 
|     |4.4  [User API - DELETE](#4_4_User_API_DELETE) | 
|     |4.5  [Role API](#4_2_Role_API) | 


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
2. [Lazy Loading another good explained](https://www.linkedin.com/pulse/spring-boot-lazy-initialized-entities-ashley-shookhye?trk=articles_directory)
3. [Lazy JOIN FETCH - Baeldung](https://www.baeldung.com/java-jpa-lazy-collections)


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------

###### 1_Bi_directional_Lazy_Loading

<img src="https://img.shields.io/badge/- 1. Bi_directional_Lazy_Loading %20-blue" height=40px>

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

Code  :
1. [`dependencies`](#-)
2. [`Entity`](#-)
3. [`Repository`](#-)
4. [`Dao`](#-)
5. [`DaoImpl`](#-)

--------------------------------------------------------------------------------------------------

###### 3_1_dependencies

<img src="https://img.shields.io/badge/-3.1. dependencies  %20-yellow" height=40px>

Dependencies:

![image](https://user-images.githubusercontent.com/36256986/197414794-b5aba6e7-591a-40ee-bf84-87781f6b9876.png)

Package Layout:

![image](https://user-images.githubusercontent.com/36256986/197415424-d2565f01-f099-4022-9c17-d09832ebc4d0.png)


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------

###### 3_2_Entitiy

<img src="https://img.shields.io/badge/-3.1. Entity  %20-yellow" height=40px>

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

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@JsonIgnore
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
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
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

	// I add the "foreignKey = @ForeignKey(name = "fk_shabtay_shalem_test")" just 
	// For learning purpose , this will add CONTRAINTS to the foreign key in DB
	@ManyToOne(fetch = FetchType.LAZY)	
	@JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_shabtay_shalem_test"))
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

--------------------------------------------------------------------------------------------------

###### 3_3_Repository

<img src="https://img.shields.io/badge/-3.3 Repository  %20-yellow" height=40px>

### [`UserRepository`](#-)

```java
package com.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jpa.entity.RoleEntity;
import com.jpa.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	/**
	 * I Must remove from the toString() methods in the Entities the association Entity
	 * Otherwise the queries won't work
	 * Meaning If I have UserEntity with Set<RoleEntity> son't add the RoleEntity to the to String method
	 * We will get Stuck Overflow 
	 */
	
	
	// One Hiberante Query which uses LEFT OUTER JOIN
	UserEntity findById(long id);

	@Query("SELECT user from UserEntity user WHERE user.id=:id")
	UserEntity jpqlFindById(@Param("id") long id);
	
	/**
	 * @Query("SELECT user from UserEntity user WHERE user.id = ?1")
	 * UserEntity jpqlFindById(long id);
	 */

	@Query(value = "SELECT * FROM USERS_TB WHERE id=:id", nativeQuery = true)
	UserEntity nativeFindById(@Param("id") long id);
	
	/**
	 *  
	 */

	UserEntity findByPid(long pid);

	@Query("SELECT user from UserEntity user WHERE user.pid=:pid")
	UserEntity jpqlFindByPid(@Param("pid") long pid);
	
	/**
	 * @Query("SELECT user from UserEntity user WHERE user.pid = ?1")
	 * UserEntity jpqlFindByPid(long pid);
	 */
	
	// the * means return all fields
	@Query(value = "SELECT * FROM USERS_TB WHERE pid=:pid", nativeQuery = true)
	UserEntity nativeFindByPid(@Param("pid") long pid);
	
	/**
	 *  
	 */

	UserEntity findByName(String name);

	@Query("SELECT user from UserEntity user WHERE user.name=:name")
	UserEntity jpqlFindByName(@Param("name") String name);
	
	/**
	 * @Query("SELECT user from UserEntity user WHERE user.name = ?1")
	 * UserEntity jpqlFindByName(String name);
	 */
	
	// the * means return all fields
	@Query(value = "SELECT * FROM USERS_TB WHERE name=:name", nativeQuery = true)
	UserEntity nativeFindByName(@Param("name") String name);
	
	/**
	 *  
	 */

	UserEntity findByEmail(String email);

	@Query("SELECT user from UserEntity user WHERE user.email=:email")
	UserEntity jpqlFindByEmail(@Param("email") String email);
	
	/**
	 * @Query("SELECT user from UserEntity user WHERE user.email = ?1")
	 * UserEntity jpqlFindByEmail(String email);
	 */
	
	// the * means return all fields
	@Query(value = "SELECT * FROM USERS_TB WHERE email=:email", nativeQuery = true)
	UserEntity nativeFindByEmail(@Param("email") String email);
	
	/**
	 *  
	 */

	@Query(value = "SELECT * "
			+ "FROM USERS_TB utb " 
			+ "LEFT JOIN ROLES_TB rtb " 
			+ "ON rtb.user_id=utb.id "
			+ "WHERE rtb.role=:role" ,nativeQuery = true)
	List<UserEntity> nativeFindUsersWithRoleName(@Param("role") String role);
	
	/**
	 * 
	 */
	
	@Query("SELECT re from RoleEntity re where re.user.id = ?1 AND re.role like ?2")
	RoleEntity getRoleByIdAndRole(long id, String role);
	
	@Query("SELECT re from RoleEntity re WHERE re.user.id=:id AND re.role=:role")
	RoleEntity getRoleByIdAndRoleParamQuery(@Param("id")long id, @Param("role") String role);
		
	@Query("SELECT re from RoleEntity re WHERE re.user.id = ?1 AND re.role like ?2")
	RoleEntity getRoleByIdAndRoleLikeOperator(long id, String role);

	@Query("SELECT re from RoleEntity re WHERE re.user.id=:id AND re.role LIKE :role")
	RoleEntity getRoleByIdAndRoleLikeOperatorParamQuery(@Param("id")long id, @Param("role") String role);	
}
```

### [`RoleRepository`](#-)

```java
package com.jpa.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jpa.entity.RoleEntity;
import com.jpa.entity.UserEntity;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

	/**
	 * I Must remove from the toString() methods in the Entities the associationEntity
	 * Otherwise the queries won't work
	 * Meaning If I have UserEntity with Set<RoleEntity> son't add the RoleEntity to the to String method
	 * We will get Stuck Overflow 
	 */ 

	List<RoleEntity> findById(long id);

	@Query("SELECT role FROM RoleEntity role WHERE role.id=:id")
	List<RoleEntity> jpqlFindById(@Param("id") long id);
		
	/**
	 * @Query("SELECT role FROM RoleEntity role WHERE role.id = ?1")
	 * List<RoleEntity> jpqlFindById(long id);
	 */

	@Query(value = "SELECT * FROM ROLES_TB WHERE id=:id", nativeQuery = true)
	List<RoleEntity> nativeFindById(@Param("id") long id);

	/**
	 * 
	 */
	
	List<RoleEntity> findByRole(String role);

	@Query("SELECT r FROM RoleEntity r WHERE r.role=:role")
	List<RoleEntity> jpqlFindByRole(@Param("role") String role);
		
	/**
	 * @Query("SELECT r FROM RoleEntity r WHERE r.role = ?1")
	 * List<RoleEntity> jpqlFindByRole(String role);
	 */

	@Query(value = "SELECT * FROM ROLES_TB WHERE role=:role", nativeQuery = true)
	List<RoleEntity> nativeFindByRole(@Param("role") String role);

	/**
	 * 
	 */
	
	List<RoleEntity> findByPid(long pid);

	@Query("SELECT role FROM RoleEntity role WHERE role.pid=:pid")
	List<RoleEntity> jpqlFindByPid(@Param("pid") long pid);
		
	/**
	 * @Query("SELECT role from RoleEntity role WHERE role.pid = ?1")
	 * List<RoleEntity> jpqlFindByPid(long pid);
	 */

	@Query(value = "SELECT * FROM ROLES_TB WHERE pid=:pid", nativeQuery = true)
	List<RoleEntity> nativeFindByPid(@Param("pid") long pid);

	/**
	 * 
	 */	
	
	// This JPQL Query works as Expected	
	@Query("SELECT u FROM UserEntity u JOIN u.roles AS r WHERE r.role=:role")
	List<UserEntity> jpqlFindUsersWithRoleName(@Param("role") String role);

	/**
	 * we cannot set SELECT *	 * 
	 * NonUniqueDiscoveredSqlAliasException: Encountered a duplicated sql alias [id] during auto-discovery of a native-sql query
	 * The NATIVE Query below does't work here in RoleRepo,
	 * This Native Query as is , works great in UserRepo
	 */

	//	@Query(value = "SELECT * "			
	//			+ "FROM USERS_TB utb "
	//			+ "JOIN ROLES_TB rtb "
	//			+ "ON rtb.user_id=utb.id "
	//			+ "WHERE rtb.role=:role" ,nativeQuery = true)
	//	List<UserEntity> nativeFindUsersWithRoleName(@Param("role") String role);
	
	// *****************************************************************************
	// *****************************************************************************
	// *****************************************************************************	
	
	RoleEntity findByPidAndRole(long pid, String role);
	
	@Query("SELECT r FROM RoleEntity r WHERE r.pid=:pid AND r.role=:role")
	RoleEntity jpqlFindRoleByPidAndRoleName(@Param("pid") long pid, @Param("role") String role);

	@Modifying
	@Query("DELETE FROM RoleEntity re WHERE re.pid=:pid AND re.role=:role")
	void jpqlDeleteUserRoleByPidAndRoleName(@Param("pid") long pid, @Param("role") String role);
	
	@Query("SELECT r FROM RoleEntity r WHERE r.pid=:pid AND r.role=:role")
	RoleEntity jpqlFindRole(@Param("pid") long pid, @Param("role") String role);
	
	@Query("SELECT r FROM RoleEntity r WHERE r.pid=:pid")
	Set<RoleEntity> jpqlFindAllRoles(@Param("pid") long pid);	
}
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------

###### 3_4_Dao

<img src="https://img.shields.io/badge/-3.4. Dao  %20-yellow" height=40px>

### [`UserDao`](#-)

```java
package com.jpa.dao;

import java.util.List;

import com.jpa.dto.UserDto;
import com.jpa.entity.RoleEntity;
import com.jpa.entity.UserEntity;

public interface UserDao {
	
	UserDto createUser(UserEntity userEntity);		
	UserDto getUserById(long id);	
	UserDto getUserByPid(long id);	
	UserDto getUserByName(String name);	
	UserDto getUserByEmail(String email);
	List<UserDto> getAllUsers();
	void removeUserByPid(long pid);
	UserDto addRoleToUser(long userPid, RoleEntity roleEntity);
	UserEntity removeRoleFromUser(long userPid, String role);
}
```

### [`RoleDao`](#-)

```java
package com.jpa.dao;

import java.util.List;
import com.jpa.entity.RoleEntity;
import com.jpa.entity.UserEntity;

public interface RoleDao {

	List<RoleEntity> getRoleById(long id);
	List<UserEntity> getUsersWithRoleName(String role);
	List<RoleEntity> getRoleByPid(long pid);
	List<RoleEntity> getAllRoles();
}
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------

###### 3_5_Dao_Impl

<img src="https://img.shields.io/badge/-3.5. DaoImpl  %20-yellow" height=40px>

### [`UserDaoImpl`](#-)

```java
package com.jpa.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jpa.dto.UserDto;
import com.jpa.entity.RoleEntity;
import com.jpa.entity.UserEntity;
import com.jpa.repository.RoleRepository;
import com.jpa.repository.UserRepository;

@Service
public class UserDaoImpl implements UserDao {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public UserDto createUser(UserEntity userEntity) {		
		UserDto userDto = new UserDto();		
		UserEntity userEntityFromDB = userRepository.save(userEntity);		
		BeanUtils.copyProperties(userEntityFromDB, userDto);		
		return userDto;
	}	
	
	@Override
	public UserDto getUserById(long id) {
		UserDto userDto = new UserDto();
//		UserEntity userEntity =  userRepository.findById(id);
		UserEntity userEntity =  userRepository.jpqlFindById(id);
//		UserEntity userEntity =  userRepository.nativeFindById(id);
		BeanUtils.copyProperties(userEntity, userDto);		
		return userDto;		
	}

	@Override
	public UserDto getUserByPid(long pid) {
		UserDto userDto = new UserDto();		
		UserEntity userEntity = userRepository.findByPid(pid);		
		BeanUtils.copyProperties(userEntity, userDto);		
		return userDto;
	}
	
	/**
	 * Since I use LazyLoading , I must return a DTO and not a UserEntity , otherwise I get the following warn which :
	  
		.w.s.m.s.DefaultHandlerExceptionResolver :
		Resolved [org.springframework.http.converter.HttpMessageNotWritableException: 
			Could not write JSON: 
				failed to lazily initialize a collection of role: com.jpa.entity.UserEntity.roles, 
				could not initialize proxy - no Session; 
				nested exception is com.fasterxml.jackson.databind.JsonMappingException: 
				failed to lazily initialize a collection of role: 
					com.jpa.entity.UserEntity.roles, could not initialize proxy - no Session]
		
		This is thrown by the RestController , and Not by the service layer 
		Thus I define here to return a:
		DTO object
	*/
	@Override	
	public UserDto getUserByName(String name) {				
		UserDto userDto = new UserDto();		
		UserEntity userEntity = userRepository.findByName(name);		
		BeanUtils.copyProperties(userEntity, userDto);		
		return userDto;
	}
	
	@Override
	public UserDto getUserByEmail(String email) {
		UserDto userDto = new UserDto();		
		UserEntity userEntity = userRepository.findByEmail(email);		
		BeanUtils.copyProperties(userEntity, userDto);		
		return userDto;
	}	

	@Override
	public List<UserDto> getAllUsers() {
		
		List<UserEntity> userEntities = userRepository.findAll();
		
		List<UserDto> returnedValue = new ArrayList<>();
		UserDto userDto = new UserDto();
		
		for (UserEntity userEntity : userEntities) {
			BeanUtils.copyProperties(userEntity, userDto);
			returnedValue.add(userDto);
		}
		return returnedValue;
	}
	
	@Override
	public void removeUserByPid(long pid) {
		UserEntity userEntity = userRepository.findByPid(pid);
		userRepository.delete(userEntity);
	}
	
	
	/**
	 * I got this error when tried to add role to user 
	 * 	org.hibernate.LazyInitializationException: 
	 * 		failed to lazily initialize a collection of role: 
	 * 			com.jpa.entity.UserEntity.roles, 
	 * 				could not initialize proxy - no Session
	 * 
	 * Thus , I need to add annotation of @Transactional
	 * From BAELDUNG:
	 * 
	 * The @Transactional annotation configures a transactional proxy around the instance of the related test class.
	 * Moreover, the transaction is associated with the thread executing it. 
	 * Considering the default transaction propagation setting, every Persistence Context created from this method joins to this same transaction.
	 * Consequently, the transaction persistence context is bound to the transaction scope of the test method.
	 */
	
	@Override
	@Transactional
	public UserDto addRoleToUser(long userPid, RoleEntity roleEntity) {
		
		// Best Practice to return UserDto and not UserEntity , 
		// But since I know that we have few rows of RoleEntity thus I return UserEntity
		
		UserEntity userEntity = userRepository.findByPid(userPid);
		roleEntity.setPid(userPid);
		userEntity.addRole(roleEntity); 
		
		UserEntity savedUserEntity = userRepository.save(userEntity);		
		UserDto userDto = new UserDto();			
		BeanUtils.copyProperties(savedUserEntity, userDto);		
		return userDto;		
	}
	
	/**
	 * @Transactional Annotation - 
	 * 			Should be only on 
	 * 			'PUBLIC' methods that returns value to higher level layer
	 */
	/**
	 * I got this error when tried to add role to user 
	 * 	org.hibernate.LazyInitializationException: 
	 * 		failed to lazily initialize a collection of role: 
	 * 			com.jpa.entity.UserEntity.roles, 
	 * 				could not initialize proxy - no Session
	 * 
	 * Thus , I need to add annotation of @Transactional
	 * From BAELDUNG:
	 * 
	 * The @Transactional annotation configures a transactional proxy around the instance of the related test class.
	 * Moreover, the transaction is associated with the thread executing it. 
	 * Considering the default transaction propagation setting, every Persistence Context created from this method joins to this same transaction.
	 * Consequently, the transaction persistence context is bound to the transaction scope of the test method.
	 */
	@Override
	@Transactional
	public UserEntity removeRoleFromUser(long userPid, String role) {

		// Best Practice to return UserDto and not UserEntity , 
		// But since I know that we have few rows of RoleEntity thus I return UserEntity
		/**
		 * In this Implementation
		 * 1. I add the orphanRemoval to UserEntity One2Many 
		 * 2. I Query For RoleEntity (I try with 2 different implementations) 
		 * 3. remove the Entity from the SET<RoleEntity> collection 
		 * 4. Save the the info to UserEntity
		 * 5. I must add @Transactional annotation to the method `removeRoleFromUser()` 
		 */
		
		/**
		 * there are 2 different ways to retrieve roleEntity from DB:
		 * (1) search for roelEntity from getRoles()
		 * (2) Query from UserRepo or RoleRepo 
		 */

		UserEntity userEntity = userRepository.findByPid(userPid);
		
		// (1) search for roelEntity from getRoles()		 
		Set<RoleEntity> roles = userEntity.getRoles();

		RoleEntity roleEntity = null;

		for (RoleEntity r : roles) {
			if (r.getRole().equals(role)) {
				roleEntity = r;
			}
		}

//		(2) Query from UserRepo or RoleRepo
//		RoleEntity roleEntity = userRepository.getRoleByIdAndRole(userEntity.getId(), role);
//		RoleEntity roleEntity = roleRepository.jpqlFindRoleByPidAndRoleName(userPid, role);
//		RoleEntity roleEntity = roleRepository.findByPidAndRole(userPid, role);

		/**
		 * When Using the "roleRepository.delete(roleEntity)" from the roleRepo ,
		 *  no need to use the "orphanRemoval = true"
		 * If we want to return userEntity , we must keep @Transactional at method level, to keep session open
		 */
//		roleRepository.delete(roleEntity);
		
		userEntity.removeRole(roleEntity);		
		UserEntity returnedValue = userRepository.save(userEntity);			
		return returnedValue;		
	}			
}
```

### [`RoleDaoImpl`](#-)

```java
package com.jpa.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpa.entity.RoleEntity;
import com.jpa.entity.UserEntity;
import com.jpa.repository.RoleRepository;
import com.jpa.repository.UserRepository;

@Service
public class RoleDaoImpl implements RoleDao{

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<RoleEntity> getRoleById(long id) {
		return roleRepository.findById(id);
	}

	@Override	
	public List<UserEntity> getUsersWithRoleName(String role) {
//		return roleRepository.jpqlFindUsersWithRoleName(role);
		return userRepository.nativeFindUsersWithRoleName(role);
	}
 
	@Override
	public List<RoleEntity> getRoleByPid(long pid) {
		return roleRepository.findByPid(pid);
	}

	@Override
	public List<RoleEntity> getAllRoles() {
		return roleRepository.findAll();
	}
}
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------

###### 3_6_Dto

<img src="https://img.shields.io/badge/-3.6. Dto  %20-yellow" height=40px>


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------

###### 3_7_FrontEnd

<img src="https://img.shields.io/badge/-3.7. FrontEnd  %20-yellow" height=40px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------

###### 4_test_app

<img src="https://img.shields.io/badge/-4. test app %20-blue" height=40px>

Let's Run the App, adn test our API.
I have 2 Controllers, whith these Controllers I test the API's :
1. UserController API
2. RoleController API

![image](https://user-images.githubusercontent.com/36256986/197419012-317b3223-2db9-48ac-9f66-dc6b7465d760.png)


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------

###### 4_1_User_API_GET

<img src="https://img.shields.io/badge/-4.1. User API GET %20-yellow" height=40px>

Let's test each method in our UserDaoImpl and see how many queries are executed during each request, and see if [`FETCH LAZY`](#-) is created.

### Get Methods:

![image](https://user-images.githubusercontent.com/36256986/197419708-ff8fa985-a081-434b-a2db-d20aba904386.png)

#### [`Following Methods are tested from `UserRepository` `](#-)

```sql
// (1)
UserEntity findById(long id);

// (2)
@Query("SELECT user from UserEntity user WHERE user.id=:id")
UserEntity jpqlFindById(@Param("id") long id);
	
/** Another way to write above query
 * @Query("SELECT user from UserEntity user WHERE user.id = ?1")
 * UserEntity jpqlFindById(long id);
 */

// (3)
@Query(value = "SELECT * FROM USERS_TB WHERE id=:id", nativeQuery = true)
UserEntity nativeFindById(@Param("id") long id);
```

#### [`UserDaoImpl method`](#-)

```java
@Override
public UserDto getUserById(long id) {
	UserDto userDto = new UserDto();
//	UserEntity userEntity =  userRepository.findById(id);
//	UserEntity userEntity =  userRepository.jpqlFindById(id);
	UserEntity userEntity =  userRepository.nativeFindById(id);
	BeanUtils.copyProperties(userEntity, userDto);		
	return userDto;		
}
```

#### [`Hibernate Queries`](#-)

with methods 1/2 , Hibernate makes one SQL query w/o fetching Roles , so lazy Loading works:

```sql
Hibernate: 
    select
        userentity0_.id as id1_1_,
        userentity0_.email as email2_1_,
        userentity0_.name as name3_1_,
        userentity0_.password as password4_1_,
        userentity0_.pid as pid5_1_ 
    from
        users_tb userentity0_ 
    where
        userentity0_.id=?
```

with method 3 , Hibernate makes one SQL query w/o fetching Roles (native SQL query) :

```sql
Hibernate: 
    SELECT
        * 
    FROM
        USERS_TB 
    WHERE
        id=?
```

#### [`Response Data`](#-)

Console log shows we got a DTO object of `UserDTO`

![image](https://user-images.githubusercontent.com/36256986/197420376-e47438c1-7045-49e9-aafd-76b2edb1e06f.png)

* Question :
	What if I will return a `UserEntity` instead of `UserDto` what will happend?

* Answer :
	



[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------

###### 4_2_User_API_POST

<img src="https://img.shields.io/badge/-4.2. User API POST %20-yellow" height=40px>

Let's test each method in our UserDaoImpl and see how many queries are executed during each request, and see if [`FETCH LAZY`](#-) is created.

### POST Methods:

![image](https://user-images.githubusercontent.com/36256986/197419708-ff8fa985-a081-434b-a2db-d20aba904386.png)

Following Methods are tested from `UserRepository`

```sql

```

methods 1/2  makes one SQL query w/o fetching Roles , so lazy Loading works:

```sql

```

methods 3 makes one SQL query w/o fetching Roles (native SQL query) :

```sql

```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------

###### 4_3_User_API_PUT

<img src="https://img.shields.io/badge/-4.3. User API PUT %20-yellow" height=40px>

Let's test each method in our UserDaoImpl and see how many queries are executed during each request, and see if [`FETCH LAZY`](#-) is created.

### POST Methods:

![image](https://user-images.githubusercontent.com/36256986/197419708-ff8fa985-a081-434b-a2db-d20aba904386.png)

Following Methods are tested from `UserRepository`

```sql

```

methods 1/2  makes one SQL query w/o fetching Roles , so lazy Loading works:

```sql

```

methods 3 makes one SQL query w/o fetching Roles (native SQL query) :

```sql

```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------

###### 4_4_User_API_PUT

<img src="https://img.shields.io/badge/-4.4. User API DELETE %20-yellow" height=40px>

Let's test each method in our UserDaoImpl and see how many queries are executed during each request, and see if [`FETCH LAZY`](#-) is created.

### POST Methods:

![image](https://user-images.githubusercontent.com/36256986/197419708-ff8fa985-a081-434b-a2db-d20aba904386.png)

Following Methods are tested from `UserRepository`

```sql

```

methods 1/2  makes one SQL query w/o fetching Roles , so lazy Loading works:

```sql

```

methods 3 makes one SQL query w/o fetching Roles (native SQL query) :

```sql

```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------


###### 4_5_Role_API

<img src="https://img.shields.io/badge/-4.5. Role API  %20-yellow" height=40px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------

###### x

<img src="https://img.shields.io/badge/-x. xxx %20-blue" height=40px>


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--------------------------------------------------------------------------------------------------
