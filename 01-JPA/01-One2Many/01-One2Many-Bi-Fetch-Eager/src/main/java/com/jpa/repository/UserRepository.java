package com.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jpa.entity.RoleEntity;
import com.jpa.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	// ***************************************
	// *******    find Methods    ************
	// ***************************************	
	
	// Only one Hiberante Query
	UserEntity findById(long id);
	
	UserEntity findByPid(long pid);

	UserEntity findByName(String name);
	
	UserEntity findByEmail(String email);
	
	UserEntity findByPassword(String password);
		
	
	// ***************************************
	// *******    JPQL Query Methods     *****
	// ***************************************
	
	// Two Hiberante Query
	@Query("SELECT user from UserEntity user WHERE user.id=:id")
	UserEntity JPQLfindById(@Param("id") long id);	
	
	// Need to check the QUery with LEFT OUTER JOIN since Hibrenate makes on line of Query
	// @Query("SELECT user from UserEntity user WHERE user.id=:id")
	// UserEntity JPQLJoinLeftOuterfindById(@Param("id") long id);	
	
	
	/**
	 * I Must remove from the toString() methods in the Entities the association Entity
	 * Otherwise the queries won't work
	 * Meaning If I have UserEntity with Set<RoleEntity> son't add the RoleEntity to the to String method
	 * We will get Stuck Overflow 
	 */
	@Query("SELECT re from RoleEntity re where re.user.id = ?1 AND re.role like ?2")
	RoleEntity getRoleByIdAndRole(long id, String role);

	
	

}
