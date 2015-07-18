package com.leolore.kata.vend.controller.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.leolore.kata.vend.InitializationException;
import com.leolore.kata.vend.controller.KataInventoryManager;

public class KataInventoryManagerTest extends KataInventoryManager {
	private static KataInventoryManager imgr = null;
	private static Map<String, String> config = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		config = new HashMap<String, String>();
		try {
			imgr.init(config);
		}
		catch (Throwable t) {
			// Continue on, allow the tests to fail
		}
	}

	@Before
	public void setUp() throws Exception {
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
	public void testReStock() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetProductForSelectionCode() {
		fail("Not yet implemented");
	}

}
