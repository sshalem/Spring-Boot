package com.trans.mngnt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trans.mngnt.dto.OrganizationDto;
import com.trans.mngnt.entity.Employee;
import com.trans.mngnt.entity.EmployeeHealthInsurance;
import com.trans.mngnt.exception.InvalidInsuranceAmountException;
import com.trans.mngnt.service.impl.EmployeeServiceImpl;
import com.trans.mngnt.service.impl.OrganzationServiceImpl;

@RestController
@RequestMapping("/transaction-management")
public class TransactionManagementController {

	private static Logger LOGGER = LoggerFactory.getLogger(OrganzationServiceImpl.class);

	@Autowired
	private OrganzationServiceImpl organzationServiceImpl;

	@Autowired
	private EmployeeServiceImpl employeeServiceImpl;

	@PostMapping(path = "/joinOrganization")
	public String joinOrganization(@RequestBody OrganizationDto organizationDto) throws InvalidInsuranceAmountException {

		Employee emp = organizationDto.getEmployee();
		EmployeeHealthInsurance employeeHealthInsurance = organizationDto.getEmployeeHealthInsurance();
		LOGGER.info(" --> invoke organzationServiceImpl.joinOrganization(emp, employeeHealthInsurance)");
		organzationServiceImpl.joinOrganization(emp, employeeHealthInsurance);
		return "joinOrganization successful";
	}

	@PostMapping(path = "/addEmployee")
	public String addEmployee(@RequestBody Employee employee) {
		LOGGER.info(" --> invoke employeeServiceImpl.addEmployee(employee)");
		employeeServiceImpl.addEmployee(employee);
		return "add Employee successful";
	}
}
