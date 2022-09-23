package com.jwt.sk.response;

public enum RequestOperationStatus {

	ERROR("ERROR during operation"), 
	SUCCESS("Operation successfully perfromed");

	private String status;

	private RequestOperationStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

}
