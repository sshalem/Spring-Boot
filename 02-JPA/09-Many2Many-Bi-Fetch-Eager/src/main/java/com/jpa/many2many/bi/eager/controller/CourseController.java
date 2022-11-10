package com.jpa.many2many.bi.eager.controller;

import java.time.LocalDate;
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

import com.jpa.many2many.bi.eager.dao.CourseDaoImpl;
import com.jpa.many2many.bi.eager.entity.CourseEntity;

@RestController
@RequestMapping("/course")
@CrossOrigin("*")
public class CourseController {

	@Autowired
	private CourseDaoImpl courseDaoImpl;

	// *******************************
	// POST (Create) methods
	// ********************************

	@PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createCourse(@RequestBody CourseEntity courseEntity) {
		CourseEntity _course = courseDaoImpl.createCourse(courseEntity);
		return new ResponseEntity<>(_course, HttpStatus.CREATED);
	}

	// *******************************
	// GET methods
	// ********************************

	@GetMapping(path = "/getCourseByCourseNumber/{courseNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCourseByCourseNumber(@PathVariable("courseNumber") String courseNumber) {
		CourseEntity _course = courseDaoImpl.getCourseByCourseNumber(courseNumber);
		return new ResponseEntity<>(_course, HttpStatus.OK);
	}
	
	@GetMapping(path = "/getCourseByCourseName/{courseName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCourseByCourseName(@PathVariable("courseName") String courseName) {
		CourseEntity _course = courseDaoImpl.getCourseByCourseName(courseName);
		return new ResponseEntity<>(_course, HttpStatus.OK);
	}
	
	@GetMapping(path = "/getCoursesByLearningYear/{learningYear}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCoursesByLearningYear(@PathVariable("learningYear") int learningYear) {
		
		List<CourseEntity> _courses = courseDaoImpl.getCoursesByLearningYear(learningYear);		
		if(_courses.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);		
		return new ResponseEntity<>(_courses, HttpStatus.OK);
	}

	@GetMapping(path = "/getCoursesByStartDateBetween/{fromStartDate}/{toStartDate}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCoursesByStartDateBetween(@PathVariable("fromStartDate") LocalDate fromStartDate,@PathVariable("toStartDate") LocalDate toStartDate) {
		
		List<CourseEntity> _courses = courseDaoImpl.getCoursesByStartDateBetween(fromStartDate, toStartDate);
		if(_courses.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(_courses, HttpStatus.OK);
	}
	
	@GetMapping(path = "/getCoursesByEndDateBetween/{fromEndDate}/{toEndDate}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCoursesByEndDateBetween(@PathVariable("fromEndDate") LocalDate fromEndDate,@PathVariable("toEndDate") LocalDate toEndDate) {
		
		List<CourseEntity> _courses = courseDaoImpl.getCoursesByEndDateBetween(fromEndDate, toEndDate);
		if(_courses.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(_courses, HttpStatus.OK);
	}
	
	@GetMapping(path = "/getCoursesBetweenStartDateAndEndDate/{startDate}/{endDate}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCoursesBetweenStartDateAndEndDate(@PathVariable("startDate") LocalDate startDate, @PathVariable("endDate") LocalDate endDate) {
		
		List<CourseEntity> _courses = courseDaoImpl.getCoursesBetweenStartDateAndEndDate(startDate, endDate);
		if(_courses.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(_courses, HttpStatus.OK);
	}
}
