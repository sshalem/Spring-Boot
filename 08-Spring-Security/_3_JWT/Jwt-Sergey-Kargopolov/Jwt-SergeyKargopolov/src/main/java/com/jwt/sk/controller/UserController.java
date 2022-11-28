package com.jwt.sk.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.sk.dto.AddressDto;
import com.jwt.sk.dto.UserDto;
import com.jwt.sk.exception.ErrorMessages;
import com.jwt.sk.exception.UserServiceException;
import com.jwt.sk.request.UserDetailsRequestModel;
import com.jwt.sk.response.AddressResponse;
import com.jwt.sk.response.OperationalStatusModel;
import com.jwt.sk.response.RequestOperationName;
import com.jwt.sk.response.RequestOperationStatus;
import com.jwt.sk.response.UserResponse;
import com.jwt.sk.service.AddressService;
import com.jwt.sk.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private AddressService addressService;
	
	@Autowired
	private AddressService addressesService;

	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public UserResponse getUser(@PathVariable("id") String id) {

		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = userService.getUserByUserId(id);
		UserResponse returnValue = modelMapper.map(userDto, UserResponse.class);

		LOGGER.info("user Data retrieved {}", returnValue);
		return returnValue;
	}

	@PostMapping(
			consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public UserResponse createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception {
		
		if (userDetails.getFirstName().isEmpty())
			throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(userDetails, UserDto.class);

		UserDto createdUser = userService.createUser(userDto);
		
		UserResponse returnValue = modelMapper.map(createdUser, UserResponse.class);
	
		LOGGER.info("new user created {}", createdUser); 
		return returnValue;
	}

	@PutMapping(path = "/{id}", 
			consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public UserResponse updateUser(@RequestBody UserDetailsRequestModel userDetails, @PathVariable("id") String id) {
		UserResponse returnValue = new UserResponse();

		if (userDetails.getFirstName().isEmpty())
			throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);

		UserDto updatedUser = userService.updateUser(id, userDto);
		BeanUtils.copyProperties(updatedUser, returnValue);

		LOGGER.info("update user {}", updatedUser);
		return returnValue;
	}

	@DeleteMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public OperationalStatusModel deleteUser(@PathVariable("id") String id) {

		OperationalStatusModel returnValue = new OperationalStatusModel();

		userService.deleteUser(id);

		LOGGER.info("user deleted");

		returnValue.setOperationName(RequestOperationName.DELETE.getOperationRequest());
		returnValue.setOperationResult(RequestOperationStatus.SUCCESS.getStatus());

		return returnValue;
	}

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<UserResponse> getUsers(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "5") int limit) {
		
		List<UserResponse> returnValue = new ArrayList<>();
		List<UserDto> users = userService.getUsers(page, limit);
		
		for(UserDto userDto: users) {
			UserResponse userResponse = new UserResponse();
			BeanUtils.copyProperties(userDto, userResponse);
			returnValue.add(userResponse);
		}
		return returnValue;
	}
	
	@GetMapping(path = "/{id}/addresses", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<AddressResponse> getAddresses(@PathVariable("id") String userId) {
		
		List<AddressResponse> returnValue = new ArrayList<>();
		List<AddressDto> addressesDto = addressesService.getAddresses(userId);
				
		if(addressesDto != null && !addressesDto.isEmpty()) {
			ModelMapper modelMapper = new ModelMapper();
			java.lang.reflect.Type listType = new TypeToken<List<AddressResponse>>() {}.getType();
			returnValue = modelMapper.map(addressesDto, listType);
		}
		return returnValue;
	}
	
	@GetMapping(path = "/{userId}/addresses/{addressId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public AddressResponse getUserAddress(@PathVariable("addressId") String addressId) {
		
		AddressDto addressDto = addressService.getAddress(addressId);
		ModelMapper modelMapper = new ModelMapper();
		AddressResponse returnValue = modelMapper.map(addressDto, AddressResponse.class);
		
		return returnValue;
	}
}
