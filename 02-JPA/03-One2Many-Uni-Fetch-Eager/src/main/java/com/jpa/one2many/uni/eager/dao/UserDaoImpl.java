package com.jpa.one2many.uni.eager.dao;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jpa.one2many.uni.eager.entity.RoleEntity;
import com.jpa.one2many.uni.eager.entity.UserEntity;
import com.jpa.one2many.uni.eager.repository.RoleRepository;
import com.jpa.one2many.uni.eager.repository.UserRepository;

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
	public UserEntity getUserById(long id) {
//		return userRepository.findById(id);
		return userRepository.jpqlFindById(id);
//		return userRepository.nativeFindById(id);
	}
	
	@Override
	public UserEntity getUserByPid(long pid) {
		return userRepository.findByPid(pid);
	}
	
	@Override
	public UserEntity getUserByName(String name) {
		return userRepository.findByName(name);
	}	

	@Override
	public UserEntity getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}	

	@Override
	public List<UserEntity> getAllUsers() {
		return userRepository.findAll();
	}
	
	@Override
	public void removeUserByPid(long pid) {
		UserEntity userEntity = userRepository.findByPid(pid);
		userRepository.delete(userEntity);
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
	public UserEntity removeRoleFromUser(long userPid, String role) {

		/**
		 * In this Implementation
		 * 1. I add the orphanRemoval to UserEntity One2Many 
		 * 2. I Query For RoleEntity (I try with 2 different implementations) 
		 * 3. remove the Entity from the SET<RoleEntity> collection 
		 * 4. Save the the info to UserEntity
		 * 5. I must add @Transactional annotation to the method `removeRoleFromUser()` 
		 * 	  which returns returnedValue(Of UserEntity) from service layer to controller layer,
		 *    Only if I use a query from Repository
		 */

		UserEntity userEntity = userRepository.findByPid(userPid);
		
		/**
		 * there are 4 different ways to retrieve roleEntity from DB
		 */
		
		long start = System.nanoTime();
		
		/**
		 * (1) We Don't need to add @Transactional If we search with For loop  
		 */
		Set<RoleEntity> roles = userEntity.getRoles();

		RoleEntity roleEntity = null;

		for (RoleEntity r : roles) {
			if (r.getRole().equals(role)) {
				roleEntity = r;
			}
		}

		/**
		 * (2) Query from UserRepo  
		 */
//		RoleEntity roleEntity = userRepository.getRoleByIdAndRole(userEntity.getId(), role);

		/**
		 * (3) Query from RoleRepo	
		 */
//		RoleEntity roleEntity = roleRepository.jpqlFindRoleByPidAndRoleName(userPid, role);
		
		/**
		 * (4) Query from RoleRepo
		 */
//		RoleEntity roleEntity = roleRepository.findByPidAndRole(userPid, role);
																											
		userEntity.removeRole(roleEntity);
		
		long end = System.nanoTime();
		
		System.out.println(end - start);
		
		UserEntity returnedValue = userRepository.save(userEntity);
		
		return returnedValue;
	}
}
