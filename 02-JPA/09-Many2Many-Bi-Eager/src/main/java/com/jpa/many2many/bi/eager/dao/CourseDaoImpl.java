package com.jpa.many2many.bi.eager.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.jpa.many2many.bi.eager.dto.DtoCourseNameAndCourseNumber;
import com.jpa.many2many.bi.eager.entity.CourseEntity;
import com.jpa.many2many.bi.eager.entity.StudentEntity;
import com.jpa.many2many.bi.eager.exception.ResourceNotFoundException;
import com.jpa.many2many.bi.eager.repository.CourseRepository;
import com.jpa.many2many.bi.eager.repository.StudentRepository;

@Service
public class CourseDaoImpl implements CourseDao {

	private final static Logger LOGGER = LoggerFactory.getLogger(CourseDaoImpl.class);

	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private StudentRepository studentRepository;

	/***********************
	 * CREATE
	 ***********************/
	@Override
	public CourseEntity createCourse(CourseEntity courseEntity) {

		LOGGER.info("invoke createCourse() ");

		CourseEntity _courseEntityByName = courseRepository.findCourseByCourseName(courseEntity.getCourseName());
		CourseEntity _courseEntityByNumber = courseRepository.findCourseByCourseNumber(courseEntity.getCourseNumber());

		if (_courseEntityByName != null)
			throw new DuplicateKeyException("Course with name : " + courseEntity.getCourseName() + " , already Exist");
		if (_courseEntityByNumber != null)
			throw new DuplicateKeyException("Course with number : " + courseEntity.getCourseNumber() + " , already Exist");
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
		LOGGER.info("invoke gettAllCourses() ");
		return courseRepository.findAll();
	}
	
	@Override
	public List<DtoCourseNameAndCourseNumber> getAllCoursesOnlyFieldsOfCourseNumberAndCourseName(){
		LOGGER.info("invoke getAllCoursesOnlyFieldsOfCourseNumberAndCourseName() ");
		return courseRepository.jpqlGetCoursesWithFieldsCourseNameAndCourseNumber();
	}
	
	@Override
	public List<StudentEntity> getStudentsWhoTookCourse(int learningYear, String courseNumber) {
		LOGGER.info("invoke getStudentsWhoTookCourse() ");
		
		List<StudentEntity> _students = courseRepository.jpqlFindStudentsWhoTookCourseInLearningYear(learningYear, courseNumber);	
		return _students;
	}

	/***********************
	 * UPDATE
	 ***********************/
	
	@Override
	public CourseEntity updateCourseDetails(CourseEntity courseEntity) {
		CourseEntity _courseEntity = courseRepository.findCourseByCourseNumber(courseEntity.getCourseNumber());
		
		if(_courseEntity == null)
			throw new ResourceNotFoundException(" Course Number " + courseEntity.getCourseNumber() + " Not Found");
		
		_courseEntity.setCourseNumber(courseEntity.getCourseNumber());
		_courseEntity.setCourseName(courseEntity.getCourseName());		
		_courseEntity.setLearningYear(courseEntity.getLearningYear());
		_courseEntity.setStartDate(courseEntity.getStartDate());
		_courseEntity.setEndDate(courseEntity.getEndDate());
				
		return courseRepository.save(_courseEntity);
	}

	@Override
	public StudentEntity addCourseToStudent(int identityNumber, String courseNumber) {
		LOGGER.info("invoke addCourseToStudent()");

		StudentEntity _studentEntity = studentRepository.findStudentByIdentityNumber(identityNumber);

		if (_studentEntity == null)
			throw new NullPointerException("Student with Identity Number : " + identityNumber + " , Not Exist");
		
		CourseEntity _courseEntity = courseRepository.findCourseByCourseNumber(courseNumber);

		boolean contains = _studentEntity.getCourses().contains(_courseEntity);
		
		if (contains)
			throw new DuplicateKeyException("Student already has courseNumber: " + courseNumber);

		_studentEntity.addCourse(_courseEntity);
		StudentEntity returnedValue = studentRepository.save(_studentEntity);
		return returnedValue;
	}
	
	@Override
	public CourseEntity addStudentToCourse(int identityNumber, String courseName) {
		return null;
	}

	/***********************
	 * DELETE
	 ***********************/
	
	@Override
	public void deleteCourseByCourseNumber(String courseNumber) {
		
		List<StudentEntity> _students = studentRepository.findAll();		
		CourseEntity _courseEntity = courseRepository.findCourseByCourseNumber(courseNumber);
		
		for (StudentEntity studentEntity: _students) {			
			boolean contains = studentEntity.getCourses().contains(_courseEntity);			
			if(contains) {
				studentEntity.removeCourse(_courseEntity);
				studentRepository.save(studentEntity);				
			}			
		}				
		courseRepository.delete(_courseEntity);
	}

	@Override
	public StudentEntity removeCourseFromStudentByCourseNumber(int identityNumber, String courseNumber) {
		
		LOGGER.info("invoke removeCourseFromStudentByCourseNumber()");
		
		StudentEntity _studentEntity = studentRepository.findStudentByIdentityNumber(identityNumber);

		if (_studentEntity == null)
			throw new NullPointerException("Student with Identity Number : " + identityNumber + " , Not Exist");
		
		CourseEntity _courseEntity = courseRepository.findCourseByCourseNumber(courseNumber);
		_studentEntity.removeCourse(_courseEntity);
		return studentRepository.save(_studentEntity);		
	}

	@Override
	public Set<CourseEntity> removeAllCoursesFromStudent(int identityNumber) {
		LOGGER.info("invoke removeAllCoursesFromStudent()");

		List<CourseEntity> _courses = courseRepository.jpqlFindCoursesOfStudentByIdentityNumber(identityNumber);

		StudentEntity _studentEntity = studentRepository.findStudentByIdentityNumber(identityNumber);

		for (CourseEntity courseEntity : _courses) {
			_studentEntity.removeCourse(courseEntity);
			studentRepository.save(_studentEntity);
		}		
		return _studentEntity.getCourses();
	}
	

	@Override
	public void deleteAllCourses() {	
		LOGGER.info("invoke deleteAllCourses()");
		
		List<CourseEntity> _courses = courseRepository.findAll();
		
		List<StudentEntity> _students = studentRepository.findAll();
		
		for (CourseEntity courseEntity : _courses) {			
			for (StudentEntity studentEntity : _students) {
				studentEntity.removeCourse(courseEntity);
				studentRepository.save(studentEntity);
			}		
		}
		courseRepository.deleteAll();
	}

}
