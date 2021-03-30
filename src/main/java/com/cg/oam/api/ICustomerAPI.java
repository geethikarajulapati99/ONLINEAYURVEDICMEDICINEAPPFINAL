package com.cg.oam.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.oam.exception.CustomerNotFoundException;
import com.cg.oam.model.CustomerModel;
import com.cg.oam.service.ICustomerService;

@RestController
@RequestMapping(path="/customer")
public class ICustomerAPI {
	@Autowired
	private ICustomerService cusService;
	
	@GetMapping("/getallcustomers")
	public ResponseEntity<List<CustomerModel>> findAll() {
		return new ResponseEntity<>(cusService.findAll(), HttpStatus.OK); 
	}
	
	@GetMapping("/getcustomer/{customerId}")
	public ResponseEntity<CustomerModel> findById(@PathVariable("customerId") Long customerId)throws CustomerNotFoundException {
		ResponseEntity<CustomerModel> response = null;
		System.out.println("find-by-cusid");
		CustomerModel cus = cusService.findById(customerId);
		
		if (cus == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			response = new ResponseEntity<>(cus, HttpStatus.OK);
		}
		return response;
	}
	
	
	@PostMapping("/addcustomer")
	public ResponseEntity<CustomerModel> createCustomer(@RequestBody CustomerModel cus) throws CustomerNotFoundException {
		cus = cusService.add(cus);
		return new ResponseEntity<>(cus, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deletecustomer/{customerId}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable("customerId") Long customerId) {
		ResponseEntity<Void> response = null;
		CustomerModel cus = cusService.findById(customerId);
		if (cus == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			cusService.deleteById(customerId);
			response = new ResponseEntity<>(HttpStatus.OK);
		}
		return response;
	}
	@PutMapping("/updatecustomer/{customerId}")
	public ResponseEntity<CustomerModel> updateEmployee(@RequestBody CustomerModel cus,@PathVariable("customerId") Long customerId)throws CustomerNotFoundException {
		cus = cusService.modify(cus,cus.getCustomerId());
		return new ResponseEntity<>(cus, HttpStatus.OK);
	}
}
