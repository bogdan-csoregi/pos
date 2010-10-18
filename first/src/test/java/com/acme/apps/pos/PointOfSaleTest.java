package com.acme.apps.pos;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PointOfSaleTest {
 
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testCode1() {
		String code1 = "010101";
		String price1 = "100";
		
		PointOfSale pos = new PointOfSale();
		pos.addItem(code1, price1);
		
		assertEquals("Not the expected price.", price1, pos.scan(code1));
	}
	
	@Test
	public void testCode2() {
		String code2 = "1234";
		String price2 = "345";
		
		PointOfSale pos = new PointOfSale();
		pos.addItem(code2, price2);
		
		assertEquals("Not the expected price.", price2, pos.scan(code2));
	}
	
	@Test
	public void testMultipleCodes() {
		String code1 = "010101";
		String price1 = "100";
		String code2 = "1234";
		String price2 = "345";
		
		PointOfSale pos = new PointOfSale();
		pos.addItem(code1, price1);
		pos.addItem(code2, price2);
		
		assertEquals("Not the expected price.", price1, pos.scan(code1));
		assertEquals("Not the expected price.", price2, pos.scan(code2));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testUnknownCode() {
		String code1 = "010101";
		String price1 = "100";
		String code2 = "1234";
		String price2 = "345";
		
		String code3 = "4321";
		
		PointOfSale pos = new PointOfSale();
		pos.addItem(code1, price1);
		pos.addItem(code2, price2);
		
		pos.scan(code3);
	}
}
