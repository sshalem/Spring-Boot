package com.jpa.many2many.bi.eager.dao;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.jpa.many2many.bi.eager.entity.CourseEntity;
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

		LOGGER.info("invoke createCourse");
		CourseEntity _courseEntity = courseRepository.findCourseByCourseName(courseEntity.getCourseName());

		if (_courseEntity != null)
			throw new DuplicateKeyException(
					"Course with name : " + _courseEntity.getCourseName() + " , already Exist");
		return courseRepository.save(courseEntity);
	}

	@Override
	public CourseEntity getCourseByCourseNumber(String courseNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CourseEntity getCourseByCourseName(String courseName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CourseEntity> getCoursesByLearningYear(long learningYear) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CourseEntity> getCoursesByStartDate(LocalDate startDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CourseEntity> getCoursesBetweenStartDateAndEndDate(LocalDate startDate, LocalDate endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CourseEntity> gettAllCourses() {
		// TODO Auto-generated method stub
		return null;
	}

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
