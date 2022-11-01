package com.jpa.one2many.uni.lazy.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jpa.one2many.uni.lazy.dto.UserDto;
import com.jpa.one2many.uni.lazy.entity.RoleEntity;
import com.jpa.one2many.uni.lazy.entity.UserEntity;
import com.jpa.one2many.uni.lazy.exception.ResourceNotFoundException;
import com.jpa.one2many.uni.lazy.repository.UserRepository;

@Service
public class UserDaoImpl implements UserDao {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDto createUser(UserEntity userEntity) {		
		UserDto userDto = new UserDto();		
		UserEntity userEntityFromDB = userRepository.save(userEntity);		
		BeanUtils.copyProperties(userEntityFromDB, userDto);		
		return userDto;
	}	
	
	@Override
	public UserDto getUserById(long id) {
		UserDto userDto = new UserDto();
//		UserEntity userEntity =  userRepository.findById(id);
//		UserEntity userEntity =  userRepository.nativeFindById(id);
		UserEntity userEntity =  userRepository.jpqlFindById(id);
		if(userEntity == null)
			throw new ResourceNotFoundException("Not found User with id = " + id);	
		BeanUtils.copyProperties(userEntity, userDto);		
		return userDto;		
	}

	@Override
	public UserDto getUserByPid(long pid) {
		UserDto userDto = new UserDto();		
		UserEntity userEntity = userRepository.findByPid(pid);		
		if(userEntity == null)
			throw new ResourceNotFoundException("Not found User with pid = " + pid);
		BeanUtils.copyProperties(userEntity, userDto);		
		return userDto;
	}
	
	/**
	 * Since I use LazyLoading , I must return a DTO and not a UserEntity , otherwise I get the following warn which :
	  
		.w.s.m.s.DefaultHandlerExceptionResolver :
		Resolved [org.springframework.http.converter.HttpMessageNotWritableException: 
			Could not write JSON: 
				failed to lazily initialize a collection of role: com.jpa.entity.UserEntity.roles, 
				could not initialize proxy - no Session; 
				nested exception is com.fasterxml.jackson.databind.JsonMappingException: 
				failed to lazily initialize a collection of role: com.jpa.entity.UserEntity.roles, could not initialize proxy - no Session]
		
		This is thrown by the RestController , and Not by the service layer 
		Thus I define here to return a:
		DTO object
	*/
	@Override	
	public UserDto getUserByName(String name) {				
		UserDto userDto = new UserDto();		
		UserEntity userEntity = userRepository.findByName(name);	
		if(userEntity == null)
			throw new ResourceNotFoundException("Not found User with name = " + name);
		BeanUtils.copyProperties(userEntity, userDto);		
		return userDto;
	}
	
	@Override
	public UserDto getUserByEmail(String email) {
		UserDto userDto = new UserDto();		
		UserEntity userEntity = userRepository.findByEmail(email);		
		if(userEntity == null)
			throw new ResourceNotFoundException("Not found User with email = " + email);
		BeanUtils.copyProperties(userEntity, userDto);		
		return userDto;
	}	

	@Override
	public List<UserDto> getAllUsers() {
		
		List<UserEntity> userEntities = userRepository.findAll();
		
		List<UserDto> returnedValue = new ArrayList<>();
		UserDto userDto = new UserDto();
		
		for (UserEntity userEntity : userEntities) {
			BeanUtils.copyProperties(userEntity, userDto);
			returnedValue.add(userDto);
		}
		return returnedValue;
	}
	
	@Override
	public void removeUserByPid(long pid) {
		UserEntity userEntity = userRepository.findByPid(pid);
		if(userEntity == null)
			throw new ResourceNotFoundException("Not found User with pid = " + pid);
		userRepository.delete(userEntity);
	}
	
	
	/**
	 * I got this error when tried to add role to user 
	 * 	org.hibernate.LazyInitializationException: 
	 * 		failed to lazily initialize a collection of role: 
	 * 			com.jpa.entity.UserEntity.roles, 
	 * 				could not initialize proxy - no Session
	 * 
	 * Thus , I need to add annotation of @Transactional
	 * From BAELDUNG:
	 * 
	 * The @Transactional annotation configures a transactional proxy around the instance of the related test class.
	 * Moreover, the transaction is associated with the thread executing it. 
	 * Considering the default transaction propagation setting, every Persistence Context created from this method joins to this same transaction.
	 * Consequently, the transaction persistence context is bound to the transaction scope of the test method.
	 */
	
