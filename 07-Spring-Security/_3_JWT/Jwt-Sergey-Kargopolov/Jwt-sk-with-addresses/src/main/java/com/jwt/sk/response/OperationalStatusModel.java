package com.jwt.sk.response;

public class OperationalStatusModel {

	private String operationResult;
	private String operationName;

	public OperationalStatusModel() {
		super();
	}

	public String getOperationResult() {
		return operationResult;
	}

	public void setOperationResult(String operationResult) {
		this.operationResult = operationResult;
	}

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

}
