package com.trans.mngnt.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trans.mngnt.entity.Employee;
import com.trans.mngnt.entity.EmployeeHealthInsurance;
import com.trans.mngnt.service.EmployeeService;
import com.trans.mngnt.service.HealthInsuranceService;
import com.trans.mngnt.service.OrganizationService;

@Service
public class OrganzationServiceImpl implements OrganizationService {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private HealthInsuranceService healthInsuranceService;

	
	@Override
	@Transactional
	public void joinOrganization(Employee employee, EmployeeHealthInsurance employeeHealthInsurance) {
		
		// Proxy begin Transaction Statement
		Employee _employee = employeeService.addEmployee(employee);
		
		if (_employee.getEmpName().equals("shabtay")) {
			throw new RuntimeException("throwing exception to test transaction rollback");
		}
				
		employeeHealthInsurance.setEmpId(_employee.getEmpId());
		healthInsuranceService.registerEmployeeHealthInsurance(employeeHealthInsurance);
		
		// commit Transaction
	}

	@Override
	public void leaveOrganization(Employee employee, EmployeeHealthInsurance employeeHealthInsurance) {
		employeeService.deleteEmpolyee(employee.getEmpId());
		healthInsuranceService.deleteEmployeeHealthInsuranceById(employeeHealthInsurance.getEmpId());
	}
}
