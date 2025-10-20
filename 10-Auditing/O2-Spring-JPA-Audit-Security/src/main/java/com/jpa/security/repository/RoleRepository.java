package com.jpa.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jpa.security.entity.RoleEntity;
import com.jpa.security.entity.UserEntity;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

	RoleEntity findByRole(String role);

	@Query("SELECT re FROM RoleEntity re JOIN re.users AS reuse WHERE reuse.email = ?1")
	List<RoleEntity> jpqlFindRolesOfUserByEmail(String email);

	@Query("SELECT reuse FROM RoleEntity re JOIN re.users AS reuse WHERE re.role=:role")
	List<UserEntity> jpqlFindUsersWithRole(@Param("role") String role);

}
