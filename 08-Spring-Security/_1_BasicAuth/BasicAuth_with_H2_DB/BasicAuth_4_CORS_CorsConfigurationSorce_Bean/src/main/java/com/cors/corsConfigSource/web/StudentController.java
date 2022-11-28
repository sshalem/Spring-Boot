package com.cors.corsConfigSource.web;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cors.corsConfigSource.entity.Student;

@RestController
@RequestMapping("/api")
public class StudentController {

	private static final List<Student> STUDENTS = Arrays.asList(
			new Student(1, "shabtay shalem"),
			new Student(2, "karin shalem"),
			new Student(3, "odel shalem"));

	@GetMapping("/v1/{studentId}")
	public Student getStudent(@PathVariable("studentId") Integer studentId) {
		return STUDENTS.stream().filter(student -> student.getStudentId().equals(studentId)).findFirst()
				.orElseThrow(() -> new IllegalStateException("id not exist"));
	}

	@GetMapping("/app/test")
	public String getTest() {
		return "<h1>test completed</h1>";
	}

	@PostMapping("/post")
	public String createStudent(@RequestBody Student student) {
		System.out.println(student);
		return "POST successfull " + student.getStudentName() + " " + student.getStudentId();
	}

	@PutMapping("/put")
	public String updateStudent(@RequestBody Student student) {
		System.out.println(student);
		return "PUT successfull " + student.getStudentName() + " " + student.getStudentId();
	}

	@DeleteMapping("/delete/{studentId}")
	public String deleteStudent(@PathVariable("studentId") int studentId, @RequestBody Student student) {
		System.out.println(studentId);
		System.out.println(student);
		return "DELETE successfull " + student.getStudentName() + " " + student.getStudentId();
	}
}
