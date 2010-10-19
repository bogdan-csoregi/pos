package com.acme.apps.pos;

public class ReceipItem {
	private double price;
	private boolean hasCommercialTax;
	
	public ReceipItem(double price, boolean hasCommercialTax) {
		super();
		this.price = price;
		this.hasCommercialTax = hasCommercialTax;
	}

	public double getPrice() {
		return price;
	}

	public boolean isHasCommercialTax() {
		return hasCommercialTax;
	}
}
