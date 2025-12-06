package com.delete.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.delete.entity.UserEntity;
import com.delete.repository.UserRepository;

@Service
public class UserService {

	public static int current = 1;

	@Autowired
	private UserRepository userRepository;

	@Transactional(noRollbackFor = RuntimeException.class)
	public UserEntity deleteAndCreateTest(UserEntity user) {
		if (current > 2) {
			userRepository.deleteById(1L);
			throw new RuntimeException("Check if delete User finsished or RollBack from DB performed");
		}
		current++;
		return userRepository.save(user);
	}

}
