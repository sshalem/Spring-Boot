package com.jpa.one2one.bi.eager.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpa.one2one.bi.eager.entity.AddressEntity;
import com.jpa.one2one.bi.eager.entity.UserEntity;
import com.jpa.one2one.bi.eager.repository.UserRepository;

@Service
public class UserDaoImpl implements UserDao {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserEntity createUser(UserEntity userEntity) {
		return userRepository.save(userEntity);
	}

	@Override
	public UserEntity getUserById(long id) {
		return userRepository.findUserById(id);
	}

	@Override
	public List<UserEntity> getUsersByPublished(boolean isPublished) {
		return userRepository.findUsersByPublished(isPublished);
	}

	@Override
	public List<UserEntity> getUsersByNameContaining(String name) {
		return userRepository.findByNameContaining(name);
	}

	@Override
	public UserEntity getUserByName(String name) {
		return userRepository.findUserByName(name);
	}

	@Override
	public List<UserEntity> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public UserEntity updateUser(long id, UserEntity user) {
		return null;
	}

	@Override
	public UserEntity addAddressToUser(AddressEntity addressEntity, String name) {

		UserEntity userEntity = userRepository.findUserByName(name);
		addressEntity.setUser(userEntity);

		// See setAddress() implementation in the UserEntity Class
		userEntity.setAddress(addressEntity);
		UserEntity returnedValue = userRepository.save(userEntity);

		return returnedValue;
	}

	@Override
	public void deleteUser(long id) {
		userRepository.deleteById(id);
	}

	@Override
	public void deleteAllUsers() {
		userRepository.deleteAll();
	}

}
