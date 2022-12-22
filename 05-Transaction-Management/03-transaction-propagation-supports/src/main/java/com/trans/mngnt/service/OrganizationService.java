package com.trans.mngnt.service;

import com.trans.mngnt.entity.Employee;
import com.trans.mngnt.entity.EmployeeHealthInsurance;

public interface OrganizationService {

	void joinOrganization(Employee employee, EmployeeHealthInsurance employeeHealthInsurance);

	void leaveOrganization(Employee employee, EmployeeHealthInsurance employeeHealthInsurance);

}
