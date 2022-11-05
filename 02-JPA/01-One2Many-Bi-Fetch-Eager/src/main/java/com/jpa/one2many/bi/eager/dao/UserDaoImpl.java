package com.jpa.one2many.bi.eager.dao;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jpa.one2many.bi.eager.entity.RoleEntity;
import com.jpa.one2many.bi.eager.entity.UserEntity;
import com.jpa.one2many.bi.eager.exception.ResourceNotFoundException;
import com.jpa.one2many.bi.eager.repository.RoleRepository;
import com.jpa.one2many.bi.eager.repository.UserRepository;

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
		//	UserEntity userEntity = userRepository.findById(id);
		//	UserEntity userEntity =  userRepository.nativeFindById(id);
		UserEntity userEntity = userRepository.jpqlFindById(id);		
		if(userEntity == null)
			throw new ResourceNotFoundException("Not found User with id = " + id);			
		return userEntity;
	}
	
	@Override
	public UserEntity getUserByPid(long pid) {		
		UserEntity userEntity = userRepository.findByPid(pid);		
		if(userEntity == null)
			throw new ResourceNotFoundException("Not found User with pid = " + pid);	
		return userEntity;
	}
	
	@Override
	public UserEntity getUserByName(String name) {			
		UserEntity userEntity = userRepository.findByName(name);		
		if(userEntity == null)
			throw new ResourceNotFoundException("Not found User with name = " + name);	
		return userEntity;
	}	

	@Override
	public UserEntity getUserByEmail(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);		
		if(userEntity == null)
			throw new ResourceNotFoundException("Not found User with email = " + email);	
		return userEntity;
	}	

	@Override
	public List<UserEntity> getAllUsers() {
		return userRepository.findAll();
	}
	
	@Override
	public void removeUserByPid(long pid) {
		UserEntity userEntity = userRepository.findByPid(pid);
		if(userEntity == null)
			throw new ResourceNotFoundException("Not found User with pid = " + pid);	
		userRepository.delete(userEntity);
	}
	
	@Override
	public UserEntity addRoleToUser(long userPid, RoleEntity roleEntity) {

		UserEntity userEntity = userRepository.findByPid(userPid);
		
		if(userEntity == null)
			throw new ResourceNotFoundException("Not found User with userPid = " + userPid);	

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
		
		if(userEntity == null)
			throw new ResourceNotFoundException("Not found User with userPid = " + userPid);	
		
		/**
		 * there are 4 different ways to retrieve roleEntity from DB
		 */
	
		
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
							
		/**
		 * With this Implementation , Must add orphanRemoval = true on the @OneToMany
		 */
//		userEntity.removeRole(roleEntity);
//		UserEntity returnedValue = userRepository.save(userEntity);		
//		return returnedValue;
				
		/**
		 * With this Implementation , NO NEED  orphanRemoval = true on the @OneToMany
		 * also @OneToMany in w/o CascadeType.REMOVE
		 */
		userEntity.removeRole(roleEntity);
		roleRepository.delete(roleEntity);
		return userEntity;
		
		/**
		 * Need to check which approach is better for performance
		 */
		
	}
}
