package com.cg.oam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.oam.entity.Customer;
@Repository
public interface ICustomerRepository extends JpaRepository<Customer,Long>{
	boolean existsByMobileNumber(Long mobileNumber);
	boolean existsByEmailId(String emailId);
}
