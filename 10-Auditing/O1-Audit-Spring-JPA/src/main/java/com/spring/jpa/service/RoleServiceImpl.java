package com.spring.jpa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.jpa.dto.RoleDto;
import com.spring.jpa.dto.UserDto;
import com.spring.jpa.entity.RoleEntity;
import com.spring.jpa.entity.UserEntity;
import com.spring.jpa.repository.RoleRepository;
import com.spring.jpa.repository.UserRepository;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	/**
	 * Since I have @JsonIgnore on my RoleEntity association 
	 * Thus I don'g have to return a List<RoleDto> 
	 * I could return List<RoleEntity>
	 * But best practice is to return a DTO object
	 */
	@Override
	public List<RoleDto> getRoleById(long id) {
		List<RoleEntity> roles = roleRepository.findById(id);		
		return roleDtoConverter(roles);
	}

	/**
	 * I must return a List<UserDto> with FETCH.LAZY other wise I will get error
	 * 		.w.s.m.s.DefaultHandlerExceptionResolver : 
	 * 		Resolved [org.springframework.http.converter.HttpMessageNotWritableException: 
	 * 			Could not write JSON: 
	 * 				failed to lazily initialize a collection of role: com.jpa.entity.UserEntity.roles, 
	 * 				could not initialize proxy - no Session; 
	 * 				nested exception is com.fasterxml.jackson.databind.JsonMappingException: 
	 * 				failed to lazily initialize a collection of role: com.jpa.entity.UserEntity.roles, 
	 * 				could not initialize proxy - no Session 
	 * 				(through reference chain: java.util.ArrayList[0]->com.jpa.entity.UserEntity["roles"])]
	 * 
	 * 1. Best Practice : must return List<UserDto>
	 * 2. this will work as well : add @JsonIgnore annotation at UserEntity to the Association 
	 */
	@Override
	public List<UserDto> getUsersWithRoleName(String role) {
//		return roleRepository.jpqlFindUsersWithRoleName(role);
		List<UserEntity> users = userRepository.nativeFindUsersWithRoleName(role);
		return userDtoConverter(users);		
	}

	@Override
	public List<RoleDto> getRoleByPid(long pid) {
		List<RoleEntity> roles = roleRepository.findByPid(pid);
		return roleDtoConverter(roles);
	}

	@Override
	public List<RoleDto> getAllRoles() {
		List<RoleEntity> roles = roleRepository.findAll();
		return roleDtoConverter(roles);
	}
	
	private List<UserDto> userDtoConverter(List<UserEntity> users) {
		
		List<UserDto> returnedValue = new ArrayList<>();
		
		for(UserEntity user: users) {
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(user, userDto);
			returnedValue.add(userDto);	
		}
		return returnedValue;
	}
	
	private List<RoleDto> roleDtoConverter(List<RoleEntity> roles) {
		
		List<RoleDto> returnedValue = new ArrayList<>();
		
		for(RoleEntity role: roles) {
			RoleDto roleDto = new RoleDto();
			BeanUtils.copyProperties(role, roleDto);
			returnedValue.add(roleDto);	
		}
		return returnedValue;
	}
}
