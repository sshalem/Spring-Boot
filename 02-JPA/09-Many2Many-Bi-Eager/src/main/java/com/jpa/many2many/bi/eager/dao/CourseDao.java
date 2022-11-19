package com.jpa.many2many.bi.eager.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.jpa.many2many.bi.eager.dto.DtoCourseNameAndCourseNumber;
import com.jpa.many2many.bi.eager.entity.CourseEntity;
import com.jpa.many2many.bi.eager.entity.StudentEntity;

public interface CourseDao {

	/**
	 * Create
	 */
	CourseEntity createCourse(CourseEntity courseEntity);

	/**
	 * Read
	 */
	CourseEntity getCourseByCourseNumber(String courseNumber);

	CourseEntity getCourseByCourseName(String courseName);

	List<CourseEntity> getCoursesByLearningYear(int learningYear);

	List<CourseEntity> getCoursesByStartDateBetween(LocalDate fromStartDate, LocalDate toStartDate);

	List<CourseEntity> getCoursesByEndDateBetween(LocalDate fromEndDate, LocalDate toEndDate);

	List<CourseEntity> getCoursesBetweenStartDateAndEndDate(LocalDate startDate, LocalDate endDate);

	List<CourseEntity> gettAllCourses();
	
	List<DtoCourseNameAndCourseNumber> getAllCoursesOnlyFieldsOfCourseNumberAndCourseName();

	List<StudentEntity> getStudentsWhoTookCourse(int learningYear, String courseNumber);

	/**
	 * Update
	 */
	CourseEntity updateCourseDetails(CourseEntity courseEntity);

	StudentEntity addCourseToStudent(int identityNumber, String courseNumber);
	
	CourseEntity addStudentToCourse(int identityNumber, String courseName);
	


	/**
	 * Delete
	 */
	void deleteCourseByCourseNumber(String courseNumber);

	StudentEntity removeCourseFromStudentByCourseNumber(int identityNumber, String courseNumber);

	Set<CourseEntity> removeAllCoursesFromStudent(int identityNumber);

	void deleteAllCourses();

}
