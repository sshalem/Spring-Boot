package com.trans.mngnt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trans.mngnt.entity.EmployeeHealthInsurance;
import com.trans.mngnt.exception.InvalidInsuranceAmountException;
import com.trans.mngnt.repository.HealthInsuraceRepository;
import com.trans.mngnt.service.HealthInsuranceService;

@Service
public class HealthInsuranceServiceImpl implements HealthInsuranceService {

	@Autowired
	private HealthInsuraceRepository healthInsuraceRepository;

	@Override
	@Transactional
	public void registerEmployeeHealthInsurance(EmployeeHealthInsurance employeeHealthInsurance) throws InvalidInsuranceAmountException {
		
		if (employeeHealthInsurance.getCoverageAmount() < 0) {
			throw new InvalidInsuranceAmountException("Coverage Amount Should not be negative");
		}		
		healthInsuraceRepository.save(employeeHealthInsurance);
	}

	@Override
	public void deleteEmployeeHealthInsuranceById(long empid) {
		healthInsuraceRepository.deleteById(empid);
	}
}
