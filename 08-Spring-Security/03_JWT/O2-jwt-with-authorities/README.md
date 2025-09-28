<img src="https://img.shields.io/badge/02_jwt_main_with_authorities%20-brightgreen" height=70px>

###### \_

|     | Subject                                                                                |
| :-: | :------------------------------------------------------------------------------------- |
|     | [links](#Links)                                                                        |
|     | [Authentication flow](#Authentication_flow)                                            |
|  2  | [02_jwt_main_with_authorities](#2_jwt_main_with_authorities)                              
|     | 2.1. [POM](#2_1_POM)                                                                            |
|     | 2.2. [Package Layout](#2_2_Package_Layout)                                             |
|     | 2.3. [application.properties](#2_3_application_properties)  				|
|     | 2.4. [entity](#2_4_entity)                       					|
|     | 2.5. [repository](#2_5_repository)          						|
|     | 2.6. [dao](#2_6_dao)  									|
|     | 2.7. [service](#2_7_service)                                                                     |
|     | 2.8. [model](#2_8_model)                                                                       |
|     | 2.9. [exceptions](#2_9_exceptions)                                                                  |
|     | 2.10. [config](#2_10_config)                                                                     |
|     | 2.11. [jwt](#2_11_jwt)                                                                        |
|     | 2.12. [controller](#2_12_controller)                                                                 |
|     | 2.13. [Test App](#2_13_Test_App)                                                                   |
|     | 2.14. [Test App with Front End](#2_14_Test_App_with_Front_End)   			|


https://www.bezkoder.com/spring-boot-refresh-token-jwt/

###### Links

<img src="https://img.shields.io/badge/- links %20-blue" height=40px>

https://www.youtube.com/watch?v=pnLcbNUOZvU&ab_channel=TheDevWorld-bySergioLema

GREAT LINK (Well Explained) https://www.toptal.com/spring/spring-security-tutorial

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)
---

###### Authentication_flow

<img src="https://img.shields.io/badge/- Authentication_flow %20-blue" height=40px>

![image](https://user-images.githubusercontent.com/36256986/212500141-fdeb0aec-36d5-4051-9130-7a9083fb8355.png)

During JWT Authentication, These filters are working in the `FilterChainProxy` :

![image](https://user-images.githubusercontent.com/36256986/212500214-58133f2e-f31e-466c-a50b-30a5f0b374c8.png)

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------

###### 2_jwt_main_with_authorities

<img src="https://img.shields.io/badge/- 2. jwt_main_with_authorities %20-blue" height=40px>

In this project, I took the previous project and add a full implemation of JWT with authorization </br>
Meaning , the API will be accessed only to authorized user.

Let's see the implemetations in the next code.

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### 2_1_POM

<img src="https://img.shields.io/badge/- 2.1.POM %20- green" height=30px>

<p align="center">
  <img src="https://user-images.githubusercontent.com/36256986/212492696-987d17cb-f290-49fe-a775-95a56d8a575a.png"/>
</p>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### 2_2_Package_Layout

<img src="https://img.shields.io/badge/- 2.2. Package_Layout %20- green" height=30px>

I added new classes in this implementations . see the differences in section 2.2. 

<p align="center">
  <img src="https://user-images.githubusercontent.com/36256986/212296608-06f2acb7-d2ac-423b-b495-a5348ad99ccb.png"/>
</p>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### 2_3_application_properties

<img src="https://img.shields.io/badge/- 2.3. application_properties %20- green" height=30px>

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

###### 2_4_entity

<img src="https://img.shields.io/badge/- 2.4. entity %20- green" height=30px>

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
	 * 				@SequenceGenerator(name = "studentseq", initialValue = 20001, allocationSize = 50)
	 *				@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "studentseq")
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
	
	/**
	 * Don't include the mapped variables to Hashcode & equeals 
	 * We also dont add them to the toString method
	 */
	Ctor/G/S/Hash/Equals	
	
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

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### 2_5_repository

<img src="https://img.shields.io/badge/- 2.5. repository %20- green" height=30px>

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


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### 2_6_dao

<img src="https://img.shields.io/badge/- 2.6. dao %20- green" height=30px>

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

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### 2_7_service

<img src="https://img.shields.io/badge/- 2.7. service %20- green" height=30px>

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

###### 2_8_model

<img src="https://img.shields.io/badge/- 2.8. model %20- green" height=30px>

Same as section [2.8](#2_8_model)

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### 2_9_exceptions

<img src="https://img.shields.io/badge/- 2.9. exceptions %20- green" height=30px>

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

###### 2_10_config

<img src="https://img.shields.io/badge/- 2.10. config %20- green" height=30px>

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

//	public static final long EXPIRATION_TIME = 3_600_000; // this is in milli second = 1 hour
	public static final long EXPIRATION_TIME = 200_000; // this is 200 seconds
//	public static final long EXPIRATION_TIME = 5_000; // this is 5 seconds
	public static final String BEARER_PREFIX = "Bearer "; // Don't forget to add white space after Bearer prefix 
	public static final String AUTHORIZATION = "Authorization";
	public static final String REGISTER_URL = "/register";
}
```


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)


###### 2_11_jwt

<img src="https://img.shields.io/badge/- 2.11. jwt %20- green" height=30px>

### [JwtTokenUtil](#-)

```java
@Component
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = 3540583232420968407L;

	private final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);

	@Value("${jwt.signing.key}")
	private String secretKey;


	public String extractUsernameFromToken(String token) {
		Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
		Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
		return claims.getSubject(); // subject is the user-name , in our case I use email as user-name
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
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.signWith(key, SignatureAlgorithm.HS512)
				.compact();
	}
	
	
	public boolean validateToken(String token) {
		
		Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
		
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return true;		
		} catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
			LOGGER.error(ex.getMessage());
			throw new BadCredentialsException("INVALID_CREDENTIALS", ex);
		} catch (ExpiredJwtException ex) {
			LOGGER.error(ex.getMessage());
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

		System.out.println( authException.getMessage());
		System.out.println(authException.getClass());
		
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

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)


###### 2_12_controller

<img src="https://img.shields.io/badge/- 2.12. controller %20- green" height=30px>

In this package I have 4 controllers:

![image](https://user-images.githubusercontent.com/36256986/212500873-6a3e43e3-3b9f-476b-9db3-d660a02bc850.png)

### [JwtAuthenticationController](#-)

```java
@RestController
@RequestMapping(path = "/auth")
public class JwtAuthenticationController {

	private static Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationController.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

//	@Autowired
//	private JwtUserDetailsService jwtUserDetailsService;

	@Autowired
	private UserDaoImpl userDaoImpl;

	/**
	 * Login Request
	 */
	@PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtTokenLoginRequest authLoginReq) throws Exception {

		Authentication authenticate;

		try {
			authenticate = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authLoginReq.getEmail(), authLoginReq.getPassword()));
		} catch (BadCredentialsException e) {
			LOGGER.error("Authentication failed, throwing BadCredentialsException");
			throw new BadCredentialsException(e.getMessage());
		}

		/**
		 * First Way:
		 * If the authentication process is successful, we can get User’s information
		 * such as username, password, authorities from an Authentication object.
		 */
				
		final JwtUserDetails jwtUserDetails = (JwtUserDetails) authenticate.getPrincipal();
		final String name = jwtUserDetails.getUsername();
		final String token = jwtTokenUtil.generateToken(jwtUserDetails);

		return ResponseEntity.ok(new JwtTokenResponse(name, token));
		
		/**
		 * Second way:
		 * This is also a way to get the user-name since we are already authenticated,
		 * (if no exception is thrown), we can get user details from
		 * `loadUserByUsername`
		 */
		//		final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authLoginReq.getEmail());
		//		final String name = userDaoImpl.getUserName(authLoginReq.getEmail());
		//		final String token = jwtTokenUtil.generateToken(userDetails);
		//		return ResponseEntity.ok(new JwtTokenResponse(name, token));

		// JwtTokenResponse jwtTokenResponse =
		// JwtTokenResponse.name(name).token(token).build();
		// return ResponseEntity.ok(jwtTokenResponse);
	}

	/**
	 * Register Request
	 * 
	 * @throws EmailAlreadyExistException
	 */
	@PostMapping(path = SecurityConstants.REGISTER_URL, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> signUp(@RequestBody UserRegisterRequest userRegisterRequest) {

		UserRegisterResponse userRegisterResponse = userDaoImpl.createUser(userRegisterRequest);
		LOGGER.info("User registration Succeeded");
		return ResponseEntity.ok(userRegisterResponse);
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

###### 2_13_Test_App

<img src="https://img.shields.io/badge/- 2.13. Test_App %20- green" height=30px>

Run project of `02-jwt-main-with-authorities` . </br>
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

![image](https://user-images.githubusercontent.com/36256986/212501001-c7fc6294-b7e7-4312-a457-d2a3ee94ec2d.png)

1. Sigup
2. Login
3. Check URLs

* Notice to the expiration of the token , we can modify it in the code `SecurityConstants` class.
* When we get the token , we can go to [`https://jwt.io/`](https://jwt.io/) and paste the token the following way:

- At the Encoded , paste the JWT token
- At the decoded we have 3 sub titles, `header` , `payload` , `verify signature`

* `Header` - is where we have the ALGORITHM & TOKEN TYPE
* `payload` - thius is the `CLAIM` , subject , iat, ext (We can also add more data to it , by adding to the claims (It's a MAP) more data)
* `verify signature` - we need to insert the secret key (Form what I have in application properties `jwt.signing.key` ) , to verify the signature

![image](https://user-images.githubusercontent.com/36256986/212552652-8541a113-b8fe-4d87-b765-fe5b50ba28de.png)


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)



