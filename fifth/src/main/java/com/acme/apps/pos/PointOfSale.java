package com.acme.apps.pos;

import java.util.ArrayList;
import java.util.List;

public class PointOfSale {
	public static final double FED_TAX = 0.05;
	public static final double COMERCIAL_TAX = 0.08;
	
	private Catalog catalog;
	private Display display;
	
	private Receip receip = new Receip();
	private List<Receip> receipsCach = new ArrayList<Receip>();
	private List<Receip> receipsCard = new ArrayList<Receip>();
	
	public PointOfSale(Catalog catalog, Display display) {
		this.catalog = catalog;
		this.display = display;
	}

	public void scan(String code) {
		if ("".equals(code)) {
			display.write("Empty code.");
		} else {
			printProductForCode(code);
		}
	}
	
	private void printProductForCode(String code) {
		Product product = catalog.getProduct(code);
		
		if (product == null) {
			display.write("Couldn't find a product to match the code:" + code);
		} else {
			Double fedTax = product.getPrice() * FED_TAX;
			Double commercialTax = 0.0;
			if (product.isNeedsCommercialTax()) {
				commercialTax = product.getPrice() * COMERCIAL_TAX;
			}
			Double finalPrice = product.getPrice() + fedTax + commercialTax;
			receip.addNewPrice(product.getPrice(), product.isNeedsCommercialTax(), fedTax, commercialTax);
			display.printPrice(finalPrice);
		}
	}

	public void payWithCash() {
		pay(receipsCach);
	}
	
	public void payWithCard() {
		pay(receipsCard);
	}
	
	private void pay(List<Receip> receips) {
		if (receip.isEmpty()) {
			throw new IllegalStateException("No item to pay.");
		}
		receips.add(receip);
		
		for (ReceipItem item : receip.getItems()) {
			String line = "$" + item.getPrice() + " G";
			if (item.isHasCommercialTax()) {
				line += "P";
			}
			display.write(line);
		}
		display.write("Total: $" + receip.getTotalWithoutTax());
		display.write("GST: $" + receip.getGST());
		display.write("PST: $" + receip.getPST());
		display.write("Grand Total: $" + receip.getTotalWithTax());
		receip = new Receip();
	}

	public List<Receip> getCashReport() {
		List<Receip> result = new ArrayList<Receip>(receipsCach);
		receipsCach.clear();
		return result;
	}
	
	public List<Receip> getCardReport() {
		List<Receip> result = new ArrayList<Receip>(receipsCard);
		receipsCard.clear();
		return result;
	}

	public void setDisplay(MockDisplay display) {
		this.display = display;
		
	}
}
