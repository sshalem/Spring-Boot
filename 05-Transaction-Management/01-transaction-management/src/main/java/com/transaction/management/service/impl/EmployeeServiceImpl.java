package com.transaction.management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transaction.management.entity.Employee;
import com.transaction.management.repository.EmployeeRepository;
import com.transaction.management.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee addEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public void deleteEmpolyee(long empid) {
		employeeRepository.deleteById(empid);
	}
}
