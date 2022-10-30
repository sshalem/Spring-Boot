package com.jpa.one2one.bi.eager.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpa.one2one.bi.eager.entity.AddressEntity;
import com.jpa.one2one.bi.eager.repository.AddressRepository;

@Service
public class AddressDaoImpl implements AddressDao {

	@Autowired
	private AddressRepository addressRepository;

//	I actually set the address to user Via UserRepo and UserEntity
	@Override
	public AddressEntity createAddress(long userId, AddressEntity address) {
		return addressRepository.save(address);
	}

	@Override
	public AddressEntity getAddressById(long id) {
		return addressRepository.findAddressById(id);
	}

	@Override
	public List<AddressEntity> getAllAddresses() {
		return addressRepository.findAll();
	}

	@Override
	public AddressEntity updateAddress(long id, AddressEntity address) {
		return null;
	}

	@Override
	public void deleteAddress(long id) {
		addressRepository.deleteById(id);
	}

	@Override
	public void deleteAddressOfUser(long userId) {
		addressRepository.deleteById(userId);
	}

	@Override
	public void deleteAllAddresses() {
		addressRepository.deleteAll();
	}

}
