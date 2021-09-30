package com.tataelxsi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tataelxsi.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
	
	@Query( "select p from Project p where p.projectName=?1")
	Project findByProjectName(String projectName);
	@Query("select p from Project p where CONCAT(p.projectId,'') like %?1%" +"OR  p.projectName like %?1%")
	Page<Project> findByProjectsContaining(String search, Pageable pageable);
}
