package com.transaction.management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transaction.management.entity.EmployeeHealthInsurance;
import com.transaction.management.repository.HealthInsuraceRepository;
import com.transaction.management.service.HealthInsuranceService;

@Service
public class HealthInsuranceServiceImpl implements HealthInsuranceService {

	@Autowired
	private HealthInsuraceRepository healthInsuraceRepository;

	@Override
	public void registerEmployeeHealthInsurance(EmployeeHealthInsurance employeeHealthInsurance) {
		healthInsuraceRepository.save(employeeHealthInsurance);
	}

	@Override
	public void deleteEmployeeHealthInsuranceById(long empid) {
		healthInsuraceRepository.deleteById(empid);
	}

}