	@Override
	@Transactional
	public UserDto addRoleToUser(long userPid, RoleEntity roleEntity) {
		
		// Best Practice to return UserDto and not UserEntity , 
		// But since I know that we have few rows of RoleEntity thus I return UserEntity
		
		UserEntity userEntity = userRepository.findByPid(userPid);
		if(userEntity == null)
			throw new ResourceNotFoundException("Not found User with userPid = " + userPid);
		roleEntity.setPid(userPid);
		userEntity.addRole(roleEntity); 
		
		UserEntity savedUserEntity = userRepository.save(userEntity);		
		UserDto userDto = new UserDto();			
		BeanUtils.copyProperties(savedUserEntity, userDto);		
		return userDto;		
	}
	
	/**
	 * @Transactional Annotation - 
	 * 			Should be only on 
	 * 			'PUBLIC' methods that returns value to higher level layer
	 */
	/**
	 * I got this error when tried to add role to user 
	 * 	org.hibernate.LazyInitializationException: 
	 * 		failed to lazily initialize a collection of role: 
	 * 			com.jpa.entity.UserEntity.roles, 
	 * 				could not initialize proxy - no Session
	 * 
	 * Thus , I need to add annotation of @Transactional
	 * From BAELDUNG:
	 * 
	 * The @Transactional annotation configures a transactional proxy around the instance of the related test class.
	 * Moreover, the transaction is associated with the thread executing it. 
	 * Considering the default transaction propagation setting, every Persistence Context created from this method joins to this same transaction.
	 * Consequently, the transaction persistence context is bound to the transaction scope of the test method.
	 */
	@Override
	@Transactional
	public UserEntity removeRoleFromUser(long userPid, String role) {

		// Best Practice to return UserDto and not UserEntity , 
		// But since I know that we have few rows of RoleEntity thus I return UserEntity
		/**
		 * In this Implementation
		 * 1. I add the orphanRemoval to UserEntity One2Many 
		 * 2. I Query For RoleEntity (I try with 2 different implementations) 
		 * 3. remove the Entity from the SET<RoleEntity> collection 
		 * 4. Save the the info to UserEntity
		 * 5. I must add @Transactional annotation to the method `removeRoleFromUser()` 
		 */
		
		/**
		 * there are 2 different ways to retrieve roleEntity from DB:
		 * (1) search for roelEntity from getRoles()
		 * (2) Query from UserRepo or RoleRepo 
		 */

		UserEntity userEntity = userRepository.findByPid(userPid);
		
		if(userEntity == null)
			throw new ResourceNotFoundException("Not found User with userPid = " + userPid);	
		
		// (1) search for roelEntity from getRoles()		 
		Set<RoleEntity> roles = userEntity.getRoles();

		RoleEntity roleEntity = null;

		for (RoleEntity r : roles) {
			if (r.getRole().equals(role)) {
				roleEntity = r;
			}
		}

//		(2) Query from UserRepo or RoleRepo
//		RoleEntity roleEntity = userRepository.getRoleByIdAndRole(userEntity.getId(), role);
//		RoleEntity roleEntity = roleRepository.jpqlFindRoleByPidAndRoleName(userPid, role);
//		RoleEntity roleEntity = roleRepository.findByPidAndRole(userPid, role);

		/**
		 * When Using the "roleRepository.delete(roleEntity)" from the roleRepo ,
		 *  no need to use the "orphanRemoval = true"
		 * If we want to return userEntity , we must keep @Transactional at method level, to keep session open
		 */
//		roleRepository.delete(roleEntity);
		
		userEntity.removeRole(roleEntity);
		
		UserEntity returnedValue = userRepository.save(userEntity);
			
		return returnedValue;		
	}	
		
}
