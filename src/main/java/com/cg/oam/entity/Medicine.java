package com.cg.oam.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name="medicine")
public class Medicine implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="medicine_id")
	private String medicineId;
	
	@Column(name="medicine_name")
	private String medicineName;
	
	@Column(name="medicine_cost")
	private float medicineCost;
	
	private LocalDate mfd;
	
	private LocalDate expiryDate;
	
	private int quantity;
	
	@Column(name = "company")
	@Enumerated(EnumType.STRING)
	private Company companyName;
	
	private String category;
	
	@ManyToMany
	private List<Order> orders;
	
	
	
	public Medicine(String medicineId,float medicineCost,int quantity) {
		super();
		this.medicineId = medicineId;
		this.medicineCost = medicineCost;
		this.quantity=quantity;
		
	}

	public Medicine(String medicineId,String medicineName,float medicineCost,LocalDate mfd,LocalDate expiryDate,
			Company companyName) {
		super();
		this.medicineId = medicineId;
		this.medicineName = medicineName;
		this.medicineCost = medicineCost;
		this.mfd = mfd;
		this.expiryDate = expiryDate;
		this.companyName = companyName;
	}
	public Medicine() {
		// TODO Auto-generated constructor stub
	}
	
	public Medicine(String medicineId,String medicineName,float medicineCost,LocalDate mfd,LocalDate expiryDate,int quantity,
			Company companyName, String category) {
		super();
		this.medicineId = medicineId;
		this.medicineName = medicineName;
		this.medicineCost = medicineCost;
		this.mfd = mfd;
		this.expiryDate = expiryDate;
		this.quantity=quantity;
		this.companyName = companyName;
		this.category = category;
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
	public Company getCompanyName() {
		return companyName;
	}
	public void setCompanyName(Company companyName) {
		this.companyName = companyName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
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

}