package com.transaction.management.service;

import com.transaction.management.entity.EmployeeHealthInsurance;

public interface HealthInsuranceService {

	void registerEmployeeHealthInsurance(EmployeeHealthInsurance employeeHealthInsurance);

	void deleteEmployeeHealthInsuranceById(long empid);
}
