package com.trans.mngnt.service;

import com.trans.mngnt.entity.Employee;
import com.trans.mngnt.entity.EmployeeHealthInsurance;
import com.trans.mngnt.exception.InvalidInsuranceAmountException;

public interface OrganizationService {

	void joinOrganization(Employee employee, EmployeeHealthInsurance employeeHealthInsurance) throws InvalidInsuranceAmountException;

	void leaveOrganization(Employee employee, EmployeeHealthInsurance employeeHealthInsurance);
}
