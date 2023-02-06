package com.eh107.cache.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eh107.cache.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
