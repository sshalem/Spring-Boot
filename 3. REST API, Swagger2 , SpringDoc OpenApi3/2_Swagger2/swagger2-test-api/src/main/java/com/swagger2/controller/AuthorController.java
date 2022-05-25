package com.swagger2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swagger2.dao.AuthorDaoImpl;
import com.swagger2.entity.Author;

@RestController
@RequestMapping("/project/author")
public class AuthorController {

	@Autowired
	private AuthorDaoImpl authorDaoImpl;

	@PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
		Author createAuthor = authorDaoImpl.createAuthor(author);
		return new ResponseEntity<Author>(createAuthor, HttpStatus.CREATED);
	}

	@GetMapping(path = "/get/{firstname}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Author> getAuthorByFirstName(@PathVariable("firstname") String firstname) {
		Author author = authorDaoImpl.getAuthorFirstName(firstname);
		return new ResponseEntity<Author>(author, HttpStatus.CREATED);
	}

	@GetMapping(path = "/get/{lastname}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Author> getAuthorByLastName(@PathVariable("lastname") String lastname) {
		Author author = authorDaoImpl.getAuthorLastName(lastname);
		return new ResponseEntity<Author>(author, HttpStatus.CREATED);
	}
}
