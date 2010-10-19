package com.acme.apps.pos;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.*;

import com.acme.apps.pos.PointOfSale;

import static org.mockito.Mockito.*;

public class PointOfSaleTest {
	Display display;
	PointOfSale pos;
	
	private static final String code1 = "1234";
	private static final double price1 = 100;
	private static final double priceWiothTax1 = 113;
	private static final String expectedPrice1 = "Price: $113.0";
	
	private static final String code2 = "4321";
	private static final double price2 = 200;
	private static final double priceWiothTax2 = 210;
	private static final String expectedPrice2 = "Price: $210.0";
	
	private static final String code3 = "1111";
	private static final double price3 = 400;
	private static final double priceWiothTax3 = 420;
	private static final String expectedPrice3 = "Price: $420.0";
	
	private static final String code4 = "2222";
	private static final double price4 = 600;
	private static final double priceWiothTax4 = 630;
	private static final String expectedPrice4 = "Price: $630.0";
	
	private static final String code10 = "6789";

	@Before
	public void setUp() throws Exception {
		Catalog catalog = new Catalog(new HashMap<String, Product>(){{
			put(code1, new Product(price1, true));
			put(code2, new Product(price2, false));
			put(code3, new Product(price3, false));
			put(code4, new Product(price4, false));
		}});
		//display = new MockDisplay();
		display = mock(Display.class);
		pos = new PointOfSale(catalog, display);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCode1() {
		pos.scan(code1);
		
		verify(display).printPrice(priceWiothTax1);
	}
	
//	@Test
//	public void testCode2() {
//		pos.scan(code2);
//		List<String> result = display.getDisplayLines();
//		assertEquals("Not the expected price.", expectedPrice2, result.get(0));
//	}
//	
//	@Test
//	public void testUnknownCode() {
//		pos.scan(code10);
//		
//		List<String> result = display.getDisplayLines();
//		assertEquals("Not the expected message.", "Couldn't find a product to match the code:" + code10, result.get(0));
//	}
//	
//	@Test
//	public void testEmptyCode() {
//		pos.scan("");
//		
//		List<String> result = display.getDisplayLines();
//		assertEquals("Not the expected message.", "Empty code.", result.get(0));
//	}
//	
//	@Test
//	public void testMultipleScans() {
//		pos.scan(code1);
//		pos.scan(code2);
//		pos.scan(code3);
//		pos.scan(code4);
//		List<String> result = display.getDisplayLines();
//		
//		List<String> expectedResult = new ArrayList<String>(){{
//			add(expectedPrice1);
//			add(expectedPrice2);
//			add(expectedPrice3);
//			add(expectedPrice4);
//			}};
//		
//		assertEquals("Not the expected price.", expectedResult, result);
//	}
//	
//	@Test
//	public void testPayWithCash() {
//		pos.scan(code1);
//		pos.scan(code2);
//		pos.payWithCash();
//		
//		List<String> result = display.getDisplayLines();
//		List<String> expectedResult = new ArrayList<String>(){{
//			add("Price: $113.0");
//			add("Price: $210.0");
//			add("$100.0 GP");
//			add("$200.0 G");
//			add("Total: $300.0");
//			add("GST: $15.0");
//			add("PST: $8.0");
//			add("Grand Total: $323.0");
//		}};
//		
//		assertEquals("Not the expected receip lines.", expectedResult, result);
//	}
//	
//	@Test
//	public void testEndOfDayCashReport() {
//		pos.scan(code1);
//		pos.scan(code2);
//		pos.payWithCash();
//		
//		pos.scan(code1);
//		pos.payWithCash();
//		
//		List<Receip> result = pos.getCashReport();
//		
//		assertEqualsReport("Not the expected report line.", new double[][] {{300.0, 323.0}, {100, 113}}, result);
//	}
//	
//	@Test
//	public void testEndOfDayCardReport() {
//		pos.scan(code1);
//		pos.scan(code2);
//		pos.payWithCard();
//		
//		pos.scan(code1);
//		pos.payWithCard();
//		
//		List<Receip> result = pos.getCardReport();
//		
//		assertEqualsReport("Not the expected report line.", new double[][] {{300.0, 323.0}, {100, 113}}, result);
//	}
//	
//	@Test
//	public void testEndOfDayCashAndCardReport() {
//		pos.scan(code1);
//		pos.scan(code2);
//		pos.payWithCard();
//		
//		pos.scan(code1);
//		pos.scan(code3);
//		pos.payWithCash();
//		
//		pos.scan(code1);
//		pos.scan(code3);
//		pos.payWithCard();
//		
//		pos.scan(code4);
//		pos.scan(code3);
//		pos.payWithCash();
//		
//		List<Receip> result = pos.getCardReport();
//		assertEqualsReport("Not the expected report line.", new double[][] {{300.0, 323.0}, {500, 533}}, result);
//		
//		result = pos.getCashReport();
//		assertEqualsReport("Not the expected report line.", new double[][] {{500.0, 533.0}, {1000, 1050}}, result);
//	}
//	
//	@Test
//	public void testEndOfDayCashReportTwoDays() {
//		pos.scan(code1);
//		pos.scan(code2);
//		pos.payWithCash();
//		
//		pos.scan(code1);
//		pos.payWithCash();
//		
//		List<Receip> result = pos.getCashReport();
//		assertEqualsReport("Not the expected report line.", new double[][] {{300.0, 323.0}, {100.0, 113.0}}, result);
//		
//		pos.scan(code1);
//		pos.scan(code3);
//		pos.payWithCash();
//		
//		pos.scan(code4);
//		pos.payWithCash();
//		
//		result = pos.getCashReport();
//		assertEqualsReport("Not the expected report line.", new double[][] {{500.0, 533.0}, {600.0, 630.0}}, result);
//	}
//	
//	@Test
//	public void testEndOfDayCardReportTwoDays() {
//		pos.scan(code1);
//		pos.scan(code2);
//		pos.payWithCard();
//		
//		pos.scan(code1);
//		pos.payWithCard();
//		
//		List<Receip> result = pos.getCardReport();
//		assertEqualsReport("Not the expected report line.", new double[][] {{300.0, 323.0}, {100.0, 113.0}}, result);
//		
//		pos.scan(code1);
//		pos.scan(code3);
//		pos.payWithCard();
//		
//		pos.scan(code4);
//		pos.payWithCard();
//		
//		result = pos.getCardReport();
//		assertEqualsReport("Not the expected report line.", new double[][] {{500.0, 533.0}, {600.0, 630.0}}, result);
//	}
//	
//	@Test(expected = IllegalStateException.class)
//	public void testAttemptToPayWithCash() {
//		pos.payWithCash();
//	}
//	
//	@Test(expected = IllegalStateException.class)
//	public void testAttemptToPayWithCard() {
//		pos.payWithCash();
//	}
//	
//	private void assertEqualsReport(String message, double[][] expected, List<Receip> actual) {
//		assertEquals("Report has a diffrent number of lines than expected.", expected.length, actual.size());
//		for(int i=0; i < expected.length; i++) {
//			assertEquals(message, expected[i][0], actual.get(i).getTotalWithoutTax(), 0);
//			assertEquals(message, expected[i][1], actual.get(i).getTotalWithTax(), 0);
//			
//		}
//	}
}
