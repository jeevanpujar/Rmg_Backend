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
public class EmployeeBillingStatus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int billabilityStatusId;
	private String billabilityStatus;
	private String reservedforAccount;
	private String planningAllocatedBilled;
	
	  
	    @OneToOne
	    @JoinColumn(name = "employeeNumber", referencedColumnName = "employeeNumber")
	    private Employee employee;


		public int getBillabilityStatusId() {
			return billabilityStatusId;
		}


		public void setBillabilityStatusId(int billabilityStatusId) {
			this.billabilityStatusId = billabilityStatusId;
		}


		public String getBillabilityStatus() {
			return billabilityStatus;
		}


		public void setBillabilityStatus(String billabilityStatus) {
			this.billabilityStatus = billabilityStatus;
		}


		public String getReservedforAccount() {
			return reservedforAccount;
		}


		public void setReservedforAccount(String reservedforAccount) {
			this.reservedforAccount = reservedforAccount;
		}


		public String getPlanningAllocatedBilled() {
			return planningAllocatedBilled;
		}


		public void setPlanningAllocatedBilled(String planningAllocatedBilled) {
			this.planningAllocatedBilled = planningAllocatedBilled;
		}


		public Employee getEmployee() {
			return employee;
		}


		public void setEmployee(Employee employee) {
			this.employee = employee;
		}


		public EmployeeBillingStatus(int billabilityStatusId, String billabilityStatus, String reservedforAccount,
				String planningAllocatedBilled, Employee employee) {
			super();
			this.billabilityStatusId = billabilityStatusId;
			this.billabilityStatus = billabilityStatus;
			this.reservedforAccount = reservedforAccount;
			this.planningAllocatedBilled = planningAllocatedBilled;
			this.employee = employee;
		}


		public EmployeeBillingStatus() {
			super();
		}



	  
}
