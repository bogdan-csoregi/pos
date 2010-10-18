package com.acme.apps.pos;

public class Product {
	private double price;
	private boolean needsCommercialTax;
	
	public Product(double price, boolean needsCommercialTax) {
		super();
		this.price = price;
		this.needsCommercialTax = needsCommercialTax;
	}

	public double getPrice() {
		return price;
	}

	public boolean isNeedsCommercialTax() {
		return needsCommercialTax;
	}
}
