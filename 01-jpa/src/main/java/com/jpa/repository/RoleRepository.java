package com.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jpa.entity.RoleEntity;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

//	RoleEntity findByPidAndRole(long pid, String role);
	
//	@Query("SELECT r FROM RoleEntity r WHERE r.pid=:pid AND r.role=:role")
//	RoleEntity findRole(@Param("pid") long pid, @Param("role") String role);

}
