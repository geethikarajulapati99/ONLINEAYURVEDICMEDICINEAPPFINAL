package com.cg.oam.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.cg.oam.entity.Customer;
import com.cg.oam.entity.Order;
import com.cg.oam.exception.OrderNotFoundException;
import com.cg.oam.model.OrderModel;
import com.cg.oam.repository.IOrderRepository;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {
	@Mock
	private IOrderRepository orderRepository;
	@InjectMocks
	private OrderServiceImpl orderServiceImpl;
	
	
	@Test
	@DisplayName("delete order by id")
	void testDeleteById() {
		Long orderId = 1L;
		orderServiceImpl.deleteById(orderId);
		verify(orderRepository,times(1)).deleteById(orderId);

	}
	@Test
	@DisplayName("get customer by id")
	void testFindById() throws OrderNotFoundException {
		
		Order testdata = new Order(4L,LocalDate.parse("2021-03-24"),LocalDate.parse("2021-03-30"),65,"Delivered", new Customer(1L,"swarn","swara@123",9896540123L,"swara@gmail.com"));
		OrderModel expected = new OrderModel(4L,LocalDate.parse("2021-03-24"),LocalDate.parse("2021-03-30"),65,"Delivered",1L);
		
		
		Mockito.when(orderRepository.findById(testdata.getOrderId())).thenReturn(Optional.of(testdata));
		
		OrderModel actual=orderServiceImpl.findById(testdata.getOrderId());
		assertEquals(expected,actual);
	}
	@Test
	@DisplayName("find all the customers")
	void testFindAll() {
		List<Order> testdata = Arrays.asList(new Order[] {
				 new Order(1L,LocalDate.parse("2021-03-24"),LocalDate.parse("2022-03-24"),65,"Delivered", new Customer(1L,"swarn","swara@123",9896540123L,"swara@gmail.com")),
				 new Order(2L,LocalDate.parse("2021-03-24"),LocalDate.parse("2022-03-24"),65,"Delivered", new Customer(2L,"swarn","swara@123",9296540123L,"swara@gmail.com"))
					
		
		});
		Mockito.when(orderRepository.findAll()).thenReturn(testdata);
		
		List<OrderModel> expected = Arrays.asList(new OrderModel[] {
				new OrderModel(1L,LocalDate.parse("2021-03-24"),LocalDate.parse("2022-03-24"),65,"Delivered",1L),
				new OrderModel(2L,LocalDate.parse("2021-03-24"),LocalDate.parse("2022-03-24"),65,"Delivered",2L)
		
		});
		List<OrderModel>actual = orderServiceImpl.findAll();
		assertEquals(expected,actual);
		
	}
	
	@Test
	@DisplayName("get medicine by id : id not exists")
	void testFindByIdNotExists() {
		Order testdata = new Order(4L,LocalDate.parse("2021-03-24"),LocalDate.parse("2021-03-30"),65,"Delivered", new Customer(1L,"swarn","swara@123",9896540123L,"swara@gmail.com"));
		OrderModel expected = new OrderModel(1L,LocalDate.parse("2021-03-24"),LocalDate.parse("2021-03-30"),65,"Delivered",1L);
		
		
		Mockito.when(orderRepository.findById(testdata.getOrderId())).thenReturn(Optional.of(testdata));
		
		OrderModel actual=orderServiceImpl.findById(testdata.getOrderId());
		assertNotEquals(expected,actual);
	}

	@Test
	@DisplayName("find all the medicine:no medicine is available")
	void testFindAll1() {
		List<Order> testdata = Arrays.asList(new Order[] {
				 new Order(1L,LocalDate.parse("2021-03-24"),LocalDate.parse("2022-03-24"),65,"Delivered", new Customer(1L,"swarn","swara@123",9896540123L,"swara@gmail.com")),
				 new Order(2L,LocalDate.parse("2021-03-24"),LocalDate.parse("2022-03-24"),65,"Delivered", new Customer(2L,"swarn","swara@123",9296540123L,"swara@gmail.com"))
					
		
		});
		Mockito.when(orderRepository.findAll()).thenReturn(testdata);
		
		List<OrderModel> expected = Arrays.asList(new OrderModel[] {
				new OrderModel(1L,LocalDate.parse("2021-03-24"),LocalDate.parse("2022-03-24"),65,"Delivered",1L),
		
		});
		List<OrderModel>actual = orderServiceImpl.findAll();
		assertNotEquals(expected,actual);
	}
	
	@Test
	@DisplayName("get by id return null")
	void testGetByIdNull() {		
		
		Mockito.when(orderRepository.findById(1L)).thenReturn(Optional.empty());
		
		OrderModel actual = orderServiceImpl.findById(1L);
		assertNull(actual);
	}
	
	

}
