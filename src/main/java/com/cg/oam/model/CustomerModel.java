package com.cg.oam.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class CustomerModel {
	private Long customerId;

	@NotEmpty(message="can't be blank")
	@NotNull(message="cannot be omitted")
	private String customerName;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@NotEmpty(message="can't be blank")
	@NotNull(message="cannot be omitted")
	private String password;
	
	@NotNull(message="cannot be omitted")
	private Long mobileNumber;
	
	
	private String emailId;
	
	public CustomerModel() {
		
	}
	
	
	public CustomerModel(Long customerId, String customerName, String password, Long mobileNumber, String emailId) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.emailId = emailId;
	}
	
	public CustomerModel(String customerName, String password, Long mobileNumber, String emailId) {
		super();
	
		this.customerName = customerName;
		this.password = password;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
		result = prime * result + ((emailId == null) ? 0 : emailId.hashCode());
		result = prime * result + ((mobileNumber == null) ? 0 : mobileNumber.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerModel other = (CustomerModel) obj;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (customerName == null) {
			if (other.customerName != null)
				return false;
		} else if (!customerName.equals(other.customerName))
			return false;
		if (emailId == null) {
			if (other.emailId != null)
				return false;
		} else if (!emailId.equals(other.emailId))
			return false;
		if (mobileNumber == null) {
			if (other.mobileNumber != null)
				return false;
		} else if (!mobileNumber.equals(other.mobileNumber))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return String.format("CustomerModel [customerId=%s, customerName=%s, password=%s, mobileNumber=%s, emailId=%s]",
				customerId, customerName, password, mobileNumber, emailId);
	}
	

}
