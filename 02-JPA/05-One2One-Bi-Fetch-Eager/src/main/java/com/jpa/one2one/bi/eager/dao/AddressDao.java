package com.jpa.one2one.bi.eager.dao;

import java.util.List;

import com.jpa.one2one.bi.eager.entity.AddressEntity;

public interface AddressDao {

	// POST
	AddressEntity createAddress(long userId, AddressEntity address);

	// GET
	AddressEntity getAddressById(long id);

	List<AddressEntity> getAllAddresses();

	// PUT
	AddressEntity updateAddress(long id, AddressEntity address);

	// DELETE
	void deleteAddress(long id);

	void deleteAddressOfUser(long userId);

	void deleteAllAddresses();
}
