package com.jpa.many2many.bi.eager.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jpa.many2many.bi.eager.entity.CourseEntity;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Long> {

	CourseEntity findCourseByCourseName(String courseName);

	CourseEntity findCourseByCourseNumber(String courseNumber);

	List<CourseEntity> findCoursesByLearningYear(int learningYear);

	List<CourseEntity> findCoursesByStartDateBetween(LocalDate fromDate, LocalDate toDate);

	List<CourseEntity> findCoursesByEndDateBetween(LocalDate fromDate, LocalDate toDate);

	@Query("SELECT ce FROM CourseEntity ce WHERE  ce.startDate >= ?1 and ce.endDate <= ?2")
	List<CourseEntity> findCoursesBetweenDates(LocalDate startDate, LocalDate endDate);

	/**
	 * private String courseNumber; private String courseName; private LocalDate
	 * startDate; private LocalDate endDate;
	 */
}
