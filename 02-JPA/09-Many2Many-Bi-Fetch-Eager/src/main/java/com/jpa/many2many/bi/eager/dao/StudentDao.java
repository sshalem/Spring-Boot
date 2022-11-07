package com.jpa.many2many.bi.eager.dao;

import java.util.List;

import com.jpa.many2many.bi.eager.entity.CourseEntity;
import com.jpa.many2many.bi.eager.entity.StudentEntity;

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

	StudentEntity getStudentByIdentityNumber(long identityNumber);

	StudentEntity getStudentByEmail(String email);

	List<StudentEntity> getStudentsWhoLearnInLearningYear(long learningYear);

	/**
	 * Update
	 */
	StudentEntity updateStudentDetails(StudentEntity studentEntity);

	StudentEntity addCourseToStudent(long identityNumber, CourseEntity courseEntity);

	/**
	 * Delete
	 */
	void removeStudentByFirstName(String firstName);

	void removeCourseFromStudentByCourseNumber(long identityNumber, String courseNumber);

	void removeAllCoursesFromStudent(long identityNumber);

	void removeAllStudents();

}