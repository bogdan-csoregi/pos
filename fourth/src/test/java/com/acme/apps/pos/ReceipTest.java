package com.acme.apps.pos;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ReceipTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testAdditonOfNewPrice() {
		Receip receip = new Receip();
		
		receip.addNewPrice(10, 33);
		
		assertEquals("Total without tax has wrong value.", 10, receip.getTotalWithoutTax(), 0);
		assertEquals("Total with tax has wrong value.", 33, receip.getTotalWithTax(), 0);
	}
	
	@Test
	public void testEmptyReceip() {
		Receip receip = new Receip();
		
		assertTrue("Receipe should be empty.", receip.isEmpty());
	}
	
	@Test
	public void testNonEmptyReceip() {
		Receip receip = new Receip();
		
		receip.addNewPrice(10, 33);
		
		assertFalse("Receipe should not be empty.", receip.isEmpty());
	}
}
