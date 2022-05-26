package com.lazy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lazy.common.PhoneType;
import com.lazy.entity.PhoneNumber;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Long> {

//	@Query("SELECT course FROM StudentCourse sc JOIN sc.course as course WHERE sc.studentIdentity=:studentIdentity")
//	@Query("SELECT phone FROM PhoneNumber phone JOIN phone.customer AS phone WHERE phone.id=:id")
//	Set<PhoneNumber> getAllPhones(@Param("id") long customerId);

	@Query("SELECT phone FROM PhoneNumber phone WHERE phone.phoneType=:phoneType")
	PhoneNumber findByPhoneType(@Param("phoneType") PhoneType phoneType);
}
