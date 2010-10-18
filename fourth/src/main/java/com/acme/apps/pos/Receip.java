package com.acme.apps.pos;

public class Receip {
	private double totalWithoutTax;
	private double totalWithTax;
	
	public void addNewPrice(double withoutTax, double withTax) {
		totalWithoutTax += withoutTax;
		totalWithTax += withTax;
	}

	public double getTotalWithoutTax() {
		return totalWithoutTax;
	}

	public double getTotalWithTax() {
		return totalWithTax;
	}
	
	public boolean isEmpty() {
		return totalWithoutTax == 0;
	}
}
