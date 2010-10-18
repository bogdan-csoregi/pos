/**
 * 
 */
package com.acme.apps.pos;


import static org.junit.Assert.*;

import org.junit.*;

import com.acme.apps.pos.BarCodeScanner;
import com.acme.apps.pos.PointOfSale;

/**
 * @author Betfair
 *
 */
public class AnotherPointOfSaleTest {
	MockDisplay display;
	PointOfSale pos;
	BarCodeScanner barCodeScanner;
	
	private static final String code1 = "1234";
	private static final String price1 = "100";
	
	private static final String code2 = "4321";
	private static final String price2 = "123";
	
	private static final String code3 = "6789";

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		display = new MockDisplay();
		pos = new PointOfSale(display);
		pos.addItem(code1, price1);
		pos.addItem(code2, price2);
		barCodeScanner = new BarCodeScanner(pos);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSimple() {
		barCodeScanner.scan(code1);
		String result = display.getLastDisplayLine();
		assertEquals("Not the expected price.", price1, result);
	}
	
	@Test
	public void testCode2() {
		barCodeScanner.scan(code2);
		String result = display.getLastDisplayLine();
		assertEquals("Not the expected price.", price2, result);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testUnknownCode() {
		barCodeScanner.scan(code3);
	}
}
