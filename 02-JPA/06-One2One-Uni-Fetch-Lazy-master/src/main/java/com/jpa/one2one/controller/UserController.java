package com.jpa.one2one.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jpa.one2one.entity.UserEntity;
import com.jpa.one2one.exception.ResourceNotFoundException;
import com.jpa.one2one.repository.AddressRepository;
import com.jpa.one2one.repository.UserRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	private AddressRepository detailsRepository;

	
	// *******************************
	//       POST (Create) methods
	//********************************
	
	@PostMapping("/users")
	public ResponseEntity<UserEntity> createTutorial(@RequestBody UserEntity user) {
		
		UserEntity _tutorial = userRepository
				.save(new UserEntity(
						user.getName(), 
						user.getEmail(), 
						user.isPublished()));
		return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
	}

	
	// *******************************
	//       GET methods
	//********************************
	
	@GetMapping("/users/{id}")
	public ResponseEntity<UserEntity> getUserById(@PathVariable("id") long id) {
		
		UserEntity tutorial = userRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));

		return new ResponseEntity<>(tutorial, HttpStatus.OK);
	}

	
	@GetMapping("/users/published")
	public ResponseEntity<List<UserEntity>> findByPublished() {
		List<UserEntity> tutorials = userRepository.findByPublished(true);

		if (tutorials.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(tutorials, HttpStatus.OK);
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<UserEntity>> getAllTutorials(@RequestParam(required = false) String name) {
		
		List<UserEntity> users = new ArrayList<UserEntity>();

		if (name == null) {
			userRepository.findAll().forEach(users::add);
		}			
		else {
			userRepository.findByUserContaining(name).forEach(users::add);
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
	public ResponseEntity<UserEntity> updateTutorial(@PathVariable("id") long id, @RequestBody UserEntity user) {
		
		UserEntity _user = userRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));

		_user.setName(user.getName());
		_user.setEmail(user.getEmail());
		_user.setPublished(user.isPublished());

		return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
	}

	// *******************************
	//       DELETE methods
	//********************************
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
		
		if (detailsRepository.existsById(id)) {
			detailsRepository.deleteById(id);
		}
		userRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/tutorials")
	public ResponseEntity<HttpStatus> deleteAllTutorials() {
		
		userRepository.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
