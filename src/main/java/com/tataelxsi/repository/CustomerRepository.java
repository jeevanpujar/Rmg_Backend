package com.tataelxsi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tataelxsi.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	@Query( "select c from Customer c where c.customerName=?1")
	Customer findByCustomerName(String customerName);
	
	@Query("select c from Customer c where CONCAT(c.customerId,'') like %?1%" +"OR  c.customerName like %?1%" + "OR c.deliveryManagerName like %?1%")
	Page<Customer> findByCustomerContaining(String search, Pageable pageable);
}
