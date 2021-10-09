package com.em.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.em.entity.UserEntity;
import com.em.exception.UserServiceException;
import com.em.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Override
	public UserEntity createUser(UserEntity userEntity) throws UserServiceException {
		if (userRepo.findByFirstName(userEntity.getFirstName()) != null) {
			throw new UserServiceException("User " + userEntity.getFirstName() + " already Exist");
		}
		return userRepo.save(userEntity);
	}

	@Override
	public UserEntity getUser(long id) {
		return userRepo.findById(id).get();
	}

	@Override
	public UserEntity updateUser(UserEntity userEntity) {
		return userRepo.save(userEntity);
	}

	@Override
	public void deleteUser(UserEntity userEntity) {
		userRepo.delete(userEntity);
	}

}
