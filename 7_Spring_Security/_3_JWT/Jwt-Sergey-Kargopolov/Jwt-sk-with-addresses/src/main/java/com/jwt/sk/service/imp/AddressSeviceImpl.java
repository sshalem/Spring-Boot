package com.jwt.sk.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwt.sk.dto.AddressDto;
import com.jwt.sk.entity.AddressEntity;
import com.jwt.sk.entity.UserEntity;
import com.jwt.sk.repository.AddressRepository;
import com.jwt.sk.repository.UserRepository;
import com.jwt.sk.service.AddressService;

@Service
public class AddressSeviceImpl implements AddressService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Override
	public List<AddressDto> getAddresses(String userId) {

		List<AddressDto> returnedValue = new ArrayList<>();
		ModelMapper modelMapper = new ModelMapper();

		UserEntity userEntity = userRepository.findByUserId(userId);
		Iterable<AddressEntity> addresses = addressRepository.findAllByUserDetails(userEntity);

		for (AddressEntity addressEntity : addresses) {
			returnedValue.add(modelMapper.map(addressEntity, AddressDto.class));
		}

		return returnedValue;
	}

	@Override
	public AddressDto getAddress(String addressId) {

		AddressDto returnedValue = null;
		AddressEntity addressEntity = addressRepository.findByAddressId(addressId);

		if (addressEntity != null) {
			returnedValue = new ModelMapper().map(addressEntity, AddressDto.class);
		}

		return returnedValue;
	}

}
