package com.jpa.dao;

import com.jpa.entity.RoleEntity;
import com.jpa.entity.UserEntity;

public interface UserDao {

	UserEntity createUser(UserEntity userEntity);

	UserEntity addRoleToUser(UserEntity userEntity, RoleEntity roleEntity);

	UserEntity removeRoleFromUser(UserEntity userEntity, RoleEntity roleEntity);

	UserEntity getUserById(long id);
}
