package com.cg.oam.api;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import com.cg.oam.exception.OrderNotFoundException;
import com.cg.oam.model.OrderModel;
import com.cg.oam.service.IOrderService;

@RestController
@RequestMapping(path="/orders")
public class OrderAPI {
	@Autowired
	private IOrderService orderService;
	
	@GetMapping("/getallorders")
	public ResponseEntity<List<OrderModel>> findAll() {
		return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK); 
	}

	@GetMapping("/getorderdate/{orderDate}")
	public ResponseEntity<List<OrderModel>> findAllByOrderDate(@PathVariable(name = "orderDate") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate orderDate) {
		System.out.println("date is valid"+orderDate);
		ResponseEntity<OrderModel> response = null;
		List<OrderModel> order = orderService.findAllByOrderDate(orderDate);

			return new ResponseEntity<>(orderService.findAllByOrderDate(orderDate), HttpStatus.OK); 
		
	}
	
	@GetMapping("/getorderbycustomerid/{customerId}")
	public ResponseEntity<List<OrderModel>> findAllByCustomerId(@PathVariable(name = "customerId") Long customerId)throws OrderNotFoundException {
		ResponseEntity<OrderModel> response = null;
		List<OrderModel> order = orderService.findAllByCustomerId(customerId);

		return new ResponseEntity<>(orderService.findAllByCustomerId(customerId), HttpStatus.OK); 
		
	}
	
	@GetMapping("/getorder/{orderId}")
	public ResponseEntity<OrderModel> findById(@PathVariable("orderId") Long orderId)throws OrderNotFoundException {
		ResponseEntity<OrderModel> response = null;
		OrderModel order = orderService.findById(orderId);
		
		if (order == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			response = new ResponseEntity<>(order, HttpStatus.OK);
		}
		return response;
	}
	
	@PostMapping("/addorder")
	public ResponseEntity<OrderModel> createOrder(@RequestBody OrderModel order) throws OrderNotFoundException {
		order = orderService.add(order);
		return new ResponseEntity<>(order, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deleteorder/{orderId}")
	public ResponseEntity<Void> deleteOrder(@PathVariable("orderId") Long orderId) throws OrderNotFoundException {
		ResponseEntity<Void> response = null;
		OrderModel order = orderService.findById(orderId);
		if (order == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			orderService.deleteById(orderId);
			response = new ResponseEntity<>(HttpStatus.OK);
		}
		return response;
	}
	@PutMapping("/updateorder/{orderId}")
	public ResponseEntity<OrderModel> updateOrder(@RequestBody OrderModel order,@PathVariable("orderId") Long userId)throws OrderNotFoundException {
		order = orderService.modify(order,order.getOrderId());
		return new ResponseEntity<>(order, HttpStatus.OK);
	}
	
	
	
	
}
