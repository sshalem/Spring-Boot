package com.jpa.many2many.bi.eager.dao;

import java.time.LocalDate;
import java.util.List;

import com.jpa.many2many.bi.eager.entity.CourseEntity;

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

	List<CourseEntity> getCoursesByLearningYear(long learningYear);

	List<CourseEntity> getCoursesByStartDate(LocalDate startDate);

	List<CourseEntity> getCoursesBetweenStartDateAndEndDate(LocalDate startDate, LocalDate endDate);

	List<CourseEntity> gettAllCourses();

	/**
	 * Update
	 */
	CourseEntity updateCourseDetails(CourseEntity courseEntity);

	CourseEntity addStudentToCourse(long identityNumber, String courseName, long learningYear);

	/**
	 * Delete
	 */
	void removeCourseByCourseNumber(String courseNumber);

	void removeCourseByCourseNumberAndLearningYear(String courseNumber, long learningYear);

	void removeCourseFromStudentByCourseNumber(long identityNumber, String courseNumber);

	void removeAllStudentsFromCourse(String courseNumber, long learningYear);

}
