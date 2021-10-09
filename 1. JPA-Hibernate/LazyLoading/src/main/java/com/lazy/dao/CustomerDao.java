package com.lazy.dao;

import java.util.Set;

import com.lazy.entity.Customer;

public interface CustomerDao {

	// Create
	void createCustomer(Customer customer);

	// Read
	Customer findById(long id);

	// Read
	Customer findCustomerByFirstName(String firstName);

	// Read
	Customer findCustomerWithJoinFetchFirstName(String firstName);

	// Update
	Customer updateCustomer(Customer customer);

	// Delete
	void deleteCusotmer(Customer customer);

	Set<Customer> getAllCustomers();

}
