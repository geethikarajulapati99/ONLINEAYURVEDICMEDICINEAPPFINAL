package com.cg.oam.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
@Entity
@Table(name="customer")
public class Customer implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="customer_id")
	private Long customerId;
	
	@Column(name="customer_name")
	private String customerName;
	
	@Column(name="customer_pass")
	private String customerPassword;
	
	@Column(name="mobile_no")
	private Long mobileNumber;
	
	@Column(name="email_id")
	private String emailId;
	  
	 @OneToMany(mappedBy="customer",cascade=CascadeType.ALL) 
	 @Fetch(FetchMode.SELECT)
	 private List<Order> order;
	 
	 public Customer() {
		 
	 }

	public Customer(Long customerId,String customerName,
			String customerPassword,Long mobileNumber, String emailId) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerPassword = customerPassword;
		this.mobileNumber = mobileNumber;
		this.emailId = emailId;
	}
	public Customer(String customerName,
			String customerPassword,Long mobileNumber, String emailId) {
		super();
		this.customerName = customerName;
		this.customerPassword = customerPassword;
		this.mobileNumber = mobileNumber;
		this.emailId = emailId;
	}
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPassword() {
		return customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}

	public Long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}
	 
	
	

}
