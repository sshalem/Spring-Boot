package com.webflux.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.webflux.entity.Users;

@Repository
public interface UsersRepository extends ReactiveCrudRepository<Users, Long> {

}
