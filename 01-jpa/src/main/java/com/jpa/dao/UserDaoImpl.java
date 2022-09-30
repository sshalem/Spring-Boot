package com.jpa.dao;

import java.util.Set;

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
	public UserEntity addRoleToUser(long userPid, RoleEntity roleEntity) {

		UserEntity userEntity = userRepository.findByPid(userPid);
		
		userEntity.addRole(roleEntity);

		UserEntity returnedValue = userRepository.save(userEntity);

		return returnedValue;
	}

	@Override
	public UserEntity removeRoleFromUser(long userPid, RoleEntity roleEntity) {
		
		UserEntity userEntity = userRepository.findByPid(userPid);
		
		System.out.println(userEntity);
		
		Set<RoleEntity> roles = userEntity.getRoles();
		
		roles.forEach(r -> {
			if(r.getRole().equals(roleEntity.getRole())) {
				userEntity.removeRole(r);
			}
		});
		
//		userEntity.removeRole(roleEntity);
		
		UserEntity returnedValue = userRepository.save(userEntity);
		
		System.out.println(returnedValue);
		
		return returnedValue; 
	}

	@Override
	public UserEntity getUserByPid(long pid) { 
		return userRepository.findByPid(pid);
	}

}
