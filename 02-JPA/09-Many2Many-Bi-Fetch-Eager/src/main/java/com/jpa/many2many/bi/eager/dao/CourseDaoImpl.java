package com.jpa.many2many.bi.eager.dao;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.jpa.many2many.bi.eager.entity.CourseEntity;
import com.jpa.many2many.bi.eager.exception.ResourceNotFoundException;
import com.jpa.many2many.bi.eager.repository.CourseRepository;

@Service
public class CourseDaoImpl implements CourseDao {

	private final static Logger LOGGER = LoggerFactory.getLogger(CourseDaoImpl.class);

	@Autowired
	private CourseRepository courseRepository;

	/***********************
	 * CREATE
	 ***********************/
	@Override
	public CourseEntity createCourse(CourseEntity courseEntity) {

		LOGGER.info("invoke createCourse() ");

		String _courseName = courseRepository.findCourseByCourseName(courseEntity.getCourseName()).getCourseName();
		String _courseNumber = courseRepository.findCourseByCourseNumber(courseEntity.getCourseNumber()).getCourseNumber();

		if (_courseName != null)
			throw new DuplicateKeyException("Course with name : " + courseEntity.getCourseName() + " , already Exist");
		if (_courseNumber != null)
			throw new DuplicateKeyException(
					"Course with number : " + courseEntity.getCourseNumber() + " , already Exist");
		return courseRepository.save(courseEntity);
	}

	/***********************
	 * GET
	 ***********************/
	
	@Override
	public CourseEntity getCourseByCourseNumber(String courseNumber) {
		LOGGER.info("invoke getCourseByCourseNumber() ");

		CourseEntity _courseEntity = courseRepository.findCourseByCourseNumber(courseNumber);

		if (_courseEntity == null)
			throw new ResourceNotFoundException("Course with Number : " + courseNumber + " , NOT Exist");
		return _courseEntity;
	}

	@Override
	public CourseEntity getCourseByCourseName(String courseName) {
		LOGGER.info("invoke getCourseByCourseName() ");

		CourseEntity _courseEntity = courseRepository.findCourseByCourseName(courseName);

		if (_courseEntity == null)
			throw new ResourceNotFoundException("Course with NAme : " + courseName + " , NOT Exist");
		return _courseEntity;
	}

	@Override
	public List<CourseEntity> getCoursesByLearningYear(int learningYear) {
		LOGGER.info("invoke getCoursesByLearningYear() ");

		List<CourseEntity> _courses = courseRepository.findCoursesByLearningYear(learningYear);
		return _courses;
	}

	@Override
	public List<CourseEntity> getCoursesByStartDateBetween(LocalDate fromStartDate, LocalDate toStartDate) {
		LOGGER.info("invoke getCoursesByStartDateBetween() ");
		
		List<CourseEntity> _courses = courseRepository.findCoursesByStartDateBetween(fromStartDate, toStartDate);
		return _courses;
	}

	@Override
	public List<CourseEntity> getCoursesByEndDateBetween(LocalDate fromEndDate, LocalDate toEndDate) {
		LOGGER.info("invoke getCoursesByEndDateBetween() ");
		
		List<CourseEntity> _courses = courseRepository.findCoursesByEndDateBetween(fromEndDate, toEndDate);
		return _courses;
	}
	
	@Override
	public List<CourseEntity> getCoursesBetweenStartDateAndEndDate(LocalDate startDate, LocalDate endDate) {
		LOGGER.info("invoke getCoursesBetweenStartDateAndEndDate() ");
		
		List<CourseEntity> _courses = courseRepository.jpqlFindCoursesBetweenDates(startDate, endDate);
		return _courses;
	}

	@Override
	public List<CourseEntity> gettAllCourses() {
		// TODO Auto-generated method stub
		return null;
	}

	/***********************
	 * UPDATE
	 ***********************/
	
	@Override
	public CourseEntity updateCourseDetails(CourseEntity courseEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CourseEntity addStudentToCourse(long identityNumber, String courseName, long learningYear) {
		// TODO Auto-generated method stub
		return null;
	}

	/***********************
	 * DELETE
	 ***********************/
	
	@Override
	public void removeCourseByCourseNumber(String courseNumber) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeCourseByCourseNumberAndLearningYear(String courseNumber, long learningYear) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeCourseFromStudentByCourseNumber(long identityNumber, String courseNumber) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeAllStudentsFromCourse(String courseNumber, long learningYear) {
		// TODO Auto-generated method stub

	}

}
