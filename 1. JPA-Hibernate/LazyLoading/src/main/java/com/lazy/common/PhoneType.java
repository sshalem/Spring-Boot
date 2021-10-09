package com.lazy.common;

import java.util.Random;

public enum PhoneType {

	SAMSUNG, XIAOMI, IPHONE, SONY, BEZEQ, HOT, CELLCOM;

	public static PhoneType getRandomPhoneType() {
		Random RANDOM = new Random();
		PhoneType phoneTypes[] = PhoneType.values();
		return phoneTypes[RANDOM.nextInt(phoneTypes.length)];
	}

}
