package com.emac.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.emac.entity.UserEntity;

@Repository
public interface UserRepo extends CrudRepository<UserEntity, Long> {

	UserEntity findByFirstName(String firstname);
}
