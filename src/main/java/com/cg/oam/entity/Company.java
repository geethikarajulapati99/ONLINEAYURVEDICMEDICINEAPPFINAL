package com.cg.oam.entity;

public enum Company {
	Divis("Divis Laboratories Ltd."), 
	sun("Sun Pharmaceutical Industries Ltd."),
	Reddy("Dr. Reddys Laboratories Ltd."), 
	Cipla("Cipla Ltd."), Balcon("Biocon Ltd.");
	String name;

	Company(String string) {
		this.name = string;
	}
	
	
}
