package com.jpa.one2one.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jpa.one2one.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	List<UserEntity> findByPublished(boolean published);

	List<UserEntity> findByUserContaining(String name);
}
