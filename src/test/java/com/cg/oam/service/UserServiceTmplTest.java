package com.cg.oam.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

import com.cg.oam.entity.UserEntity;
import com.cg.oam.exception.OrderNotFoundException;
import com.cg.oam.exception.UserException;
import com.cg.oam.model.UserModel;
import com.cg.oam.repository.IUserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTmplTest {
	
	@Mock
	private IUserRepository userRepository;
	@InjectMocks
	private UserServiceImpl userServiceImpl;
	
	@Test
	@DisplayName("find all the users")
	void testFindAll() {
		List<UserEntity> testdata = Arrays.asList(new UserEntity[] {
				 new UserEntity(1L,"Swarna&123","customer"),
				 new UserEntity(2L,"Swarna&123","customer"),
		});
		Mockito.when(userRepository.findAll()).thenReturn(testdata);
		
		List<UserModel> expected = Arrays.asList(new UserModel[] {
				new UserModel(1L,"Swarna&123","customer"),
				new UserModel(2L,"Swarna&123","customer")
		
		});
		List<UserModel>actual = userServiceImpl.findAll();
		assertEquals(expected,actual);
		
	}
	
	@Test
	@DisplayName("get user by id")
	void testFindById() throws  UserException {
		
		UserEntity testdata = new UserEntity(1L,"Swarna&123","customer");
		UserModel expected = new UserModel(1L,"Swarna&123","customer");
		
		
		Mockito.when(userRepository.findById(testdata.getCustomerId())).thenReturn(Optional.of(testdata));
		Mockito.when(userRepository.existsById(1L)).thenReturn(true);
		UserModel actual = userServiceImpl.findById(testdata.getCustomerId());
		
		assertEquals(expected,actual);
	}
	
	@Test
	@DisplayName("get user by id : id not exists")
	void testFindByIdNotExists() throws UserException {
		Mockito.when(userRepository.existsById(4L)).thenReturn(false);
		assertThrows(UserException.class, () -> {
			userServiceImpl.findById(4L);
		});
	}
	
	@Test
	@DisplayName("delete user by id")
	void testDeleteById() {
		Long customerId = 1L;
		userServiceImpl.deleteById(customerId);
		verify(userRepository,times(1)).deleteById(customerId);

	}
}