package com.trans.mngnt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trans.mngnt.entity.EmployeeHealthInsurance;

@Repository
public interface HealthInsuraceRepository extends JpaRepository<EmployeeHealthInsurance, Long> {

}
