package com.cg.oam.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cg.oam.entity.Order;

public interface IOrderRepository extends JpaRepository<Order, Long> {
	List<Order> findAllByOrderDate(LocalDate orderDate);
	
}

