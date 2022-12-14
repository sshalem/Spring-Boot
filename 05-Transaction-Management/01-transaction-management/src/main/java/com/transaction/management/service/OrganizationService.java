package com.transaction.management.service;

import com.transaction.management.entity.Employee;
import com.transaction.management.entity.EmployeeHealthInsurance;

public interface OrganizationService {

	void joinOrganization(Employee employee, EmployeeHealthInsurance employeeHealthInsurance);

	void leaveOrganization(Employee employee, EmployeeHealthInsurance employeeHealthInsurance);

}
