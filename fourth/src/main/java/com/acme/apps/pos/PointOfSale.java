package com.acme.apps.pos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PointOfSale implements BarcodeEventHandler {
	public static final double FED_TAX = 1.05;
	public static final double COMERCIAL_TAX = 0.08;
	
	private Display display;
	private Map<String, Product> items = new HashMap<String, Product>();
	private Receip receip = new Receip();
	private List<Receip> receipsCach = new ArrayList<Receip>();
	private List<Receip> receipsCard = new ArrayList<Receip>();
	
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
			display.write("Couldn't find a product to match ther code:" + code);
		} else {
			Double finalPrice = product.getPrice() * FED_TAX;
			
			if (product.isNeedsCommercialTax()) {
				finalPrice = finalPrice + (product.getPrice() * COMERCIAL_TAX); 
			}
			
			receip.addNewPrice(product.getPrice(), finalPrice);
			display.write("Price: " + finalPrice);
		}
	}

	public void payWithCash() {
		if (receip.isEmpty()) {
			throw new IllegalStateException("No item to pay.");
		}
		receipsCach.add(receip);
		receip = new Receip();
	}
	
	public void payWithCard() {
		if (receip.isEmpty()) {
			throw new IllegalStateException("No item to pay.");
		}
		receipsCard.add(receip);
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
