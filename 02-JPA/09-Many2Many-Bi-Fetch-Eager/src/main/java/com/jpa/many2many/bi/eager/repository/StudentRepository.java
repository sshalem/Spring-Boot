package com.jpa.many2many.bi.eager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jpa.many2many.bi.eager.entity.CourseEntity;
import com.jpa.many2many.bi.eager.entity.StudentEntity;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

	List<StudentEntity> findStudentsByFirstName(String firstName);

	List<StudentEntity> findStudentsByLastName(String lastName);

	StudentEntity findStudentByIdentityNumber(long identityNumber);

	StudentEntity findStudentByEmail(String email);

	@Query("SELECT ce FROM CourseEntity ce JOIN ce.students AS st WHERE ce.learningYear = ?1 AND st.identityNumber = ?2")
	List<CourseEntity> jpqlFindStudentCoursesByLearningYear(long learningYear, long identityNumber);

	@Query(value = "SELECT * " + 
				   "FROM COURSE_TB ctb " +
				   "LEFT JOIN STUDENT_TB stb " +
				   "ON ctb.user_id=stb.id " +
				   "WHERE ctb.learningYear = ?1 AND stb.identityNumber = ?2", nativeQuery = true)
	List<CourseEntity> nativeFindStudentCoursesByLearningYear(long learningYear, long identityNumber);

	@Query("SELECT se FROM StudentEntity se JOIN se.courses AS cours WHERE cours.learningYear=:learningYear")
	List<StudentEntity> jpqlFindStudentsWhoLearedInLearningYear(@Param("learningYear") long learningYear);
	
	/**
	 * 	@Query("SELECT cp FROM CUSTOMER cust JOIN cust.coupons AS cp WHERE cust.id=:id AND cp.type=:couponType")
		List<Coupon> findAllPurchasedCouponsType(@Param("id") long custId, @Param("couponType") CouponType type);
	 */
	/**
	 * 		@Query(value = "SELECT * "
			+ "FROM USERS_TB utb " 
			+ "LEFT JOIN ROLES_TB rtb " 
			+ "ON rtb.user_id=utb.id "
			+ "WHERE rtb.role=:role" ,nativeQuery = true)
	List<UserEntity> nativeFindUsersWithRoleName(@Param("role") String role);
	 */
}
