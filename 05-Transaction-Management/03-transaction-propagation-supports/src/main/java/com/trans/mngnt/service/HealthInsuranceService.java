package com.trans.mngnt.service;

import com.trans.mngnt.entity.EmployeeHealthInsurance;

public interface HealthInsuranceService {

	void registerEmployeeHealthInsurance(EmployeeHealthInsurance employeeHealthInsurance);

	void deleteEmployeeHealthInsuranceById(long empid);
}
