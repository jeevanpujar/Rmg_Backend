package com.tataelxsi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;

@Builder
@Entity
@Table(name="Customer")
public class Customer {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerId;
	private String customerName; 
	private String deliveryManagerName; 
	

	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getDeliveryManagerName() {
		return deliveryManagerName;
	}
	public void setDeliveryManagerName(String deliveryManagerName) {
		this.deliveryManagerName = deliveryManagerName;
	}
	public Customer(int customerId, String customerName, String deliveryManagerName) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.deliveryManagerName = deliveryManagerName;
	}
	public Customer() {
		super();
	}
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", deliveryManagerName="
				+ deliveryManagerName + "]";
	}
	
}
