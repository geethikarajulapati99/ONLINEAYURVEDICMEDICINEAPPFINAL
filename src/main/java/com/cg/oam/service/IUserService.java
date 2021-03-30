package com.cg.oam.service;

import java.util.List;

import com.cg.oam.exception.UserException;
import com.cg.oam.model.UserModel;

public interface IUserService {

	boolean existsById(Long customerId);	
	
	UserModel add(UserModel user) throws UserException;	
	UserModel save(UserModel user) throws UserException;
	
	UserModel signIn(UserModel user) throws UserException;	
	
	void deleteById(Long customerId);
	
	UserModel findById(Long customerId) throws UserException;
	List<UserModel> findAll();
}