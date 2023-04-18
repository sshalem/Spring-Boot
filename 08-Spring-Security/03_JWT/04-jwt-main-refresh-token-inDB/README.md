<img src="https://img.shields.io/badge/04_jwt_main_refresh_token_inDB %20-blue" height=70px>

###### \_

|     | Subject                                                                                |
| :-: | :------------------------------------------------------------------------------------- |
|     | [links](#Links)                                                                        |
|     | [Refresh JWT Token](#Refresh_JWT_Token)                                                |
|     | [Refresh JWT Token from DB](#Refresh_JWT_Token_from_DB)                                |
|  4  | [JWT with Authorities impl](#4_JWT_with_Authorities_impl)                              
|     | 4.1. [POM](#4_1_POM)                                                                            |
|     | 4.2. [Package Layout](#4_2_Package_Layout)                                             |
|     | 4.3. [application.properties](#4_3_application_properties)  				|
|     | 4.4. [entity](#4_4_entity)                       					|
|     | 4.5. [repository](#4_5_repository)          						|
|     | 4.6. [dao](#4_6_dao)  									|
|     | 4.7. [service](#4_7_service)                                                                     |
|     | 4.8. [model](#4_8_model)                                                                       |
|     | 4.9. [exceptions](#4_9_exceptions)                                                                  |
|     | 4.10. [config](#4_10_config)                                                                     |
|     | 4.11. [jwt](#4_11_jwt)                                                                        |
|     | 4.12. [controller](#4_12_controller)                                                                 |
|     | 4.13. [Test App](#4_13_Test_App)                                                                   |
|     | 4.14. [Test App with Front End](#4_14_Test_App_with_Front_End)   			|




###### Links

<img src="https://img.shields.io/badge/- links %20-blue" height=40px>

* [Bezkoder - Refresh JWT token](https://www.bezkoder.com/spring-boot-refresh-token-jwt/)
* [Bezkoder - Refresh JWT token with HttpOnly Cookie](https://www.bezkoder.com/spring-security-refresh-token/)
* [Javainuse - refresh JWT token](https://www.javainuse.com/webseries/spring-security-jwt/chap7)

https://www.youtube.com/watch?v=pnLcbNUOZvU&ab_channel=TheDevWorld-bySergioLema

GREAT LINK (Well Explained) https://www.toptal.com/spring/spring-security-tutorial

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------

###### Refresh_JWT_Token

<img src="https://img.shields.io/badge/- Refresh_JWT_Token %20-blue" height=40px>

### [Questions](#-)

What to do if JWT token is expired? </br>
How To handle the Token , API ? </br>

### [Flow diagram](#-)

The diagram shows flow of how we implement Authentication process with Access Token and Refresh Token.

<p align="center">
	<img src="https://user-images.githubusercontent.com/36256986/212567883-87f2c508-f899-4903-87e1-47ef5606faff.png" />
</p>



article from [3 Scenarios Where You Can Store JWT Token in Your DB](https://betterprogramming.pub/should-we-store-tokens-in-db-af30212b7f22)

Token-based authentication (most often JWT based) is referred to as stateless authentication — because the authentication server doesn’t need to maintain any state, the token itself contains all the necessary information to verify a token bearer’s authentication. </br>

The server can seamlessly check whether the JWT contains the necessary information about the user’s identity and authorization to perform an action without querying the database. Now the question arises, if such is the scenario then do we need to save the JWT token in the database? If yes, then when and why? </br>

I will try to cover three scenarios where the necessity to save the tokens in DB arises. </br>
Before we dive deep into the topic let me give you a tiny introduction on :
* [access tokens ](#-)
* [refresh tokens](#-)

### [access tokens ](#-)

When a user logs in, the authorization server issues an access token (generally JWT), then the client can use this token to make secure API calls.</br>
Access tokens have a very short lifespan (generally not more than 30mins). </br>
* [`access_token`] - 5 minute access token as JWT (Is ok) 


Once the `access token` expires the client application can :
1. prompt the user to `re-login` (which is certainly not a good user experience) 
2. or the client can use a `refresh token` which is issued by the authorization/authentication server to generate a new `access token`.

### [Refresh tokens](#-)

`Refresh tokens` generally have a much higher life span than the `access tokens`. </br>
`Refresh tokens` may or may not be JWT. </br>
`Refresh tokens` can be a simple encoded string or a UUID. </br>
`Refresh tokens` are also Bearer tokens, hence ​malicious users can theoretically steal the `refresh token` and use it indefinitely to access protected resources from the server. 

* [`refresh_token`] - 7 day refresh token for one-time usage

### [Question](#-) </br>
Then how do we secure our application from malicious users accessing protected resources?

### [Answer](#-) </br>
The most straightforward answer to this question would be saving `refresh tokens` in the `database` and revoking access of all users by deleting all the `refresh tokens` when any such malicious behavior is reported. </br>

### [Question](#-) </br>
But what if it’s not reported, do we let the malicious user access protected resources indefinitely? </br>
Do we keep on saving `refresh tokens` in our database?

### [Answer](#-) </br>
The answer to this question is [`refresh token rotation`](#-). </br>
`Refresh token` reuse detection and deleting all old `refresh tokens` when a new one is generated. </br>

### [`Refresh Token Rotation`](#-)

Let's explain what [`refresh token rotation`](#-) :
* when a new `access token` is generated (at the time of sign in/signup or using a `refresh token`) ,  a new `refresh token` should also be generated. 
* (this is called refresh token rotation), and all the previous refresh tokens must be deleted.

In this way — even if a malicious user steals the `refresh token`, when the legitimate user tries to log in to the application, a new `access token` and a new `refresh token` will be generated, and all other `refresh tokens` will be deleted. </br> 
if the malicious user tries to use the old refresh token , the refresh token reuse detection would already detect the reuse or the refresh token wouldn’t exist in DB. This way we can prevent a malicious attack.


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)




---------------------------------------------------------------------------------------


###### Refresh_JWT_Token_from_DB

<img src="https://img.shields.io/badge/- Refresh_JWT_Token_from_DB %20-blue" height=40px>

In this implementation I store the refresh Token In DB. </br>
These are the changes I made :
1. create `RefreshTokenEntity` 
2. associate `RefreshTokenEntity` with `OneToOne` annotation with `UserEntity` (Didnt add @OneToOne on UserEntity Side)
3. create `RefreshTokenRepository`
4. create `RefreshTokenDao` and `RefreshTokenDaoImpl` 
5. Implement the following methods:
	* `generateRefreshToken` - I implement a token w/o JWT
	* `validateRefreshToken` 
	* `getUserByRefreshToken`
6. I also modified the code for deleteing a User , since it is associated with `RefreshTokenEntity` code had to be modified other wise I get an error
7. delete refresh token frokm DB once user is `logout`


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)



---------------------------------------------------------------------------------------------

###### 4_JWT_with_Authorities_impl

<img src="https://img.shields.io/badge/- 3. JWT_with_Authorities_impl %20-blue" height=40px>

In this project, I took the previous project and add a full implemation of JWT with authorization </br>
Meaning , the API will be accessed only to authorized user.

Let's see the implemetations in the next code.

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### 4_1_POM

<img src="https://img.shields.io/badge/- 4.1.POM %20- green" height=30px>

<p align="center">
  <img src="https://user-images.githubusercontent.com/36256986/212492696-987d17cb-f290-49fe-a775-95a56d8a575a.png"/>
</p>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### 4_2_Package_Layout

<img src="https://img.shields.io/badge/- 4.2. Package_Layout %20- green" height=30px>

I added new classes in this implementations . see the differences in section 3.2. 

<p align="center">
  <img src="https://user-images.githubusercontent.com/36256986/213997991-aacd59dc-0e04-44b6-909f-3af4d5c81b6f.png"/>
</p>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### 4_3_application_properties

<img src="https://img.shields.io/badge/- 4.3. application_properties %20- green" height=30px>

In this implementation , I made 4 files of properties :
 
1. `application.properties` - main
2. `application-h2.properties` - for H2 config
3. `application-mysql.properties` - for MySql
4. `application-postgres.properties` - for Postgres

![image](https://user-images.githubusercontent.com/36256986/212297438-7af31bd5-f9e2-42c7-835a-79199e6cfd10.png)

### [application.properties](#-)

```sql
server.port=8080
#spring.profiles.active=h2
spring.profiles.active=postgres

jwt.signing.key=weferuogp8734thq3ncf3948u9\
				p3cnj4fx0-3m8uf3t[83ucnygh\
				fenhadf8ayh9ep9r8fghbsakfo\
				jinfcoer83ucnyghfenhadf8ay\
				h9ep9r8fghbsakfojinfcoer48\
				u9p3cnj4fx0-3m8uf3t[83ucny\
				ghfenhadf8ayh9ep9r8vfergec

#logging.level.org.springframework.web=trace
#logging.level.org.springframework.core=trace
#logging.level.org.springframework.security.web=trace
#logging.level.org.springframework.security.web.access=trace
 
#logging.level.org.springframework.security=trace
#logging.level.org.springframework.security.authentication=trace
#logging.level.org.springframework.security.authentication.dao=trace


#logging.level.org.apache.coyote.http11=trace
#logging.level.javax.servlet=trace

server.error.include-binding-errors=always
server.error.include-exception=true
server.error.include-message=always
server.error.include-stacktrace=never
server.error.whitelabel.enabled=true

# ===========================================
# 	dev-tools (Need to add dependency)
# ===========================================
spring.devtools.restart.enabled=true
```

### [application-h2.properties](#-)

```sql
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

# Enabling H2 Console
spring.h2.console.enabled=true

# Custom H2 Console URL from /h2-console to /h2
spring.h2.console.path=/h2

# to initialize using `data.sql` file
spring.sql.init.platform=H2
spring.sql.init.mode=always
spring.jpa.defer-datasource-initialization=true


#Spring will create a schema
spring.jpa.hibernate.ddl-auto=create
spring.jpa.generate-ddl=true
spring.jpa.show-sql=true
#spring.jpa.properties.hibernate.generate_statistics=true
#spring.jpa.properties.hibernate.format_sql=true

spring.jpa.open-in-view=false
```

### [application-mysql.properties](#-)

```sql
# ===============================
# 		DATA SOURCE
# =============================== 
spring.datasource.url=jdbc:mysql://localhost:3306/jwt?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=root

# ===============================
# 	 	JPA / HIBERNATE
# ===============================
 
#Spring will create a schema
spring.jpa.hibernate.ddl-auto=create
spring.jpa.generate-ddl=true
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.generate_statistics=true
spring.jpa.open-in-view=false
spring.jpa.properties.hibernate.format_sql=true

# When using java version JDK11 use with mysql dialect
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL8Dialect

# ==========================================
#  we must add this config as well for 
#  schema.sql and data.sql could work
# ==========================================

# this is in order to use data.sql for mysql connection 
# by setting the platform as shMysql
# then modifying `data.sql` to `data-shMysql.sql`  
spring.sql.init.platform=Mysql
spring.sql.init.mode=always
spring.jpa.defer-datasource-initialization=true
```

### [application-postgres.properties](#-)

```sql
# ===============================
# 		DATA SOURCE
# =============================== 
spring.datasource.url=jdbc:postgresql://localhost/jwt
spring.datasource.username=postgres
spring.datasource.password=root

###############################
# Spring will create a schema
###############################
spring.jpa.hibernate.ddl-auto=create
spring.jpa.generate-ddl=true
spring.jpa.show-sql=true
#spring.jpa.properties.hibernate.generate_statistics=true
#spring.jpa.properties.hibernate.format_sql=true

spring.jpa.open-in-view=false

# ==========================================
#  we must add this config as well for 
#  schema.sql and data.sql could work
# ==========================================

# this is in order to use data.sql for postgresql connection 
# by setting the platform as shPostgres
# then modifying `data.sql` to `data-shPostgres.sql`  
spring.sql.init.platform=PostGreSql
spring.sql.init.mode=always
spring.jpa.defer-datasource-initialization=true

# Allows Hibernate to generate SQL optimized for a particular DBMS
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### 4_4_entity

<img src="https://img.shields.io/badge/- 3.4. entity %20- green" height=30px>

In Have 2 Entities which are mapped with `@Many2Many` (unlike I did it in project 01, better approach to make it as Many@Many mapping):
1. UserEntity
2. RoleEntity

### [UserEntity](#-)

```java
@Entity
@Table(name = "USERS_TB")
public class UserEntity {

	/** Since I use data-xxxx.sql files for DB's 
	 * Thus this is the preffered generator to use with each on:
	 * h2 DB : 
	 * 			@GeneratedValue(strategy = GenerationType.IDENTITY)
	 * 
	 * MySql DB :
	 * 			@GeneratedValue(strategy = GenerationType.IDENTITY)
	 * 
	 * PostGresql DB:
	 * 			
	 *
	 * With PostGresql DB :     
	 * 			@SequenceGenerator(name = "studentseq", initialValue = 20001, allocationSize = 50)
	 *			@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "studentseq")
	 *
	 *if we look in the data.postgres.sql we can see that the id's satrt from 1 for each table
	 * With postgresql , even though i have user id's from 1-10 , when I attempt to create a new record w/o specifiyng the ID
	 * It pulls the value from sequence (1) thus i can get unique violation
	 */
	@Id
	@SequenceGenerator(name = "userseq", initialValue = 20001, allocationSize = 50)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userseq")
	@Column(name = "user_id")	
	private long id;
	private String name;
	private String email;
	private String password;

	@ManyToMany(fetch = FetchType.EAGER, 
			cascade = { 
					CascadeType.PERSIST, 
					CascadeType.MERGE })
	@JoinTable(name = "user_role", 
			joinColumns = { @JoinColumn(name = "user_id") }, 
			inverseJoinColumns = { @JoinColumn(name = "role_id") })
	@JsonIgnore
	private Set<RoleEntity> roles = new HashSet<>();
	
	public UserEntity() {
		super();
	}

	/**
	 * G/S/Hashcode/Equals
	 */

	/**
	 * Helper Methods for Adding/Removing Course
	 */

	public void addRole(RoleEntity roleEntity) {
		this.roles.add(roleEntity);
		roleEntity.getUsers().add(this);
	}

	public void removeRole(RoleEntity role) {
		this.roles.remove(role);
		role.getUsers().remove(this);
	}
```

### [RoleEntity](#-)

```java
@Entity
@Table(name = "ROLES_TB")
public class RoleEntity {

	
	/** Since I use data-xxxx.sql files for DB's 
	 * Thus this is the preffered generator to use with each on:
	 * h2 DB : 
	 * 			@GeneratedValue(strategy = GenerationType.IDENTITY)
	 * 
	 * MySql DB :
	 * 			@GeneratedValue(strategy = GenerationType.IDENTITY)
	 * 
	 * PostGresql DB:
	 * 			
	 *
	 * With PostGresql DB :     
	 * 				@SequenceGenerator(name = "studentseq", initialValue = 20001, allocationSize = 50)
	 *				@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "studentseq")
	 *
	 *if we look in the data.postgres.sql we can see that the id's satrt from 1 for each table
	 * With postgresql , even though i have user id's from 1-10 , when I attempt to create a new record w/o specifiyng the ID
	 * It pulls the value from sequence (1) thus i can get unique violation
	 */
	@Id
	@SequenceGenerator(name = "roleseq", initialValue = 20001, allocationSize = 50)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roleseq")
	@Column(name = "role_id")
	private long id;
	private String role;

	@ManyToMany(mappedBy = "roles", 
			fetch = FetchType.EAGER, 
			cascade = {
					//	CascadeType.PERSIST,
					//	CascadeType.MERGE
	})
	@JsonIgnore
	private Set<UserEntity> users = new HashSet<>();
	
	/**
	 * Don't include the mapped variables to Hashcode & equeals 
	 * We also dont add them to the toString method
	 */
	Ctor/G/S/Hash/Equals
```

### [RefreshTokenEntity](#-)

In this class I have a `OneToOne` mapping with UserEntity.

```java
@Entity
@Table(name = "REFRESH_TOKENS_TB")
public class RefreshTokenEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false, unique = true)
	private String token;

	@Column(nullable = false)
	private Instant expiryDate;

	@OneToOne(fetch = FetchType.EAGER)			
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	@JsonIgnore
	private UserEntity userEntity;

	Ctor/G/S/Hash/Equals	
}
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### 4_5_repository

<img src="https://img.shields.io/badge/- 4.5. repository %20- green" height=30px>

### [UserRepository](#-)

```java
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	UserEntity findByEmail(String email);

	List<UserEntity> findByName(String name);

	@Query("SELECT ue FROM UserEntity ue JOIN ue.roles AS rl WHERE rl.role=:role")
	List<UserEntity> jpqlFindUsersWithRole(@Param("role") String role);

	@Query("SELECT ur FROM UserEntity user JOIN user.roles AS ur WHERE user.email=? 1")
	List<RoleEntity> jpqlFindAllRolesOfUserByEmail(String email);
}
```

### [RoleRepository](#-)

```java
@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

	RoleEntity findByRole(String role);

	@Query("SELECT re FROM RoleEntity re JOIN re.users AS reuse WHERE reuse.email = ?1")
	List<RoleEntity> jpqlFindRolesOfUserByEmail(String email);

	@Query("SELECT reuse FROM RoleEntity re JOIN re.users AS reuse WHERE re.role=:role")
	List<UserEntity> jpqlFindUsersWithRole(@Param("role") String role);

}
```

### [RefreshTokenRepository](#-)

```java
@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {

	Optional<RefreshTokenEntity> findByToken(String token);

	@Query("SELECT rteuse FROM RefreshTokenEntity rte JOIN rte.userEntity AS rteuse WHERE rte.token=:token")
	UserEntity findUserByRefreshToken(@Param("token") String token);
	
	@Query("SELECT rte FROM RefreshTokenEntity rte JOIN rte.userEntity AS rteuse WHERE rteuse.id=:userId")
	RefreshTokenEntity findRefreshTokenEntityByUserId(@Param("userId") long userId);
}
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### 4_6_dao

<img src="https://img.shields.io/badge/- 4.6. dao %20- green" height=30px>

### [UserDao](#-)

```java
public interface UserDao {

	/**************
	 * Create
	 *************/
	UserRegisterResponse createUser(UserRegisterRequest userRegisterRequest);

	/****************
	 * Read
	 ***************/
	List<UserEntity> getUserByName(String name);
	UserEntity getUserByEmail(String email);
	List<UserEntity> getUsersWithRole(String role);
	List<RoleEntity> getAllRolesOfUserByEmail(String email);
	List<UserEntity> getAllUsers();
	String getUserName(String email);

	/****************
	 * Update
	 ***************/
	UserEntity updateUserDetails(String email, UserEntity userEntity);

	/****************
	 * Delete
	 ***************/
	void deleteUserByEmail(String email);
	void removeAllUsersFromRole(String role);
	void deleteAllUsers();
}
```

### [UserDaoImpl explained](#-)

In this implementation , I modified the code in method of `deleteUserByEmail`. </br>
since I have a `OneToOne` mapping , I need first to delete the `refreshTokenEntity` which deatach from DB , the delete the UserEntity :

```java
	@Override
	public void deleteUserByEmail(String email) {
		UserEntity _userEntity = this.getUserByEmail(email);
		RefreshTokenEntity refreshTokenEntity = refreshTokenRepository.findRefreshTokenEntityByUserId(_userEntity.getId());
		if(refreshTokenEntity != null)
			refreshTokenRepository.delete(refreshTokenEntity);
		userRepository.delete(_userEntity);
	}
```

### [UserDaoImpl](#-)

```java
@Service
public class UserDaoImpl implements UserDao {

	private final static Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	/***********************
	 * CREATE
	 ***********************/
	@Override
	public UserRegisterResponse createUser(UserRegisterRequest userRegisterRequest) {

		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(userRegisterRequest, userEntity);

		if (userRepository.findByEmail(userEntity.getEmail()) != null)
			throw new EmailAlreadyExistException("Email Already Exist");

		userEntity.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));

		UserEntity createdUser = userRepository.save(userEntity);

		UserRegisterResponse userRegisterResponse = new UserRegisterResponse();
		BeanUtils.copyProperties(createdUser, userRegisterResponse);

		userRegisterResponse.setId(UUID.randomUUID());

		return userRegisterResponse;
	}

	/****************
	 * Read
	 ***************/

	@Override
	public String getUserName(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);
		return userEntity.getName();
	}

	@Override
	public List<UserEntity> getUserByName(String name) {
		List<UserEntity> _users = userRepository.findByName(name);
		
		if (_users.isEmpty())
			throw new ResourceNotFoundException("User with name : " + name + " , Not Exist");
		return _users;
	}

	@Override
	public UserEntity getUserByEmail(String email) {
		LOGGER.info("invoke getUserByEmail()");

		UserEntity _userEntity = userRepository.findByEmail(email);

		if (_userEntity == null)
			throw new ResourceNotFoundException("User with Email : " + email + " , Not Exist");
		return _userEntity;
	}

	@Override
	public List<UserEntity> getUsersWithRole(String role) {
		return userRepository.jpqlFindUsersWithRole(role);
	}

	@Override
	public List<RoleEntity> getAllRolesOfUserByEmail(String email) {
		return userRepository.jpqlFindAllRolesOfUserByEmail(email);
	}

	@Override
	public List<UserEntity> getAllUsers() {
		return userRepository.findAll();
	}

	/****************
	 * Update
	 ***************/

	@Override
	public UserEntity updateUserDetails(String email, UserEntity userEntity) {
		UserEntity _userEntity = this.getUserByEmail(email);

		_userEntity.setName(userEntity.getName());
		_userEntity.setEmail(userEntity.getEmail());
		_userEntity.setPassword(userEntity.getPassword());

		return userRepository.save(_userEntity);
	}

	/****************
	 * Delete
	 ***************/

	@Override
	public void deleteUserByEmail(String email) {
		UserEntity _userEntity = this.getUserByEmail(email);
		RefreshTokenEntity refreshTokenEntity = refreshTokenRepository.findRefreshTokenEntityByUserId(_userEntity.getId());
		if(refreshTokenEntity != null)
			refreshTokenRepository.delete(refreshTokenEntity);
		userRepository.delete(_userEntity);
	}

	@Override
	public void removeAllUsersFromRole(String role) {

		List<UserEntity> _users = userRepository.jpqlFindUsersWithRole(role);
		RoleEntity roleEntity = roleRepository.findByRole(role);

		for (UserEntity _userEntity : _users) {
			_userEntity.removeRole(roleEntity);
			userRepository.save(_userEntity);
		}
	}

	@Override
	public void deleteAllUsers() {
		userRepository.deleteAll();
	}
}
```

### [RoleDao](#-)

```java
public interface RoleDao {

	/***********
	 * Create
	 ***********/
	RoleEntity createRole(RoleEntity roleEntity);

	/***********
	 * Read
	 ***********/
	RoleEntity getRoleByRolename(String role);
	List<RoleEntity> gettAllRoles();
	List<UserEntity> getUsersWhoHasRole(String role);

	/************
	 * Update
	 ************/
	RoleEntity updateRoleDetails(RoleEntity roleEntity);
	UserEntity addRoleToUser(String email, String role);
	
	/***********
	 * Delete
	 ***********/
	void deleteRoleByRoleName(String role);
	UserEntity removeRoleFromUserByRoleName(String email, String role);
	Set<RoleEntity> removeAllRolesFromUser(String email);
	void deleteAllRoles();
}
```

### [RoleDaoImpl](#-)

```java
@Service
public class RoleDaoImpl implements RoleDao {

	private final static Logger LOGGER = LoggerFactory.getLogger(RoleDaoImpl.class);

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	/***********************
	 * CREATE
	 ***********************/
	@Override
	public RoleEntity createRole(RoleEntity roleEntity) {
		LOGGER.info("invoke createRole() ");

		RoleEntity _roleEntityByRole = roleRepository.findByRole(roleEntity.getRole());

		if (_roleEntityByRole != null)
			throw new DuplicateKeyException("Role with name : " + roleEntity.getRole() + " , already Exist");
		return roleRepository.save(roleEntity);
	}

	/****************
	 * GET
	 ***************/

	@Override
	public RoleEntity getRoleByRolename(String role) {
		RoleEntity _roleEntity = roleRepository.findByRole(role);

		if (_roleEntity == null)
			throw new ResourceNotFoundException("Role : " + role + " , NOT Exist");
		return _roleEntity;
	}

	@Override
	public List<RoleEntity> gettAllRoles() {
		return roleRepository.findAll();
	}

	@Override
	public List<UserEntity> getUsersWhoHasRole(String role) {
		List<UserEntity> _users = roleRepository.jpqlFindUsersWithRole(role);
		return _users;
	}

	/****************
	 * Update
	 ***************/

	@Override
	public RoleEntity updateRoleDetails(RoleEntity roleEntity) {
		RoleEntity _roleEntity = roleRepository.findByRole(roleEntity.getRole());

		if (_roleEntity == null)
			throw new ResourceNotFoundException(" Role " + roleEntity.getRole() + " Not Found");

		_roleEntity.setRole(roleEntity.getRole());

		return roleRepository.save(_roleEntity);
	}

	@Override
	public UserEntity addRoleToUser(String email, String role) {
		UserEntity _userEntity = userRepository.findByEmail(email);

		if (_userEntity == null)
			throw new NullPointerException("User with Email : " + email + " , Not Exist");

		RoleEntity _roleEntity = roleRepository.findByRole(role);
		
		if(_roleEntity == null)	
			throw new ResourceNotFoundException("Role : " + role + " Not Exist");

		boolean contains = _userEntity.getRoles().contains(_roleEntity);

		if (contains)
			throw new DuplicateKeyException("User already has role: " + role);

		_userEntity.addRole(_roleEntity);
		UserEntity returnedValue = userRepository.save(_userEntity);
		return returnedValue;
	}

	/****************
	 * Delete
	 ***************/
	@Override
	public void deleteRoleByRoleName(String role) {

		List<UserEntity> _users = userRepository.findAll();
		RoleEntity _roleEntity = roleRepository.findByRole(role);

		for (UserEntity userEntity : _users) {
			boolean contains = userEntity.getRoles().contains(_roleEntity);
			if (contains) {
				userEntity.removeRole(_roleEntity);
				userRepository.save(userEntity);
			}
		}
		roleRepository.delete(_roleEntity);
	}

	@Override
	public UserEntity removeRoleFromUserByRoleName(String email, String role) {
		UserEntity _userEntity = userRepository.findByEmail(email);

		if (_userEntity == null)
			throw new NullPointerException("User with Email: " + email + " , Not Exist");

		RoleEntity _roleEntity = roleRepository.findByRole(role);
		_userEntity.removeRole(_roleEntity);
		return userRepository.save(_userEntity);
	}

	@Override
	public Set<RoleEntity> removeAllRolesFromUser(String email) {
		List<RoleEntity> _roles = roleRepository.jpqlFindRolesOfUserByEmail(email);

		UserEntity _userEntity = userRepository.findByEmail(email);

		for (RoleEntity roleEntity : _roles) {
			_userEntity.removeRole(roleEntity);
			userRepository.save(_userEntity);
		}
		return _userEntity.getRoles();
	}

	@Override
	public void deleteAllRoles() {
		List<RoleEntity> _roles = roleRepository.findAll();

		List<UserEntity> _users = userRepository.findAll();

		for (RoleEntity roleEntity : _roles) {
			for (UserEntity userEntity : _users) {
				userEntity.removeRole(roleEntity);
				userRepository.save(userEntity);
			}
		}
		roleRepository.deleteAll();
	}
}
```

### [RefreshTokenDao](#-)

```java
public interface RefreshTokenDao {
	String generateRefreshToken(String email, String invokedMethod, String refreshToken);
	RefreshTokenEntity validateRefreshToken(String refreshToken);
	UserEntity getUserByRefreshToken(String refreshToken);
	void deleteRefreshToken(String refreshToken);
}
```

### [RefreshTokenDaoImpl](#-)

In this class I create the Refresh Token By myself , w/o using the JwtUtil class.
`token` - Created by using a `UUID`
`setExpiryDate` - By using the `Instant` class to create a Date

The saving the refreshToken to DB.


```java
@Service
public class RefreshTokenDaoImpl implements RefreshTokenDao {

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public String generateRefreshToken(String email, String invokedMethod, String refreshToken) {

		RefreshTokenEntity refreshTokenEntity = null;
		
		if(invokedMethod.equals(SecurityConstants.INVOKED_LOGIN_URL)) {			
			UserEntity userEntity = userRepository.findByEmail(email);			
			refreshTokenEntity = new RefreshTokenEntity();			
			refreshTokenEntity.setUserEntity(userEntity);			
		}
		else if (invokedMethod.equals(SecurityConstants.INVOKED_REFRESH_URL)) {
			refreshTokenEntity = refreshTokenRepository.findByToken(refreshToken).get();
		}
		
		refreshTokenEntity.setExpiryDate(Instant.now().plusMillis(SecurityConstants.REFRESH_TOKEN_EXPIRATION_TIME_ms));
		refreshTokenEntity.setToken(UUID.randomUUID().toString());
		refreshTokenEntity = refreshTokenRepository.save(refreshTokenEntity);
		
		return refreshTokenEntity.getToken();
	}

	@Override
	public RefreshTokenEntity validateRefreshToken(String refreshToken) {

		RefreshTokenEntity refreshTokenEntity = refreshTokenRepository.findByToken(refreshToken).get();

		if (refreshTokenEntity.getExpiryDate().compareTo(Instant.now()) < 0) {
			refreshTokenRepository.delete(refreshTokenEntity);
			throw new TokenRefreshException("Refresh token was expired. Please make a new signin request");
		}
		return refreshTokenEntity;
	}

	@Override
	public UserEntity getUserByRefreshToken(String refreshToken) {

		UserEntity userEntity = refreshTokenRepository.findUserByRefreshToken(refreshToken);
		
		if(userEntity == null)
			throw new UsernameNotFoundException("Could Not extract username from Refresh Token");

		return userEntity;
	}
	
	@Override
	public void deleteRefreshToken(String refreshToken) {		
		refreshTokenRepository.delete(refreshTokenRepository.findByToken(refreshToken).get());
	}
}
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### 4_7_service

<img src="https://img.shields.io/badge/- 4.7. service %20- green" height=30px>

In service package I have 2 classes:

![image](https://user-images.githubusercontent.com/36256986/212492894-d1fa2280-d408-4555-9e60-5e39db096532.png)

### [JwtUserDetailsService](#-) 

The UserDetailsService is loaded By the DAO provider.

```java
@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		UserEntity userEntity = userRepo.findByEmail(email);

		if (userEntity == null)
			throw new UsernameNotFoundException("user Email  :" + email + " not Exist");

		return new JwtUserDetails(userEntity);
	}
}
```

### [JwtUserDetails](#-) 

In this class I implement the `UserDetails`. </br>
`UserDetails` interface has 7 methods which I implement. 
1. `getAuthorities()`
2. `getPassword()`
3. `getUsername()`
4. `isAccountNonExpired()`
5. `isAccountNonLocked()`
6. `isCredentialsNonExpired()`
7. `isEnabled()`

The last 4 methods, which return a boolean , I initialy set them as true.
I have the UserEntity as avariable, becuase I need to use the username(Which Is the eamil) and the password , from the `UserEntity` class. </br>


```java
public class JwtUserDetails implements UserDetails {

	private static final long serialVersionUID = -1689702881017645097L;
	private UserEntity userEntity;

	public JwtUserDetails(UserEntity userEntity) {
		super();
		this.userEntity = userEntity;
	}

	public UserEntity getUser() {
		return userEntity;
	}

	public void setUser(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		Set<SimpleGrantedAuthority> roles = new HashSet<>();

		Set<RoleEntity> rolesFromDB = userEntity.getRoles();

		rolesFromDB.forEach(role -> {
			SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + role.getRole());
			roles.add(grantedAuthority);
		});

		return roles;
	}

	@Override
	public String getPassword() {
		return this.userEntity.getPassword();
	}

	@Override
	public String getUsername() {
		return this.userEntity.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
```


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### 4_8_model

<img src="https://img.shields.io/badge/- 4.8. model %20- green" height=30px>

I modified the `JwtTokenResponse` class , to have also a `refreshToken`. </br>
The rest same as Same as section [2.8](#2_8_model)

```java
public class JwtTokenResponse implements Serializable {

	private static final long serialVersionUID = -5884238416098732332L;

	private String name;
	private String accessToken;
	private String refreshToken;

	public JwtTokenResponse() {
		super();
	}

	public JwtTokenResponse(String name, String accessToken, String refreshToken) {
		super();
		this.name = name;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	/*
	 * Once I get the JwtBuilder instance I can access the methods
	 */
	public static JwtBuilder name(String name) {
		return new JwtBuilder(name);
	}

	public static class JwtBuilder {

		private final JwtTokenResponse jwtTokenResponse = new JwtTokenResponse();

		public JwtBuilder(String name) {
			jwtTokenResponse.setName(name);
		}

		public JwtBuilder accessToken(String token) {
			jwtTokenResponse.setAccessToken(token);
			return this;
		}

		public JwtBuilder refreshToken(String token) {
			jwtTokenResponse.setRefreshToken(token);
			return this;
		}

		public JwtTokenResponse build() {
			return jwtTokenResponse;
		}
	}
}
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### 4_9_exceptions

<img src="https://img.shields.io/badge/- 4.9. exceptions %20- green" height=30px>

![image](https://user-images.githubusercontent.com/36256986/212500440-33302b6c-0e0a-421e-80bd-dd985af5312c.png)

```java
@RestControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(EmailAlreadyExistException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorMessage emailAlreadyExistException(EmailAlreadyExistException ex, WebRequest request) {

		ErrorMessage message = new ErrorMessage();

		message.setTimestamp(new Date());
		message.setStatusCode(HttpStatus.BAD_REQUEST.value());
		message.setError(HttpStatus.valueOf(HttpStatus.BAD_REQUEST.value()).getReasonPhrase());
		message.setException(EmailAlreadyExistException.class.getName());
		message.setMessage(ex.getMessage());
		message.setUriDescription(request.getDescription(false));

		return message;
	}

	@ExceptionHandler({ Exception.class })
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorMessage globalExceptionHandler(Exception ex, WebRequest request) {

		ErrorMessage message = new ErrorMessage();

		message.setTimestamp(new Date());
		message.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		message.setError(HttpStatus.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()).getReasonPhrase());
		message.setException(ex.getClass().getCanonicalName());
		message.setMessage(ex.getMessage());
		message.setUriDescription(request.getDescription(false));

		return message;
	}
}
```

```java
public class ErrorMessage {

	private Date timestamp;
	private int statusCode;
	private String error;
	private String exception;
	private String message;
	private String uriDescription;

	Ctor/G/S
```

```sql
server.error.include-binding-errors=always
server.error.include-exception=true
server.error.include-message=always
server.error.include-stacktrace=never
server.error.whitelabel.enabled=true
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### 4_10_config

<img src="https://img.shields.io/badge/- 4.10. config %20- green" height=30px>

Here, I define authorization for users ,roles, books . Each API has it's on `SecurityFilterChain` , with different Authorities.

```java
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;
	
	@Bean
	public PasswordEncoder passEncode() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passEncode());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()		
			.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
		.and()
			.authorizeRequests()
			.antMatchers("/*", "/css/*", "/js/*").permitAll()
			.antMatchers("/h2/**/**").permitAll() // Should not be in Production!
			.antMatchers("/auth/**").permitAll()
			.antMatchers("/api/users/**").hasAnyRole("SUPER-ADMIN", "ADMIN")
			.antMatchers("/api/roles/**").hasAnyRole("SUPER-ADMIN", "ADMIN")
			.antMatchers("/api/book/**").authenticated()
			.anyRequest()
			.authenticated()
		.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        // fix H2 database console: 
		// Refused to display ' in a frame because it set 'X-Frame-Options' to 'deny'
		http
			.headers()
			.frameOptions()
			.sameOrigin() // H2 Console Needs this setting
			.cacheControl(); // disable caching
	}
}
```

```java
public class SecurityConstants {

	// 3_600_000 = 60min
	// 1_800_000 = 30min
	// 60_000 = 1min
	// 5_000 = 5sec
	// 1_000 = 1sec
	public static final long JWT_EXPIRATION_TIME_ms = 5_000; 
	
	public static final long REFRESH_TOKEN_EXPIRATION_TIME_ms = 3_600_000;
	
	public static final String BEARER_PREFIX = "Bearer "; // Don't forget to add white space after Bearer prefix
	public static final String REFRESH_TOKEN_PREFIX = "Refresh_token "; // Don't forget to add white space after 'Refresh_token' prefix
	public static final String AUTHORIZATION = "Authorization";
	public static final String REGISTER_URL = "/register";
}
```


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)


###### 4_11_jwt

<img src="https://img.shields.io/badge/- 4.11. jwt %20- green" height=30px>

### [JwtTokenUtil](#-)

```java
@Component
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = 3540583232420968407L;

	private final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);

	@Value("${jwt.signing.key}")
	private String secretKey;


	public String extractUsernameFromToken(String token) {
		// subject is the user-name , 
		// in our case I use email as user-name
		Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
	}
		

	public String generateToken(UserDetails userDetails) {

		Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
		Map<String, Object> claims = new HashMap<>();
		Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
		
		claims.put("roles", authorities);
		
		/**
		 * This is another implementation , the Payload of the Token will be different
		 * 
		 * List<RoleEntity> listOfRoles = roleRepository.findAll();
		 * listOfRoles.forEach(role -> {
		 * 		if(authorities.contains(new SimpleGrantedAuthority("ROLE_" + role.getRole())))
		 * 			claims.put("is" + role.getRole(), true);
		 * 	}); 
		 */
		
		
		return Jwts
				.builder()
				.setHeaderParam("type", "JWT") // this is the Header of the token
				.setClaims(claims) // claims - It's a hash map where we can define several details
				.setSubject(userDetails.getUsername()) // Subject - this is the user name
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.JWT_EXPIRATION_TIME_ms))
				.signWith(key, SignatureAlgorithm.HS512)
				.compact();
	}
	
	public boolean validateToken(String token) {
		
		Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
		
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return true;		
		} catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
			LOGGER.debug(ex.getMessage());
			throw new BadCredentialsException("INVALID_CREDENTIALS", ex);
		} catch (ExpiredJwtException ex) {
			LOGGER.debug(ex.getMessage());
			throw ex;
		}
	}
}
```

### [JwtAuthenticationFilter](#-)

```java
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final String authorizationHeader = request.getHeader(SecurityConstants.AUTHORIZATION);

		if (authorizationHeader != null && authorizationHeader.startsWith(SecurityConstants.BEARER_PREFIX)) {

			String jwtToken = authorizationHeader.substring(7);

			try {
				if (jwtTokenUtil.validateToken(jwtToken) && SecurityContextHolder.getContext().getAuthentication() == null) {

					String email = jwtTokenUtil.extractUsernameFromToken(jwtToken);

					UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(email);

					UsernamePasswordAuthenticationToken userPassAuthToken = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());

					userPassAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(userPassAuthToken);
				}
			} catch (ExpiredJwtException ex) {
				LOGGER.error(ex.getMessage());				
				request.setAttribute("exception", ex);				
			} catch (BadCredentialsException ex) {
				LOGGER.error(ex.getMessage());
				request.setAttribute("exception", ex);
			}
		}

		filterChain.doFilter(request, response);		
	}	
}
```

### [JwtAuthenticationEntryPoint](#-)

```java
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private final Logger LOGGER = Logger.getLogger(this.getClass());

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {

		Exception exception = (Exception) request.getAttribute("exception");
		
		if (exception.getClass().getSimpleName().equals("ExpiredJwtException")) {
			LOGGER.error("Getting -> " + HttpServletResponse.SC_UNAUTHORIZED + " - " + exception.getMessage());			
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, exception.getMessage());
		}
		else {
			LOGGER.error("Getting -> " + HttpServletResponse.SC_BAD_REQUEST + " - Access Denied, Probably Bad credentials");
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "400 - Access Denied, Probably Bad credentials");
		}
	}
}
```

ANother way to Implement the class of `JwtAuthenticationEntryPoint`

```java
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private final Logger LOGGER = Logger.getLogger(this.getClass());

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {

		Exception exception = (Exception) request.getAttribute("exception");

		if (exception.getClass().getSimpleName().equals("ExpiredJwtException")) {
			/**
			 * If authentication fails , return 401 un-authorized error
			 * This way I send with the response the exception.getMessage() 
			 */
			LOGGER.error("Getting -> " + HttpServletResponse.SC_UNAUTHORIZED + " - " + exception.getMessage());			
			// response.sendError(HttpServletResponse.SC_UNAUTHORIZED, exception.getMessage().substring(0, 35) + ", " + authException.getMessage());
			
			/**
			 * This is another way ,I saw developers returning a HttpServletResponse, 
			 * even though the one raw above does the same 
			 */
		    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

		    final Map<String, Object> body = new HashMap<>();
		    body.put("timestamp", LocalDateTime.now().toString());
		    body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
		    body.put("error", "Unauthorized");
		    body.put("message", exception.getMessage() + " " + authException.getMessage());
		    body.put("path", request.getServletPath());

		    final ObjectMapper mapper = new ObjectMapper();
		    mapper.writeValue(response.getOutputStream(), body);
		}
		else {
			LOGGER.error("Getting -> " + HttpServletResponse.SC_BAD_REQUEST + " - Access Denied, Probably Bad credentials");
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "400 - Access Denied, Probably Bad credentials");
		}
	}
}
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)


###### 4_12_controller

<img src="https://img.shields.io/badge/- 4.12. controller %20- green" height=30px>

In this package I have 4 controllers:

![image](https://user-images.githubusercontent.com/36256986/212500873-6a3e43e3-3b9f-476b-9db3-d660a02bc850.png)

### [JwtAuthenticationController](#-)

In the `JwtAuthenticationController` I have the following urls:

1. `/login`
2. `/logout`
3. `/register`
4. `/refreshToken`


```java
@RestController
@RequestMapping(path = "/auth")
public class JwtAuthenticationController {

	private static Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationController.class);

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;
	@Autowired
	private UserDaoImpl userDaoImpl;
	@Autowired
	private RefreshTokenDaoImpl refreshTokenDaoImpl;

	/**
	 * Login Request
	 */
	@PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtTokenLoginRequest authLoginReq)
			throws Exception {

		Authentication authenticate;

		try {
			authenticate = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authLoginReq.getEmail(), authLoginReq.getPassword()));
		} catch (BadCredentialsException e) {
			LOGGER.error("Authentication failed, throwing BadCredentialsException");
			throw new BadCredentialsException(e.getMessage());
		}

		final JwtUserDetails jwtUserDetails = (JwtUserDetails) authenticate.getPrincipal();
		final String name = jwtUserDetails.getUsername();
		final String accessToken = jwtTokenUtil.generateToken(jwtUserDetails);
		final String refreshToken = refreshTokenDaoImpl.generateRefreshToken(jwtUserDetails.getUsername(),
				SecurityConstants.INVOKED_LOGIN_URL, null);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new JwtTokenResponse(name, accessToken, refreshToken));
	}

	/**
	 * logout Request
	 */
	@GetMapping(path = SecurityConstants.LOGOUT_URL, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LogoutResponse> logout(HttpServletRequest request) {

		final String authorizationHeader = request.getHeader(SecurityConstants.AUTHORIZATION);

		if (authorizationHeader != null && authorizationHeader.startsWith(SecurityConstants.REFRESH_TOKEN_PREFIX)) {
			String _refreshToken = authorizationHeader.substring(14);			
			refreshTokenDaoImpl.deleteRefreshToken(_refreshToken);				
		}				
		LOGGER.info("User logged out Succeeded");
		return ResponseEntity.ok(new LogoutResponse("User Logged Out"));
	}

	/**
	 * Register Request
	 */
	@PostMapping(path = SecurityConstants.REGISTER_URL, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> signUp(@RequestBody UserRegisterRequest userRegisterRequest) {

		UserRegisterResponse userRegisterResponse = userDaoImpl.createUser(userRegisterRequest);
		LOGGER.info("User registration Succeeded");
		return ResponseEntity.ok(userRegisterResponse);
	}

	/**
	 * RefreshToken Request
	 */
	@GetMapping(path = "/refreshToken", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception {

		final String authorizationHeader = request.getHeader(SecurityConstants.AUTHORIZATION);

		if (authorizationHeader != null && authorizationHeader.startsWith(SecurityConstants.REFRESH_TOKEN_PREFIX)) {
			String _refreshToken = authorizationHeader.substring(14);

			try {
				refreshTokenDaoImpl.validateRefreshToken(_refreshToken);

				String email = refreshTokenDaoImpl.getUserByRefreshToken(_refreshToken).getEmail();

				UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(email);

				final String name = userDetails.getUsername();
				final String accessToken = jwtTokenUtil.generateToken(userDetails);
				final String refreshToken = refreshTokenDaoImpl.generateRefreshToken(userDetails.getUsername(),
						SecurityConstants.INVOKED_REFRESH_URL, _refreshToken);

				return ResponseEntity.status(HttpStatus.CREATED)
						.body(new JwtTokenResponse(name, accessToken, refreshToken));

			} catch (Exception ex) {

				Map<String, String> errorResponse = new HashMap<>();
				errorResponse.put("error", ex.getMessage());

				response.setHeader("error", ex.getMessage());
				response.setStatus(HttpStatus.FORBIDDEN.value());
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);

				new ObjectMapper().writeValue(response.getOutputStream(), errorResponse);				
			}
		}
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
				.body("Refresh Token Failed");
	}
}
```

### [UserController](#-)

```java
@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserDaoImpl userDaoImpl;

	// *******************************
	// GET methods
	// ********************************
	@GetMapping(path = "/getUserByName/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getUserByName(@PathVariable("name") String name) {
		LOGGER.info("getUserByName()");		
		return new ResponseEntity<>(userDaoImpl.getUserByName(name), HttpStatus.OK);
	}

	@GetMapping(path = "/getUserByEmail/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getUserByEmail(@PathVariable("email") String email) {
		return new ResponseEntity<>(userDaoImpl.getUserByEmail(email), HttpStatus.OK);
	}

	@GetMapping(path = "/getUsersWithRole/{role}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getUsersWithRole(@PathVariable("role") String role) {

		List<UserEntity> _users = userDaoImpl.getUsersWithRole(role);
		if (_users.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(_users, HttpStatus.OK);
	}

	@GetMapping(path = "/getAllRolesOfUser/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllRolesOfUser(@PathVariable("email") String email) {

		List<RoleEntity> _roles = userDaoImpl.getAllRolesOfUserByEmail(email);
		if (_roles.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(_roles, HttpStatus.OK);
	}

	@GetMapping(path = "/getAllUsers", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllUsers() {
		return new ResponseEntity<>(userDaoImpl.getAllUsers(), HttpStatus.OK);
	}

	// ********************************
	// UPDATE methods
	// ********************************
	@PutMapping(path = "/updateUserDetails/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateUserDetails(@PathVariable("email") String email,
			@RequestBody UserEntity userEntity) {

		UserEntity _user = userDaoImpl.updateUserDetails(email, userEntity);
		return new ResponseEntity<>(_user, HttpStatus.OK);
	}

	// ********************************
	// DELETE methods
	// ********************************
	@DeleteMapping(path = "/deleteUserByEmail/{email}")
	public void deleteUserByEmail(@PathVariable("email") String email) {
		userDaoImpl.deleteUserByEmail(email);
	}

	/**
	 * Need to check this API why its not working
	 */
	@DeleteMapping(path = "/removeAllUsersFromRole/{role}")
	public ResponseEntity<Void> removeAllUsersFromRole(@PathVariable("role") String role) {

		userDaoImpl.removeAllUsersFromRole(role);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping(path = "/deleteAllUsers")
	public ResponseEntity<Void> deleteAllUsers() {

		userDaoImpl.deleteAllUsers();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
```

### [RoleController](#-)

```java
@RestController
@RequestMapping("/api/roles")
@CrossOrigin("*")
public class RoleController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);

	@Autowired
	private RoleDaoImpl roleDaoImpl;

	/******************
	 * POST methods
	 ******************/
	@PostMapping(path = "/createRole", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createRole(@RequestBody RoleEntity roleEntity) {
		LOGGER.info("createRole()");
		return new ResponseEntity<>(roleDaoImpl.createRole(roleEntity), HttpStatus.OK);
	}

	/******************
	 * GET methods
	 ******************/
	@GetMapping(path = "/getRoleByRolename/{role}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getRoleByRolename(@PathVariable("role") String role) {
		return new ResponseEntity<>(roleDaoImpl.getRoleByRolename(role), HttpStatus.OK);
	}

	@GetMapping(path = "/gettAllRoles", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> gettAllRoles() {
		return new ResponseEntity<>(roleDaoImpl.gettAllRoles(), HttpStatus.OK);
	}

	@GetMapping(path = "/getUsersWhoHasRole/{role}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getUsersWhoHasRole(@PathVariable("role") String role) {
		return new ResponseEntity<>(roleDaoImpl.getUsersWhoHasRole(role), HttpStatus.OK);
	}

	/******************
	 * Update methods
	 ******************/
	@PutMapping(path = "/updateRoleDetails", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateRoleDetails(@RequestBody RoleEntity roleEntity) {
		return new ResponseEntity<>(roleDaoImpl.updateRoleDetails(roleEntity), HttpStatus.OK);
	}

	@PutMapping(path = "/addRoleToUser/{email}/{role}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addRoleToUser(@PathVariable("email") String email, @PathVariable("role") String role) {
		return new ResponseEntity<>(roleDaoImpl.addRoleToUser(email, role), HttpStatus.OK);
	}

	/******************
	 * Delete methods
	 ******************/
	@DeleteMapping(path = "/deleteRoleByRoleName/{role}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteRoleByRoleName(@PathVariable("role") String role) {
		roleDaoImpl.deleteRoleByRoleName(role);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@DeleteMapping(path = "/removeRoleFromUserByRoleName/{email}/{role}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> removeRoleFromUserByRoleName(@PathVariable("email") String email,
			@PathVariable("role") String role) {
		return new ResponseEntity<>(roleDaoImpl.removeRoleFromUserByRoleName(email, role), HttpStatus.OK);
	}

	@DeleteMapping(path = "/removeAllRolesFromUser/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> removeAllRolesFromUser(@PathVariable("email") String email) {
		return new ResponseEntity<>(roleDaoImpl.removeAllRolesFromUser(email), HttpStatus.OK);
	}

	@DeleteMapping(path = "/deleteAllRoles")
	public ResponseEntity<?> deleteAllRoles() {
		roleDaoImpl.deleteAllRoles();
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
```

### [BookController](#-)

```java
@RestController
@RequestMapping("/api/book")
public class BookController {

	@GetMapping(path = "/getAllBooks")
	public ResponseEntity<List<Book>> getBook() {
		List<Book> books = Arrays.asList(new Book("one", "author-one"), new Book("two", "author-two"),
				new Book("three", "author-three"));

		return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
	}

	@GetMapping(path = "/amount")
	public String getAmount() {
		return "amount is large";
	}

	public class Book {
		private String bookName;
		private String author;

		public Book(String bookName, String author) {
			this.bookName = bookName;
			this.author = author;
		}

		public String getBookName() {
			return bookName;
		}

		public void setBookName(String bookName) {
			this.bookName = bookName;
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}
	}
}
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### 4_13_Test_App

<img src="https://img.shields.io/badge/- 4.13. Test_App %20- green" height=30px>

Run project of `03-jwt-main-refresh-token` . </br>
I have `data-H2.sql` , `data-MySql.sql` , `data-PostGreSql.sql` to initialize the DB with some data . (depends on which DB I'm connected to) </br>
All files have the same data.

```sql
-------------------------
-- Insert into users_tb
-------------------------
insert into users_tb(user_id,name,email,password) values(1, 'shabtay shalem', 'shabtay.shalem@gmail.com','$2a$10$ITuqt3CLVrMuo0yGH7GhHuWttO.45i4aCH.jcX3s1IUTOiJLcXVk6');
insert into users_tb(user_id,name,email,password) values(2, 'karin shalem', 'karin.shalem@gmail.com','$2a$10$ITuqt3CLVrMuo0yGH7GhHuWttO.45i4aCH.jcX3s1IUTOiJLcXVk6');
insert into users_tb(user_id,name,email,password) values(3, 'avigail shalem', 'avigail.shalem@gmail.com','$2a$10$ITuqt3CLVrMuo0yGH7GhHuWttO.45i4aCH.jcX3s1IUTOiJLcXVk6');
insert into users_tb(user_id,name,email,password) values(4, 'ariel shalem', 'ariel.shalem@gmail.com','$2a$10$ITuqt3CLVrMuo0yGH7GhHuWttO.45i4aCH.jcX3s1IUTOiJLcXVk6');

-------------------------
-- Insert into roles_tb
-------------------------
insert into roles_tb(role_id, role) values (1, 'SUPER-ADMIN');
insert into roles_tb(role_id, role) values (2, 'ADMIN');
insert into roles_tb(role_id, role) values (3, 'USER');

-------------------------
-- Insert into course_tb
-------------------------
INSERT INTO user_role(user_id, role_id) VALUES(1,1);
INSERT INTO user_role(user_id, role_id) VALUES(1,2);
INSERT INTO user_role(user_id, role_id) VALUES(1,3);
INSERT INTO user_role(user_id, role_id) VALUES(2,1);
INSERT INTO user_role(user_id, role_id) VALUES(2,2);
INSERT INTO user_role(user_id, role_id) VALUES(2,3);
INSERT INTO user_role(user_id, role_id) VALUES(3,3);
```

Send via postman the following request:

![image](https://user-images.githubusercontent.com/36256986/214005095-4ea6c35e-d7f3-4750-804f-d6df3e5f79ac.png)

Since we want to see how the flow of `refreshToken` works lets perform the following :

Before starting the tests,  in `SecurityConstants` class , set the `JWT_EXPIRATION_TIME_ms = 5_000` to 5 secs. thhis will ensure that the accessToken will expire after 5 secs.

With Postman :
1. send a Login request
2. send `getUserByName` few times till we get Expiration Error
3. send `refreshToken` request 
4. send right away (because we have a 5 sec gap before expiring) another `getUserByName` to see if new token is valid.


### Send Login request

As we can See , the RefreshToken is a UUID string (Which we created)

![image](https://user-images.githubusercontent.com/36256986/214005306-b66cabc9-e902-4e84-83e2-5657ee9ce2e4.png)

### send `getUserByName` few times till we get Expiration Error (after 5 seccs we get error)

![image](https://user-images.githubusercontent.com/36256986/213416005-832d7406-25dc-463f-84ed-3623d6e0531f.png)

### send `refreshToken` request 

I got back :
* new accessToken 
* new refreshToken

which we can use now for our app in a secured way</br>

![image](https://user-images.githubusercontent.com/36256986/213416186-b5140086-1356-49bd-97d6-4a88867fd8ee.png)


### [Check `deleteUserByEmail` ](#-)

We also want to check the method of `deleteUserByEmail` because `UserEntity` is mapped with `RefreshTokenEntity` , and we want to see that It is properly works.</br>
Example:
* Scenario1 - If `Shabtay` is connected and tries to delete himself , it won't be possible.
* Scenario1 - connect Shabtay  then connect Ariel. Now connect again with Shabtay and delete Ariel . This will work 


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)



