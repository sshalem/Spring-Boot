package com.lazy.dao;

import com.lazy.entity.PhoneNumber;

public interface PhoneNumberDao {

	// Create
	void createPhone(PhoneNumber phone);

	// Update
	PhoneNumber updateCustomer(PhoneNumber phone);

	// Delete
	void deleteCusotmer(PhoneNumber phone);

	Iterable<PhoneNumber> getAllPhones();
}
