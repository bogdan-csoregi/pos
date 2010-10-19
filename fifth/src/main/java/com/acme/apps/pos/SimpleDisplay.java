package com.acme.apps.pos;

public class SimpleDisplay implements Display {
	
	/* (non-Javadoc)
	 * @see com.acme.apps.pos.second.Display#writePrice(java.lang.String)
	 */
	@Override
	public void write(String line) {
		System.out.println(line);
	}

	@Override
	public void printPrice(double price) {
		System.out.println("$" + price);
	}

	@Override
	public void printEmptyCodeMessage() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void printCodeNotFound(String code) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void printReceip(Receip receip) {
		// TODO Auto-generated method stub
		
	}
}
