package com.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	UserEntity findByEmail(String email);

	UserEntity findByName(String name);

}
