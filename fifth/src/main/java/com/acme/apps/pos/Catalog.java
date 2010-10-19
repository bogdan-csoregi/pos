package com.acme.apps.pos;

import java.util.HashMap;
import java.util.Map;

public class Catalog {
	private Map<String, Product> products;

	public Catalog(HashMap<String, Product> products) {
		this.products = products; 
	}

	public Product getProduct(String code) {
		return products.get(code);
	}
}
