package com.in28min.Jwt.H2.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28min.Jwt.H2.entity.Todo;
import com.in28min.Jwt.H2.service.TodoDaoImpl;

@RestController
@CrossOrigin("http://localhost:4200")
public class TodosResource {

	@Autowired
	private TodoDaoImpl todoDaoImpl;

	@GetMapping("/users/{username}/todos")
	public List<Todo> getAllTodos(@PathVariable("username") String username) {
		return todoDaoImpl.getAllTodoPerUsername(username);
	}

	@DeleteMapping("/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable("username") String username, @PathVariable("id") long id) {
		// since I use no Content back thus i have VOid in the arrow braces
		todoDaoImpl.deleteTodoById(id);
		Optional<Todo> todo = todoDaoImpl.getTodoById(id);
		if (todo != null) {
			return ResponseEntity.noContent().build();
		}
//		return ResponseEntity.notFound().build();
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/users/{username}/todos/{id}")
	public Todo getTodo(@PathVariable("username") String username, @PathVariable("id") long id) {
		return todoDaoImpl.getTodoById(id).get();
	}

	@PutMapping("/users/{username}/todos/{id}")
	public ResponseEntity<Todo> updateTodo(@RequestBody Todo todo, @PathVariable("username") String username,
			@PathVariable("id") long id) {
		Todo savedTodo = todoDaoImpl.updateTodo(todo);
		return new ResponseEntity<Todo>(savedTodo, HttpStatus.OK);
	}

	@PostMapping("/users/{username}/todos")
	public ResponseEntity<Void> createTodo(@RequestBody Todo todo, @PathVariable("username") String username) {
		
		todo.setUsername(username);
		Todo createdTodo = todoDaoImpl.createTodo(todo);
		// When Creating , we can return the current resource url
		// So we use the class of 'ServletUriComponentsBuilder'

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdTodo.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

}
