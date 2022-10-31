package com.jpa.one2one.bi.lazy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jpa.one2one.bi.lazy.entity.AddressEntity;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

	// *****************************************************************************
	// *****************************************************************************
	// *****************************************************************************

	AddressEntity findAddressById(long id);

	// Query with Named Parameters
	@Query("SELECT address from AddressEntity address WHERE user.id=:id")
	AddressEntity jpqlFindById(@Param("id") long id);

	// *****************************************************************************
	// *****************************************************************************
	// *****************************************************************************

	void deleteById(long id);

	void deleteByUserId(long userId);
}
