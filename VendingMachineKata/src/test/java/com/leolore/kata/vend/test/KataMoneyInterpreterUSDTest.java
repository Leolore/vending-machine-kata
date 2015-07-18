package com.leolore.kata.vend.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.leolore.kata.vend.IMoneyInterpreter;
import com.leolore.kata.vend.InitializationException;
import com.leolore.kata.vend.controller.KataMoneyInterpreterUSD;
import com.leolore.kata.vend.model.Coin;
import com.leolore.kata.vend.model.ProtoCoin;

public class KataMoneyInterpreterUSDTest {

	KataMoneyInterpreterUSD det = new KataMoneyInterpreterUSD();
	Map<String, String> config = null;

	@Before
	public void setUp() throws Exception {
		config = new HashMap<String, String>();
		config.put("machineHasPaperSlot", "Yes");
		det.init(config);
	}

	@Test
	public void testInitWithNullConfigThrowsException() {
		KataMoneyInterpreterUSD d2 = new KataMoneyInterpreterUSD();
		boolean gotException = false;
		try {
			d2.init(null);
		}
		catch (InitializationException ie) {
			gotException = true;
		}
		assertTrue("Did not get InitializationException with null configuration", gotException);
	}
	
	@Test
	public void testInitWithGoodConfigDoesNotThrowException() {
		KataMoneyInterpreterUSD d2 = new KataMoneyInterpreterUSD();
		boolean gotException = false;
		try {
			d2.init(config);
		}
		catch (InitializationException ie) {
			gotException = true;
		}
		assertFalse("Got InitializationException with good configuration", gotException);
	}

	@Test
	public void testGetProtoCoinNamed() {
		ProtoCoin pc = det.getProtoCoinNamed("Dime");
		assertNotNull("Got null while looking for a dime", pc);
		assertEquals("Failed to get a coin named Dime", pc.getName(), "Dime");
	}

	@Test
	public void testGetValueOf() {
		Coin c = new Coin(2.75d, 6.00d, false, null);
		assertEquals("Failed to get a penny for penny parameters", Double.valueOf(0.01d), Double.valueOf(det.getValueOf(c)));
	}

	@Test
	public void testGetLargestCoinValuedLessOrEqual() {
		ProtoCoin p = det.getLargestCoinValuedLessOrEqual(2.02d);
		assertNotNull("Failed to get any coin worth less than $2.02", p);
		assertEquals("Failed to get a dollar", Double.valueOf(1.00d), Double.valueOf(p.getValue()));
		p = det.getLargestCoinValuedLessOrEqual(0.99d);
		assertEquals("Failed to get a half dollar", "Half Dollar", p.getName());
		p = det.getLargestCoinValuedLessOrEqual(0.49d);
		assertEquals("Failed to get a quarter", "Quarter", p.getName());
		p = det.getLargestCoinValuedLessOrEqual(0.24d);
		assertEquals("Failed to get a quarter", "Dime", p.getName());
		p = det.getLargestCoinValuedLessOrEqual(0.07d);
		assertEquals("Failed to get a quarter", "Nickel", p.getName());
		p = det.getLargestCoinValuedLessOrEqual(0.03d);
		assertEquals("Failed to get a quarter", "Penny", p.getName());
		p = det.getLargestCoinValuedLessOrEqual(0.00d);
		assertNull("Got a coin when not expected", p);
	}

}
