package com.acme.apps.pos;

import java.util.ArrayList;
import java.util.List;

import com.acme.apps.pos.Display;

public class MockDisplay implements Display {
	private List<String> displayLines = new ArrayList<String>();
	
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
}
