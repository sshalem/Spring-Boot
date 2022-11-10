package com.jpa.many2many.bi.lazy.dao;

import java.util.List;

import com.jpa.many2many.bi.lazy.dto.StudentDto;
import com.jpa.many2many.bi.lazy.entity.CourseEntity;
import com.jpa.many2many.bi.lazy.entity.StudentEntity;

public interface StudentDao {

	/**
	 * Create
	 */
	StudentEntity createStudent(StudentEntity studentEntity);

	/**
	 * Read
	 */
	List<StudentEntity> getStudentsByFirstName(String firstName);

	List<StudentEntity> getStudentsByLastName(String lastName);

	StudentEntity getStudentByIdentityNumber(int identityNumber);

	StudentEntity getStudentByEmail(String email);

	List<StudentEntity> getStudentsThatLearnedCoursesInLearningYear(int learningYear);

	List<StudentEntity> getStudentsWhoLearedCourseName(String courseName);
	
	List<CourseEntity> getAllCoursesOfStudentByIdentityNumber(int identityNumber);
	
	List<StudentDto> getStudentsWhoTookCourseInLearningYear(int learningYear, String courseNumber);

	/**
	 * Update
	 */
	StudentEntity updateStudentDetails(int identityNumber, StudentEntity studentEntity);

	StudentEntity addCourseToStudent(int identityNumber, String courseNumber);

	/**
	 * Delete
	 */
	void removeStudentByFirstName(String firstName);

	void removeCourseFromStudentByCourseNumber(int identityNumber, String courseNumber);

	void removeAllCoursesFromStudent(int identityNumber);

	void removeAllStudents();

}