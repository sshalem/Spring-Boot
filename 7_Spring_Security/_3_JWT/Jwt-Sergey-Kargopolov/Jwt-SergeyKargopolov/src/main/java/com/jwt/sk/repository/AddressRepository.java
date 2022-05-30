package com.jwt.sk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jwt.sk.entity.AddressEntity;
import com.jwt.sk.entity.UserEntity;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

	List<AddressEntity> findAllByUserDetails(UserEntity userDetails);

	AddressEntity findByAddressId(String addressId);
}
