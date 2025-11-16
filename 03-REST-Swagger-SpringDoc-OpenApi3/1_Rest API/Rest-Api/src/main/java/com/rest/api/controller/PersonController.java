package com.rest.api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rest.api.entity.Person;
import com.rest.api.service.PersonServiceImpl;

@RestController
@RequestMapping(path = "/api")
public class PersonController {

	@Autowired
	private PersonServiceImpl personServiceImpl;

	@GetMapping(path = "/getMethod/{id}/{firstName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Person getPerson(@PathVariable String firstName, @PathVariable long id) {
		return new Person("shab", "sayhsc", 159, "male");
	}

	@PostMapping(path = "/postMethod", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Person createPerson(@RequestBody Person person) {
		System.out.println(person);
		return person;
	}

	@GetMapping(path = "/getAll")
	public ResponseEntity<?> getAllPersons(@RequestParam long id, @RequestParam String firsName) {
		return null;
	}

	@GetMapping(path = "/getAll/required")
	public ResponseEntity<?> getAllPersonsRequired(@RequestParam(required = false) Long id,
			@RequestParam(required = true, defaultValue = "shalem") String firsName) {
		System.out.println(id);
		System.out.println(firsName);
		return ResponseEntity.ok().body(personServiceImpl.getAllPersons());
	}

	@GetMapping(path = "/get/listParam")
	public ResponseEntity<?> getListParam(@RequestParam List<String> tag) {
		tag.forEach(i -> System.out.println(i));
		return null;
	}

	@GetMapping(path = "/get/mapParam")
	public ResponseEntity<?> getMapParam(@RequestParam Map<Object, Object> params) {
		params.forEach((i,j) -> {			
			System.out.println(i + " : " + j);
		});
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
}
