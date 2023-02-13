<img src="https://img.shields.io/badge/-Testing JUnit Jenkins etc%20- blue" height=70px>

###### \_

|     | Subject                                              |
| :-: | :--------------------------------------------------- |
|  1  | [JUnit](#1_JUnit)             |
|  2  | [Unit Test Vs Integration Test](#2_UnitTest_Vs_IntegrationTest)       |
|  3  | [Unit Testing](#3_Unit_Testing)             |
|     | [3.1. Respository Layer Testing](#3_1_Respository_Layer_Testing)             |
|     | [3.2. Service Layer Testing](#3_2_Service_Layer_Testing)             |
|     | [3.3. ](#3_3)             |
|  8  | [CI-CD](#3_CI_CD)             |
|  9  | [Jenkins](#4_Jenkins)             |

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

In the service Lyer of testing we will use annotations of ([see explanation from javatpoint](https://www.javatpoint.com/mockito-annotations)) : 
1. `@InjectMocks`
2. `@Mock` - In the Test, we annotate `@Mock` on fields that are annoteted with `@Autowired` in the service layer. </br>
for example: 
	* if we have annotated `MovieRepository` with `Autowired` in the service layer, we will annotate it with `@Mock` in the test.	
3. `@ExtendWith(MockitoExtension.class)` - Mark the test class with this annotation
4. 

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
