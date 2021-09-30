package com.tataelxsi.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="employee")
public class Employee {
	
	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employeeNumber;
	private String employeeName;
	private String emailAddress;
	
	private String employeePhoneNumber;
	private Date dateOfBirth;
	private String campusBatch;
	private double totalExperience;
	private String allocationBand;
	
	public Employee() {
		super();
	}

	public Employee(int employeeNumber, String employeeName, String emailAddress, String employeePhoneNumber,
			String campusBatch, double totalExperience, String allocationBand, String skills) {
		super();
		this.employeeNumber = employeeNumber;
		this.employeeName = employeeName;
		this.emailAddress = emailAddress;
		this.employeePhoneNumber = employeePhoneNumber;
		this.campusBatch = campusBatch;
		this.totalExperience = totalExperience;
		this.allocationBand = allocationBand;
		this.skills = skills;
	
	}

	public Employee(int employeeNumber, String employeeName, String emailAddress, String employeePhoneNumber,
			Date dateOfBirth, String campusBatch, double totalExperience, String allocationBand, String skills,
			Customer customer) {
		super();
		this.employeeNumber = employeeNumber;
		this.employeeName = employeeName;
		this.emailAddress = emailAddress;
		this.employeePhoneNumber = employeePhoneNumber;
		this.dateOfBirth = dateOfBirth;
		this.campusBatch = campusBatch;
		this.totalExperience = totalExperience;
		this.allocationBand = allocationBand;
		this.skills = skills;
		this.customer = customer;
	}

	public int getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(int employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getEmployeePhoneNumber() {
		return employeePhoneNumber;
	}

	public void setEmployeePhoneNumber(String employeePhoneNumber) {
		this.employeePhoneNumber = employeePhoneNumber;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getCampusBatch() {
		return campusBatch;
	}

	public void setCampusBatch(String campusBatch) {
		this.campusBatch = campusBatch;
	}

	public double getTotalExperience() {
		return totalExperience;
	}

	public void setTotalExperience(double totalExperience) {
		this.totalExperience = totalExperience;
	}

	public String getAllocationBand() {
		return allocationBand;
	}

	public void setAllocationBand(String allocationBand) {
		this.allocationBand = allocationBand;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Lob
	private String skills;
	
	@OneToOne
    @JoinColumn(name = "custId", referencedColumnName = "customerId")
    private Customer customer;




}
