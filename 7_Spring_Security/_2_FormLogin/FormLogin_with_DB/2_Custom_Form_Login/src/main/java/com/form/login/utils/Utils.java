package com.form.login.utils;

import org.springframework.stereotype.Component;

@Component
public class Utils {

	public long generateId() {

		// This generates a random number of 10 digits
		long theRandomNum = (long) (Math.random() * Math.pow(10, 10));
		return theRandomNum;
	}
}
