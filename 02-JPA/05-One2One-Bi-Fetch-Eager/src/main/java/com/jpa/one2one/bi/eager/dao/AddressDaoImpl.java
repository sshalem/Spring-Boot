package com.jpa.one2one.bi.eager.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpa.one2one.bi.eager.entity.AddressEntity;
import com.jpa.one2one.bi.eager.entity.UserEntity;
import com.jpa.one2one.bi.eager.exception.ResourceNotFoundException;
import com.jpa.one2one.bi.eager.repository.AddressRepository;
import com.jpa.one2one.bi.eager.repository.UserRepository;

@Service
public class AddressDaoImpl implements AddressDao {

	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private UserRepository userRepository;

//	I actually set the address to user Via UserRepo and UserEntity
	@Override
	public AddressEntity createAddress(long userId, AddressEntity address) {
		return addressRepository.save(address);
	}

	@Override
	public AddressEntity getAddressById(long id) {
		
//		AddressEntity addressEntity = addressRepository.findAddressById(id);
		AddressEntity addressEntity = addressRepository.jpqlFindById(id);
		
		if(addressEntity == null)
			// For Practice throwing NullPointerException
			throw new NullPointerException("Not found Address Details with id = " + id);
		
		return addressEntity;
	}

	@Override
	public List<AddressEntity> getAllAddresses() {
		return addressRepository.findAll();
	}

	@Override
	public AddressEntity updateAddress(long id, AddressEntity address) {
		
		AddressEntity addressEntity = addressRepository.findAddressById(id);
		
		if(addressEntity == null)
			throw new ResourceNotFoundException("Not found Address Details with id = " + id);
		
		addressEntity.setCity(address.getCity());
		addressEntity.setStreet(address.getStreet());
		
		AddressEntity returnedValue = addressRepository.save(addressEntity);
		return returnedValue;
	}

	@Override
	public UserEntity addAddressToUser(AddressEntity addressEntity, long userId) {

		UserEntity userEntity = userRepository.findUserById(userId);
		
		if(userEntity == null) 
			throw new ResourceNotFoundException("Not found User with id = " + userId);
				
		userEntity.setAddress(addressEntity);
		
		addressEntity.setUser(userEntity);		
				
		UserEntity returnedValue = userRepository.save(userEntity);
		return returnedValue;
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
