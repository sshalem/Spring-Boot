package com.jpa.dao;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public UserEntity getUserByName(String name) {
		return userRepository.findByName(name);
	}

	@Override
	public UserEntity addRoleToUser(long userPid, RoleEntity roleEntity) {

		UserEntity userEntity = userRepository.findByPid(userPid);

		roleEntity.setPid(userPid);

		userEntity.addRole(roleEntity);

		UserEntity returnedValue = userRepository.save(userEntity);

		return returnedValue;
	}

	/**
	 * @Transactional Annotation - 
	 * 			Should be only on 
	 * 			'PUBLIC' methods that returns value to higher level layer
	 */
	
	@Override
	@Transactional
	public UserEntity removeRoleFromUser(long userPid, RoleEntity roleEntity) {

//		UserEntity returnedValue = imp1(userPid, roleEntity);

		UserEntity returnedValue = imp2(userPid, roleEntity);

		return returnedValue;
	}

	@Override
	public UserEntity getUserByPid(long pid) {
		return userRepository.findByPid(pid);
	}

	public UserEntity imp1(long userPid, RoleEntity roleEntity) {

		/**
		 * In this Implementation 
		 * 1. I add the orphanRemoval to the UserEntity 
		 * 2. I Search For RoleEntity 
		 * 3. remove the Entity from the SET collection 
		 * 4. Save the the info to UserEntity
		 */

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

		return returnedValue;
	}

	
	public UserEntity imp2(long userPid, RoleEntity roleEntity) {

		/**
		 * In this Implementation
		 * 1. I add the orphanRemoval to UserEntity One2Many 
		 * 2. I Query For RoleEntity (I try with 2 different implementations) 
		 * 3. remove the Entity from the SET collection 
		 * 4. Save the the info to UserEntity
		 * 5. I must add @Transactional annotation to the method `removeRoleFromUser()` that returns from service layer to controller layer
		 */

		UserEntity userEntity = userRepository.findByPid(userPid);
				
		/**
		 * Query from UserRepository
		 */
//		RoleEntity role = userRepository.getRoleByIdAndRole(userEntity.getId(), roleEntity.getRole());

		/**
		 * Query from RoleRepository
		 */
//		RoleEntity role = roleRepository.findRole(userPid, roleEntity.getRole());
		
		/**
		 * Query from RoleRepository
		 */
		RoleEntity role = roleRepository.findByPidAndRole(userPid, roleEntity.getRole());
				
		userEntity.removeRole(role);
		UserEntity returnedValue = userRepository.save(userEntity);
		
		return returnedValue;
	}
}
