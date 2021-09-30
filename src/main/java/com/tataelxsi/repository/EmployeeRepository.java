package com.tataelxsi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tataelxsi.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	@Query( "select e from Employee e where e.employeeNumber=?1")
	Employee findByEmployeeNumber(int employeeNumber);

	@Query("select e from Employee e where CONCAT(e.employeeNumber,'') like %?1%" +"OR  e.employeeName like %?1%" + "OR e.emailAddress like %?1%")
	Page<Employee> findByEmployeeContaining(String search, Pageable pageable);

}
