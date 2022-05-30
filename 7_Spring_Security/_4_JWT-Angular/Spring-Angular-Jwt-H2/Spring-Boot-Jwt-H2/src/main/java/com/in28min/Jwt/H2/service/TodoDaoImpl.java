package com.in28min.Jwt.H2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in28min.Jwt.H2.entity.Todo;
import com.in28min.Jwt.H2.repository.TodoRepository;

@Service
public class TodoDaoImpl implements TodoDao {

	@Autowired
	private TodoRepository todoRepo;

	@Override
	public Todo createTodo(Todo todo) {
		return todoRepo.save(todo);
	}

	@Override
	public Optional<Todo> getTodoById(long id) {
		return todoRepo.findById(id);
	}

	@Override
	public Todo updateTodo(Todo todo) {
		return todoRepo.save(todo);
	}

	@Override
	public void deleteTodoById(long id) {
		todoRepo.deleteById(id);
	}

	@Override
	public List<Todo> getAllTodoPerUsername(String username) {
		return todoRepo.findAllByUsername(username);
	}

}
