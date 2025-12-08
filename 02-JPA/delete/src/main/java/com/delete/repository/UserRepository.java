package com.delete.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.delete.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	UserEntity findByName(String name);

	// ✔ Option 1 — Derived delete method (Recommended)
	// Spring Data JPA will automatically generate the delete query:
	// ✔ No annotations needed. 
	// ✔ This will perform: 
	//	(1) SELECT query
	//  (2) DELETE FROM user WHERE email = ? 
	void deleteByEmail(String email);


	// ✔ Option 2 — Custom JPQL delete query	
	// ✔ This will perform:
	// (1) only  DELETE FROM user WHERE email = ? 
	// This is better for performance

//	@Transactional
	@Modifying
	@Query("DELETE FROM UserEntity u WHERE u.email = :email")
	void deleteUserByEmail(@Param("email") String email);
}
