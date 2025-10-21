package com.trans.mngnt.exception;

public class InvalidInsuranceAmountException extends Exception {

	private static final long serialVersionUID = 5510944707529735023L;

	public InvalidInsuranceAmountException(String msg) {
		super(msg);
	}
}
