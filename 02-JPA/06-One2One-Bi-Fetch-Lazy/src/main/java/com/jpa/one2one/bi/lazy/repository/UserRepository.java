package com.jpa.one2one.bi.lazy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jpa.one2one.bi.lazy.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	UserEntity findUserById(long id);

	UserEntity findUserByName(String name);

	List<UserEntity> findUsersByPublished(boolean published);

	List<UserEntity> findByNameContaining(String name);

}
