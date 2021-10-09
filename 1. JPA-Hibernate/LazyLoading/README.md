# Lazy Loading 

* this is an  example

- In this exapmle I use "Lazy Loading" with @OneToMany and @ManyToOne mapping.
- few comments to pay attention to:

1. I add the "fetch = FetchType.LAZY" in both sides 
	- at the @OneToMany side and at the @ManyToOne

```java
@Entity
@Table(name = "customer")
public class Customer {

	...
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<PhoneNumber> phoneNumbers = new HashSet<>();
	
	
@Entity
@Table(name = "phoneNumber")
public class PhoneNumber {

	...
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	@JsonIgnore
	private Customer customer;
```

2. In application.properties file I define the property of:
	- spring.jpa.open-in-view=false

 Becuase of following error:
          WARN 13496 --- [  restartedMain] JpaBaseConfiguration$JpaWebConfiguration : spring.jpa.open-in-view is enabled by default. 
          Therefore, database queries may be performed during view rendering. 
          Explicitly configure spring.jpa.open-in-view to disable this warning
 BUT,
 	when using this parameter as set to false, and we are using FetchType.LAZY, it will throw Lazy fetch error
 	So , 2 options are :
  1. No to add it at all (this way we still see the warning but it won't affect the lazy loading
  2. add this : 'spring.jpa.open-in-view=true'  and set it to true, 
	  this will make  the warining disappear and lazy loading still works as expected 
 Note:
 accordint the following link: 
 	[spring.jpa.open-in-view=true](https://stackoverflow.com/questions/30549489/what-is-this-spring-jpa-open-in-view-true-property-in-spring-boot)
 	Unfortunately, OSIV (Open Session in View) 
   is enabled by default in Spring Boot, and OSIV is really a bad idea from a performance and scalability perspective.

	So, make sure that in the application.properties configuration file, you have the following entry:
		spring.jpa.open-in-view=false
	This will disable OSIV so that you can handle the LazyInitializationException the right way.
	Anyway, DO NOT use the following Anti-Patterns as suggested by some of the answers:
		Open Session in View (OSIV)
 		hibernate.enable_lazy_load_no_trans
	Sometimes, a DTO projection is a better choice than fetching entities, and this way, you won't get any LazyInitializationException.

 Question: 
		How to handle the LazyInitializationException the right way?
 Answer:
		see in the link [handle LazyInitializationException](https://www.youtube.com/watch?v=6p-fuwVxryg&ab_channel=ThorbenJanssen)

 Since I'm using JPQL, 
 better use JOIN FETCH is the easiest way in the CustomerRepository 

 ```java
	@Query("SELECT c FROM Customer c JOIN FETCH c.phoneNumbers")
	Customer findWithJoinFetchFirstName(String firstname);
  ```
  
  And in the RestController :
  
```java
 	@GetMapping(path = "/Join-Fetch/{firstname}")
	public Customer getCustomerWithJoinFetchFirstname(@PathVariable("firstname") String firstname) {
		System.out.println();
		LOGGER.info("invoke --> getCustomerWithJoinFetchFirstname");
		return customerDaoImpl.findCustomerWithJoinFetchFirstName(firstname);
	}
```


# link example
1. [israel hayom](https://www.israelhayom.co.il/)
2. [hidavroot](https://www.hidabroot.org/)

# List example
1. item one
2. item two
3. item three

# Blockquotes :
> Dorothy followed her through many of the beautiful rooms in her castle.


### Blockquotes with Other Elements  :
> ### The quarterly results look great!
>
> - Revenue was off the chart.
> - Profits were higher than ever.
>
>  *Everything* is going according to **plan**.

### Blockquotes another example:
*   This is the first list item.
*   Here's the second list item.

    > A blockquote would look great below the second list item.

*   And here's the third list item.


# Code Blocks
To create code blocks, indent every line of the block by at least four spaces or one tab.

    <html>
      <head>
      </head>
    </html>

### Code Blocks
Code blocks are normally indented four spaces or one tab. When they’re in a list, indent them eight spaces or two tabs.

1.  Open the file.
2.  Find the following code block on line 21:

        <html>
          <head>
            <title>Test</title>
          </head>
		  

```
code fences
```



# checkbox

- [ ] Checkbox off
- [x] Checkbox on

# Add Inline HTML

<dl>
  <dt>Definition list</dt>
  <dd>Is something people use sometimes.</dd>

  <dt>Markdown in HTML</dt>
  <dd>Does *not* work **very** well. Use HTML <em>tags</em>.</dd>
</dl>

# Add Tables
Colons can be used to align columns.

| Tables        | Are           | Cool  |
| ------------- |:-------------:| -----:|
| col 3 is      | right-aligned | $1600 |
| col 2 is      | centered      |   $12 |
| zebra stripes | are neat      |    $1 |

There must be at least 3 dashes separating each header cell.
The outer pipes (|) are optional, and you don't need to make the 
raw Markdown line up prettily. You can also use inline Markdown.

Markdown | Less | Pretty
--- | --- | ---
*Still* | `renders` | **nicely**
1 | 2 | 3

# Add Image
![Image](https://images.unsplash.com/photo-1528132032628-89493baa1e29?ixid=MXwxMjA3fDB8MHxzZWFyY2h8M3x8Z3JlYXR8ZW58MHx8MHw%3D&ixlib=rb-1.2.1&w=1000&q=80)

