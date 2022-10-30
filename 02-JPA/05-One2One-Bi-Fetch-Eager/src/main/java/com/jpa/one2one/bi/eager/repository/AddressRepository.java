package com.jpa.one2one.bi.eager.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jpa.one2one.bi.eager.entity.AddressEntity;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

	AddressEntity findAddressById(long id);
	
	@Transactional
	void deleteById(long id);

	@Transactional
	void deleteByUserId(long userId);
}
