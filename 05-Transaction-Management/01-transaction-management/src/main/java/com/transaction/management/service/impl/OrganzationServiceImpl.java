package com.transaction.management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transaction.management.entity.Employee;
import com.transaction.management.entity.EmployeeHealthInsurance;
import com.transaction.management.service.EmployeeService;
import com.transaction.management.service.HealthInsuranceService;
import com.transaction.management.service.OrganizationService;

@Service
public class OrganzationServiceImpl implements OrganizationService {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private HealthInsuranceService healthInsuranceService;

	@Override
	public void joinOrganization(Employee employee, EmployeeHealthInsurance employeeHealthInsurance) {
		
		Employee _employee = employeeService.addEmployee(employee);
		
		if (employeeHealthInsurance.getCoverageAmount() < 10000) {
			throw new RuntimeException("throwing exception to test transaction rollback");
		}
				
		employeeHealthInsurance.setEmpId(_employee.getEmpId());
		healthInsuranceService.registerEmployeeHealthInsurance(employeeHealthInsurance);
	}

	@Override
	public void leaveOrganization(Employee employee, EmployeeHealthInsurance employeeHealthInsurance) {
		employeeService.deleteEmpolyee(employee.getEmpId());
		healthInsuranceService.deleteEmployeeHealthInsuranceById(employeeHealthInsurance.getEmpId());
	}
}
