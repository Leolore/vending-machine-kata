package com.leolore.kata.vend.controller.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.leolore.kata.vend.InitializationException;
import com.leolore.kata.vend.controller.KataInventoryManager;
import com.leolore.kata.vend.model.ProductSlot;

public class KataInventoryManagerTest extends KataInventoryManager {
	private static KataInventoryManager imgr = null;
	private static Map<String, String> config = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		imgr = new KataInventoryManager();
	}

	@Before
	public void setUp() throws Exception {
		config = new HashMap<String, String>();
		try {
			imgr.init(config);
			HashMap<ProductSlot, Integer> i = new HashMap<ProductSlot, Integer>();
			i.put(new ProductSlot("A", "Cola", 1.00d), 1);
			i.put(new ProductSlot("B", "Chips", 0.50d), 0);
			i.put(new ProductSlot("C", "Candy", 0.65d), 3);
			imgr.reStock(i);
		}
		catch (InitializationException ie) {
			// ignore to let test proceed and fail that way
		}
	}

	@Test
	public void testInitWithNullConfigThrowsException() {
		boolean gotException = false;
		try {
			KataInventoryManager im = new KataInventoryManager();
			im.init(null);
		}
		catch (InitializationException e) {
			gotException = true;
		}
		assertTrue("Initialization with null config did not throw exception", gotException);
	}

	@Test
	public void testInitWithGoodConfigDoesNotThrowException() {
		boolean gotException = false;
		try {
			KataInventoryManager im = new KataInventoryManager();
			im.init(config);
		}
		catch (InitializationException e) {
			gotException = true;
		}
		assertFalse("Initialization with good config threw exception", gotException);
	}
	
	@Test
	public void testGetProductForSelectionCode() {
		ProductSlot p = imgr.getProductForSelectionCode("A");
		assertNotNull("Failed to get product for slot A", p);
		assertEquals("ProductRetrieved for slot A is not slotted for the Cola dispensing channel", "Cola", p.getProductDispensingCode());
	}

	@Test
	public void testReStock() {
		HashMap<ProductSlot, Integer> i = new HashMap<ProductSlot, Integer>();
		i.put(new ProductSlot("A", "Cola", 1.00d), 8);
		i.put(new ProductSlot("B", "Chips", 0.50d), 7);
		i.put(new ProductSlot("C", "Candy", 0.65d), 5);
		imgr.reStock(i);
		assertEquals("Restock did not result in 8 Cola in machine", Integer.valueOf(8), Integer.valueOf(imgr.numberRemaining("A")));
	}

}
