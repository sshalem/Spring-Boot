package com.trans.mngnt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.trans.mngnt.entity.Employee;
import com.trans.mngnt.repository.EmployeeRepository;
import com.trans.mngnt.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Employee addEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public void deleteEmpolyee(long empid) {
		employeeRepository.deleteById(empid);
	}
}
