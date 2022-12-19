package com.transaction.management.controller;

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

		Employee emp = organizationDto.getEmployee();
		EmployeeHealthInsurance employeeHealthInsurance = organizationDto.getEmployeeHealthInsurance();
		organzationServiceImpl.joinOrganization(emp, employeeHealthInsurance);
		return "Testing Transaction Management";
	}
}
