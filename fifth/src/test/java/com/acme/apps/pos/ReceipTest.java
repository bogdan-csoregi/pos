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
		
		receip.addNewPrice(10, true, 0.5, 0.8);
		
		assertEquals("Total without tax has wrong value.", 10, receip.getTotalWithoutTax(), 0);
		assertEquals("Total with tax has wrong value.", 11.3, receip.getTotalWithTax(), 0);
		assertEquals("GST has wrong value.", 0.5, receip.getGST(), 0);
		assertEquals("PST has wrong value.", 0.8, receip.getPST(), 0);
	}
	
	@Test
	public void testEmptyReceip() {
		Receip receip = new Receip();
		
		assertTrue("Receipe should be empty.", receip.isEmpty());
	}
	
	@Test
	public void testNonEmptyReceip() {
		Receip receip = new Receip();
		
		receip.addNewPrice(10, true, 0.5, 0.8);
		
		assertFalse("Receipe should not be empty.", receip.isEmpty());
	}
}
