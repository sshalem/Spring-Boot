package com.jpa.dao;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpa.entity.RoleEntity;
import com.jpa.entity.UserEntity;
import com.jpa.repository.RoleRepository;
import com.jpa.repository.UserRepository;

@Service
public class UserDaoImpl implements UserDao {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public UserEntity createUser(UserEntity userEntity) {
		return userRepository.save(userEntity);
	}

	@Override
	public UserEntity addRoleToUser(long userPid, RoleEntity roleEntity) {

		UserEntity userEntity = userRepository.findByPid(userPid);

		roleEntity.setPid(userPid);

		userEntity.addRole(roleEntity);

		UserEntity returnedValue = userRepository.save(userEntity);

		return returnedValue;
	}

	@Override
	public UserEntity removeRoleFromUser(long userPid, RoleEntity roleEntity) {

//		RoleEntity role = userRepository.getRoleByIdAndRole(userEntity.getId(), roleEntity.getRole());

//		System.out.println("----> Role " + role);
//
//		userEntity.removeRole(role);

//		Set<RoleEntity> roles = userEntity.getRoles();
//		
//		roles.remove(role);
		
		UserEntity returnedValue = imp1(userPid, roleEntity);

		return returnedValue;
	}

	@Override
	public UserEntity getUserByPid(long pid) {
		return userRepository.findByPid(pid);
	}

	private UserEntity imp1(long userPid, RoleEntity roleEntity) {
		UserEntity userEntity = userRepository.findByPid(userPid);

		Set<RoleEntity> roles = userEntity.getRoles();

		RoleEntity temp = null;

		for (RoleEntity r : roles) {
			if (r.getRole().equals(roleEntity.getRole())) {
				temp = r;
			}
		}

		userEntity.removeRole(temp);

		UserEntity returnedValue = userRepository.save(userEntity);

		System.out.println(returnedValue);

		return returnedValue;
	}

}
