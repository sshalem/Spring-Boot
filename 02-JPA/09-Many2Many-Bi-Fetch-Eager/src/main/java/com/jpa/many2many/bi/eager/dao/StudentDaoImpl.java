package com.jpa.many2many.bi.eager.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.jpa.many2many.bi.eager.entity.CourseEntity;
import com.jpa.many2many.bi.eager.entity.StudentEntity;
import com.jpa.many2many.bi.eager.exception.ResourceNotFoundException;
import com.jpa.many2many.bi.eager.repository.CourseRepository;
import com.jpa.many2many.bi.eager.repository.StudentRepository;

@Service
public class StudentDaoImpl implements StudentDao {

	private final static Logger LOGGER = LoggerFactory.getLogger(StudentDaoImpl.class);

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private CourseRepository courseRepository;
	
	/***********************
	 * CREATE
	 ***********************/
	@Override
	public StudentEntity createStudent(StudentEntity studentEntity) {

		LOGGER.info("invoke createStudent");
		StudentEntity _studentEntity = studentRepository.findStudentByIdentityNumber(studentEntity.getIdentityNumber());

		if (_studentEntity != null)
			throw new DuplicateKeyException("Student with IdentityNumber : " + studentEntity.getIdentityNumber() + " , already Exist");
		return studentRepository.save(studentEntity);
	}

	/***********************
	 * GET
	 ***********************/

	@Override
	public List<StudentEntity> getStudentsByFirstName(String firstName) {

		LOGGER.info("invoke getStudentsByFirstName");
		List<StudentEntity> _students = studentRepository.findStudentsByFirstName(firstName);
//
//		if (_students != null)
//			throw new ResourceNotFoundException("Students with first name : " + firstName + " , Not Exist");
		return _students;
	}

	@Override
	public List<StudentEntity> getStudentsByLastName(String lastName) {

		LOGGER.info("invoke getStudentsByLastName");
		List<StudentEntity> _students = studentRepository.findStudentsByLastName(lastName);

		if (_students == null)
			throw new ResourceNotFoundException("Students with last name : " + lastName + " , Not Exist");
		return _students;
	}

	@Override
	public StudentEntity getStudentByIdentityNumber(int identityNumber) {

		LOGGER.info("invoke getStudentByIdentityNumber");
		StudentEntity _studentEntity = studentRepository.findStudentByIdentityNumber(identityNumber);

		if (_studentEntity == null)
			throw new NullPointerException("Student with Identity Number : " + identityNumber + " , Not Exist");
		return _studentEntity;
	}

	@Override
	public StudentEntity getStudentByEmail(String email) {

		LOGGER.info("invoke getStudentByEmail");

		StudentEntity _studentEntity = studentRepository.findStudentByEmail(email);

		if (_studentEntity == null)
			throw new ResourceNotFoundException("Student with Email : " + email + " , Not Exist");
		return _studentEntity;
	}

	@Override
	public List<StudentEntity> getStudentsThatLearnedCoursesInLearningYear(int learningYear) {
		LOGGER.info("invoke getStudentsWhoLearnInLearningYear");
		return studentRepository.jpqlFindStudentsThatLearnedCoursesInLearningYear(learningYear);
	}

	@Override
	public List<StudentEntity> getStudentsWhoLearedCourseName(String courseName) {
		LOGGER.info("invoke getStudentsWhoLearedCourseName");
		return studentRepository.jpqlFindStudentsWhoLearedCourseName(courseName);
	}
	
	@Override
	public List<CourseEntity> getAllCoursesOfStudentByIdentityNumber(int identityNumber) {
		LOGGER.info("invoke getAllCoursesOfStudentByIdentityNumber");
		return studentRepository.jpqlFindAllCoursesOfStudentByIdentityNumber(identityNumber);		
	}
	
	@Override
	public List<StudentEntity> getStudentsWhoTookCourseInLearningYear(int learningYear, String courseNumber) {
		LOGGER.info("invoke getStudentsWhoTookCourseInLearningYear() ");
		return studentRepository.jpqlFindStudentsWhoTookCourseInLearningYear(learningYear, courseNumber);
	}
	
	/***********************
	 * UPDATE
	 ***********************/

	@Override
	public StudentEntity updateStudentDetails(int identityNumber, StudentEntity studentEntity) {
		LOGGER.info("invoke updateStudentDetails");
		
		StudentEntity _studentEntity = this.getStudentByIdentityNumber(identityNumber);
		
		_studentEntity.setFirstName(studentEntity.getFirstName());
		_studentEntity.setLastName(studentEntity.getLastName());
		_studentEntity.setEmail(studentEntity.getEmail());
						
		return studentRepository.save(_studentEntity);
	}

	@Override
	public StudentEntity addCourseToStudent(int identityNumber, String courseNumber) {
		LOGGER.info("invoke addCourseToStudent");
		
		StudentEntity _studentEntity = this.getStudentByIdentityNumber(identityNumber);
		
		CourseEntity courseEntity = courseRepository.findCourseByCourseNumber(courseNumber);
		
		// contains() method compares the hash code of the entities
		boolean contains = _studentEntity.getCourses().contains(courseEntity);
		
		if(contains)
			throw new DuplicateKeyException("Student already has courseNumber: " + courseNumber);		
		
		_studentEntity.addCourse(courseEntity);
				
		return studentRepository.save(_studentEntity);
	}

	/***********************
	 * DELETE
	 ***********************/

	@Override
	public void removeStudentByFirstName(String firstName) {
		LOGGER.info("invoke removeStudentByFirstName");

	}

	@Override
	public void removeCourseFromStudentByCourseNumber(int identityNumber, String courseNumber) {
		LOGGER.info("invoke removeCourseFromStudentByCourseNumber");

	}

	@Override
	public void removeAllCoursesFromStudent(int identityNumber) {
		LOGGER.info("invoke removeAllCoursesFromStudent");

	}

	@Override
	public void removeAllStudents() {
		LOGGER.info("invoke removeAllStudents");

	}

}
