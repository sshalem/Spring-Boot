package com.jwt.sk.service;

import java.util.List;

import com.jwt.sk.dto.AddressDto;

public interface AddressService {

	List<AddressDto> getAddresses(String userId);

	AddressDto getAddress(String addressId);
}
