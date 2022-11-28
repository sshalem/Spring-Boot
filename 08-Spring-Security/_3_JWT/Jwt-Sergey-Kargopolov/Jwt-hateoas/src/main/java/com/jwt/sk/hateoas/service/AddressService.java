package com.jwt.sk.hateoas.service;

import java.util.List;

import com.jwt.sk.hateoas.dto.AddressDto;

public interface AddressService {

	List<AddressDto> getAddresses(String userId);

	AddressDto getAddress(String addressId);
}
