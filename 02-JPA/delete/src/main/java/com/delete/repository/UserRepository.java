package com.delete.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.delete.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	UserEntity findByName(String name);

//	@Modifying
//	@Transactional(propagation = Propagation.REQUIRES_NEW)
//	void deleteByEmail(String email);
	
	@Modifying
	void deleteByEmail(String email);
}
