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
	
	@Test
	public void testHandleProductSelectionEvent() {
		/*
		 * First, we need to actually stock the machine
		 */
		/*
		 *  Here, we would need to create the event, add it to the controller's event queue, wait 
		 *  for the event to be processed, then do our assertions. This is assuming that the queue processing
		 *  is happening in a separate thread, and that we have a way of knowing that the event has been
		 *  processed other than the effects of the functionality that we are actually testing.
		 *  
		 *  For the Kata, I am simply going to bypass the whole event queue thing and call the actual handler directly.
		 */
	}

}
