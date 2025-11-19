package com.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.entity.RoleEntity;
import com.backend.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	UserEntity findByEmail(String email);

	List<UserEntity> findByName(String name);

	// Important: fetch join to initialize lazy roles in the same query
	// Always use DISTINCT in the QUERY when doing JOIN FETCH to avoid duplications
	@Query("SELECT DISTINCT ue FROM UserEntity ue LEFT JOIN FETCH ue.roles WHERE ue.email=:email")
	UserEntity findByEmailWithRoles(@Param("email") String email);

	@Query("SELECT ue FROM UserEntity ue JOIN ue.roles AS rl WHERE rl.role=:role")
	List<UserEntity> jpqlFindUsersWithRole(@Param("role") String role);

	@Query("SELECT ur FROM UserEntity user JOIN user.roles AS ur WHERE user.email=:email")
	List<RoleEntity> jpqlFindAllRolesOfUserByEmail(@Param("email") String email);
}
