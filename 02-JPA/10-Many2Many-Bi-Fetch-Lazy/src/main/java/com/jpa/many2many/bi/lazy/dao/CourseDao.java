package com.jpa.many2many.bi.lazy.dao;

import java.time.LocalDate;
import java.util.List;

import com.jpa.many2many.bi.lazy.dto.DtoCourseNameAndCourseNumber;
import com.jpa.many2many.bi.lazy.dto.StudentDto;
import com.jpa.many2many.bi.lazy.entity.CourseEntity;

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

	List<StudentDto> getStudentsWhoTookCourse(int learningYear, String courseNumber);

	/**
	 * Update
	 */
	CourseEntity updateCourseDetails(CourseEntity courseEntity);

	CourseEntity addStudentToCourse(long identityNumber, String courseName);

	/**
	 * Delete
	 */
	void removeCourseByCourseNumber(String courseNumber);

	void removeCourseByCourseNumberAndLearningYear(String courseNumber, long learningYear);

	void removeCourseFromStudentByCourseNumber(long identityNumber, String courseNumber);

	void removeAllStudentsFromCourse(String courseNumber, long learningYear);

}
