package com.cg.oam.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.oam.entity.Company;
import com.cg.oam.entity.Customer;
import com.cg.oam.entity.Medicine;
import com.cg.oam.entity.Order;
import com.cg.oam.entity.UserEntity;
import com.cg.oam.model.CustomerModel;
import com.cg.oam.model.MedicineModel;
import com.cg.oam.model.OrderModel;
import com.cg.oam.model.UserModel;
import com.cg.oam.repository.ICustomerRepository;

@Service
public class EMParser {
	@Autowired
	private ICustomerRepository customerRepo;

	public CustomerModel parse(Customer source) {
		return source == null ? null
				: new CustomerModel(source.getCustomerId(),
						source.getCustomerName(),
						source.getCustomerPassword(),
						source.getMobileNumber(),
						source.getEmailId());
	}

	public Customer parse(CustomerModel source) {
		return source == null ? null
				: new Customer(source.getCustomerId(),
						source.getCustomerName(),
						source.getPassword(),
						source.getMobileNumber(),
						source.getEmailId());
	}
	public MedicineModel parse(Medicine source) {
		return source == null ? null
				: new MedicineModel(source.getMedicineId(),
						source.getMedicineName(),
						source.getMedicineCost(),
						source.getMfd(),
						source.getExpiryDate() , 
						source.getQuantity(),
						source.getCategory(),
						String.valueOf(source.getCompanyName()));
	}

	
	 
	 public Medicine parse(MedicineModel source) { 
		 return source==null?null:
			 new Medicine(source.getMedicineId(),
					 source.getMedicineName(),
					 source.getMedicineCost(),source.getMfd(),
					 source.getExpiryDate(),
					 source.getQuantity(),
					 Company.valueOf(source.getCompanyName()),
					 source.getCategoryId());
							 
	 }	 
	
	
	public OrderModel parse(Order source) {
		return source==null?null:
			new OrderModel(source.getOrderId(),
					source.getOrderDate(),
					source.getDispatchDate(),
					source.getTotalCost(),
					source.getStatus(),
				source.getCustomer().getCustomerId(),
				source.getMedicineList());
	}

	public Order parse(OrderModel source) {
		
		return source==null?null:
			new Order(source.getOrderId(),
					source.getOrderDate(),
					source.getDispatchDate(),
					this.calculateTotalCost(source),
					source.getStatus(),
					customerRepo.findById(source.getCustomerId()).orElse(null),
					source.getMedicineList());
			}
	
	private float calculateTotalCost(OrderModel orderModel) { 
		float totalCost =0;
		List<Medicine> medicines = new ArrayList<Medicine>(); 
		medicines =orderModel.getMedicineList();
	 
		for (Medicine medical : medicines) { 
			totalCost = totalCost + medical.getMedicineCost() * medical.getQuantity(); 
		}
		return totalCost;
	}

	public UserModel parse(UserEntity source) {
		return source==null?null:
			new UserModel(source.getCustomerId(),
						source.getPassword(),
						source.getRole());
	}

	public UserEntity parse(UserModel source) {
		return source==null?null:
			new UserEntity(source.getCustomerId(),
						source.getPassword(),
						source.getRole());
	}
}
