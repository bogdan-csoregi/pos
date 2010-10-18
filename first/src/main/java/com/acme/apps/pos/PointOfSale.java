package com.acme.apps.pos;

import java.util.HashMap;
import java.util.Map;

public class PointOfSale {
	
	private Map<String, String> items = new HashMap<String, String>();
	
	public void addItem(String code, String price) {
		items.put(code, price);
	}
	
	public String scan(String code) {
		String price = items.get(code);
		
		if (price == null) {
			throw new IllegalArgumentException("Invalid code.");
		}
		
		return price;
	}
}
