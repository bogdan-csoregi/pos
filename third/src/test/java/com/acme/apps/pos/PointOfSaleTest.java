package com.acme.apps.pos;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import com.acme.apps.pos.BarCodeScanner;
import com.acme.apps.pos.PointOfSale;

public class PointOfSaleTest {
	MockDisplay display;
	PointOfSale pos;
	BarCodeScanner barCodeScanner;
	
	private static final String code1 = "1234";
	private static final double price1 = 100;
	private static final String expectedPrice1 = "Price: 113.0";
	
	private static final String code2 = "4321";
	private static final double price2 = 200;
	private static final String expectedPrice2 = "Price: 210.0";
	
	private static final String code3 = "6789";

	@Before
	public void setUp() throws Exception {
		display = new MockDisplay();
		pos = new PointOfSale(display);
		pos.addItem(code1, new Product(price1, true));
		pos.addItem(code2, new Product(price2, false));
		barCodeScanner = new BarCodeScanner(pos);
	}

	@After
	public void tearDown() throws Exception {
		display.clearDisplay();
	}

	@Test
	public void testSimple() {
		barCodeScanner.scan(code1);
		List<String> result = display.getDisplayLines();
		assertEquals("Not the expected price.", expectedPrice1, result.get(0));
	}
	
	@Test
	public void testCode2() {
		barCodeScanner.scan(code2);
		List<String> result = display.getDisplayLines();
		assertEquals("Not the expected price.", expectedPrice2, result.get(0));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testUnknownCode() {
		barCodeScanner.scan(code3);
	}
	
	@Test
	public void testMultipleScans() {
		barCodeScanner.scan(code1);
		barCodeScanner.scan(code2);
		List<String> result = display.getDisplayLines();
		
		List<String> expectedResult = new ArrayList<String>();
		expectedResult.add(expectedPrice1);
		expectedResult.add(expectedPrice2);
		
		assertEquals("Not the expected price.", expectedResult, result);
	}
}
