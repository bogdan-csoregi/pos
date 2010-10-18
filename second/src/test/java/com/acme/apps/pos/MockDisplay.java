package com.acme.apps.pos;

import com.acme.apps.pos.Display;

public class MockDisplay implements Display {
	private String lastDisplayLine;
	
	@Override
	public void writePrice(String price) {
		lastDisplayLine = price;
	}
	
	public String getLastDisplayLine() {
		return lastDisplayLine;
	}
}
