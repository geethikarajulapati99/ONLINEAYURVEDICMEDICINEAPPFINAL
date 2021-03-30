package com.cg.oam.model;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

public class MedicineModel {
	@NotNull(message="cannot be omitted")
	private String medicineId;
	
	@NotEmpty(message="cant be blank")
	@NotNull(message="cannot be omitted")
	private String medicineName;
	
	@NotEmpty(message="cant be blank")
	@NotNull(message="cannot be omitted")
	private float medicineCost;
	
	@PastOrPresent
	@NotEmpty(message="cant be blank")
	@NotNull(message="cannot be omitted")
	private LocalDate mfd;
	
	@Future
	@NotEmpty(message="cant be blank")
	@NotNull(message="cannot be omitted")
	private LocalDate expiryDate;
	
	private int quantity;
	
	private String categoryId;
	
	private String companyName;

	
	public MedicineModel() {
		
	}
	
	public MedicineModel(String medicineId,float medicineCost,int quantity) {
		super();
		this.medicineId = medicineId;
		this.medicineCost = medicineCost;
		this.quantity=quantity;
		
	}

	public MedicineModel(String medicineId, String medicineName, float medicineCost, LocalDate mfd,
			LocalDate expiryDate, String companyName) {
		super();
		this.medicineId = medicineId;
		this.medicineName = medicineName;
		this.medicineCost = medicineCost;
		this.mfd = mfd;
		this.expiryDate = expiryDate;
		this.companyName = companyName;
	}
	
	public MedicineModel(String medicineId, String medicineName, float medicineCost, LocalDate mfd,
			LocalDate expiryDate,String categoryId, String companyName) {
		super();
		this.medicineId = medicineId;
		this.medicineName = medicineName;
		this.medicineCost = medicineCost;
		this.mfd = mfd;
		this.expiryDate = expiryDate;
		this.categoryId = categoryId;
		this.companyName = companyName;
	}
	
	public MedicineModel(String medicineId, String medicineName, float medicineCost, LocalDate mfd,
			LocalDate expiryDate,int quantity, String categoryId, String companyName) {
		super();
		this.medicineId = medicineId;
		this.medicineName = medicineName;
		this.medicineCost = medicineCost;
		this.mfd = mfd;
		this.expiryDate = expiryDate;
		this.quantity=quantity;
		this.categoryId = categoryId;
		this.companyName = companyName;
	}

	public String getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(String medicineId) {
		this.medicineId = medicineId;
	}

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public float getMedicineCost() {
		return medicineCost;
	}

	public void setMedicineCost(float medicineCost) {
		this.medicineCost = medicineCost;
	}

	public LocalDate getMfd() {
		return mfd;
	}

	public void setMfd(LocalDate mfd) {
		this.mfd = mfd;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoryId == null) ? 0 : categoryId.hashCode());
		result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
		result = prime * result + ((expiryDate == null) ? 0 : expiryDate.hashCode());
		result = prime * result + Float.floatToIntBits(medicineCost);
		result = prime * result + ((medicineId == null) ? 0 : medicineId.hashCode());
		result = prime * result + ((medicineName == null) ? 0 : medicineName.hashCode());
		result = prime * result + ((mfd == null) ? 0 : mfd.hashCode());
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
		MedicineModel other = (MedicineModel) obj;
		if (categoryId == null) {
			if (other.categoryId != null)
				return false;
		} else if (!categoryId.equals(other.categoryId))
			return false;
		if (companyName == null) {
			if (other.companyName != null)
				return false;
		} else if (!companyName.equals(other.companyName))
			return false;
		if (expiryDate == null) {
			if (other.expiryDate != null)
				return false;
		} else if (!expiryDate.equals(other.expiryDate))
			return false;
		if (Float.floatToIntBits(medicineCost) != Float.floatToIntBits(other.medicineCost))
			return false;
		if (medicineId == null) {
			if (other.medicineId != null)
				return false;
		} else if (!medicineId.equals(other.medicineId))
			return false;
		if (medicineName == null) {
			if (other.medicineName != null)
				return false;
		} else if (!medicineName.equals(other.medicineName))
			return false;
		if (mfd == null) {
			if (other.mfd != null)
				return false;
		} else if (!mfd.equals(other.mfd))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format(
				"MedicineModel [medicineId=%s, medicineName=%s, medicineCost=%s, mfd=%s, expiryDate=%s, categoryId=%s, companyName=%s]",
				medicineId, medicineName, medicineCost, mfd, expiryDate, categoryId, companyName);
	}	
	
}