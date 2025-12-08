package com.delete.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.delete.entity.UserEntity;
import com.delete.repository.UserRepository;

@Service
public class UserService {

	public static int currentIdCount = 1;
	public static int currentEmailCount = 1;

	@Autowired
	private UserRepository userRepository;

	@Transactional
	public UserEntity createAndDeleteById(UserEntity user) {
		if (currentIdCount > 2) {
			userRepository.deleteById(1L);
			throw new RuntimeException("Check if RollBack from DB performed");
		}
		currentIdCount++;
		return userRepository.save(user);
	}

//	@Transactional(noRollbackFor = RuntimeException.class)
	public UserEntity createAndDeleteByEmail(UserEntity user) {
		if (currentEmailCount > 2) {
//			userRepository.deleteByEmail(user.getEmail());
			userRepository.deleteUserByEmail(user.getEmail());
			throw new RuntimeException("Check if RollBack from DB performed");
		}
		currentEmailCount++;
		return userRepository.save(user);
	}

}
