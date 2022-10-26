package com.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jpa.entity.DriveLicenseEntity;

@Repository
public interface DriveLicenseRepository extends JpaRepository<DriveLicenseEntity, Long> {

}
