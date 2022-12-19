package com.transaction.management.dto;

import com.transaction.management.entity.Employee;
import com.transaction.management.entity.EmployeeHealthInsurance;

public class OrganizationDto {

	private Employee employee;
	private EmployeeHealthInsurance employeeHealthInsurance;

	public OrganizationDto() {
		super();
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public EmployeeHealthInsurance getEmployeeHealthInsurance() {
		return employeeHealthInsurance;
	}

	public void setEmployeeHealthInsurance(EmployeeHealthInsurance employeeHealthInsurance) {
		this.employeeHealthInsurance = employeeHealthInsurance;
	}

	@Override
	public String toString() {
		return "OrganizationDto [employee=" + employee + ", employeeHealthInsurance=" + employeeHealthInsurance + "]";
	}

}
