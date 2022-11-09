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
import org.springframework.web.bind.annotation.PutMapping;
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
	public ResponseEntity<?> getStudentByIdentityNumber(@PathVariable("identityNumber") int identityNumber) {
		return new ResponseEntity<>(studentDaoImpl.getStudentByIdentityNumber(identityNumber), HttpStatus.OK);
	}

	@GetMapping(path = "/getStudentByEmail/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getStudentByEmail(@PathVariable("email") String email) {
		return new ResponseEntity<>(studentDaoImpl.getStudentByEmail(email), HttpStatus.OK);
	}

	@GetMapping(path = "/getStudentsThatLearnedCoursesInLearningYear/{learningYear}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getStudentsWhoLearnInLearningYear(@PathVariable("learningYear") int learningYear) {

		List<StudentEntity> _students = studentDaoImpl.getStudentsThatLearnedCoursesInLearningYear(learningYear);
		if (_students.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(_students, HttpStatus.OK);
	}

	@GetMapping(path = "/getStudentsWhoLearedCourseName/{courseName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getStudentsWhoLearedCourseName(@PathVariable("courseName") String courseName) {

		List<StudentEntity> _students = studentDaoImpl.getStudentsWhoLearedCourseName(courseName);
		if (_students.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(_students, HttpStatus.OK);
	}
	 
	 
	// *******************************
	// UPDATE methods
	// ********************************
	@PutMapping(path = "/updateStudentDetails/{identityNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateStudentDetails(@PathVariable("identityNumber") int identityNumber,@RequestBody StudentEntity studentEntity) {

		StudentEntity _student = studentDaoImpl.updateStudentDetails(identityNumber,studentEntity);
				
		return new ResponseEntity<>(_student, HttpStatus.OK);
	}
	
	@PutMapping(path = "/addCourseToStudent/{identityNumber}/{courseNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addCourseToStudent(@PathVariable("identityNumber") int identityNumber,@PathVariable("courseNumber") String courseNumber) {

		StudentEntity _student = studentDaoImpl.addCourseToStudent(identityNumber, courseNumber);
				
		return new ResponseEntity<>(_student, HttpStatus.OK);
	}
	
	
	// *******************************
	// DELETE methods
	// ********************************

}
