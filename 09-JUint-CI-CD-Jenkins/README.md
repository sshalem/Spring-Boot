<img src="https://img.shields.io/badge/-Testing JUnit Jenkins etc%20- blue" height=70px>

###### \_

|     | Subject                                              |
| :-: | :--------------------------------------------------- |
|  1  | [JUnit](#1_JUnit)             |
|     | 1.1. [Types of JUnit](#1_1_Types_of_JUnit)             |
|  2  | [CI-CD](#2_CI_CD)             |
|  3  | [Jenkins](#3_Jenkins)             |

- https://www.youtube.com/watch?v=2E3WqYupx7c&list=PLqq-6Pq4lTTa4ad5JISViSb2FVG8Vwa4o&ab_channel=JavaBrains
- https://www.youtube.com/watch?v=EROuIf2Ac_I&ab_channel=JavaBrains
- https://www.youtube.com/watch?v=e5uGwzz2I-s&ab_channel=JavaBrains
- 


------------------------------------------------------------------------------------------------

###### 1_JUnit

<img src="https://img.shields.io/badge/- 1_JUnit  %20-blue" height=40px>

[Junit 5 Architecture](https://www.educative.io/courses/java-unit-testing-with-junit-5/xV9mMjj74gE): 

![image](https://user-images.githubusercontent.com/36256986/218335246-1e8de40a-b56b-408d-96cd-88c370c60eb4.png)

![image](https://user-images.githubusercontent.com/36256986/218335469-42e31a97-4e81-49c2-bb6c-c69d0d51f1c7.png)

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

######

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

###### x_

<img src="https://img.shields.io/badge/- X %20- green" height=30px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---------------------------------------------------------------------------------------------------
