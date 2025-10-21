package com.trans.mngnt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employeeHealthInsurance")
public class EmployeeHealthInsurance {

	/**
	 * I DON'T add the GeneratedValue Because 
	 * i want to be able to setEmpId() by myself
	 */
	@Id
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

	@Override
	public String toString() {
		return "EmployeeHealthInsurance [empId=" + empId + ", healthInsuranceSchemeName=" + healthInsuranceSchemeName
				+ ", coverageAmount=" + coverageAmount + "]";
	}

}
