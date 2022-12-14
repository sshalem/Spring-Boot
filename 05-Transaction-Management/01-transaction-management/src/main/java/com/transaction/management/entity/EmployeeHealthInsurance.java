package com.transaction.management.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "EmployeeHealthInsurance")
public class EmployeeHealthInsurance {

	private long empId;
	private String healthInsuranceSchemeName;
	private int coverageAmount;

	public EmployeeHealthInsurance() {
		super();
	}

	public long getEmpId() {
		return empId;
	}

	public void setEmpId(long empId) {
		this.empId = empId;
	}

	public String getHealthInsuranceSchemeName() {
		return healthInsuranceSchemeName;
	}

	public void setHealthInsuranceSchemeName(String healthInsuranceSchemeName) {
		this.healthInsuranceSchemeName = healthInsuranceSchemeName;
	}

	public int getCoverageAmount() {
		return coverageAmount;
	}

	public void setCoverageAmount(int coverageAmount) {
		this.coverageAmount = coverageAmount;
	}

}
