package com.lazy.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lazy.entity.Customer;
import com.lazy.entity.PhoneNumber;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Customer findByfirstName(String firstname);

	@Query("SELECT c FROM Customer c JOIN FETCH c.phoneNumbers WHERE c.firstName=:firstName")
	Customer findWithJoinFetchFirstName(@Param("firstName") String firstName);

	@Query("SELECT cp FROM Customer cust JOIN cust.phoneNumbers AS cp WHERE cust.id=:id")
	Set<PhoneNumber> getAllPhonesPerCustomer(@Param("id") long customerId);

	@Query("SELECT c FROM Customer c")
	Set<Customer> findAllCustomers();

}
