package com.acme.apps.pos;

import java.util.ArrayList;
import java.util.List;

public class Receip {
	private List<ReceipItem> items = new ArrayList<ReceipItem>();
	private double GST;
	private double PST;
	private double totalWithoutTax;
	private double totalWithTax;
	
	public void addNewPrice(double price, boolean hasCommercialTax, double fedTax, double commercialTax) {
		ReceipItem item = new ReceipItem(price, hasCommercialTax);
		items.add(item);
		GST += fedTax;
		PST += commercialTax;
		totalWithoutTax += price;
		totalWithTax += price + (fedTax + commercialTax);
	}

	public double getTotalWithoutTax() {
		return totalWithoutTax;
	}

	public double getTotalWithTax() {
		return totalWithTax;
	}
	
	public boolean isEmpty() {
		return totalWithoutTax == 0;
	}

	public List<ReceipItem> getItems() {
		return items;
	}

	public double getGST() {
		return GST;
	}

	public double getPST() {
		return PST;
	}
}
