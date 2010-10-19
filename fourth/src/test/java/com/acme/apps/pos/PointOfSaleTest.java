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
	
	private static final String code3 = "1111";
	private static final double price3 = 400;
	private static final String expectedPrice3 = "Price: 420.0";
	
	private static final String code4 = "2222";
	private static final double price4 = 600;
	private static final String expectedPrice4 = "Price: 630.0";
	
	private static final String code10 = "6789";

	@Before
	public void setUp() throws Exception {
		display = new MockDisplay();
		pos = new PointOfSale(display);
		pos.addItem(code1, new Product(price1, true));
		pos.addItem(code2, new Product(price2, false));
		pos.addItem(code3, new Product(price3, false));
		pos.addItem(code4, new Product(price4, false));
		barCodeScanner = new BarCodeScanner(pos);
	}

	@After
	public void tearDown() throws Exception {
		display.clearDisplay();
	}

	@Test
	public void testCode1() {
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
	
	@Test
	public void testUnknownCode() {
		barCodeScanner.scan(code10);
		
		List<String> result = display.getDisplayLines();
		assertEquals("Not the expected message.", "Couldn't find a product to match ther code:" + code10, result.get(0));
	}
	
	@Test
	public void testMultipleScans() {
		barCodeScanner.scan(code1);
		barCodeScanner.scan(code2);
		barCodeScanner.scan(code3);
		barCodeScanner.scan(code4);
		List<String> result = display.getDisplayLines();
		
		List<String> expectedResult = new ArrayList<String>(){{
			add(expectedPrice1);
			add(expectedPrice2);
			add(expectedPrice3);
			add(expectedPrice4);
			}};
		
		
		assertEquals("Not the expected price.", expectedResult, result);
	}
	
	@Test
	public void testEndOfDayCashReport() {
		barCodeScanner.scan(code1);
		barCodeScanner.scan(code2);
		pos.payWithCash();
		
		barCodeScanner.scan(code1);
		pos.payWithCash();
		
		List<Receip> result = pos.getCashReport();
		
		assertEqualsReport("Not the expected report line.", new double[][] {{300.0, 323.0}, {100, 113}}, result);
	}
	
	@Test
	public void testEndOfDayCardReport() {
		barCodeScanner.scan(code1);
		barCodeScanner.scan(code2);
		pos.payWithCard();
		
		barCodeScanner.scan(code1);
		pos.payWithCard();
		
		List<Receip> result = pos.getCardReport();
		
		assertEqualsReport("Not the expected report line.", new double[][] {{300.0, 323.0}, {100, 113}}, result);
	}
	
	@Test
	public void testEndOfDayCashAndCardReport() {
		barCodeScanner.scan(code1);
		barCodeScanner.scan(code2);
		pos.payWithCard();
		
		barCodeScanner.scan(code1);
		barCodeScanner.scan(code3);
		pos.payWithCash();
		
		barCodeScanner.scan(code1);
		barCodeScanner.scan(code3);
		pos.payWithCard();
		
		barCodeScanner.scan(code4);
		barCodeScanner.scan(code3);
		pos.payWithCash();
		
		List<Receip> result = pos.getCardReport();
		assertEqualsReport("Not the expected report line.", new double[][] {{300.0, 323.0}, {500, 533}}, result);
		
		result = pos.getCashReport();
		assertEqualsReport("Not the expected report line.", new double[][] {{500.0, 533.0}, {1000, 1050}}, result);
	}
	
	@Test
	public void testEndOfDayCashReportTwoDays() {
		barCodeScanner.scan(code1);
		barCodeScanner.scan(code2);
		pos.payWithCash();
		
		barCodeScanner.scan(code1);
		pos.payWithCash();
		
		List<Receip> result = pos.getCashReport();
		assertEqualsReport("Not the expected report line.", new double[][] {{300.0, 323.0}, {100.0, 113.0}}, result);
		
		barCodeScanner.scan(code1);
		barCodeScanner.scan(code3);
		pos.payWithCash();
		
		barCodeScanner.scan(code4);
		pos.payWithCash();
		
		result = pos.getCashReport();
		assertEqualsReport("Not the expected report line.", new double[][] {{500.0, 533.0}, {600.0, 630.0}}, result);
	}
	
	@Test
	public void testEndOfDayCardReportTwoDays() {
		barCodeScanner.scan(code1);
		barCodeScanner.scan(code2);
		pos.payWithCard();
		
		barCodeScanner.scan(code1);
		pos.payWithCard();
		
		List<Receip> result = pos.getCardReport();
		assertEqualsReport("Not the expected report line.", new double[][] {{300.0, 323.0}, {100.0, 113.0}}, result);
		
		barCodeScanner.scan(code1);
		barCodeScanner.scan(code3);
		pos.payWithCard();
		
		barCodeScanner.scan(code4);
		pos.payWithCard();
		
		result = pos.getCardReport();
		assertEqualsReport("Not the expected report line.", new double[][] {{500.0, 533.0}, {600.0, 630.0}}, result);
	}
	
	@Test(expected = IllegalStateException.class)
	public void testAttemptToPayWithCash() {
		pos.payWithCash();
	}
	
	@Test(expected = IllegalStateException.class)
	public void testAttemptToPayWithCard() {
		pos.payWithCash();
	}
	
	private void assertEqualsReport(String message, double[][] expected, List<Receip> actual) {
		assertEquals("Report has a diffrent number of lines than expected.", expected.length, actual.size());
		for(int i=0; i < expected.length; i++) {
			assertEquals(message, expected[i][0], actual.get(i).getTotalWithoutTax(), 0);
			assertEquals(message, expected[i][1], actual.get(i).getTotalWithTax(), 0);
			
		}
	}
}
