package com.lazy.web;

import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lazy.dao.CustomerDaoImpl;
import com.lazy.dto.CustomerDto;
import com.lazy.entity.Customer;
import com.lazy.entity.PhoneNumber;

@RestController
@RequestMapping("/customer")
@Transactional
public class CustomerRestController {

	private static Logger LOGGER = LoggerFactory.getLogger(CustomerRestController.class);

	@Autowired
	private CustomerDaoImpl customerDaoImpl;

	@GetMapping(path = "/id/{id}")
	public CustomerDto getCustomerById(@PathVariable("id") long id) {
		System.out.println();
		LOGGER.info("invoke --> getCustomerById");
		Customer customer = customerDaoImpl.findById(id);
		return new CustomerDto(customer.getFirstName(), customer.getLastName());
	}

	@GetMapping(path = "/{firstname}")
	public CustomerDto getCustomerByFirstname(@PathVariable("firstname") String firstname) {
		System.out.println();
		LOGGER.info("invoke --> getCustomerByFirstname : returning a CustomerDto object");
		Customer customer = customerDaoImpl.findCustomerByFirstName(firstname);
		return new CustomerDto(customer.getFirstName(), customer.getLastName());

		/**
		 * If I return the code below: 
		 * return customerDaoImpl.findCustomerByFirstName(firstname); 
		 * It will give error:
		 * "Could not write JSON: failed to lazily initialize a collection of role:
		 * com.lazy.entity.Customer.phoneNumbers, could not initialize proxy - no Session;
		 * Thus need to convert the data and return a CustomerDto object
		 */
	}

	@GetMapping(path = "/Join-Fetch/{firstname}")
	public Customer getCustomerWithJoinFetchFirstname(@PathVariable("firstname") String firstname) {
		System.out.println();
		LOGGER.info("invoke --> getCustomerWithJoinFetchFirstname");		
		return customerDaoImpl.findCustomerWithJoinFetchFirstName(firstname);
	}

	@PostMapping("/create")
	public void createCustomer(@RequestBody Customer customer) {
		System.out.println();
		customerDaoImpl.createCustomer(customer);
	}

//  If I want the POST method of 'addPhoneNumberToCustomer' to work ,
//	and since I'm using JPQL, there are 2 options:
//	1. call the method that use JOIN FETCH (is the easiest way) from the CustomerRepository 
//	    and it won't cause 'LazyInitializationException', 
//		customerDaoImpl.findCustomerWithJoinFetchFirstName(firstname);
//	2. add @Transactional annotation at the top of the class , now I can use the the method of:
//		customerDaoImpl.findCustomerByFirstName(firstname);	
//	    I can use the method of findCustomerByFirstName(firstname) :
//			only when @Transactional annotation is added otherwise I will get an error.
	@PostMapping("/phoneNumber/{firstname}")
	public void addPhoneNumberToCustomer(@RequestBody PhoneNumber phoneNumber,
			@PathVariable("firstname") String firstname) {
		System.out.println();
		LOGGER.info("invoke -->  addPhoneNumberToCustomer");
		Customer customer = customerDaoImpl.findCustomerByFirstName(firstname);
		customer.addPhoneNumber(phoneNumber);	
		customerDaoImpl.updateCustomer(customer);		
	}

	@GetMapping("/getAll")
	public Set<CustomerDto> getAllCustomers() {
		System.out.println();
		LOGGER.info("invoke -->  getAllCustomers");
		Set<Customer> customers = customerDaoImpl.getAllCustomers();

		Set<CustomerDto> customersDto = 
				customers
					.stream()
					.map(customer -> {
						return new CustomerDto(customer.getFirstName(), customer.getLastName());
					})
					.collect(Collectors.toSet());
		return customersDto;
	}

}
