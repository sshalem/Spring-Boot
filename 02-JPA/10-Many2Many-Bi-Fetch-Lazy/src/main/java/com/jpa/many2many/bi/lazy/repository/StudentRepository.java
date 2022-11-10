package com.jpa.many2many.bi.lazy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jpa.many2many.bi.lazy.entity.CourseEntity;
import com.jpa.many2many.bi.lazy.entity.StudentEntity;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

	List<StudentEntity> findStudentsByFirstName(String firstName);

	List<StudentEntity> findStudentsByLastName(String lastName);

	StudentEntity findStudentByIdentityNumber(int identityNumber);

	StudentEntity findStudentByEmail(String email);

	@Query("SELECT ce FROM CourseEntity ce JOIN ce.students AS st WHERE ce.learningYear = ?1 AND st.identityNumber = ?2")
	List<CourseEntity> jpqlFindCoursesOfStudentInLearningYear(int learningYear, int identityNumber);

	@Query(value = "SELECT * " + 
				   "FROM COURSE_TB ctb " +
				   "LEFT JOIN STUDENT_TB stb " +
				   "ON ctb.user_id=stb.id " +
				   "WHERE ctb.learningYear = ?1 AND stb.identityNumber = ?2", nativeQuery = true)
	List<CourseEntity> nativeFindCoursesOfStudentInLearningYear(int learningYear, int identityNumber);

	@Query("SELECT se FROM StudentEntity se JOIN se.courses AS cours WHERE cours.learningYear=:learningYear")
	List<StudentEntity> jpqlFindStudentsThatLearnedCoursesInLearningYear(@Param("learningYear") int learningYear);
	
	@Query("SELECT se FROM StudentEntity se JOIN se.courses AS cours WHERE cours.courseName=:courseName")
	List<StudentEntity> jpqlFindStudentsWhoLearedCourseName(@Param("courseName") String courseName);
	
	@Query("SELECT cors FROM StudentEntity student JOIN student.courses AS cors WHERE student.identityNumber =? 1")
	List<CourseEntity> jpqlFindAllCoursesOfStudentByIdentityNumber(int identityNumber);
	
	@Query("SELECT st FROM CourseEntity ce JOIN ce.students AS st WHERE  ce.learningYear = ?1 AND ce.courseNumber = ?2")
	List<StudentEntity> jpqlFindStudentsWhoTookCourseInLearningYear(int learningYear, String courseNumber);
	
	
	/**
	 * 	@Query("SELECT cp FROM CUSTOMER cust JOIN cust.coupons AS cp WHERE cust.id=:id AND cp.type=:couponType")
		List<Coupon> findAllPurchasedCouponsType(@Param("id") long custId, @Param("couponType") CouponType type);
	 */
}
