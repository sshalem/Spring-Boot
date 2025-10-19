package com.spring.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.jpa.entity.RoleEntity;
import com.spring.jpa.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	/**
	 * I Must remove from the toString() methods in the Entities the association Entity
	 * Otherwise the queries won't work
	 * Meaning If I have UserEntity with Set<RoleEntity> son't add the RoleEntity to the to String method
	 * We will get Stuck Overflow 
	 */
	
	
	// One Hiberante Query which uses LEFT OUTER JOIN
	UserEntity findById(long id);

	@Query("SELECT user from UserEntity user WHERE user.id=:id")
	UserEntity jpqlFindById(@Param("id") long id);
	
	/**
	 * @Query("SELECT user from UserEntity user WHERE user.id = ?1")
	 * UserEntity jpqlFindById(long id);
	 */

	@Query(value = "SELECT * FROM USERS_TB WHERE id=:id", nativeQuery = true)
	UserEntity nativeFindById(@Param("id") long id);
	
	/**
	 *  
	 */

	UserEntity findByPid(long pid);

	@Query("SELECT user from UserEntity user WHERE user.pid=:pid")
	UserEntity jpqlFindByPid(@Param("pid") long pid);
	
	/**
	 * @Query("SELECT user from UserEntity user WHERE user.pid = ?1")
	 * UserEntity jpqlFindByPid(long pid);
	 */
	
	// the * means return all fields
	@Query(value = "SELECT * FROM USERS_TB WHERE pid=:pid", nativeQuery = true)
	UserEntity nativeFindByPid(@Param("pid") long pid);
	
	/**
	 *  
	 */

	UserEntity findByName(String name);

	@Query("SELECT user from UserEntity user WHERE user.name=:name")
	UserEntity jpqlFindByName(@Param("name") String name);
	
	/**
	 * @Query("SELECT user from UserEntity user WHERE user.name = ?1")
	 * UserEntity jpqlFindByName(String name);
	 */
	
	// the * means return all fields
	@Query(value = "SELECT * FROM USERS_TB WHERE name=:name", nativeQuery = true)
	UserEntity nativeFindByName(@Param("name") String name);
	
	/**
	 *  
	 */

	UserEntity findByEmail(String email);

	@Query("SELECT user from UserEntity user WHERE user.email=:email")
	UserEntity jpqlFindByEmail(@Param("email") String email);
	
	/**
	 * @Query("SELECT user from UserEntity user WHERE user.email = ?1")
	 * UserEntity jpqlFindByEmail(String email);
	 */
	
	// the * means return all fields
	@Query(value = "SELECT * FROM USERS_TB WHERE email=:email", nativeQuery = true)
	UserEntity nativeFindByEmail(@Param("email") String email);
	
	/**
	 *  
	 */

	@Query(value = "SELECT * "
			+ "FROM USERS_TB utb " 
			+ "LEFT JOIN ROLES_TB rtb " 
			+ "ON rtb.user_id=utb.id "
			+ "WHERE rtb.role=:role" ,nativeQuery = true)
	List<UserEntity> nativeFindUsersWithRoleName(@Param("role") String role);
	
	/**
	 * 
	 */
	
	@Query("SELECT re from RoleEntity re where re.user.id = ?1 AND re.role like ?2")
	RoleEntity getRoleByIdAndRole(long id, String role);
	
	@Query("SELECT re from RoleEntity re WHERE re.user.id=:id AND re.role=:role")
	RoleEntity getRoleByIdAndRoleParamQuery(@Param("id")long id, @Param("role") String role);
		
	@Query("SELECT re from RoleEntity re WHERE re.user.id = ?1 AND re.role like ?2")
	RoleEntity getRoleByIdAndRoleLikeOperator(long id, String role);

	@Query("SELECT re from RoleEntity re WHERE re.user.id=:id AND re.role LIKE :role")
	RoleEntity getRoleByIdAndRoleLikeOperatorParamQuery(@Param("id")long id, @Param("role") String role);
	
}
