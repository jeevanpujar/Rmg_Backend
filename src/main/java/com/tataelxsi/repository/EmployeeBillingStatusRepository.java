package com.tataelxsi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tataelxsi.entity.EmployeeBillingStatus;
@Repository
public interface EmployeeBillingStatusRepository extends JpaRepository<EmployeeBillingStatus, Integer> {
	@Query("select ebs from EmployeeBillingStatus ebs where CONCAT(ebs.billabilityStatusId,'') like %?1%" +"OR  ebs.reservedforAccount like %?1%" + "OR ebs.planningAllocatedBilled like %?1%")
	Page<EmployeeBillingStatus> findByEmployeeBillStatusContaining(String search, Pageable pageable);
}
