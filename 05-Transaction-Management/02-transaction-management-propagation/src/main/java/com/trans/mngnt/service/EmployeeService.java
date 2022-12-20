package com.trans.mngnt.service;

import com.trans.mngnt.entity.Employee;

public interface EmployeeService {

	Employee addEmployee(Employee employee);

	void deleteEmpolyee(long empid);
}
