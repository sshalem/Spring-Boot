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
import com.jpa.many2many.bi.eager.repository.StudentRepository;

@Service
public class StudentDaoImpl implements StudentDao {

	private final static Logger LOGGER = LoggerFactory.getLogger(StudentDaoImpl.class);

	@Autowired
	private StudentRepository studentRepository;

	/***********************
	 * CREATE
	 ***********************/
	@Override
	public StudentEntity createStudent(StudentEntity studentEntity) {

		LOGGER.info("invoke createStudent");
		StudentEntity _studentEntity = studentRepository.findStudentByIdentityNumber(studentEntity.getIdentityNumber());

		if (_studentEntity != null)
			throw new DuplicateKeyException(
					"Student with IdentityNumber : " + studentEntity.getIdentityNumber() + " , already Exist");
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

		if (_students != null)
			throw new ResourceNotFoundException("Students with last name : " + lastName + " , Not Exist");
		return _students;
	}

	@Override
	public StudentEntity getStudentByIdentityNumber(long identityNumber) {

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
	public List<StudentEntity> getStudentsWhoLearnInLearningYear(long learningYear) {
		LOGGER.info("invoke gettAllStudents");
		return studentRepository.jpqlFindStudentsWhoLearedInLearningYear(learningYear);
	}

	/***********************
	 * UPDATE
	 ***********************/

	@Override
	public StudentEntity updateStudentDetails(StudentEntity studentEntity) {
		LOGGER.info("invoke updateStudentDetails");
		return null;
	}

	@Override
	public StudentEntity addCourseToStudent(long identityNumber, CourseEntity courseEntity) {
		LOGGER.info("invoke addCourseToStudent");
		return null;
	}

	/***********************
	 * DELETE
	 ***********************/

	@Override
	public void removeStudentByFirstName(String firstName) {
		LOGGER.info("invoke removeStudentByFirstName");

	}

	@Override
	public void removeCourseFromStudentByCourseNumber(long identityNumber, String courseNumber) {
		LOGGER.info("invoke removeCourseFromStudentByCourseNumber");

	}

	@Override
	public void removeAllCoursesFromStudent(long identityNumber) {
		LOGGER.info("invoke removeAllCoursesFromStudent");

	}

	@Override
	public void removeAllStudents() {
		LOGGER.info("invoke removeAllStudents");

	}

}
