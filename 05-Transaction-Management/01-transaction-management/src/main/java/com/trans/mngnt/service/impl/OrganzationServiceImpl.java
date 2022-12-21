package com.trans.mngnt.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trans.mngnt.controller.TransactionManagementController;
import com.trans.mngnt.entity.Employee;
import com.trans.mngnt.entity.EmployeeHealthInsurance;
import com.trans.mngnt.service.EmployeeService;
import com.trans.mngnt.service.HealthInsuranceService;
import com.trans.mngnt.service.OrganizationService;

@Service
public class OrganzationServiceImpl implements OrganizationService {

	private static Logger LOGGER = LoggerFactory.getLogger(OrganzationServiceImpl.class); 
	
	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private HealthInsuranceService healthInsuranceService;

	@Override
	@Transactional
	public void joinOrganization(Employee employee, EmployeeHealthInsurance employeeHealthInsurance) {

		LOGGER.info("---> employeeService.addEmployee(employee)");
		// Proxy begin Transaction Statement
		Employee _employee = employeeService.addEmployee(employee);

//		if (_employee.getEmpName().equals("shabtay")) {
//			throw new RuntimeException("throwing exception to test transaction rollback");
//		}

		employeeHealthInsurance.setEmpId(_employee.getEmpId());
		
		LOGGER.info("---> healthInsuranceService.registerEmployeeHealthInsurance(employeeHealthInsurance)");
		healthInsuranceService.registerEmployeeHealthInsurance(employeeHealthInsurance);

		// commit Transaction
	}

	@Override
	public void leaveOrganization(Employee employee, EmployeeHealthInsurance employeeHealthInsurance) {
		employeeService.deleteEmpolyee(employee.getEmpId());
		healthInsuranceService.deleteEmployeeHealthInsuranceById(employeeHealthInsurance.getEmpId());
	}
}
