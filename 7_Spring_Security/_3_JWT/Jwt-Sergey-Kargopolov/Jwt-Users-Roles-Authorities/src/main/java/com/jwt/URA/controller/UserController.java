package com.jwt.URA.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.jwt.URA.dto.UserDto;
import com.jwt.URA.exception.ErrorMessages;
import com.jwt.URA.exception.UserServiceException;
import com.jwt.URA.request.UserDetailsRequestModel;
import com.jwt.URA.response.OperationalStatusModel;
import com.jwt.URA.response.RequestOperationName;
import com.jwt.URA.response.RequestOperationStatus;
import com.jwt.URA.response.UserResponse;
import com.jwt.URA.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public UserResponse getUser(@PathVariable("id") String id) {

		UserResponse returnValue = new UserResponse();
		UserDto userDto = userService.getUserByUserId(id);
		BeanUtils.copyProperties(userDto, returnValue);

		LOGGER.info("user Data retrieved {}", returnValue);
		return returnValue;
	}

	@PostMapping(path = "/signup",
			consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public UserResponse createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception {

		UserResponse returnValue = new UserResponse();

		if (userDetails.getFirstName().isEmpty())
			throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);

		UserDto createUser = userService.createUser(userDto);
		BeanUtils.copyProperties(createUser, returnValue);

		LOGGER.info("new user created {}", createUser);
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
}
