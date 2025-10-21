package com.trans.mngnt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trans.mngnt.entity.EmployeeHealthInsurance;
import com.trans.mngnt.repository.HealthInsuraceRepository;
import com.trans.mngnt.service.HealthInsuranceService;

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
