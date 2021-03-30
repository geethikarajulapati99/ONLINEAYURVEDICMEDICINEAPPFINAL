package com.cg.oam.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.oam.exception.CustomerNotFoundException;
import com.cg.oam.model.CustomerModel;
import com.cg.oam.repository.ICustomerRepository;

@Service
public class CustomerServiceImpl implements ICustomerService{
	@Autowired
	private ICustomerRepository cusRepo;
	@Autowired
	private EMParser parser;
	
	public CustomerServiceImpl() {
		
	}
	public CustomerServiceImpl(ICustomerRepository repo) {
		super();
		this.cusRepo = repo;
		this.parser=new EMParser();
	}
	
	/**
	 * @return the parser
	 */
	public EMParser getParser() {
		return parser;
	}
	/**
	 * @param parser the parser to set
	 */
	public void setParser(EMParser parser) {
		this.parser = parser;
	}
	@Transactional
	@Override
	public CustomerModel add(CustomerModel customer) throws CustomerNotFoundException {
		if (customer != null) {
			if (cusRepo.existsById(customer.getCustomerId())) {
				throw new CustomerNotFoundException("Customer with Id " + customer.getCustomerId() + " is exist already");
			} else if (cusRepo.existsByMobileNumber(customer.getMobileNumber())) {
				throw new CustomerNotFoundException("Customer with mobile number " + customer.getMobileNumber() + " is exist already");
			} else if (cusRepo.existsByEmailId(customer.getEmailId())) {
				throw new CustomerNotFoundException("Customer with email " + customer.getEmailId() + " is exist already");
			} else {
				customer = parser.parse(cusRepo.save(parser.parse(customer)));
			}
		}
		return customer;
	}
	@Transactional
	@Override
	public CustomerModel save(CustomerModel customer) throws CustomerNotFoundException {
		return customer = parser.parse(cusRepo.save(parser.parse(customer)));
	}
	@Transactional
	@Override
	public void deleteById(Long customerId) {
		cusRepo.deleteById(customerId);
		
	}
	
	@Transactional
	@Override
	public CustomerModel findById(Long customerId) throws CustomerNotFoundException {
		if (!cusRepo.existsById(customerId))
			throw new CustomerNotFoundException("No Customer  found for the given id");
		return parser.parse(cusRepo.findById(customerId).get());
	}
	
	@Transactional
	@Override
	public List<CustomerModel> findAll() {
		
		return cusRepo.findAll().stream().map(parser::parse).collect(Collectors.toList());
	}
	@Override
	public boolean existsByMobileNumber(Long mobileNumber) {
		
		return cusRepo.existsByMobileNumber(mobileNumber);
	}
	@Override
	public boolean existsByEmailId(String emailId) {
		return cusRepo.existsByEmailId(emailId);
	}
	@Override
	public CustomerModel modify(CustomerModel customerModel, Long customerId) throws CustomerNotFoundException {
		if(customerModel != null) {
			if(!cusRepo.existsById(customerId)) {
				throw new CustomerNotFoundException("no such id");
			}
			customerModel = parser.parse(cusRepo.save(parser.parse(customerModel)));
		}
		return customerModel;
	}
	
	
}