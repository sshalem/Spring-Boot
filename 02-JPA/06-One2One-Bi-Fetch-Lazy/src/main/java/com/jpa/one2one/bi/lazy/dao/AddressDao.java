package com.jpa.one2one.bi.lazy.dao;

import java.util.List;

import com.jpa.one2one.bi.lazy.entity.AddressEntity;
import com.jpa.one2one.bi.lazy.entity.UserEntity;

public interface AddressDao {

	// POST
	AddressEntity createAddress(long userId, AddressEntity address);

	// GET
	AddressEntity getAddressById(long id);

	List<AddressEntity> getAllAddresses();

	// PUT
	AddressEntity updateAddress(long id, AddressEntity address);
	
	UserEntity addAddressToUser(AddressEntity addressEntity, long userId);

	// DELETE
	void deleteAddress(long id);

	void deleteAddressOfUser(long userId);

	void deleteAllAddresses();
}
