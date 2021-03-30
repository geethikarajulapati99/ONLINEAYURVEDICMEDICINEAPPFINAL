package com.cg.oam.service;

import java.util.List;
import com.cg.oam.exception.CustomerNotFoundException;
import com.cg.oam.model.CustomerModel;

public interface ICustomerService {
	CustomerModel add(CustomerModel customer) throws CustomerNotFoundException;
	CustomerModel save(CustomerModel customer) throws CustomerNotFoundException;
	void deleteById(Long customerId);
	CustomerModel findById(Long customerId);
	List<CustomerModel> findAll();
	boolean existsByMobileNumber(Long mobileNumber); 
	boolean existsByEmailId(String emailId);
	CustomerModel modify(CustomerModel customerModel,Long customerId)throws CustomerNotFoundException;
	
}
