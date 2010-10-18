package com.acme.apps.pos;

import java.util.HashMap;
import java.util.Map;

public class PointOfSale implements BarcodeEventHandler {
	public static final double FED_TAX = 1.05;
	public static final double COMERCIAL_TAX = 0.08;
	
	private Display display;
	
	private Map<String, Product> items = new HashMap<String, Product>(); 
	
	public PointOfSale(Display display) {
		this.display = display;
	}

	public void addItem(String code, Product product) {
		items.put(code, product);
	}

	@Override
	public void handle(String code) {
		Product product = items.get(code);
		
		if (product == null) {
			throw new IllegalArgumentException("Can't find the product for the provided code.");
		}
		
		Double finalPrice = product.getPrice() * FED_TAX;
		
		if (product.isNeedsCommercialTax()) {
			finalPrice = finalPrice + (product.getPrice() * COMERCIAL_TAX); 
		}
		
		display.write("Price: " + finalPrice);
	}
}
