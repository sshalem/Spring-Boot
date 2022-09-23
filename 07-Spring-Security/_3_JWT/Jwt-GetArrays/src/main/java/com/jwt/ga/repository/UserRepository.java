package com.jwt.ga.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwt.ga.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

}
