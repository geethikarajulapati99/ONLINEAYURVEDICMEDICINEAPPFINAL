package com.cg.oam.model;

import java.time.LocalDate;
import java.util.List;

import com.cg.oam.entity.Medicine;

public class OrderModel {
	
	private Long orderId;
	private LocalDate orderDate;
	private LocalDate dispatchDate;
	private float totalCost;
	private String status;
	private Long customerId;
	
	private List<Medicine> medicineList;
	
	
	public OrderModel() {
		
	}


	public OrderModel(Long orderId, LocalDate orderDate, LocalDate dispatchDate, float totalCost, String status,
			Long customerId) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.dispatchDate = dispatchDate;
		this.totalCost = totalCost;
		this.status = status;
		this.customerId = customerId;
	}
	


	/**
	 * @return the medicineList
	 */
	public List<Medicine> getMedicineList() {
		return medicineList;
	}



	/**
	 * @param medicineList the medicineList to set
	 */
	public void setMedicineList(List<Medicine> medicineList) {
		this.medicineList = medicineList;
	}



	public OrderModel(Long orderId, LocalDate orderDate, LocalDate dispatchDate, float totalCost, String status,
			Long customerId, List<Medicine> medicineList) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.dispatchDate = dispatchDate;
		this.totalCost = totalCost;
		this.status = status;
		this.customerId = customerId;
		this.medicineList = medicineList;
	}
	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public LocalDate getDispatchDate() {
		return dispatchDate;
	}

	public void setDispatchDate(LocalDate dispatchDate) {
		this.dispatchDate = dispatchDate;
	}

	public float getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(float totalCost) {
		this.totalCost = totalCost;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}



	public Long getCustomerId() {
		return customerId;
	}

	

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((dispatchDate == null) ? 0 : dispatchDate.hashCode());
		result = prime * result + ((orderDate == null) ? 0 : orderDate.hashCode());
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + Float.floatToIntBits(totalCost);
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
		OrderModel other = (OrderModel) obj;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (dispatchDate == null) {
			if (other.dispatchDate != null)
				return false;
		} else if (!dispatchDate.equals(other.dispatchDate))
			return false;
		if (orderDate == null) {
			if (other.orderDate != null)
				return false;
		} else if (!orderDate.equals(other.orderDate))
			return false;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (Float.floatToIntBits(totalCost) != Float.floatToIntBits(other.totalCost))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return String.format(
				"OrderModel [orderId=%s, orderDate=%s, dispatchDate=%s, totalCost=%s, status=%s, customerId=%s]",
				orderId, orderDate, dispatchDate, totalCost, status, customerId);
	}
	
	
}
