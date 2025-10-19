package com.jpa.one2many.bi.eager.service;

import com.jpa.one2many.bi.eager.entity.RoleEntity;
import com.jpa.one2many.bi.eager.entity.UserEntity;

import java.util.List;

public interface UserService {

    UserEntity createUser(UserEntity userEntity);

    UserEntity getUserById(long id);

    UserEntity getUserByPid(long id);

    UserEntity getUserByName(String name);

    UserEntity getUserByEmail(String email);

    List<UserEntity> getAllUsers();

    void removeUserByPid(long pid);

    UserEntity addRoleToUser(long userPid, RoleEntity roleEntity);

    UserEntity addRoleUpdateUser(long userPid, UserEntity userEntity);

    UserEntity removeRoleFromUser(long userPid, String role);
}
