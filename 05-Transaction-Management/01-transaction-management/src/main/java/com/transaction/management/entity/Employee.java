package com.transaction.management.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Employee")
public class Employee {

	private long empId;
	private String empName;

	public Employee() {
		super();
	}

	public long getEmpId() {
		return empId;
	}

	public void setEmpId(long empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

}
