package com.leolore.kata.vend.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.leolore.kata.vend.IVendingMachineController;
import com.leolore.kata.vend.InitializationException;
import com.leolore.kata.vend.controller.KataVendingMachine;

public class KataVendingMachineControllerTest {
	private static IVendingMachineController vmac = null;
	private static Map<String, String> config = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		vmac = new KataVendingMachine();
		config = new HashMap<String, String>();
		try {
			vmac.init(config);
		}
		catch (Throwable t) {
			// Continue on, allow the tests to fail
		}
	}

	@Before
	public void setUp() throws Exception {
		vmac.reset();
	}

	@Test
	public void testInitWithNullConfigThrowsException() {
		boolean gotException = false;
		try {
			KataVendingMachine lvmac = new KataVendingMachine();
			lvmac.init(null);
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
			KataVendingMachine lvmac = new KataVendingMachine();
			lvmac.init(config);
		}
		catch (InitializationException e) {
			gotException = true;
		}
		assertFalse("Initialization with good config threw exception", gotException);
	}

}
