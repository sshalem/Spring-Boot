package com.lazy.dao;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lazy.entity.Customer;
import com.lazy.repository.CustomerRepository;

@Service
@Transactional
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	private CustomerRepository customerRepo;

	@Override
	public void createCustomer(Customer customer) {
		customerRepo.save(customer);
	}

	@Override
	public Customer findById(long id) {
		return customerRepo.findById(id).get();
	}

	@Override
	public Customer findCustomerByFirstName(String firstName) {
		return customerRepo.findByfirstName(firstName);
	}

	@Override
	public Customer findCustomerWithJoinFetchFirstName(String firstName) {
		return customerRepo.findWithJoinFetchFirstName(firstName);
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		return customerRepo.save(customer);
	}

	@Override
	public void deleteCusotmer(Customer customer) {
		customerRepo.delete(customer);
	}

	@Override
	public Set<Customer> getAllCustomers() {
		return customerRepo.findAllCustomers();
	}

}
