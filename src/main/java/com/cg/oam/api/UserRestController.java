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

import com.cg.oam.exception.UserException;
import com.cg.oam.model.CustomerModel;
import com.cg.oam.model.UserModel;
import com.cg.oam.service.ICustomerService;
import com.cg.oam.service.IUserService;

@RestController
@RequestMapping("/users")
public class UserRestController {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private ICustomerService customerService;
	
	@GetMapping
	public ResponseEntity<List<UserModel>> findAll() {
		return ResponseEntity.ok(userService.findAll());
	}
	
	@GetMapping("/{customerId}")
	public ResponseEntity<UserModel> findById(@PathVariable("customerId") Long customerId){
		return ResponseEntity.ok(userService.findById(customerId));
	}
	
	@PostMapping("/signIn")
	public ResponseEntity<CustomerModel> signIn(@RequestBody UserModel user){
		ResponseEntity<CustomerModel> response=null;
		CustomerModel customer=customerService.findById(user.getCustomerId());
		if(user.getPassword().equals(customer.getPassword())) {
			response=new ResponseEntity<>(customer,HttpStatus.OK);
		}else {
			response=new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}		
		return response;
	}
	
	
	@PostMapping("/register")
	public ResponseEntity<UserModel> add(@RequestBody UserModel user) throws UserException {
		ResponseEntity<UserModel> response=null;
		if(user==null) {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			user=userService.add(user);
			response= new ResponseEntity<>(HttpStatus.OK);
		}
		return response;
	}
	
	@DeleteMapping("/{customerId}")
	public ResponseEntity<Void> deleteUser(@PathVariable("customerId") Long customerId) {
		ResponseEntity<Void> response=null;
		UserModel user=userService.findById(customerId);
		if(user==null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			userService.deleteById(customerId);
			System.out.println("deleted");
		}
		return response;
	}
	
	@PutMapping
	public ResponseEntity<UserModel> updateUser(@RequestBody UserModel user) throws UserException{
		user =userService.save(user);
		return ResponseEntity.ok(user);
	}
}
