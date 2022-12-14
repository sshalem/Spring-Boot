package com.transaction.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transaction.management.entity.Employee;
import com.transaction.management.entity.EmployeeHealthInsurance;

@Service
public class OrganzationServiceImpl implements OrganizationService {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private HealthInsuranceService healthInsuranceService;

	@Override
	public void joinOrganization(Employee employee, EmployeeHealthInsurance employeeHealthInsurance) {
		employeeService.addEmployee(employee);
		healthInsuranceService.registerEmployeeHealthInsurance(employeeHealthInsurance);
	}

	@Override
	public void leaveOrganization(Employee employee, EmployeeHealthInsurance employeeHealthInsurance) {
		employeeService.deleteEmpolyee(employee.getEmpId());
		healthInsuranceService.deleteEmployeeHealthInsuranceById(employeeHealthInsurance.getEmpId());
	}

}
