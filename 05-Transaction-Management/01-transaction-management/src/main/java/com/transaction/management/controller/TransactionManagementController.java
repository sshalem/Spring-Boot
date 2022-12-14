package com.transaction.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transaction.management.entity.Employee;
import com.transaction.management.entity.EmployeeHealthInsurance;
import com.transaction.management.service.impl.OrganzationServiceImpl;

@RestController
@RequestMapping("/transaction-management")
public class TransactionManagementController {

	@Autowired
	private OrganzationServiceImpl organzationServiceImpl;

	@GetMapping(path = "/joinOrganization")
	public String joinOrganization() {

		Employee emp = new Employee();
		emp.setEmpId(10);
		emp.setEmpName("shabtay");

		EmployeeHealthInsurance employeeHealthInsurance = new EmployeeHealthInsurance();
		employeeHealthInsurance.setEmpId(10);
		employeeHealthInsurance.setHealthInsuranceSchemeName("Yashir");
		employeeHealthInsurance.setCoverageAmount(20000);

		organzationServiceImpl.joinOrganization(emp, employeeHealthInsurance);
		return "Testing Transaction Management";
	}
}
