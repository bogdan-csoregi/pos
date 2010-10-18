package com.acme.apps.pos;

public class BarCodeScanner {
	private BarcodeEventHandler eventHandler;
	
	public BarCodeScanner(BarcodeEventHandler eventHandler) {
		this.eventHandler = eventHandler;
	}

	public void scan(String code) {
		eventHandler.handle(code);
	}
}
