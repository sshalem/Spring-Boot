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
import com.trans.mngnt.service.impl.OrganzationServiceImpl;

@RestController
@RequestMapping("/transaction-management")
public class TransactionManagementController {

	private static Logger LOGGER = LoggerFactory.getLogger(TransactionManagementController.class);

	@Autowired
	private OrganzationServiceImpl organzationServiceImpl;

	@PostMapping(path = "/joinOrganization")
	public String joinOrganization(@RequestBody OrganizationDto organizationDto) {

		LOGGER.info("joinOrganization");

		Employee emp = organizationDto.getEmployee();
		EmployeeHealthInsurance employeeHealthInsurance = organizationDto.getEmployeeHealthInsurance();
		organzationServiceImpl.joinOrganization(emp, employeeHealthInsurance);
		return "joinOrganization successful";
	}

}
