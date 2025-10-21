package com.trans.mngnt.service;

import com.trans.mngnt.entity.EmployeeHealthInsurance;
import com.trans.mngnt.exception.InvalidInsuranceAmountException;

public interface HealthInsuranceService {

	void registerEmployeeHealthInsurance(EmployeeHealthInsurance employeeHealthInsurance) throws InvalidInsuranceAmountException;
	
	void deleteEmployeeHealthInsuranceById(long empid);
}
