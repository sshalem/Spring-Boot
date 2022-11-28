package com.security.app.web;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.app.entity.Student;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class StudentsController {

	private static final List<Student> STUDENTS = Arrays.asList(
			new Student(1, "shabtay shalem"),
			new Student(2, "karin shalem"), 
			new Student(3, "odel shalem"));

	@GetMapping("/v1/{studentId}")
	public Student getStudent(@PathVariable("studentId") Integer studentId) {
		return STUDENTS
				.stream()
				.filter(student -> student.getStudentId().equals(studentId))
				.findFirst()
				.orElseThrow(()-> new IllegalStateException("id not exist"));
	}	
	
	@GetMapping("/app/test")
	public String getTest() {
		return "<h1>test completed</h1>";
	}
	
	@PostMapping("/v1/post")
	public void createStudent(@RequestBody Student student) {
		System.out.println(student);
	}
	
}
