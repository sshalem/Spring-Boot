package com.jpa.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpa.entity.RoleEntity;
import com.jpa.entity.UserEntity;
import com.jpa.repository.UserRepository;

@Service
public class UserDaoImpl implements UserDao {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserEntity createUser(UserEntity userEntity) {
		return userRepository.save(userEntity);
	}

	@Override
	public UserEntity addRoleToUser(UserEntity userEntity, RoleEntity roleEntity) {

		userEntity.addRole(roleEntity);

		UserEntity returnedValue = userRepository.save(userEntity);

		return returnedValue;
	}

	@Override
	public UserEntity removeRoleFromUser(UserEntity userEntity, RoleEntity roleEntity) {
		userEntity.removeRole(roleEntity);
		UserEntity returnedValue = userRepository.save(userEntity);
		return returnedValue;
	}

	@Override
	public UserEntity getUserById(long id) { 
		return userRepository.findById(id).get();
	}

}
