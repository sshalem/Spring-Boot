package com.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jpa.entity.RoleEntity;
import com.jpa.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	UserEntity findByPid(long pid);

	UserEntity findByName(String name);
	
	/**
	 * I Must remove from the toString() methods in the Entities the association Entity
	 * Otherwise the queries won't work
	 * Meaning If I have UserEntity with Set<RoleEntity> son't add the RoleEntity to the to String method
	 * We will get Stuck Overflow 
	 */
	@Query("SELECT re from RoleEntity re where re.user.id = ?1 AND re.role like ?2")
	RoleEntity getRoleByIdAndRole(long id, String role);

	

}
