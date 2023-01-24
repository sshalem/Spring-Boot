<img src="https://img.shields.io/badge/-01_jwt_main_project %20-brightgreen" height=70px>

###### \_

|     | Subject                                                                                |
| :-: | :------------------------------------------------------------------------------------- |
|     | [links](#Links)                                                                        |
|     | [Authentication flow](#Authentication_flow)                                            |
|  1  | [01-jwt-main-project](#1_Spring_boot_JWT)                                             |
|     | 1.1. [POM](#1_1_POM)                                                                   |
|     | 1.2. [Package Layout](#1_2_Package_Layout)                                             |
|     | 1.3. [application.properties](#1_3_application_properties)                             |
|     | 1.4. [entity](#1_4_entity)                                                                      |
|     | 1.5. [repository](#1_5_repository)                                                                  |
|     | 1.6. [dao](#1_6_dao)                                                                         |
|     | 1.7. [service](#1_7_service)                                                                     |
|     | 1.8. [model](#1_8_model)                                                                       |
|     | 1.9. [exceptions](#1_9_exceptions)                                                                  |
|     | 1.10. [config](#1_10_config)                                                                     |
|     | 1.11. [jwt](#1_11_jwt)                                                                        |
|     | 1.12. [controller](#1_12_controller)                                                                 |
|     | 1.13. [Test App](#1_13_Test_App)                                                                   |
|     | 1.14. [Test App with Front End](#1_14_Test_App_with_Front_End)   			|


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

---------------------------------

###### 1_Spring_boot_JWT

<img src="https://img.shields.io/badge/- 1. Spring_boot_JWT %20-blue" height=40px>

link : [Bezkoder : spring-boot-jwt-authentication](https://www.bezkoder.com/spring-boot-jwt-authentication/)

link : [Bezkoder : Spring Boot Security Login example with JWT and H2 Database ](https://www.bezkoder.com/spring-boot-security-login-jwt/)

In this tutorial, we’re gonna build a Spring Boot Application that supports Token based Authentication with JWT.

We will see:

- Appropriate Flow for User Signup (Register) & User Login with JWT Authentication
- Spring Boot Application Architecture with Spring Security
- How to configure Spring Security to work with JWT
- How to define Data Models and association for Authentication and Authorization
- Way to use Spring Data JPA to interact with PostgreSQL/MySQL Database

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### 1_1_POM

<img src="https://img.shields.io/badge/- 1.1. POM %20- green" height=30px>

here we can see all the dependencies of the project. </br>
We can see I have 3 differetn dependencies of JWT which I added:

<p align="center">
  <img src="https://user-images.githubusercontent.com/36256986/210587455-d3e04d3c-b995-4e73-bd53-25e5225e27af.png"/>
</p>

```sql
		<!-- https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-api -->
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-api</artifactId>
			<version>0.11.5</version>
		</dependency>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-impl</artifactId>
			<version>0.11.5</version>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-jackson</artifactId> <!-- or jjwt-gson if Gson is preferred -->
			<version>0.11.5</version>
			<scope>runtime</scope>
		</dependency>
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### 1_2_Package_Layout

<img src="https://img.shields.io/badge/- 1.2_Package_Layout %20- green" height=30px>

<p align="center">
    <img src="https://user-images.githubusercontent.com/36256986/210656211-c81bc08a-4008-4c32-aac4-b10bb7a0d2f6.png"/>
</p>


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### 1_3_application_properties

<img src="https://img.shields.io/badge/- 1.3_application_properties %20- green" height=30px>

I have 2 profiles configured:

<p align="center">
    <img src="https://user-images.githubusercontent.com/36256986/210656526-fa811f37-77e3-4a36-9dbc-61c108a82b12.png"/>
</p>

### [application.properties](#-)

```sql
server.port=8080
spring.profiles.active=h2

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
```


### [application-mysql.properties](#-)

```sql
# ===============================
# 		DATA SOURCE
# =============================== 
spring.datasource.url=jdbc:mysql://localhost:3306/jobify?useSSL=false&serverTimezone=UTC
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
spring.jpa.hibernate.naming-strategy = org.hibernate.cfg.ImprovedNamingStrategy

# ===========================================
# 	dev-tools (Need to add dependency)
# ===========================================
spring.devtools.restart.enabled=true
```


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### 1_4_entity

<img src="https://img.shields.io/badge/- 1.4_entity %20- green" height=30px>

In this project I mapped the entities as `OneToMany` just for the exapmle. </br>
In project `02-jwt-main-with-authorities` I made an enhanced code with `ManyToMany` mapping.

```java
@Entity
@Table(name = "USERS_TB")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String email;
	private String password;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<RoleEntity> roles;
	
	Ctor/G/S/Hash/Equals
```

```java
@Entity
@Table(name = "ROLES_TB")
public class RoleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String role;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;
	
	Ctor/G/S/Hash/Equals
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### 1_5_repository

<img src="https://img.shields.io/badge/- 1.5_repository %20- green" height=30px>

```java
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	UserEntity findByEmail(String email);
	UserEntity findByName(String name);
}
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### 1_6_dao

<img src="https://img.shields.io/badge/- 1.6_dao %20- green" height=30px>

```java
public interface UserDao {
	UserRegisterResponse createUser(UserRegisterRequest userRegisterRequest);	
	String getUserName(String email);
}
```

### [UserDaoImpl](#-) 

```java
@Service
public class UserDaoImpl implements UserDao {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserRegisterResponse createUser(UserRegisterRequest userRegisterRequest) {

		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(userRegisterRequest, userEntity);

		if (userRepo.findByEmail(userEntity.getEmail()) != null)
			throw new EmailAlreadyExistException("Email Already Exist");

		userEntity.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));

		UserEntity createdUser = userRepo.save(userEntity);

		UserRegisterResponse userRegisterResponse = new UserRegisterResponse();
		BeanUtils.copyProperties(createdUser, userRegisterResponse);

		userRegisterResponse.setId(UUID.randomUUID());

		return userRegisterResponse;
	}

	@Override
	public String getUserName(String email) {
		UserEntity userEntity = userRepo.findByEmail(email);
		return userEntity.getName();
	}
}
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### 1_7_service

<img src="https://img.shields.io/badge/- 1.7_service %20- green" height=30px>

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

###### 1_8_model

<img src="https://img.shields.io/badge/- 1.8_model %20- green" height=30px>

In this package , I have 4 classes for interacting with the fronend.

![image](https://user-images.githubusercontent.com/36256986/210674530-e0d4db1a-67df-40c1-a5f2-bf5a67d7a0e5.png)

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### 1_9_exceptions

<img src="https://img.shields.io/badge/- 1.9_exceptions %20- green" height=30px>

See the usual code.

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### 1_10_config

<img src="https://img.shields.io/badge/- 1.10_config %20- green" height=30px>

### [SecurityConfig](#-)

In this class i the configuration of the security. </br>
See the way I config it, which Objects I use, which Bean's I create etc... </br>
1. I `@Autowire` :
* [`JwtAuthenticationFilter`](#-)
* [`JwtAuthenticationEntryPoint`](#-) - for exception handling
* [`JwtUserDetailsService`](#-)

2. I create a `Bean` of [`AuthenticationManager`](#-) . Why? </br>
Because I will use AuthenticationManager in my Controller </br>

3. I define the session as stateless [`.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)`](#-)
4. I add the filter [`JwtAuthenticationFilter`](#-) of </br>
[`http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)`](#-) to run before [`UsernamePasswordAuthenticationFilter`](#-) .
5. With JWT we need to `disable csrf()`


* [`WebSecurityConfigurerAdapter`](#-) is deprecated from Spring 2.7.0. More details at: [Bezkoder JWT Implementation](https://www.bezkoder.com/spring-boot-jwt-authentication/)

* Spring Security will load User details to perform authentication & authorization. So it has UserDetailsService interface that we need to implement.
* The implementation of UserDetailsService will be used for configuring DaoAuthenticationProvider by AuthenticationManagerBuilder.userDetailsService() method.
* We also need a PasswordEncoder for the DaoAuthenticationProvider. If we don’t specify, it will use plain text.

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
			.antMatchers("/auth/**").permitAll()
			.antMatchers("/h2/**/**").permitAll() // Should not be in Production!
			.anyRequest()
			.authenticated()
		.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		// fix H2 database console: Refused to display ' in a frame because it set 'X-Frame-Options' to 'deny'
		http
			.headers()
			.frameOptions()
			.sameOrigin() // H2 Console Needs this setting
			.cacheControl(); // disable caching
	}
}
```

### [SecurityConstants](#-)

```java
public class SecurityConstants {

	public static final long EXPIRATION_TIME = 3_600_000; // this is in milli second
	public static final String BEARER_PREFIX = "Bearer "; // Don't forget to add white space after Bearer prefix 
	public static final String AUTHORIZATION = "Authorization";
	public static final String REGISTER_URL = "/register";
}
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### 1_11_jwt

<img src="https://img.shields.io/badge/- 1.11_jwt %20- green" height=30px>

In this package I have following classes:

1. `JwtTokenUtil` 
2. `JwtAuthenticationFilter`
3. `JwtAuthenticationEntryPoint`

### [`1. JwtTokenUtil`](#-)

I use this class as a Utility class to perform the following actions:
- generateToken
- Validating token
- Checking the signature (In method extractClaim)
- Verifying claims and permissions (In private method extractClaim)

```java
@Component
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = 3540583232420968407L;

	private final Logger LOGGER = Logger.getLogger(JwtTokenUtil.class);

	@Value("${jwt.signing.key}")
	private String secretKey;

	public boolean validateToken(String token, UserDetails userDetails) {
		boolean isTokenExpired = extractClaim(token).getExpiration().before(new Date());
		final String username = extractUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired);
	}

	public String extractUsernameFromToken(String token) {
		return extractClaim(token).getSubject();
	}

	public String generateToken(UserDetails userDetails) {

		Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
		Map<String, Object> claims = new HashMap<>();		
		return Jwts
				.builder()
				.setClaims(claims) // claims - It's a hash map where we can define several details
				.setSubject(userDetails.getUsername()) // Subject - this is the user name
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.signWith(key, SignatureAlgorithm.HS512)
				.compact();
	}

	private Claims extractClaim(String token) {

		Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
		
		try {
			Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return claimsJws.getBody();		
		} catch (RuntimeException ex) {
			LOGGER.error("An exception occured, while extracting claims in JwtTokenUtil");
			throw new RuntimeException(ex.getMessage());
		}		
	}
}
```

### [`2. JwtAuthenticationFilter`](#-)

Define a filter that executes once per request. </br>
What we do inside doFilterInternal():
– get JWT from the Authorization header (by removing Bearer prefix)
– if the request has JWT, validate it, parse username from it
– from username, get UserDetails to create an Authentication object
– set the current UserDetails in SecurityContext using setAuthentication(authentication) method.

After this, everytime you want to get UserDetails, just use SecurityContext like this:

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
				String email = jwtTokenUtil.extractUsernameFromToken(jwtToken);
				if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

					UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(email);

					if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {

						UsernamePasswordAuthenticationToken userPassAuthToken = new UsernamePasswordAuthenticationToken(
								userDetails, null, userDetails.getAuthorities());

						userPassAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
						SecurityContextHolder.getContext().setAuthentication(userPassAuthToken);
					}
				}
			} catch (RuntimeException e) {
				// this will catch all exceptions thrown
				// by extractClaim(String token) method from JwtTokenUtil class
				LOGGER.error(e.getMessage());
			}
		}

		filterChain.doFilter(request, response);
	}
}
```

### [`3. JwtAuthenticationEntryPoint`](#-)

The exceptionHandling will be handled by JwtAuthenticationEntryPoint (This is how i config in `SecurityConfig` class)
```java
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private final Logger LOGGER = Logger.getLogger(this.getClass());

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		LOGGER.error("Getting -> " + HttpServletResponse.SC_BAD_REQUEST	+ " - Access Denied, Probably Bad credentials");
		response.sendError(HttpServletResponse.SC_BAD_REQUEST, "400 - Access Denied, Probably Bad credentials");
	}
}
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### 1_12_controller

<img src="https://img.shields.io/badge/- 1.12_controller %20- green" height=30px>

In this section I have 2 controllers :
1. for signup and login `JwtAuthenticationController`
2. For the rest of the project (For example BookController)

### [`JwtAuthenticationController`](#-)

In thihs controller I have 2 methods:
1. for `sign Up` (signUp)
2. for `login` (createAuthenticationToken)

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

	/**
	 * Login Request
	 */
	@PostMapping(path = "/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtTokenLoginRequest authLoginReq)
			throws Exception {

		Authentication authenticate;

		try {
			authenticate = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authLoginReq.getEmail(), authLoginReq.getPassword()));
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException(e.getMessage());
		}

		/**
		 * If the authentication process is successful, we can get User’s information
		 * such as username, password, authorities from an Authentication object.
		 */

		JwtUserDetails jwtUserDetails = (JwtUserDetails) authenticate.getPrincipal();

		// The user-name is the email address (check the method)
		String name = jwtUserDetails.getUsername();
		final String token = jwtTokenUtil.generateToken(jwtUserDetails);

		/**
		 * This is also a way to get the user-name since we are already authenticated, 
		 * (if no exception is thrown), we can get user details from `loadUserByUsername`
		 */
//		final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authLoginReq.getEmail());
//		final String name = userDaoImpl.getUserName(authLoginReq.getEmail());
//		final String token = jwtTokenUtil.generateToken(userDetails);

		// JwtTokenResponse jwtTokenResponse =
		// JwtTokenResponse.name(name).token(token).build();
		// System.out.println(jwtTokenResponse);
		// return ResponseEntity.ok(jwtTokenResponse);

		return ResponseEntity.ok(new JwtTokenResponse(name, token));

	}

	/**
	 * Register Request
	 * 
	 * @throws EmailAlreadyExistException
	 */
	@PostMapping(path = SecurityConstants.REGISTER_URL)
	public ResponseEntity<?> signUp(@RequestBody UserRegisterRequest userRegisterRequest) {

		UserRegisterResponse userRegisterResponse = userDaoImpl.createUser(userRegisterRequest);
		LOGGER.info("User registration Succeeded");
		return ResponseEntity.ok(userRegisterResponse);
	}
}
```

### [`BookController`](#-)

```java
@RestController
@RequestMapping("/book")
public class BookController {

	@GetMapping(path = "/get")
	public String getBook() {
		return "books returned successfully";
	}
}
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### 1_13_Test_App

<img src="https://img.shields.io/badge/- 1.13_Test_App %20- green" height=30px>

1. Run the app `01-jwt-main-project`
2. Open postman with following url's

![image](https://user-images.githubusercontent.com/36256986/211173269-f0919fc8-d53f-4ea3-8518-e222cb2696eb.png)

1. Sent a sign-up request

Will return following message:

![image](https://user-images.githubusercontent.com/36256986/211173299-3661537c-fbd8-4ec8-8f3a-ab475cec2767.png)


2. Send a login request 

This will return back the name and also the `jwtToken` :

![image](https://user-images.githubusercontent.com/36256986/211173315-7f62cc0f-32bb-47d5-b4c5-ff56029f7951.png)

3. send a GET request by adding to request 
	* `Authorization` header
	* Add `Bearer` and join to it the generated JWT token we just received from server

![image](https://user-images.githubusercontent.com/36256986/211173361-4f54a666-1e6e-4a70-87c7-79de6850036f.png)

### [Flow Diagram](#-)

The diagram shows flow of how we implement :
* User Registration (signup)
* User Login (signin)
* Authorization process.

<p align="center">
  <img src="https://user-images.githubusercontent.com/36256986/211184595-956df3f1-5a1c-41de-a92a-e642742280ca.png" width="650px" height="500px"/>
</p>


### [`Work Around with Postman`](#-)

In order to prevent adding manually the generated JWT token we get from server , we can make Postman assign the token from server automatically to the request.</br>
see video form [John Smilga - Jobify project video 111 token setup](https://www.udemy.com/course/mern-stack-course-mongodb-express-react-and-nodejs/learn/lecture/30029488#search)

Steps :

1. type the following code in the `Tests` tab of Login url in Potsman:

```js
const jaonData = pm.response.json();
pm.globals.set("token", jaonData.jwtToken);
```

![image](https://user-images.githubusercontent.com/36256986/211174632-77beb905-f3ff-44b9-ba3c-f0c484bf2489.png)

2. set a global variable and give it the name as I wrote in the code `token` 

3. Add to `Authrization` header of every url I need to send the JWT token, the `token`. </br>
This way the token will be added programatically to the GET request (I need to do it , to every request I sent the JWT )

![image](https://user-images.githubusercontent.com/36256986/211174681-ffc15d21-10c3-498d-bf97-ef66d9d82579.png)

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### 1_14_Test_App_with_Front_End

<img src="https://img.shields.io/badge/- 1.14_Test_App_with_Front_End %20- green" height=30px>

Open Broweser and got to http://localhost:8080 

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)





