package com.jpa.one2one.bi.eager.dao;

import java.util.List;

import com.jpa.one2one.bi.eager.entity.AddressEntity;
import com.jpa.one2one.bi.eager.entity.UserEntity;

public interface UserDao {

	// POST
	UserEntity createUser(UserEntity userEntity);

	// GET
	UserEntity getUserById(long id);

	UserEntity getUserByName(String name);
	
	List<UserEntity> getUsersByPublished(boolean isPublished);

	List<UserEntity> getUsersByNameContaining(String name);
	
	List<UserEntity> getAllUsers();

	// PUT
	UserEntity updateUser(long id, UserEntity user);

	UserEntity addAddressToUser(AddressEntity addressEntity, String name);

	// DELETE
	void deleteUser(long id);

	void deleteAllUsers();

}
