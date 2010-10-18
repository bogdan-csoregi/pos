package com.acme.apps.pos;

import java.util.HashMap;
import java.util.Map;

public class PointOfSale implements BarcodeEventHandler {
	
	private Display display;
	
	private Map<String, String> items = new HashMap<String, String>(); 
	
	public PointOfSale(Display display) {
		this.display = display;
	}

	public void addItem(String code, String price) {
		items.put(code, price);
	}

	@Override
	public void handle(String code) {
		String price = items.get(code);
		
		if (price == null) {
			throw new IllegalArgumentException("Invalid code.");
		}
		
		display.writePrice(price);
	}
}
