package com.jpa.repository;

import java.util.List;

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
	
	// *****************************************************************************
	// *****************************************************************************
	// *****************************************************************************

	List<RoleEntity> findById(long id);

	@Query("SELECT role FROM RoleEntity role WHERE role.id=:id")
	List<RoleEntity> jpqlFindById(@Param("id") long id);
		
	/**
	 * @Query("SELECT role FROM RoleEntity role WHERE role.id = ?1")
	 * List<RoleEntity> jpqlFindById(long id);
	 */

	@Query(value = "SELECT * FROM ROLES_TB WHERE id=:id", nativeQuery = true)
	List<RoleEntity> nativeFindById(@Param("id") long id);

	// *****************************************************************************
	// *****************************************************************************
	// *****************************************************************************

	List<RoleEntity> findByRole(String role);

	@Query("SELECT r FROM RoleEntity r WHERE r.role=:role")
	List<RoleEntity> jpqlFindByRole(@Param("role") String role);
		
	/**
	 * @Query("SELECT r FROM RoleEntity r WHERE r.role = ?1")
	 * List<RoleEntity> jpqlFindByRole(String role);
	 */

	@Query(value = "SELECT * FROM ROLES_TB WHERE role=:role", nativeQuery = true)
	List<RoleEntity> nativeFindByRole(@Param("role") String role);

	// *****************************************************************************
	// *****************************************************************************
	// *****************************************************************************
	
	List<RoleEntity> findByPid(long pid);

	@Query("SELECT role FROM RoleEntity role WHERE role.pid=:pid")
	List<RoleEntity> jpqlFindByPid(@Param("pid") long pid);
		
	/**
	 * @Query("SELECT role from RoleEntity role WHERE role.pid = ?1")
	 * List<RoleEntity> jpqlFindByPid(long pid);
	 */

	@Query(value = "SELECT * FROM ROLES_TB WHERE pid=:pid", nativeQuery = true)
	List<RoleEntity> nativeFindByPid(@Param("pid") long pid);

	// *****************************************************************************
	// *****************************************************************************
	// *****************************************************************************	
	
	RoleEntity findByPidAndRole(long pid, String role);
	
	@Query("SELECT r FROM RoleEntity r WHERE r.pid=:pid AND r.role=:role")
	RoleEntity jpqlFindRoleByPidAndRoleName(@Param("pid") long pid, @Param("role") String role);

	@Modifying
	@Query("delete from RoleEntity re where re.pid=:pid AND re.role=:role")
	void jpqlDeleteUserRoleByPidAndRoleName(@Param("pid") long pid, @Param("role") String role);

}
