package com.tataelxsi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class EmployeeProject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int empProjectId;
    
	    @OneToOne
	    @JoinColumn(name = "employeeNumber", referencedColumnName = "employeeNumber")
	    private Employee employee;

	    @OneToOne
	    @JoinColumn(name = "projectId", referencedColumnName = "projectId")
	    private Project project;

		public int getEmpProjectId() {
			return empProjectId;
		}

		public void setEmpProjectId(int empProjectId) {
			this.empProjectId = empProjectId;
		}

		public Employee getEmployee() {
			return employee;
		}

		public void setEmployee(Employee employee) {
			this.employee = employee;
		}

		public Project getProject() {
			return project;
		}

		public void setProject(Project project) {
			this.project = project;
		}

		public EmployeeProject(int empProjectId, Employee employee, Project project) {
			super();
			this.empProjectId = empProjectId;
			this.employee = employee;
			this.project = project;
		}

		public EmployeeProject() {
			super();
		}

	
}
