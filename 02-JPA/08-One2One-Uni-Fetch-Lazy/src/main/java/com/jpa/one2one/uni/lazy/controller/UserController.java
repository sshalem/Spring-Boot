package com.jpa.one2one.uni.lazy.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.jpa.one2one.uni.lazy.entity.UserEntity;
import com.jpa.one2one.uni.lazy.exception.ResourceNotFoundException;
import com.jpa.one2one.uni.lazy.repository.AddressRepository;
import com.jpa.one2one.uni.lazy.repository.UserRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
@Transactional
public class UserController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	private AddressRepository addressRepository;

	
	// *******************************
	//       POST (Create) methods
	//********************************
	
	@PostMapping("/users")
	public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
		
		UserEntity _user = userRepository
				.save(new UserEntity(
						user.getName(), 
						user.getEmail(), 
						user.isPublished()));
		return new ResponseEntity<>(_user, HttpStatus.CREATED);
	}

	
	// *******************************
	//       GET methods
	//********************************
	
	@GetMapping("/users/{id}")
	public ResponseEntity<UserEntity> getUserById(@PathVariable("id") long id) {
		
		UserEntity userEntity = userRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found User with id = " + id));

		return new ResponseEntity<>(userEntity, HttpStatus.OK);
	}

	
	@GetMapping("/users/published")
	public ResponseEntity<List<UserEntity>> getUsersByPublished() {
		List<UserEntity> users = userRepository.findUsersByPublished(true);

		if (users.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<UserEntity>> getAllUsers(@RequestParam(required = false) String name) {
		
		List<UserEntity> users = new ArrayList<UserEntity>();

		if (name == null) {
			userRepository.findAll().forEach(users::add);
		}			
		else {
			userRepository.findByNameContaining(name).forEach(users::add);
		}			

		if (users.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	// *******************************
	//       UPDATE methods
	//********************************
	
	@PutMapping("/users/{id}")
	public ResponseEntity<UserEntity> updateUser(@PathVariable("id") long id, @RequestBody UserEntity user) {
		
		UserEntity _user = userRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found User with id = " + id));

		_user.setName(user.getName());
		_user.setEmail(user.getEmail());
		_user.setPublished(user.isPublished());

		return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
	}

	// *******************************
	//       DELETE methods
	//********************************
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<HttpStatus> deleteUserById(@PathVariable("id") long id) {
		
		/**
		 * No Need to remove Address , before removing user.
		 * We can Immediately remove a User 
		 */
		if (addressRepository.existsById(id)) {
			addressRepository.deleteById(id);
		}
		userRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/users/delete/all")
	public ResponseEntity<HttpStatus> deleteAllUsers() {
		
		userRepository.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
