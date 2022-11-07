package com.jpa.many2many.bi.eager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.many2many.bi.eager.dao.StudentDaoImpl;
import com.jpa.many2many.bi.eager.entity.StudentEntity;

@RestController
@RequestMapping("/student")
@CrossOrigin("*")
public class StudentController {

	@Autowired
	private StudentDaoImpl studentDaoImpl;

	// *******************************
	// POST (Create) methods
	// ********************************
	@PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createStudent(@RequestBody StudentEntity studentEntity) {
		return new ResponseEntity<>(studentDaoImpl.createStudent(studentEntity), HttpStatus.CREATED);
	}

	// *******************************
	// GET methods
	// ********************************
	@GetMapping(path = "/getStudentsByFirstName/{firstName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getStudentsByFirstName(@PathVariable("firstName") String firstName) {

		List<StudentEntity> _students = studentDaoImpl.getStudentsByFirstName(firstName);
		if (_students.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(_students, HttpStatus.OK);
	}

	@GetMapping(path = "/getStudentsByLastName/{lastName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getStudentsByLastName(@PathVariable("lastName") String lastName) {

		List<StudentEntity> _students = studentDaoImpl.getStudentsByLastName(lastName);
		if (_students.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(_students, HttpStatus.OK);
	}

	@GetMapping(path = "/getStudentByIdentityNumber/{identityNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getStudentByIdentityNumber(@PathVariable("identityNumber") long identityNumber) {
		return new ResponseEntity<>(studentDaoImpl.getStudentByIdentityNumber(identityNumber), HttpStatus.OK);
	}

	@GetMapping(path = "/getStudentByEmail/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getStudentByEmail(@PathVariable("email") String email) {
		return new ResponseEntity<>(studentDaoImpl.getStudentByEmail(email), HttpStatus.OK);
	}

	// *******************************
	// UPDATE methods
	// ********************************

	// *******************************
	// DELETE methods
	// ********************************

}
