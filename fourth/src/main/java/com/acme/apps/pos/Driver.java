package com.acme.apps.pos;

public class Driver {

	public static void main(String[] args) {
		Display display = new SimpleDisplay();
		PointOfSale pos = new PointOfSale(display);
		pos.addItem("1234", new Product(100, true));
		BarCodeScanner barCodeScanner = new BarCodeScanner(pos);
		
		barCodeScanner.scan("1234");
	}
}
