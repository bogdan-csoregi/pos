package com.acme.apps.pos;

import java.util.HashMap;

public class Driver {

	public static void main(String[] args) {
		Display display = new SimpleDisplay();
		Catalog catalog = new Catalog(new HashMap<String, Product>(){{
			put("1234", new Product(100, true));
		}});
		PointOfSale pos = new PointOfSale(catalog, display);
		
		pos.scan("1234");
	}
}
