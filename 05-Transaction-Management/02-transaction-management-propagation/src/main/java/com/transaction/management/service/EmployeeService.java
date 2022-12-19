package com.transaction.management.service;

import com.transaction.management.entity.Employee;

public interface EmployeeService {

	Employee addEmployee(Employee employee);

	void deleteEmpolyee(long empid);
}
