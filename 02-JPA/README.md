<img src="https://img.shields.io/badge/-JPA Mapping%20- blue" height=70px>

###### \_

|     | Subject                                              |
| :-: | :--------------------------------------------------- |
|  1  | [One2Many_Bi_Eager](#1_One2Many_Bi_Eager)            |
|  2  | [One2Many_Bi_Lazy](#2_One2Many_Bi_Lazy)              |
|  3  | [One2Many_Uni_Eager](#3_One2Many_Uni_Eager)          |
|  4  | [One2Many_Uni_Lazy](#4_One2Many_Uni_Lazy)            |
|  5  | [One2One_Bi_Eager](#5_One2One_Bi_Eager)              |
|  6  | [One2One_Bi_Lazy](#6_One2One_Bi_Lazy)                |
|  7  | [One2One_Uni_Eager](#7_One2One_Uni_Eager)            |
|  8  | [One2One_Uni_Lazy](#8_One2One_Uni_Lazy)              |
|  9  | [Many2Many_Bi_Eager](#9_Many2Many_Bi_Eager)          |
| 10  | [Many2Many_Bi_Lazy](#10_Many2Many_Bi_Lazy)           |
| 11  | [Paging_and_Sorting](#11_Paging_and_Sorting)         |
| 12  | [AOP (Aspect Oriented Programming)](#12_AOP)         |
| 13  | [Caching](#13_Caching)         |
| 14  | [Transaction_Management](#14_Transaction_Management) |

###### 1_One2Many_Bi_Eager

<img src="https://img.shields.io/badge/- 1. One2Many_Bi_Eager %20-blue" height=40px>

In the OneToMany examples I define my entities as follows:

- [`UserEntity`](#-) - Is parent Entity
- [`RoleEntity`](#-) - Is Child Entity

### [UserEntity ](#-)

In the Parent Entity I add the :

1. `mappedBy` - need to add the filed name of the cahild entity
2. `cascade`
3. `fetch` - `FetchType.EAGER` , By default in One2Many the Fetch is Lazy
4. `orphanRemoval` - need to set to `true` so we can remove child Entity from Parent Entity (We will see diffrence between `CASCADE.REMOVE` to `orphanRemoval`)
5. Add 2 methods
   - `addRole`
   - `removeRole`

````java
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
        /** role.setUser(null) --> remove the associated object reference , break bi-directional link
	 */
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
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private UserEntity user;

	// Default Constructor , Getters / Setters
	// toString: don't add the associated entity in the toString method
	// This will cause ```ERROR```
````

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

###### 2_One2Many_Bi_Lazy

<img src="https://img.shields.io/badge/- 2. One2Many_Bi_Lazy %20-blue" height=40px>

I use a DTO object to return from Service layer. </br>
I use @Transactionl to keep session open , when doing DELETE or UPDATE actions. </br>
Since it is LAZY loaded , thus Child Entity is not loaded

In the OneToMany examples I define my entities as follows:

- [`UserEntity`](#-) - Is parent Entity
- [`RoleEntity`](#-) - Is Child Entity

### [UserEntity ](#-)

In the Parent Entity I add the :

1. `mappedBy` - need to add the filed name of the cahild entity
2. `cascade`
3. `fetch` - `FetchType.LAZY` , By default in One2Many the Fetch is Lazy
4. `orphanRemoval` - need to set to `true` so we can remove child Entity from Parent Entity (We will see diffrence between `CASCADE.REMOVE` to `orphanRemoval`)
5. Add 2 methods
   - `addRole`
   - `removeRole`

````java
@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
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
        /** role.setUser(null) --> remove the associated object reference , break bi-directional link
	 */
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
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private UserEntity user;

	// Default Constructor , Getters / Setters
	// toString: don't add the associated entity in the toString method
	// This will cause ```ERROR```
````

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

###### 3_One2Many_Uni_Eager

<img src="https://img.shields.io/badge/- 3. One2Many_Uni_Eager%20-blue" height=40px>

with OneToMany Uni-Directional LAZY/EAGER , I have a field `only` in the parent Entity (which is UserEntity). </br>
Child Entity doesn't obtain a parent Entity as afield (Unlike Bi-Direc). </br>

```java
@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
@JoinColumn(name = "user_id")
private Set<RoleEntity> roles;

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
        /** role.setUser(null) --> remove the associated object reference , break bi-directional link
	 */
	// role.setUser(null);
}
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

###### 4_One2Many_Uni_Lazy

<img src="https://img.shields.io/badge/- 4. One2Many_Uni_Lazy %20-blue" height=40px>

with OneToMany Uni-Directional LAZY/EAGER , I have a field `only` in the parent Entity (which is UserEntity). </br>
Child Entity doesn't obtain a parent Entity as afield (Unlike Bi-Direc). </br>
Also I use a DTO object to return from Service layer. </br>
I use @Transactionl to keep session open , when doing DELETE or UPDATE actions. </br>
Since it is LAZY loaded , thus Child Entity is not loaded

```java
@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
@JoinColumn(name = "user_id")
private Set<RoleEntity> roles;

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
        /** role.setUser(null) --> remove the associated object reference , break bi-directional link
	 */
	// role.setUser(null);
}
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

###### 5_One2One_Bi_Eager

<img src="https://img.shields.io/badge/- 5. One2One_Bi_Eager %20-blue" height=40px>

In the OneToOne BI Direc examples I define my entities as follows:

- [`UserEntity`](#-) - Is parent Entity
- [`AddressEntity`](#-) - Is Child Entity

### [UserEntity ](#-)

In the Parent Entity I add the :

1. `mappedBy` - need to add the filed name of the cahild entity
2. `cascade` - W/O `CASCADE.REMOVE` , because I want to be able to remove any side (Parent or child) w/o removeing the corresponding side. (Meaning If I delete Address, I want to keep the User). we use following cascades :

   - `CascadeType.PERSIST`
   - `CascadeType.MERGE`
   - `CascadeType.DETACH`
   - `CascadeType.REFRESH`

3. `fetch` - `FetchType.EAGER` , By default in One2One the Fetch is EAGER
4. `orphanRemoval` - DON'T use this in OneToOne mapping

```java
/**
 * I set Cascade only for MERGE REFRESH DETACH PERSIST
 * W/O REMOVE , because I want to be able to   :
 * 1. Remove Only child Entity w/o removing parent
 * 2. Remove Only Parent Entity w/o removing child
 *
 * Don't add any kind of CASCADE type to the child Entity
 */
@OneToOne(mappedBy = "user", cascade = {
		CascadeType.MERGE,
		CascadeType.REFRESH,
		CascadeType.DETACH,
		CascadeType.PERSIST },
			fetch = FetchType.EAGER)
private AddressEntity address;
```

### [AddressEntity ](#-)

In the Child Entity I add the :

1. `@OneToOne(fetch = FetchType.EAGER)` - need to add the field name of the child entity
2. `cascade` - Don't add any kind of CASCADE type in the CHild Entity
3. `fetch` - `FetchType.EAGER` , By default in One2One the Fetch is EAGER
4. `@JsonIgnore` - must add it to child entity, otherwise we will have a `stack overflow` error. (prevent circular serialization issue)

```java
/**
 * Don't add any kind of CASCADE type in the CHild Entity
 */
@OneToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "user_id")
@JsonIgnore
private UserEntity user;
```

### [Add Address to user](#-)

In this example we define :

- UserEntity is parent
- AddressEntity is child

this is how we add an address to user:

```java
@Override
public UserEntity addAddressToUser(AddressEntity addressEntity, long userId) {

	UserEntity userEntity = userRepository.findUserById(userId);

	if(userEntity == null)
		throw new ResourceNotFoundException("Not found User with id = " + userId);

    // (1)
	userEntity.setAddress(addressEntity);

    // (2)
	addressEntity.setUser(userEntity);

    // (3) save the userEntity via UserRepository
	UserEntity returnedValue = userRepository.save(userEntity);
	return returnedValue;
}
```

### [Remove Address from User](#-)

```java
@Override
public void deleteAddress(long id) {
	/**
	 *  remove the associated object reference
	 *  break bi-directional link
	 */
	AddressEntity addressEntity = addressRepository.findAddressById(id);
	if(addressEntity.getUser() != null) {
		addressEntity.setUser(null);
	}
	// Unlike in deleting deleteUsewr,
	// After I break the link , I also must save before Deleting the user
	// Otherwise : we still have a row of address in DB (Even if the link is broken)
	// Thus must add here this line of : addressRepository.save(addressEntity);
	addressRepository.save(addressEntity);
	addressRepository.delete(addressEntity);
}
```

### [Remove User and Keep Address](#-)

Since It is a Bi-Directional One2One, we can remove the User w/o removing the Address.
As I stated before:

1. We don't use `orphanRemoval`
2. we don't use `CASCADE.REMOVE`

this way we will control the remove of the entities.

```java
@Override
public void deleteUser(long id) {
	/**
	 *  remove the associated object reference
	 *  break bi-directional link
	 */
	UserEntity userEntity = userRepository.findUserById(id);
	AddressEntity addressEntity = userEntity.getAddress();

	if(addressEntity != null) {
		addressEntity.setUser(null);
	}
	userRepository.delete(userEntity);
}
```

### [Tables in DB ](#-)

This is how the Tables in DB looks:

We define the PK in User Table , which is a FK in Address Table.

- User_TB is parent
- Address_TB is child

![image](https://user-images.githubusercontent.com/36256986/199134637-464ae213-61ae-42fa-a06d-bd192f545784.png) ----- ![image](https://user-images.githubusercontent.com/36256986/199134669-56461c1c-e6f7-4c4f-9144-892bcd63c026.png)


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

###### 6_One2One_Bi_Lazy

<img src="https://img.shields.io/badge/- 6. One2One_Bi_Lazy %20-blue" height=40px>

Implementation exact the same as in previous section, Only difference is that now we set FETCH type as [`LAZY`](#-).

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

###### 7_One2One_Uni_Eager

<img src="https://img.shields.io/badge/- 7. One2One_Uni_Eager %20-blue" height=40px>

This implementation of One2One Uni Eager is unique. </br>
In this impl, we set the Primary Key of Parent Entity to be same as Primary key of Child Entity.

In the One2One Uni Direc , Where Both Entities share the same Primary Key , this is how we define our Entity classes.

### [UserEntity ](#-)

UserEntity DOESN'T have a reference to AddressEntity.

```java
@JsonIgnoreProperties({ "hibernateLazyInitializer" })
@Entity
@Table(name = "USERS_TB")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String email;
	private boolean published;
	.
	.
```

### [AddressEntity ](#-)

AddressEntity Has the following Annotations:

1. `@OneToOne(fetch = FetchType.EAGER)`
2. `@MapsId` - Means that the `private Long id` will be set as FK from Users_TB , thus both will have the same ID (`user_id`)
3. `@JoinColumn(name = "user_id")`
4. No Cascading

```java
@Entity
@Table(name = "ADDRESS_TB")
public class AddressEntity {

	@Id
	private Long id;
	private String street;
	private String city;

	@OneToOne(fetch = FetchType.EAGER)
	@MapsId
	@JoinColumn(name = "user_id")
	private UserEntity user;
```

### [Tables in DB ](#-)

This is how the Tables in DB looks:

We define the PK in Address Table to be same PK as in User Table.

- User_TB is parent (JSON response show that Address is Parent and User is Child)
- Address_TB is child

Accoring MySql , this is how tables are created:


```sql
CREATE TABLE `users_tb` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `published` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8

CREATE TABLE `address_tb` (
  `user_id` bigint(20) NOT NULL,
  `city` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `FK7wjfdv2b0wj25g84w0h2og54` FOREIGN KEY (`user_id`) REFERENCES `users_tb` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
```

![image](https://user-images.githubusercontent.com/36256986/199121744-b6c7cf1b-d931-491b-a6bc-ab3f6292044a.png) ----- ![image](https://user-images.githubusercontent.com/36256986/199121805-17c7e651-b31c-49a9-be6c-67a55bc8d81b.png)

The JSON we get once we want to get an address is as follows:

```java
{
  "id": 2,
  "street": "AAAA 15/15",
  "city": "HOLON",
  "user": {
    "id": 2,
    "name": "karin shalem",
    "email": "karin.shalem@gmail.com",
    "published": false
  }
}
```

With this implementation ,we can remove an Address w/o removing a User.
If we remove a User , it will also remove an Address (see code Implementaion).

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

###### 8_One2One_Uni_Lazy

<img src="https://img.shields.io/badge/- 8. One2One Uni Lazy %20-blue" height=40px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

###### 9_Many2Many_Bi_Eager

<img src="https://img.shields.io/badge/- 9. Many2Many Bi Eager %20-blue" height=40px>

There are several possible ways to do the association mapping with Many2Many.

With `One2Many Bi-Direc` , we had the :
* parent entity [User](#-) , since it has the `mappedBy` value , it is also called the `Not Owning side`.
* child entity [Role](#-) , also called the Owning side.

### See link from [Bezkoder](https://www.bezkoder.com/jpa-many-to-many/)  https://www.bezkoder.com/jpa-many-to-many/ 

With [Many2Many Bi-Direc](#-) this is how I prefer to do it:
* [`Student entity`](#-) 
	1. Owning side has the `JoingTable` annotations
	2. has the Helper methods of `addCourse` and `removeCourse`
* [`Course Entity`](#-)
	1. Not owning side 
	2. has the `mappedBy` 


In this Implementation, Student entity is the owner of the relationship and Course entity is the inverse side. </br>
The join table is specified on the owning side (Student) using @JoinTable annotation. </br>
This relationship is bidirectional, the inverse side (Course) must use the mappedBy element to specify the relationship field or property of the owning side.</br>

The owner side is the side which Hibernate looks at to know which association exists. </br>
For example, if you add a Course in the set of courses of a Student, a new row will be inserted by Hibernate in the join table (student_course). </br>
On the contrary, if you add a Student to the set of students of a Course, nothing will be modified in the database. </br>

@JsonIgnore is used to ignore the logical property used in serialization and deserialization.

### [StudentEntity ](#-)

```java
@ManyToMany(fetch = FetchType.EAGER,
		cascade = { 
			CascadeType.PERSIST,
			CascadeType.MERGE})	
@JoinTable(name = "student_course", 
		joinColumns = { @JoinColumn(name = "student_id") }, 
		inverseJoinColumns = { @JoinColumn(name = "course_id") })
@JsonIgnore
private Set<CourseEntity> courses = new HashSet<>();


/**
 * Helper Methods for Adding/Removing Course
 */

public void addCourse(CourseEntity courseEntity) {
	this.courses.add(courseEntity);
	courseEntity.getStudents().add(this);		
}

public void removeCourse(CourseEntity courseEntity) {		
	this.courses.remove(courseEntity);
	courseEntity.getStudents().remove(this);		
}
	
```

### [CourseEntity ](#-)

I don't use CASCADE at the NOT-Owning side.

```java
@ManyToMany(mappedBy = "courses", fetch = FetchType.EAGER,
@JsonIgnore
private Set<StudentEntity> students;
```


The most complicated things are doing the operations of:
* add 
* remove/delete on both Entities. </br>

[Adding](#-):
1. addCourseToStudent - implemented
2. addStudentToCourse - is same as adding Course To Student, thus No need to implement this

[Removing/Deleting from StudentDaoImpl](#-) :
1. deleteStudentByIdentityNumber(int identityNumber)
2. removeAllStudentsFromCourse(String courseNumber) 
3. deleteAllStudents()

[Removing/Deleting from CourseDaoImpl](#-) :
1. deleteCourseByCourseNumber(String courseNumber)
2. removeCourseFromStudentByCourseNumber(int identityNumber, String courseNumber)
3. removeAllCoursesFromStudent(int identityNumber)
4. deleteAllCourses()

See Code implementation inside the project

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

###### 10_Many2Many_Bi_Lazy

<img src="https://img.shields.io/badge/- 10. Many2Many_Bi_Lazy %20-blue" height=40px>

The code from previous section is the same with few adjustments to do:

1. set the `fetch = FetchType.LAZY` on both entities
2. Add `@Transactionl` annotation on the methods that are doing `add/delete/remove` operations (Otherwise I get a "Session Proxy bla bla error" )
3. Note : best approch is to have a DTO layer for my entities CourseDto and StudentDto. But since I have `@JsonIgnore` on both entities I wont get the error below. (this is not best practice , but it save me some time) as said before best Practice is to have `@JsonIgnore` on the owning side , and have a DTO layer.

`"Could not write JSON: failed to lazily initialize a collection of role: com.jpa.many2many.bi.lazy.entity.CourseEntity.students, could not initialize proxy - no Session; nested exception is com.fasterxml.jackson.databind.JsonMappingException: failed to lazily initialize a collection of role: com.jpa.many2many.bi.lazy.entity.CourseEntity.students, could not initialize proxy - no Session (through reference chain: java.util.ArrayList[0]->com.jpa.many2many.bi.lazy.entity.CourseEntity[\"students\"])"`

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

###### 11_Paging_and_Sorting

<img src="https://img.shields.io/badge/- 11. Paging_and_Sorting %20-blue" height=40px>

https://www.youtube.com/watch?v=Wa0GQwWwzJE&ab_channel=JavaTechie

In this project I show how to use Pagination that comes from Server Side.

### [ProductEntity](#-)

```java
@Entity
@Table(name = "PRODUCT_TB")
public class ProductEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private long id;
	private String name;
	private int quantity;
	private long price;
```

### [ProductRepository](#-)

```java
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

	/**
	 * I have in the parameters beside price, also Pageable pageable.
	 * In the ProductDaoImpl we sent an attribute as follows:
	 * 
	 * Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, field));		
	 * Page<ProductEntity> _pageOfProducts = productRepository.findProductsWithPriceLessThan(price, pageable);
	 * 	 
	 * Notice : 
	 * 		I return Page<ProductEntity> and Not a List<ProductEntity> 
	 */
	@Query(value = "SELECT * FROM product_tb p WHERE p.price <= :price", 
			countQuery = "SELECT COUNT(*) FROM product_tb",
			nativeQuery = true)
	Page<ProductEntity> findProductsWithPriceLessThan(@Param("price") long price, Pageable pageable);
}
```

### [ProductDaiImpl](#-)

```java
	@Override
	public List<ProductEntity> getAllProducts() {
		return productRepository.findAll();
	}

	/**
	 * Here I return a Sorted List by the field name
	 * the 'field' can be any of the entity variables: 
	 * 		id, name , quantity, price	
	 */
	@Override
	public List<ProductEntity> findProductWithSorting(String field) {
		List<ProductEntity> _listProductEntities = productRepository.findAll(Sort.by(Sort.Direction.ASC, field));
		return _listProductEntities;
	}
	
	/**
	 * Here I implement pagination , and get a: Limited Number of Courses per PAGE
	 * page: zero-based page index, must NOT be negative. 
	 * size: number of items in a page to be returned, must be greater than 0. 
	 * sort: the Sort object.
	 */
	@Override
	public List<ProductEntity> getProductsByPageAndSize(int page, int size) {
		if (page > 0) {
			page = page - 1;
		}	
		Pageable pageable = PageRequest.of(page, size);
		Page<ProductEntity> _pageOfProducts = productRepository.findAll(pageable);
		List<ProductEntity> _products = _pageOfProducts.getContent();
		return _products;
	}
	
	/**
	 * Here I implement pagination And Sorting
	 * price: Query according Price  
	 * page: page number 
	 * size: number of items  
	 * field : sort by field
	 */
	@Override
	public List<ProductEntity> getProductsWithPriceLessThan(long price, int page, int size, String field) {		
		if (page > 0) {
			page = page - 1;
		}			
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, field));		
		Page<ProductEntity> _pageOfProducts = productRepository.findProductsWithPriceLessThan(price, pageable);		
		List<ProductEntity> _products = _pageOfProducts.getContent();		
		return _products;
	}	
```

### [ApiResponse](#-)

I made this Generic class , in order to be able to return the number (recordCount) of objects per page.

```java
public class ApiResponse<T> {

	private int recordCount;
	private T response;

	// Get/Set
}
```

### [ProductController](#-) 

With the Controller , if we want to sent the page & size we can use 2 different approches:
1. By passing page/size in the `@PathVariable` (But I didn't use this approch)
2. By passing page/size in the `@RequestParam`


```java
@RestController
@CrossOrigin("*")
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductDaoImpl productDaoImpl;

	@GetMapping(path = "/getAllProducts", produces = { 
			MediaType.APPLICATION_JSON_VALUE, 
			MediaType.APPLICATION_XML_VALUE })
	public ApiResponse<List<ProductEntity>> getCourses() {
		
		List<ProductEntity> _products = productDaoImpl.getAllProducts();
		
		return new ApiResponse<List<ProductEntity>>(_products.size(), _products);
	}
	
	/**
	 * the 'field' can be any of the entity variables:
	 * id, name , quantity, price
	 */
	@GetMapping(path = "/getProductsWithSorting/{field}", produces =  MediaType.APPLICATION_JSON_VALUE)
	public ApiResponse<List<ProductEntity>> findProductWithSorting(@PathVariable("field") String field) {
		
		List<ProductEntity> _productWithSorting = productDaoImpl.findProductWithSorting(field);		
		return new ApiResponse<List<ProductEntity>>(_productWithSorting.size(), _productWithSorting);
	}
	
	
	@GetMapping(path = "/getProductsByPagination", produces = { 
			MediaType.APPLICATION_JSON_VALUE, 
			MediaType.APPLICATION_XML_VALUE })
	public ApiResponse<List<ProductEntity>> getProductsByPageAndSize(
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "25") int size) {
		
		List<ProductEntity> _products = productDaoImpl.getProductsByPageAndSize(page, size);		
		return new ApiResponse<List<ProductEntity>>(_products.size(), _products);
	}
		  
	
	@GetMapping(path = "/getProductsWithPriceLessThan/{price}/{field}", produces = { 
			MediaType.APPLICATION_JSON_VALUE, 
			MediaType.APPLICATION_XML_VALUE })
	public ApiResponse<List<ProductEntity>> getProductsWithPriceLessThan(
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "25") int size,
			@PathVariable("price") long price,
			@PathVariable("field") String field) {
		
		List<ProductEntity> _products = productDaoImpl.getProductsWithPriceLessThan(price, page, size ,field);
		return new ApiResponse<List<ProductEntity>>(_products.size(), _products);
	}	
}
```


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

###### 12_AOP

<img src="https://img.shields.io/badge/- 12. AOP (Aspect Oriented Programming) %20-blue" height=40px>

See AOP READ.MD file [AOP](https://github.com/sshalem/Spring-Boot/tree/main/02-JPA/12-AOP)

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

###### 13_Caching

<img src="https://img.shields.io/badge/- 13. Caching %20-blue" height=40px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

###### 14_Transaction_Management

<img src="https://img.shields.io/badge/- 14. Transaction_Management %20-blue" height=40px>

* https://medium.com/javarevisited/spring-transactional-mistakes-everyone-did-31418e5a6d6b
* https://www.concretepage.com/spring/spring-transactional

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

######

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

######

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---
