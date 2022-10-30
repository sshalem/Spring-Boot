package com.jpa.one2one.uni.eager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jpa.one2one.uni.eager.entity.AddressEntity;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

//  Don't Know why Bezkoder Added this @Transactional annotation from javax.transaction.Transactional
//	@Transactional
	void deleteById(long id);

//  Don't Know why Bezkoder Added this @Transactional annotation from javax.transaction.Transactional
	void deleteByUserId(long userId);
}
