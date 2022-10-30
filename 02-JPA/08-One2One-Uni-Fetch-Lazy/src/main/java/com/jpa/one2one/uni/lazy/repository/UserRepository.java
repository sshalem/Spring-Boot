package com.jpa.one2one.uni.lazy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jpa.one2one.uni.lazy.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	List<UserEntity> findUsersByPublished(boolean published);

	List<UserEntity> findByNameContaining(String name);
}
