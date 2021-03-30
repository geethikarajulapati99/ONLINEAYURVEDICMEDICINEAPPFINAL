package com.cg.oam.exception;

public class MedicineNotFoundException extends Exception{	
private static final long serialVersionUID = 1L;
	
	public MedicineNotFoundException(String err) {
		super(err);
	}

}
