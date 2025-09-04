package com.jpa.one2many.bi.eager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jpa.one2many.bi.eager.entity.RoleEntity;
import com.jpa.one2many.bi.eager.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	// *****************************************************************************
	// *****************************************************************************
	// *****************************************************************************

	// One Hiberante Query which uses LEFT OUTER JOIN
	UserEntity findById(long id);
	
	// Query with Named Parameters
	@Query("SELECT user from UserEntity user WHERE user.id=:id")
	UserEntity jpqlFindById(@Param("id") long id);
	
	/** Query with Positioned Parameters
	 * @Query("SELECT user from UserEntity user WHERE user.id = ?1")
	 * UserEntity jpqlFindById(long id);
	 */

	@Query(value = "SELECT * FROM USERS_TB WHERE id=:id", nativeQuery = true)
	UserEntity nativeFindById(@Param("id") long id);
	
	// *****************************************************************************
	// *****************************************************************************
	// *****************************************************************************

	UserEntity findByPid(long pid);

	// Query with Named Parameters
	@Query("SELECT user from UserEntity user WHERE user.pid=:pid")
	UserEntity jpqlFindByPid(@Param("pid") long pid);
	
	/** Query with Positioned Parameters
	 * @Query("SELECT user from UserEntity user WHERE user.pid = ?1")
	 * UserEntity jpqlFindByPid(long pid);
	 */
	
	// the * means return all fields
	@Query(value = "SELECT * FROM USERS_TB WHERE pid=:pid", nativeQuery = true)
	UserEntity nativeFindByPid(@Param("pid") long pid);
	
	// *****************************************************************************
	// *****************************************************************************
	// *****************************************************************************

	UserEntity findByName(String name);

	// Query with Named Parameters
	@Query("SELECT user from UserEntity user WHERE user.name=:name")
	UserEntity jpqlFindByName(@Param("name") String name);
	
	/** Query with Positioned Parameters
	 * @Query("SELECT user from UserEntity user WHERE user.name = ?1")
	 * UserEntity jpqlFindByName(String name);
	 */
	
	// the * means return all fields
	@Query(value = "SELECT * FROM USERS_TB WHERE name=:name", nativeQuery = true)
	UserEntity nativeFindByName(@Param("name") String name);
	
	// *****************************************************************************
	// *****************************************************************************
	// *****************************************************************************

	UserEntity findByEmail(String email);

	// Query with Named Parameters
	@Query("SELECT user from UserEntity user WHERE user.email=:email")
	UserEntity jpqlFindByEmail(@Param("email") String email);
	
	/** Query with Positioned Parameters
	 * @Query("SELECT user from UserEntity user WHERE user.email = ?1")
	 * UserEntity jpqlFindByEmail(String email);
	 */
	
	// the * means return all fields
	@Query(value = "SELECT * FROM USERS_TB WHERE email=:email", nativeQuery = true)
	UserEntity nativeFindByEmail(@Param("email") String email);
	
	// *****************************************************************************
	// *****************************************************************************
	// *****************************************************************************
	
	/**
	 * I Must remove from the toString() methods in the Entities the association Entity
	 * Otherwise the queries won't work
	 * Meaning If I have UserEntity with Set<RoleEntity> son't add the RoleEntity to the to String method
	 * We will get Stuck Overflow 
	 */
	@Query("SELECT re from RoleEntity re where re.user.id = ?1 AND re.role like ?2")
	RoleEntity getRoleByIdAndRole(long id, String role);

	@Query(value = "SELECT * "
			+ "FROM USERS_TB utb " 
			+ "LEFT JOIN ROLES_TB rtb " 
			+ "ON rtb.user_id=utb.id "
			+ "WHERE rtb.role=:role" ,nativeQuery = true)
	List<UserEntity> nativeFindUsersWithRoleName(@Param("role") String role);
}
