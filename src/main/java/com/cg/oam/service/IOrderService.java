package com.cg.oam.service;

import java.time.LocalDate;
import java.util.List;
import com.cg.oam.exception.OrderNotFoundException;
import com.cg.oam.model.OrderModel;

public interface IOrderService {
	OrderModel add(OrderModel orderModel) throws OrderNotFoundException;
	OrderModel save(OrderModel orderModel) throws OrderNotFoundException;
	void deleteById(Long orderId);
	OrderModel findById(Long orderId) throws OrderNotFoundException ;
	List<OrderModel> findAll();
	List<OrderModel> findAllByCustomerId(Long customerId);
	List<OrderModel> findAllByOrderDate(LocalDate orderDate);
	OrderModel modify(OrderModel orderModel, Long orderId) throws OrderNotFoundException;
}
