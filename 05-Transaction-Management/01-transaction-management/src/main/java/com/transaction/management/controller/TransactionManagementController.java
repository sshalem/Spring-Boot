package com.transaction.management.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transaction.management.dto.OrganizationDto;
import com.transaction.management.entity.Employee;
import com.transaction.management.entity.EmployeeHealthInsurance;
import com.transaction.management.service.impl.OrganzationServiceImpl;

@RestController
@RequestMapping("/transaction-management")
public class TransactionManagementController {

	@Autowired
	private OrganzationServiceImpl organzationServiceImpl;

	@PostMapping(path = "/joinOrganization")
	public String joinOrganization(@RequestBody OrganizationDto organizationDto) {

		Employee emp = new Employee();
		emp.setEmpId(10);
		emp.setEmpName("shabtay");

		EmployeeHealthInsurance employeeHealthInsurance = new EmployeeHealthInsurance();
		employeeHealthInsurance.setEmpId(10);
		employeeHealthInsurance.setHealthInsuranceSchemeName("Yashir");
		employeeHealthInsurance.setCoverageAmount(20000);

		organzationServiceImpl.joinOrganization(emp, employeeHealthInsurance);

		
//		Random random = new Random();
//		
//		long randomEmpId = 1000 + random.nextInt(1000);  
//		
//		Employee emp = organizationDto.getEmployee();		
//		emp.setEmpId(randomEmpId);
//		
//		EmployeeHealthInsurance employeeHealthInsurance = organizationDto.getEmployeeHealthInsurance();
//		employeeHealthInsurance.setEmpId(randomEmpId);
//		
//		organzationServiceImpl.joinOrganization(emp, employeeHealthInsurance);
		return "Testing Transaction Management";
	}
}
