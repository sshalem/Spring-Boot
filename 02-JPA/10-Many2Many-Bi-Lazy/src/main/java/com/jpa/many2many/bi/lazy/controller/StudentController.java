package com.jpa.many2many.bi.lazy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.many2many.bi.lazy.dao.StudentDaoImpl;
import com.jpa.many2many.bi.lazy.entity.CourseEntity;
import com.jpa.many2many.bi.lazy.entity.StudentEntity;

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
	 
	@GetMapping(path = "/getAllCoursesOfStudentByIdentityNumber/{identityNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllCoursesOfStudentByIdentityNumber(@PathVariable("identityNumber") int identityNumber) {

		List<CourseEntity> _courses = studentDaoImpl.getAllCoursesOfStudentByIdentityNumber(identityNumber);
		if (_courses.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(_courses, HttpStatus.OK);
	}
	
	@GetMapping(path = "/getStudentsWhoTookCourseInLearningYear/{learningYear}/{courseNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getStudentsWhoTookCourseInLearningYear(@PathVariable("learningYear") int learningYear, @PathVariable("courseNumber") String courseNumber) {

		List<StudentEntity> _students = studentDaoImpl.getStudentsWhoTookCourseInLearningYear(learningYear, courseNumber);
		
		if (_students.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(_students, HttpStatus.OK);
	}
	
	@GetMapping(path = "getAllStudents", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllStudents() {
		return new ResponseEntity<>(studentDaoImpl.getAllStudents(), HttpStatus.OK);
	}
	 
	// ********************************
	// 		UPDATE methods
	// ********************************
	@PutMapping(path = "/updateStudentDetails/{identityNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateStudentDetails(@PathVariable("identityNumber") int identityNumber, @RequestBody StudentEntity studentEntity) {

		StudentEntity _student = studentDaoImpl.updateStudentDetails(identityNumber,studentEntity);
				
		return new ResponseEntity<>(_student, HttpStatus.OK);
	}	
	
	// ********************************
	// 		DELETE methods
	// ********************************
	@DeleteMapping(path = "/deleteStudentByIdentityNumber/{identityNumber}")
	public void deleteStudentByIdentityNumber(@PathVariable("identityNumber") int identityNumber) {
		
		studentDaoImpl.deleteStudentByIdentityNumber(identityNumber);		
	}
	
	/**
	 * Need to check this API why its not working
	 */
	@DeleteMapping(path = "/removeAllStudentsFromCourse/{courseNumber}")
	public ResponseEntity<Void> removeAllStudentsFromCourse(@PathVariable("courseNumber") String courseNumber){
		
		studentDaoImpl.removeAllStudentsFromCourse(courseNumber);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping(path = "/deleteAllStudents")
	public ResponseEntity<Void> deleteAllStudents() {
		
		studentDaoImpl.deleteAllStudents();		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
