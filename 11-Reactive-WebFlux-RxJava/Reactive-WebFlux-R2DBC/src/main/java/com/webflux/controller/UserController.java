package com.webflux.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.webflux.entity.Users;
import com.webflux.service.UsersService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UsersService usersService;

	@GetMapping(value = "/users", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Flux<Users> findAllUsers() {
		return usersService.getUsers();
	}

	@GetMapping("/user/{id}")
	public Mono<Users> findUserById(@PathVariable long id) {
		return usersService.getUserById(id);
	}

	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	public void saveUser(@RequestBody Users users) {
		usersService.addUser(users);
	}

	@PutMapping("/update")
	@ResponseStatus(HttpStatus.OK)
	public Mono<Users> updateUser(@RequestBody Users user) {
		return usersService.updateUsers(user);
	}

	@DeleteMapping("/user/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Mono<Void> deleteUser(@PathVariable long id) {
		return usersService.deleteUser(id);
	}

}
