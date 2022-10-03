package com.jpa.dao;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
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
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
	public UserEntity removeRoleFromUser(long userPid, RoleEntity roleEntity) {

//		UserEntity returnedValue = imp1(userPid, roleEntity);

		UserEntity returnedValue = imp2(userPid, roleEntity);

//		UserEntity returnedValue = imp3(userPid, roleEntity);

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
		
//		long start = LocalDateTime.now().getNano();
		long start = System.nanoTime();
		
		
		Set<RoleEntity> roles = userEntity.getRoles();

		RoleEntity temp = null;

		for (RoleEntity r : roles) {
			if (r.getRole().equals(roleEntity.getRole())) {
				temp = r;
			}
		}		

		userEntity.removeRole(temp);
		UserEntity returnedValue = userRepository.save(userEntity);

//		long end = LocalDateTime.now().getNano();
		
		long end = System.nanoTime();		
		long diff = end - start;		
		System.out.println(diff);		
		return returnedValue;
	}

	
	public UserEntity imp2(long userPid, RoleEntity roleEntity) {

		/**
		 * In this Implementation
		 * 1. I remove the orphanRemoval from the UserEntity 
		 * 2. I Query For RoleEntity (I try with 2 different implementations) 
		 * 3. remove the Entity from the SET collection 
		 * 3. Save the the info to UserEntity
		 */

		UserEntity userEntity = userRepository.findByPid(userPid);

//		long start = LocalDateTime.now().getNano();
		long start = System.nanoTime();
		
		/**
		 * Query from UserRepository
		 */
//		RoleEntity role = userRepository.getRoleByIdAndRole(userEntity.getId(), roleEntity.getRole());

		/**
		 * Query from RoleRepository
		 */
		RoleEntity role = roleRepository.findRole(userPid, roleEntity.getRole());

		System.out.println(role);

		userEntity.removeRole(role);

		UserEntity returnedValue = userRepository.save(userEntity);

//		long end = LocalDateTime.now().getNano();
		long end = System.nanoTime();
		
		long diff = end - start;
		
		System.out.println(diff);
		
		return returnedValue;
	}

//	public UserEntity imp3(long userPid, RoleEntity roleEntity) {
//
//		/**
//		 * In this Implementation 
//		 * 1. I remove the orphanRemoval from the Entity 
//		 * 2. I remove the Entity from the SET collection 
//		 * 3. I delete the RoleENtity from DB using RoleRepo 
//		 * 4. I add the @Transactional annotation org.springframework.transaction.annotation.Transactional; 
//		 * 		to all classes in path including the controller
//		 */
//
//		UserEntity userEntity = userRepository.findByPid(userPid);
//
//		RoleEntity role = userRepository.getRoleByIdAndRole(userEntity.getId(), roleEntity.getRole());
//
//		userEntity.removeRole(role);
//
//		roleRepository.deleteUserRole(userPid, roleEntity.getRole());
//
////		roleRepository.delete(role);
//
//		UserEntity returnedValue = userRepository.save(userEntity);
//
//		return returnedValue;
//	}

}
