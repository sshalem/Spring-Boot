package com.in28min.Jwt.controller;

import java.net.URI;
import java.util.List;

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

import com.in28min.Jwt.entity.Todo;
import com.in28min.Jwt.service.TodoHardcodedService;

@RestController
@CrossOrigin("http://localhost:4200")
public class TodosResource {

	@Autowired
	private TodoHardcodedService todoHardcodedService;

	@GetMapping("/users/{username}/todos")
	public List<Todo> getAllTodos(@PathVariable("username") String username) {
		return todoHardcodedService.findAll();
	}

	@DeleteMapping("/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable("username") String username, @PathVariable("id") long id) {
		// since I use no Content back thus i have VOid in the arrow braces
		Todo todo = todoHardcodedService.deleteById(id);
		if (todo != null) {
			return ResponseEntity.noContent().build();
		}
//		return ResponseEntity.notFound().build();
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/users/{username}/todos/{id}")
	public Todo getTodo(@PathVariable("username") String username, @PathVariable("id") long id) {
		return todoHardcodedService.findById(id);
	}

	@PutMapping("/users/{username}/todos/{id}")
	public ResponseEntity<Todo> updateTodo(@RequestBody Todo todo, @PathVariable("username") String username,
			@PathVariable("id") long id) {
		Todo savedTodo = todoHardcodedService.save(todo);
		return new ResponseEntity<Todo>(savedTodo, HttpStatus.OK);
	}

	@PostMapping("/users/{username}/todos")
	public ResponseEntity<Void> createTodo(@RequestBody Todo todo, @PathVariable("username") String username) {
		System.out.println(todo);

		Todo createdTodo = todoHardcodedService.save(todo);
		// When Creating , we can return the current resource url
		// So we use the class of 'ServletUriComponentsBuilder'

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdTodo.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

}
