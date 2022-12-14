package com.transaction.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.transaction.management.entity.EmployeeHealthInsurance;

@Repository
public interface HealthInsuraceRepository extends JpaRepository<EmployeeHealthInsurance, Long> {

}
