package com.in28min.Jwt.H2.service;

import java.util.List;
import java.util.Optional;

import com.in28min.Jwt.H2.entity.Todo;

public interface TodoDao {
	
	Todo createTodo(Todo todo);
	
	Optional<Todo> getTodoById(long id);
	
	Todo updateTodo(Todo todo);
	
	void deleteTodoById(long id);
	
	List<Todo> getAllTodoPerUsername(String username);
}
