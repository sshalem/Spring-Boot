package com.jpa.one2one.uni.eager.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.one2one.uni.eager.entity.UserEntity;
import com.jpa.one2one.uni.eager.exception.ResourceNotFoundException;
import com.jpa.one2one.uni.eager.repository.AddressRepository;
import com.jpa.one2one.uni.eager.repository.UserRepository;

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
