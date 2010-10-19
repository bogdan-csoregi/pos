package com.acme.apps.pos;

public interface Display {

	void printPrice(double price);

	void printEmptyCodeMessage();
	
	void printCodeNotFound(String code);
	
	void printReceip(Receip receip);
	
	void write(String line);

}