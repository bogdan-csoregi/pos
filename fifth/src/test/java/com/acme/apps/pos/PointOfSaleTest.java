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
	
	private static final String CODE_1 = "1234";
	private static final double PRICE_1 = 100;
	private static final double PRICE_WITH_TAX_1 = 113;
	
	private static final String CODE_2 = "4321";
	private static final double PRICE_2 = 200;
	private static final double PRICE_WITH_TAX_2 = 210;
	
	private static final String CODE_3 = "1111";
	private static final double PRICE_3 = 400;
	private static final double PRICE_WITH_TAX_3 = 420;
	
	private static final String CODE_4 = "2222";
	private static final double PRICE_4 = 600;
	private static final double PRICE_WITH_TAX_4 = 630;
	
	private static final String CODE_10 = "6789";

	@Before
	public void setUp() throws Exception {
		Catalog catalog = new Catalog(new HashMap<String, Product>(){{
			put(CODE_1, new Product(PRICE_1, true));
			put(CODE_2, new Product(PRICE_2, false));
			put(CODE_3, new Product(PRICE_3, false));
			put(CODE_4, new Product(PRICE_4, false));
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
		pos.scan(CODE_1);
		
		verify(display).printPrice(PRICE_WITH_TAX_1);
	}
	
	@Test
	public void testCode2() {
		pos.scan(CODE_2);
		
		verify(display).printPrice(PRICE_WITH_TAX_2);
	}
	
	@Test
	public void testUnknownCode() {
		pos.scan(CODE_10);
		
		verify(display).printCodeNotFound(CODE_10);
	}
	
	@Test
	public void testEmptyCode() {
		pos.scan("");
		
		verify(display).printEmptyCodeMessage();
	}
	
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
