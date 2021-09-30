package com.tataelxsi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tataelxsi.entity.EmployeeProject;
@Repository
public interface EmployeeProjectRepository  extends JpaRepository<EmployeeProject, Integer>{
	@Query("select ebs from EmployeeProject ebs where CONCAT(ebs.empProjectId,'') like %?1%" + "OR CONCAT(ebs.employee.employeeNumber,'') like %?1%" + "OR CONCAT(ebs.project.projectId,'') like %?1%")
	Page<EmployeeProject> findByEmployeeProjectsContaining(String search, Pageable pageable);
}
