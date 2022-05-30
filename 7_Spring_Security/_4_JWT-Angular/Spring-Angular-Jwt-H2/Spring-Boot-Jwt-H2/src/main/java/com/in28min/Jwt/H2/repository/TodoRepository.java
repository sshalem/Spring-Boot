package com.in28min.Jwt.H2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in28min.Jwt.H2.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long>{
 
	List<Todo> findAllByUsername(String username);
}
