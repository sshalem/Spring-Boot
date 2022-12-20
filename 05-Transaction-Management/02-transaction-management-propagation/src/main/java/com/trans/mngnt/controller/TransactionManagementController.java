package com.trans.mngnt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trans.mngnt.dto.OrganizationDto;
import com.trans.mngnt.entity.Employee;
import com.trans.mngnt.entity.EmployeeHealthInsurance;
import com.trans.mngnt.service.impl.EmployeeServiceImpl;
import com.trans.mngnt.service.impl.OrganzationServiceImpl;

@RestController
@RequestMapping("/transaction-management")
public class TransactionManagementController {

	@Autowired
	private OrganzationServiceImpl organzationServiceImpl;

	@Autowired
	private EmployeeServiceImpl employeeServiceImpl;

	@PostMapping(path = "/joinOrganization")
	public String joinOrganization(@RequestBody OrganizationDto organizationDto) {

		Employee emp = organizationDto.getEmployee();
		EmployeeHealthInsurance employeeHealthInsurance = organizationDto.getEmployeeHealthInsurance();
		organzationServiceImpl.joinOrganization(emp, employeeHealthInsurance);
		return "Testing Transaction Management";
	}

	@PostMapping(path = "/addEmployee")
	public String addEmployee(@RequestBody Employee employee) {

		employeeServiceImpl.addEmployee(employee);
		return "Testing Transaction Management";
	}
}
