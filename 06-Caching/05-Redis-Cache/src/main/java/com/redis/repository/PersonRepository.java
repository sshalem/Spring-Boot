package com.redis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.redis.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
