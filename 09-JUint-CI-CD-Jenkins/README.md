<img src="https://img.shields.io/badge/-Testing JUnit Jenkins etc%20- blue" height=70px>

###### \_

|     | Subject                                              |
| :-: | :--------------------------------------------------- |
|  1  | [JUnit](#1_JUnit)             |
|  2  | [Unit Test Vs Integration Test](#2_UnitTest_Vs_IntegrationTest)       |
|  3  | [Unit Testing](#3_Unit_Testing)             |
|     | [3.1. Respository Layer Testing](#3_1_Respository_Layer_Testing)             |
|     | [3.2. Service Layer Testing](#3_2_Service_Layer_Testing)             |
|     | [3.3. Controller Layer Testing](#3_3_Controller_Layer_Testing)             |
|  4  | [Integration Testing](#4_Integration_testing)             |
|  5  | [Jenkins](#5_Jenkins)             |
|     | [5.1. before CI](#5_1_before_CI)             |
|     | [5.2. with CI architecture](#5_2_with_CI_architecture)             |
|     | [5.3. Jenkins Server Install](#5_3_Jenkins_Server_Install)             |
|     | [5.4. Work Flow with Jenkins](#5_4_Work_Flow_with_Jenkins)             |
|     | [5.5. ](#5_5)             |
|     | [5.. ](#5_)             |
|  6  | [CI-CD](#3_CI_CD)             |


- https://www.youtube.com/watch?v=2E3WqYupx7c&list=PLqq-6Pq4lTTa4ad5JISViSb2FVG8Vwa4o&ab_channel=JavaBrains
- https://www.youtube.com/watch?v=EROuIf2Ac_I&ab_channel=JavaBrains
- https://www.youtube.com/watch?v=e5uGwzz2I-s&ab_channel=JavaBrains
- 


------------------------------------------------------------------------------------------------

###### 1_JUnit

<img src="https://img.shields.io/badge/- 1_JUnit  %20-blue" height=40px>

[Junit 5 Architecture](https://www.educative.io/courses/java-unit-testing-with-junit-5/xV9mMjj74gE): 

![image](https://user-images.githubusercontent.com/36256986/218335246-1e8de40a-b56b-408d-96cd-88c370c60eb4.png)

Junit 5 Architecture has three main components :

### [Junit Platform](#-)

It provides a core foundation to help launching testing frameworks on JVM. </br>
It acts as an interface between JUnit and its clients such as build tools (`Maven and Gradle`) and IDE's (`Eclipse and IntelliJ`). </br>
It introduces the concept of a `Launcher` which external tools use to discover, filter, and execute tests. </br>
It also provides the TestEngine API for developing a testing framework that runs on the JUnit platform. </br>
Using TestEngine API, 3rd party testing libraries such as Spock, Cucumber, and FitNesse can directly plug in and provide their custom TestEngine.


### [Junit Jupiter](#-)

It provides a new programming model and extension model for writing tests and extensions in Junit 5. </br>
It has a whole new annotation to write test cases in Junit 5. </br>
Some of the annotations are `@BeforeEach`, `@AfterEach`, `@AfterAll`, `@BeforeAll` etc. </br>
It implements TestEngine API provided by Junit Platform so that Junit 5 test can be run.

### [Junit Vintage](#-)

The term `Vintage` basically means **classic**. </br>
Thus, this sub-project provides extensive support for writing test cases in JUnit 4 and JUnit 3. </br>
Thus, backward compatibility is been provided by this project.


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------------

###### 2_UnitTest_Vs_IntegrationTest

<img src="https://img.shields.io/badge/- 2_UnitTest_Vs_IntegrationTest %20-blue" height=40px>

Lets see the type of testing we have:
1. Unit testing
2. Integration Testing

see following link for [JUnit testing](https://www.youtube.com/watch?v=pHTr3IMuRh0)

JUnit test has 3 parts:
1. Arrange
2. Act
3. Assert

```java
@Test
void test() {
	// Arrange
	// Act
	// Assert	
}
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------------

###### 3_Unit_Testing

<img src="https://img.shields.io/badge/- 3_Unit_Testing %20-blue" height=40px>

Let's see how we can Unit Test the Repository. </br>
I've created the following basic project to Test Unit a Repository. </br>

### [Dependencies](#-)

![image](https://user-images.githubusercontent.com/36256986/218346201-082faa63-d311-453f-b8b4-0008b494b0b3.png)

### [Package layout](#-)

![image](https://user-images.githubusercontent.com/36256986/218405158-1309cac9-6fe0-4ce7-89fe-67d8a724dc17.png)

### [Movie Entity](#-)

```java
@Entity
@Table(name = "movie_tb")
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String genera;
	private LocalDate releaseDate;

	Ctor/G/S  
```

### [MovieRepository](#-)

```java
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

	Movie findMovieByName(String name);

	@Query("SELECT m FROM Movie m WHERE m.releaseDate>=:test")
	List<Movie> findMoviesNewerThanReleaseDate(@Param("test") LocalDate test);
}
```

### [MovieService](#-)

```java
public interface MovieService {
	Movie save(Movie movie);
	List<Movie> getAllMovies();
	Movie getMovieById(long id);
	Movie getMovieByName(String name);
	List<Movie> getMoviesAfterReleaseDate(LocalDate localDate);
}
```

### [MovieServiceImpl](#-)

```java
@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepository movieRepository;

	@Override
	public Movie save(Movie movie) {
		return movieRepository.save(movie);
	}

	@Override
	public List<Movie> getAllMovies() {
		return movieRepository.findAll();
	}

	@Override
	public Movie getMovieById(long id) {
		return movieRepository.findById(id).orElseThrow(() -> new RuntimeException("No Movie Found"));
	}

	@Override
	public Movie getMovieByName(String name) {
		return movieRepository.findMovieByName(name);
	}

	@Override
	public List<Movie> getMoviesAfterReleaseDate(LocalDate localDate) {
		return movieRepository.findMoviesAfterReleaseDate(localDate);
	}
}
```


### [Test Unit](#-)

In order to Test Unit the Respository I will create it a short way. </br>
Right click on the Repository package and select new `JUnit Test Case`. </br>

![image](https://user-images.githubusercontent.com/36256986/218347919-1b53c8a3-cda2-4dae-b522-8268ca174572.png)

It will open the following window , which will be filled automaticaly with the marked names:

![image](https://user-images.githubusercontent.com/36256986/218348096-ca309d1c-e8df-4d42-afea-7ffcf6e6006f.png)

click on next , it will open the following window. Here I can select which methods I want to test.

![image](https://user-images.githubusercontent.com/36256986/218348194-9eac1225-0b56-48c7-bc6a-29de00891bba.png)

We can see now that a new package is created under the `src/test/java` folder.

![image](https://user-images.githubusercontent.com/36256986/218348325-3c9f9e3a-aebb-4986-b4fa-7aa4299d168f.png)

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### 3_1_Respository_Layer_Testing

<img src="https://img.shields.io/badge/- 3_1_Respository_Layer_Testing %20- green" height=30px>

Add the following annotaion to the [`@DataJpaTest`](https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/test/autoconfigure/orm/jpa/DataJpaTest.html) . </br>

[see another link with explanation](https://howtodoinjava.com/spring-boot2/testing/datajpatest-annotation/) </br>
In Spring boot applications, we can use `@DataJpaTest` annotation that focuses only on testing the JPA components.</br>
`@DataJpaTest` will disable full auto-configuration of the application context and instead apply only configuration relevant to JPA components and tests.</br>
By default, it scans for `@Entity` classes and configures Spring Data JPA repositories annotated with `@Repository` annotation.</br>

If an embedded database is available on the classpath, it configures one as well. </br>
Use @AutoConfigureTestDatabase to override this behavior. </br>
For example, to run the tests against an application configured real database, use Replace.NONE.

```
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TestAppData {
   // ..
}
```

This is the code for testing the Repository. </br>
See the annotations of:
* `@DataJpaTest`
* `@BeforeEach`
* `@TestMethodOrder(OrderAnnotation.class)` - To execute the test methods in a certain order
* `Order(x)` - Must be applied at method level


```java
@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
class MovieRepositoryTest {

	@Autowired
	private MovieRepository movieRepository;
	private Movie avatarMovie;
	private Movie titanicMovie;
	private Movie israelMovie;
	private Movie generalMovie;

	/**
	 * Before Running any Test case This init() method will be executed
	 */
	@BeforeEach
	void init() {
		// Arrange
		avatarMovie = new Movie("avatar", "action", LocalDate.of(2000, Month.APRIL, 22));
		titanicMovie = new Movie("titanic", "action", LocalDate.of(2001, Month.APRIL, 30));
		israelMovie = new Movie("israel", "action", LocalDate.of(2001, Month.APRIL, 15));
		generalMovie = new Movie("israel", "action", LocalDate.of(2002, Month.APRIL, 15));
	}

	@Test
	@Order(1)
	@DisplayName("check save movie to DB")
	void _01_save() {
		Movie savedMovie = movieRepository.save(avatarMovie);
		// Assert
		assertNotNull(savedMovie);
		assertThat(savedMovie.getId()).isNotEqualTo(null);
	}

	@Test
	@Order(2)
	void _02_test_findMovieByName() {
		Movie avatarMovie = new Movie("avatar", "action", LocalDate.of(2000, Month.APRIL, 22));
		movieRepository.save(avatarMovie);
		Movie movieByName = movieRepository.findMovieByName("avatar");
		assertThat(movieByName).isNotNull();
		assertEquals("avatar", movieByName.getName());
	}

	@Test
	@Order(3)
	void _03_test_findMoviesByReleaseDate() {
		// Act
		movieRepository.save(avatarMovie);
		movieRepository.save(titanicMovie);
		movieRepository.save(israelMovie);
		movieRepository.save(generalMovie);

		List<Movie> listMovies = movieRepository.findMoviesNewerThanReleaseDate(LocalDate.of(2000, Month.APRIL, 30));
		System.out.println(listMovies);

		assertThat(listMovies).isNotNull();
		assertEquals(3, listMovies.size());
	}
}
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### 3_2_Service_Layer_Testing

<img src="https://img.shields.io/badge/- 3_2_Service_Layer_Testing %20- green" height=30px>

In the Service Layer testing we will use:
1. `Mockito` - with Mockito we use to mock the dummy implementation of MovieService
2. `JUnit`

See the following link for [well exaplnation of Mocking](https://blog.devgenius.io/spring-boot-deep-dive-on-unit-testing-92bbdf549594)

Notes: We are testing the service layer. So we don't require a database layer actual operation. So we will make the database layer mock. </br>

In the service Lyer of testing we will use annotations of ([see explanation from javatpoint](https://www.javatpoint.com/mockito-annotations)) : 
1. `@InjectMocks`
2. `@Mock` - In the Test, we annotate `@Mock` on fields that are annoteted with `@Autowired` in the service layer. </br>
for example: 
	* if we have annotated `MovieRepository` with `Autowired` in the service layer, we will annotate it with `@Mock` in the test.	
3. `@ExtendWith(MockitoExtension.class)` - Mark the test class with this annotation

### [MovieServiceImplTest - Service Layer Unit Test](#-)

```java
package com.unit.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.unit.entity.Movie;
import com.unit.repository.MovieRepository;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(OrderAnnotation.class)
class MovieServiceImplTest {

	@InjectMocks
	private MovieServiceImpl movieServiceImpl;

	@Mock
	private MovieRepository movieRepository;

	private Movie avatarMovie;

	private Movie israelMovie;

	@BeforeEach
	void setUp() throws Exception {
		avatarMovie = new Movie();
		avatarMovie.setId(1L);
		avatarMovie.setName("avatar");
		avatarMovie.setGenera("action");
		avatarMovie.setReleaseDate(LocalDate.of(2000, Month.APRIL, 22));

		israelMovie = new Movie();
		israelMovie.setId(2L);
		israelMovie.setName("israel");
		israelMovie.setGenera("action");
		israelMovie.setReleaseDate(LocalDate.of(2000, Month.APRIL, 22));
	}

	@Test
	@Order(1)
	void test_01_Save() {

		// When this line is OK , then I can continue with code execution and use the
		// avatarMovie
		when(movieRepository.save(any(Movie.class))).thenReturn(avatarMovie);

		Movie savedMovie = movieServiceImpl.save(avatarMovie);

		assertNotNull(savedMovie);
		assertThat(savedMovie.getName()).isEqualTo("avatar");
	}

	@Test
	@Order(2)
	void test_02_GetMovieByName() {

//		when(movieRepository.findMovieByName(anyString())).thenReturn(avatarMovie);
//		Movie movieByName = movieServiceImpl.getMovieByName("anyString - doesn't matter which name, I will return avatar");

		when(movieRepository.findMovieByName("avatar")).thenReturn(avatarMovie);
		Movie movieByName = movieServiceImpl.getMovieByName("avatar");

		assertNotNull(movieByName);
		assertThat(movieByName.getName()).isEqualTo("avatar");
	}

	@Test
	@Order(3)
	void test_03_GetAllMovies() {

		List<Movie> movies = Arrays.asList(israelMovie, avatarMovie);

		when(movieRepository.findAll()).thenReturn(movies);

		List<Movie> list = movieServiceImpl.getAllMovies();

		assertNotNull(list);
		assertEquals(2, list.size());
	}

	@Test
	@Order(4)
	void test_04_GetMovieById() {

//		when(movieRepository.findById(anyLong())).thenReturn(Optional.of(avatarMovie));
//		Movie movie = movieServiceImpl.getMovieById(1L); // anyLong() - doesn't matter what long number, It will return 1L 

		when(movieRepository.findById(1L)).thenReturn(Optional.of(avatarMovie));
		Movie movie = movieServiceImpl.getMovieById(1L);

		assertNotNull(movie);
		assertThat(movie.getId()).isEqualTo(1L);
	}

	@Test
	@Order(5)
	void test_05_GetMovieByIdForException() {
		when(movieRepository.findById(10L)).thenReturn(Optional.of(avatarMovie));
		assertThrows(RuntimeException.class, () -> movieServiceImpl.getMovieById(5L));
	}

	@Test
	@Order(6)
	void test_06_updateMovie() {

		// Since we have in our updateMovie() method 2 method calls using
		// movieRepository,
		// Thus we need to have : 2 when() calls with using movieRepository
		when(movieRepository.findById(anyLong())).thenReturn(Optional.of(avatarMovie));
		when(movieRepository.save(any(Movie.class))).thenReturn(avatarMovie);

		avatarMovie.setGenera("Fantacy");

		Movie updateMovie = movieServiceImpl.updateMovie(avatarMovie);

		assertNotNull(updateMovie);
		assertEquals("Fantacy", updateMovie.getGenera());
	}

	@Test
	@Order(7)
	void test_07_deleteMovie() {

//		when(movieRepository.findById(anyLong())).thenReturn(Optional.of(avatarMovie));
		when(movieRepository.findById(1L)).thenReturn(Optional.of(avatarMovie));
		doNothing().when(movieRepository).delete(any(Movie.class));
		movieServiceImpl.deleteMovie(1L);
		verify(movieRepository, times(1)).delete(avatarMovie);
	}
}
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)


---------------------------------------------------------------------------------------------------


###### 4_Integration_testing

<img src="https://img.shields.io/badge/- 4_Integration_testing %20-blue" height=40px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------------


######

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

###### x_

<img src="https://img.shields.io/badge/- X %20- green" height=30px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------------


###### 5_Jenkins

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

See link with explaination from [Java Techie](https://www.youtube.com/watch?v=jkJgS3zDv9g&ab_channel=JavaTechie)


[Question:](#-)
* What is `Jenkins`?

[Answer:](#-)
* Jenkins is a `CI (Contiguous Integration)`  tool used to build(Compile, test) code and deploy it to the production
* Jenkins provides hundreds of plugins to support :
	* Building
	* Deploying
	* And automating any project.
* It is a server-based system that runs in servlet containers such as Apache Tomcat.
* It supports version control tools ,to automate build ,like :
	* GIT
	* SVN 

	
[Question:](#-)
* What is CI (Contiguous Integration)?

[Answer:](#-)
* `CI` is a process in which development work is integrated as early as possible. The resulting artifacts are automatically created and tested.	This process allows to identify errors as early as possible.

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)


###### 5_1_before_CI

<img src="https://img.shields.io/badge/- 5_1_before_CI %20- green" height=30px>

Let's see the problem we had before CI:

### [Flow Diagram before CI](#-)

- Developers write code and commit to Source code Repository (GIT)
- Next step it will be built in a MAVEN or GRADLE (Difficult Integration)
- Once Built Is successful , it will go to the Test environment
- Here we have 2 options :
	* If any failures/bugs , it will notify the developer
	* If NO bugs , I can be released 


![image](https://user-images.githubusercontent.com/36256986/218476416-d9b748fe-07c7-4af0-9c59-24c79adb11e2.png)

When it comes to Testing , the tester needs to identity the root error :

![image](https://user-images.githubusercontent.com/36256986/218476502-e7bf1e07-841f-424d-a50a-efe35d27785e.png)

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)


###### 5_2_with_CI_architecture

<img src="https://img.shields.io/badge/- 5_2_with_CI_architecture %20- green" height=30px>

### [Flow Diagram with CI architecture](#-)

- Developers write code and commit to Source code Repository (GIT)
- Source code on GIT gets pulled to CI server 
- CI server will do the :
	- Build 
	- Test 
	- Deploy
- At the time of test , 
	- if there is any failure on code , it immediatley notify to the developers Team 
	- If not failure it goes directly to the release.
- If there's is a problem with the release , it will notify the developer

![image](https://user-images.githubusercontent.com/36256986/218477106-cb3b77cb-836e-4288-bc1d-bd79d5441648.png)
![image](https://user-images.githubusercontent.com/36256986/218477150-6da6cb1b-863a-400c-b6cd-031230bfc685.png)

### [Comparison](#-)

![image](https://user-images.githubusercontent.com/36256986/218477625-9dff942c-629c-480d-8a4a-88ac4ba59d19.png)

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)


###### 5_3_Jenkins_Server_Install

<img src="https://img.shields.io/badge/- 5_3_Jenkins_Server_Install %20- green" height=30px>

1. Go to Jenkins official site:  https://www.jenkins.io/

![image](https://user-images.githubusercontent.com/36256986/218478100-2e13080d-3630-48b5-b920-d5253375ac45.png)

2. Click on the download button
3. Choose LTS and download the .war file (IT's without the installer) 

![image](https://user-images.githubusercontent.com/36256986/218478291-193ad3f6-ede5-48e5-99cf-c757c8d1cd31.png)

4. Open CMD and run the Jenkins Server

![image](https://user-images.githubusercontent.com/36256986/218478568-763e6378-dd95-446e-a102-6c54052e3a30.png)

5. Once it runs we need to use password for admin which is created ,And its location is at the directory mentioned below:

![image](https://user-images.githubusercontent.com/36256986/218478843-ef12b68d-cf28-485a-ae86-a2e4b453686e.png)

6. Open the browser at localhost:8080  (this is the default port of Jenkins server)

	* Need to paste the password in the field below:

![image](https://user-images.githubusercontent.com/36256986/218479098-d080c9c1-15e9-44a1-80a1-d856e467ce81.png)

7. Next page we get is following one

	* Click on install Suggested Plugins 

![image](https://user-images.githubusercontent.com/36256986/218479263-a44ed7fd-0e35-493f-8c7b-b38e53afe7c6.png)

8. After plugins are installed , following page pops up:

	* I fill the following data , click save and continue
	* Password : 1234 

![image](https://user-images.githubusercontent.com/36256986/218479438-15c405f0-7989-4ed4-aa62-304f21a4d39d.png)

9. Following page pops up

	* Here I can change the port number , but I left it as is:

![image](https://user-images.githubusercontent.com/36256986/218479678-92455575-6961-4dcf-b8cb-8445b22814f1.png)

![image](https://user-images.githubusercontent.com/36256986/218479719-e6307fce-d94c-4a83-a1e2-407857897965.png)


10. Browse to [http://localhost:8080](http://localhost:8080)

	* This is how the dashboard looks like:

![image](https://user-images.githubusercontent.com/36256986/218480072-caa6021b-fea0-4ba6-853a-125483dc377c.png)



[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)


###### 5_4_Work_Flow_with_Jenkins

<img src="https://img.shields.io/badge/- 5_4_Work_Flow_with_Jenkins %20- green" height=30px>

### [Workflow with jenkins](#-)

### [1. Create Spring boot app , with Junit test in it](#-)

```java
import javax.annotation.PostConstruct;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
 
@SpringBootApplication
public class JenkinsApplication {
 
	private static final Logger log = LoggerFactory.getLogger(JenkinsApplication.class);
 
	@PostConstruct
	public void init() {
		log.info("Application started...");
	}
 
	public static void main(String[] args) {
		log.info("Application executed...");
		SpringApplication.run(JenkinsApplication.class, args);
 
	}
 
}
```

#### [JUint Class](#-)

```java
import static org.junit.jupiter.api.Assertions.assertEquals;
 
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
 
@SpringBootTest
@ExtendWith(SpringExtension.class)
class JenkinsApplicationTests {
 
	private static final Logger log = LoggerFactory.getLogger(JenkinsApplicationTests.class);
 
	@Test
	void contextLoads() {
		log.info("Test case executing...");
		assertEquals(true, true);
	}
 
}
```


### [2. Create a GIT repo and load upload the App to GIT repo](#-)

Created GIT Repository with :
* same name of our App 
* and load code to GIT Repo

<p align="center">
	<img src="https://user-images.githubusercontent.com/36256986/218481716-90e464e0-9644-4b53-8829-ade7e90632d4.png" width=500 height=400 />
</p>

### [3. Run Jenkins War file , to make Jenkins server Run](#-)

Open CMD and run the following command:

```
java -jar jenkins.war
```

<p align="center">
	<img src="https://user-images.githubusercontent.com/36256986/218482018-8084ff45-6b57-4093-b035-65f4b5f437f1.png" width=500 height=150 />
</p>


### [4. Create new Job](#-)

1. Open Jenkins dashboard by browsing to http://localhost:8080
2. Click on create new job

<p align="center">
	<img src="https://user-images.githubusercontent.com/36256986/218482528-eb2ef7a2-57df-46b3-bf9d-aba361574207.png" width=300 height=150/>
</p>



3. Type the `app name` and select the `FreeStyle Project` then click OK

<p align="center">
	<img src="https://user-images.githubusercontent.com/36256986/218482624-5cf6a5e8-2ad4-46ad-a305-3fd238b5ffeb.png" width=500 height=400 />
</p>

4. Following Page shows up In order to sync the project with jenkins do the following:

<p align="center">
	<img src="https://user-images.githubusercontent.com/36256986/218482843-5810fdb2-5343-40ef-8908-c68928d1871f.png" width=600 height=400 />
</p>

5. Add GIT url and display name

	* Project url is not the git repo url  see in section 6

<p align="center">
	<img src="https://user-images.githubusercontent.com/36256986/218482992-40dd9189-95a6-430b-abd0-35b1e54b0e1c.png" width=300 height=200 />
</p>

6. Add repository url
	* Click on add to add password of git

<p align="center">
	<img src="https://user-images.githubusercontent.com/36256986/218483187-167a6c17-31a2-48cd-812e-5f5ce4391908.png" width=300 height=200 />
</p>

Here I need to add Jenkins Credentials made when I installed Jenkins : 
* user (admin): sshalem
* Password : 1234

click on add

<p align="center">
	<img src="https://user-images.githubusercontent.com/36256986/218483402-ddc97e46-a5d1-4c5a-a450-5c60a3253e3c.png" width=500 height=400 />
</p>

* Now I'm able to see my user:

<p align="center">
	<img src="https://user-images.githubusercontent.com/36256986/218527960-65dabc09-5cd5-4810-bdaa-a21984bbea02.png" width=300 height=200 />
</p>

7. Go to Build Triggers Section 
	* Select Poll SCM (In Jenkins, SCM stands for "Source Code Management") 
	* In the test type 5 stars cron with space between them: * * * * *
	* This will give the a message every min if a change is done
	* Usually in production this is scheduled like one a day

<p align="center">
	<img src="https://user-images.githubusercontent.com/36256986/218483745-4389b76c-9e19-498b-89ab-0f1e0e07eca9.png" width=500 height=300 />
</p>

8. Go to section Build and select the below option:

<p align="center">
	<img src="https://user-images.githubusercontent.com/36256986/218483885-eb9c26a4-0f6c-4685-baf8-c486ed30bcb8.png" width=300 height=200 />
</p>

* Type install

<p align="center">
	<img src="https://user-images.githubusercontent.com/36256986/218484033-5152bade-3ef6-449a-bb18-2f3943cbaa01.png" width=300 height=200 />
</p>

9. Go to section Post-build Actions

<p align="center">
	<img src="https://user-images.githubusercontent.com/36256986/218484184-69b0aea9-41fe-4d2f-90e6-ca2ab56e8fb8.png" width=300 height=200 />
</p>

* Select Email notification :

<p align="center">
	<img src="https://user-images.githubusercontent.com/36256986/218512655-99151bc4-1605-4e8d-9b5f-29566ae4f289.png" width=300 height=200 />
</p>

10. click on apply

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### 5_5

<img src="https://img.shields.io/badge/- 5_5 %20- green" height=30px>


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### 

<img src="https://img.shields.io/badge/- X %20- green" height=30px>


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### 

<img src="https://img.shields.io/badge/- X %20- green" height=30px>


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### 

<img src="https://img.shields.io/badge/- X %20- green" height=30px>


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------------


######

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------------


###### 

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------------


######

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

###### x_

<img src="https://img.shields.io/badge/- X %20- green" height=30px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------------
