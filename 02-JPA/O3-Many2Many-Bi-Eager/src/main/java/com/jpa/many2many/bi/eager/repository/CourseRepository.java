package com.jpa.many2many.bi.eager.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jpa.many2many.bi.eager.dto.DtoCourseNameAndCourseNumber;
import com.jpa.many2many.bi.eager.entity.CourseEntity;
import com.jpa.many2many.bi.eager.entity.StudentEntity;

import jakarta.websocket.server.PathParam;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Long> {

	CourseEntity findCourseByCourseName(String courseName);

	CourseEntity findCourseByCourseNumber(String courseNumber);

	CourseEntity findCourseByCourseNumberAndLearningYear(String courseNumber, int learningYear);

	List<CourseEntity> findCoursesByLearningYear(int learningYear); 

	List<CourseEntity> findCoursesByStartDateBetween(LocalDate fromStartDate, LocalDate toStartDate);

	List<CourseEntity> findCoursesByEndDateBetween(LocalDate fromEndDate, LocalDate toEndDate);

	@Query("SELECT ce FROM CourseEntity ce WHERE  ce.startDate >= :startDate AND ce.endDate <= :endDate")
	List<CourseEntity> jpqlFindCoursesBetweenDates(@PathParam("startDate") LocalDate startDate, @PathParam("endDate")  LocalDate endDate);

	@Query("SELECT st FROM CourseEntity ce JOIN ce.students AS st WHERE  ce.learningYear = :learningYear AND ce.courseNumber = :courseNumber")
	List<StudentEntity> jpqlFindStudentsWhoTookCourseInLearningYear(@PathParam("learningYear") int learningYear, @PathParam("courseNumber") String courseNumber);

	@Query("SELECT new com.jpa.many2many.bi.eager.dto.DtoCourseNameAndCourseNumber(ce.courseName, ce.courseNumber) FROM CourseEntity ce")
	List<DtoCourseNameAndCourseNumber> jpqlGetCoursesWithFieldsCourseNameAndCourseNumber();

	@Query("SELECT ce FROM CourseEntity ce JOIN ce.students AS st WHERE st.identityNumber = :identityNumber")
	List<CourseEntity> jpqlFindCoursesOfStudentByIdentityNumber(@PathParam("identityNumber") int identityNumber);
	
	
	
	
	
//	@Query("SELECT ce FROM CourseEntity ce WHERE  ce.startDate >= ?1 AND ce.endDate <= ?2")
//	List<CourseEntity> jpqlFindCoursesBetweenDates(LocalDate startDate, LocalDate endDate);
//
//	@Query("SELECT st FROM CourseEntity ce JOIN ce.students AS st WHERE  ce.learningYear = ?1 AND ce.courseNumber = ?2")
//	List<StudentEntity> jpqlFindStudentsWhoTookCourseInLearningYear(int learningYear, String courseNumber);
//	
//	@Query("SELECT ce FROM CourseEntity ce JOIN ce.students AS st WHERE st.identityNumber = ?1")
//	List<CourseEntity> jpqlFindCoursesOfStudentByIdentityNumber(int identityNumber);
}
