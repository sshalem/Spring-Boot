<img src="https://img.shields.io/badge/-JPA Mapping%20-blue" height=70px>

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
| 11  | [Transaction_Management](#12_Transaction_Management) |

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
