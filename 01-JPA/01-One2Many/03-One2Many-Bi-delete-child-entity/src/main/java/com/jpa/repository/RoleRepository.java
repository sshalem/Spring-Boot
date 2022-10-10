package com.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jpa.entity.RoleEntity;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

	

	/**
	 * I Must remove from the toString() methods in the Entities the associationEntity
	 * Otherwise the queries won't work
	 * Meaning If I have UserEntity with Set<RoleEntity> son't add the RoleEntity to the to String method
	 * We will get Stuck Overflow 
	 */ 
	
	RoleEntity findByPidAndRole(long pid, String role);
	
	@Query("SELECT r FROM RoleEntity r WHERE r.pid=:pid AND r.role=:role")
	RoleEntity findRole(@Param("pid") long pid, @Param("role") String role);

	@Modifying
	@Query("delete from RoleEntity re where re.pid=:pid AND re.role=:role")
	void deleteUserRole(@Param("pid") long pid, @Param("role") String role);

}
