package com.acme.apps.pos;

import java.util.ArrayList;
import java.util.List;

import com.acme.apps.pos.Display;

public class MockDisplay implements Display {
	private List<String> displayLines = new ArrayList<String>();
	
	private List<Double> priceLines = new ArrayList<Double>();
	
	@Override
	public void write(String line) {
		displayLines.add(line);
	}
	
	public List<String> getDisplayLines() {
		return displayLines;
	}
	
	public void clearDisplay() {
		displayLines.clear();
	}

	@Override
	public void printPrice(double price) {
		priceLines.add(price);
	}

	@Override
	public void printEmptyCodeMessage() {
		// TODO Auto-generated method stub
	}

	@Override
	public void printCodeNotFound(String code) {
		// TODO Auto-generated method stub
	}

	@Override
	public void printReceip(Receip receip) {
		// TODO Auto-generated method stub
	}
}
