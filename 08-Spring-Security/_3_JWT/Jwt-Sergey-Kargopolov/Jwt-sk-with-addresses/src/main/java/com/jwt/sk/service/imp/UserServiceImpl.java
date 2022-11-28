package com.jwt.sk.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwt.sk.dto.AddressDto;
import com.jwt.sk.dto.UserDto;
import com.jwt.sk.entity.UserEntity;
import com.jwt.sk.exception.ErrorMessages;
import com.jwt.sk.repository.UserRepository;
import com.jwt.sk.service.UserService;
import com.jwt.sk.shared.Utils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private Utils utils;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDto createUser(UserDto userDto) throws RuntimeException {

		if (userRepository.findByEmail(userDto.getEmail()) != null)
			throw new RuntimeException("Email already exists");

		for (int i = 0; i < userDto.getAddresses().size(); i++) {
			AddressDto addressDto = userDto.getAddresses().get(i);
			addressDto.setAddressId(utils.generateAddressId(30));
			addressDto.setUserDetails(userDto);
			userDto.getAddresses().set(i, addressDto);
		}

//		List<AddressDto> addresses = userDto.getAddresses();
//		addresses.forEach(address -> {
//			address.setUserDetails(userDto);
//			address.setAddressId(utils.generateAddressId(30));
//		});
//		userDto.setAddresses(addresses);

		ModelMapper modelMapper = new ModelMapper();
		UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);

		userEntity.setEncryptedPassword(passwordEncoder.encode(userDto.getPassword()));
		userEntity.setUserId(utils.generateUserId(30));

		UserEntity storedUserDetails = userRepository.save(userEntity);

		UserDto returnValue = modelMapper.map(storedUserDetails, UserDto.class);
		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByEmail(username);

		if (userEntity == null)
			throw new UsernameNotFoundException("User email :" + username + " Not exist");

		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
	}

	@Override
	public UserDto getUser(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);

		if (userEntity == null)
			throw new UsernameNotFoundException("User email :" + email + " Not exist");

		UserDto returnValue = new UserDto();

		BeanUtils.copyProperties(userEntity, returnValue);
		return returnValue;
	}

	@Override
	public UserDto getUserByUserId(String userId) {
		UserEntity userEntity = userRepository.findByUserId(userId);

		if (userEntity == null)
			throw new UsernameNotFoundException("User with userID : " + userId + " not found");

		ModelMapper modelMapper = new ModelMapper();
		UserDto returnValue = modelMapper.map(userEntity, UserDto.class);

		return returnValue;
	}

	/**
	 * This mEthod only Updates the users: firstname lastname For Updating the Email
	 * and Password , I will use different methods
	 */
	@Override
	public UserDto updateUser(String userId, UserDto user) {

		UserEntity userEntity = userRepository.findByUserId(userId);

		if (userEntity == null)
			throw new UsernameNotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

		userEntity.setFirstName(user.getFirstName());
		userEntity.setLastName(user.getLastName());

		UserEntity updatedUser = userRepository.save(userEntity);

		ModelMapper modelMapper = new ModelMapper();
		UserDto returnValue = modelMapper.map(updatedUser, UserDto.class);

		return returnValue;
	}

	@Override
	public void deleteUser(String userId) {
		UserEntity userEntity = userRepository.findByUserId(userId);

		if (userEntity == null)
			throw new UsernameNotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		userRepository.delete(userEntity);
	}

	@Override
	public List<UserDto> getUsers(int page, int limit) {

		/**
		 * the default value of the "first page" is defined as 0 (not 1) The line below
		 * is to prevent confusion (when sending the page number in the url) so whenever
		 * a client will send request , I decrease the page number by 1. Page number
		 * must be greater than 0.
		 */
		if (page > 0)
			page = page - 1;

		List<UserDto> returnValue = new ArrayList<>();

		Pageable pageableRequest = PageRequest.of(page, limit);
		Page<UserEntity> usersPage = userRepository.findAll(pageableRequest);
		List<UserEntity> userEntities = usersPage.getContent();

		for (UserEntity userEntity : userEntities) {
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(userEntity, userDto);
			returnValue.add(userDto);
		}

		return returnValue;
	}

}
