package com.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.entity.RefreshTokenEntity;
import com.backend.entity.UserEntity;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {

	Optional<RefreshTokenEntity> findByToken(String token);

	@Query("SELECT rteuse FROM RefreshTokenEntity rte JOIN rte.userEntity AS rteuse WHERE rte.token=:token")
	UserEntity findUserByRefreshToken(@Param("token") String token);
	
	@Query("SELECT rte FROM RefreshTokenEntity rte JOIN rte.userEntity AS rteuse WHERE rteuse.id=:userId")
	RefreshTokenEntity findRefreshTokenEntityByUserId(@Param("userId") long userId);
}
