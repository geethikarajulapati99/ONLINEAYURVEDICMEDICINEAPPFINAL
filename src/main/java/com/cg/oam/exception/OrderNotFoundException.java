package com.cg.oam.exception;

public class OrderNotFoundException extends Exception{	
private static final long serialVersionUID = 1L;
	
	public OrderNotFoundException(String err) {
		super(err);

	}
}
