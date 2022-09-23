package com.jwt.sk.hateoas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jwt.sk.hateoas.entity.AddressEntity;
import com.jwt.sk.hateoas.entity.UserEntity;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

	List<AddressEntity> findAllByUserDetails(UserEntity userDetails);

	AddressEntity findByAddressId(String addressId);
}
