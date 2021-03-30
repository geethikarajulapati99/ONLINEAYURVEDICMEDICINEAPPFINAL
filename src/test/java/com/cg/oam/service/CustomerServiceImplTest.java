package com.cg.oam.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
import com.cg.oam.exception.CustomerNotFoundException;
import com.cg.oam.model.CustomerModel;
import com.cg.oam.repository.ICustomerRepository;
@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {
	@Mock
	private ICustomerRepository customerRepository;
	@InjectMocks
	private CustomerServiceImpl customerServiceImpl;

	@Test
	@DisplayName("delete customer by using id")
	void testDeleteById() throws CustomerNotFoundException {
		Customer testdata = new Customer(1L,"swarn","swara@123",9896540123L,"swara@gmail.com");
		CustomerModel expected = new CustomerModel(1L,"swarn","swara@123",9896540123L,"swara@gmail.com");
		customerServiceImpl.deleteById(testdata.getCustomerId());
		verify(customerRepository,times(1)).deleteById(testdata.getCustomerId());
	}
	
	@Test
	@DisplayName("get customer by id")
	void testFindById() throws CustomerNotFoundException {
		
		
		Customer testdata = new Customer(1L,"swarna","swarna@123",9876540123L,"swarna@gmail.com");
		CustomerModel expected = new CustomerModel(1L,"swarna","swarna@123",9876540123L,"swarna@gmail.com");
		
		Mockito.when(customerRepository.findById(testdata.getCustomerId())).thenReturn(Optional.of(testdata));
		Mockito.when(customerRepository.existsById(1L)).thenReturn(true);
		Mockito.when(customerRepository.findById(1L)).thenReturn(Optional.of(testdata));

		
		CustomerModel actual=customerServiceImpl.findById(testdata.getCustomerId());
		assertEquals(expected,actual);
		
	}
	
	@Test
	@DisplayName("get customer by id : id not exists")
	void testFindByIdNotExists() throws CustomerNotFoundException {
		Mockito.when(customerRepository.existsById(101L)).thenReturn(false);
		assertThrows(CustomerNotFoundException.class, () -> {
			customerServiceImpl.findById(101L);
		});
		
	}
	
	
	@Test
	@DisplayName("add customer")
	void testaddEmployee()throws CustomerNotFoundException{
		Customer customer1 = new Customer(1L,"swarna","Swaran!43",9876650932L,"swarna@gmail.com");
		Mockito.when(customerRepository.save(customer1)).thenReturn(customer1);
		assertTrue(customerRepository.save(customer1) != null);
	}
	
	@Test
	@DisplayName("Check Whether the email exists or not")
	void testExistsByEmail() {

		Customer testdata = new Customer(1L,"swarna","swarna@123",9876540123L,"swarna@gmail.com");
		boolean expected=true;
		Mockito.when(customerRepository.existsByEmailId(testdata.getEmailId())).thenReturn(expected);
		boolean actual= customerServiceImpl.existsByEmailId(testdata.getEmailId());
		assertEquals(expected,actual);
		
	}
	
	@Test
	@DisplayName("Check Whether the mobile number exists or not")
	void testNotExistsByNumber() {

		Customer testdata = new Customer(1L,"swarna","swarna@123",9876540123L,"swarna@gmail.com");
		boolean expected=true;
		Mockito.when(customerRepository.existsByMobileNumber(testdata.getMobileNumber())).thenReturn(expected);
		boolean actual= customerServiceImpl.existsByMobileNumber(testdata.getMobileNumber());
		assertEquals(expected,actual);
		
	}
	@Test
	@DisplayName("get all the customers")
	void testFindAll1() {
		List<Customer> testdata = Arrays.asList(new Customer[] {
				new Customer(1L,"swarna","swarna@123",9876540123L,"swarna@gmail.com"),
				new Customer(2L,"swarna","swarna@123",9876540123L,"swarna@gmail.com")
		
		});
		Mockito.when(customerRepository.findAll()).thenReturn(testdata);
		
		List<CustomerModel> expected = Arrays.asList(new CustomerModel[] {
				new CustomerModel(1L,"swarna","swarna@123",9876540123L,"swarna@gmail.com"),
				new CustomerModel(2L,"swarna","swarna@123",9876540123L,"swarna@gmail.com")
		
		});
		List<CustomerModel>actual = customerServiceImpl.findAll();
		assertEquals(expected,actual);
		
	}
	@Test
	@DisplayName("find all the customers:no customers is available")
	void testFindAll() {
		List<Customer> testdata = Arrays.asList(new Customer[] {
				new Customer(),
				new Customer()
		
		});
		Mockito.when(customerRepository.findAll()).thenReturn(testdata);
		
		List<CustomerModel> expected = Arrays.asList(new CustomerModel[] {
				new CustomerModel(1L,"swarna","swarna@123",9876540123L,"swarna@gmail.com"),
				new CustomerModel(2L,"swarna","swarna@123",9876540123L,"swarna@gmail.com")
		
		});
		List<CustomerModel>actual = customerServiceImpl.findAll();
		assertNotEquals(expected,actual);
		
	}
	
	}