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

######

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

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

######

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

######

<img src="https://img.shields.io/badge/- X %20-blue" height=40px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---
