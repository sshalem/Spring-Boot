package com.auth02.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auth02.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	UserEntity findByEmail(String email);

	UserEntity findByUserName(String username);

}
