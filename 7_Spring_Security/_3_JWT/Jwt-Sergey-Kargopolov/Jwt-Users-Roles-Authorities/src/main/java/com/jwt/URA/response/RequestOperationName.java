package com.jwt.URA.response;

public enum RequestOperationName {

	DELETE("DELETE");

	private String OperationRequest;

	RequestOperationName(String string) {
		this.OperationRequest = string;
	}

	public String getOperationRequest() {
		return OperationRequest;
	}

}
