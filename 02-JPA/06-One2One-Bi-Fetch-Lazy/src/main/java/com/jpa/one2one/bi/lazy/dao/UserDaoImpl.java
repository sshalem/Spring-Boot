package com.jpa.one2one.bi.lazy.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpa.one2one.bi.lazy.entity.AddressEntity;
import com.jpa.one2one.bi.lazy.entity.UserEntity;
import com.jpa.one2one.bi.lazy.exception.ResourceNotFoundException;
import com.jpa.one2one.bi.lazy.repository.UserRepository;

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
		UserEntity userEntity = userRepository.findUserById(id);
		
		if(userEntity == null)
			throw new ResourceNotFoundException("Not found User with id = " + id);		
		return userEntity;
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
		
		UserEntity userEntity = userRepository.findUserById(id);
		
		if(userEntity == null)
			throw new ResourceNotFoundException("Not found User with id = " + id);
		
		userEntity.setName(user.getName());
		userEntity.setEmail(user.getEmail());
		userEntity.setPublished(user.isPublished());
		
		UserEntity returnedValue = userRepository.save(userEntity);
		
		return returnedValue;
	}	

	@Override
	public void deleteUser(long id) {

		/**
		 *  remove the associated object reference
		 *  break bi-directional link
		 */
		UserEntity userEntity = userRepository.findUserById(id);
		AddressEntity addressEntity = userEntity.getAddress();
		
		if(addressEntity != null) {
			addressEntity.setUser(null);
		}		
		
		userRepository.delete(userEntity);
	}

	@Override
	public void deleteAllUsers() {
		userRepository.deleteAll();
	}

}
