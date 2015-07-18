package com.leolore.kata.vend.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.leolore.kata.vend.IInventoryManager;
import com.leolore.kata.vend.IVendingMachineController;
import com.leolore.kata.vend.InitializationException;
import com.leolore.kata.vend.ProductNotDeliveredException;
import com.leolore.kata.vend.controller.KataVendingMachine;
import com.leolore.kata.vend.model.ProductSlot;

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
	public void testInitWithGoodConfigHasInventoryManager() {
		assertNotNull("Init resulted in null inventory manager", vmac.getInventoryManager());
	}
	
	@Test
	public void testHandleProductSelectionEventWithValidProductDoesNotCauseException() {
		/*
		 * First, we need to actually stock the machine
		 */
		stockMachine();
		
		/*
		 *  Here, we would need to create the event, add it to the controller's event queue, wait 
		 *  for the event to be processed, then do our assertions. This is assuming that the queue processing
		 *  is happening in a separate thread, and that we have a way of knowing that the event has been
		 *  processed other than the effects of the functionality that we are actually testing.
		 *  
		 *  For the Kata, I am simply going to bypass the whole event queue thing and call the actual handler directly.
		 */
		boolean gotException = false;
		String exceptionMessage = null;
		try {
			vmac.handleProductSelection("A");
		}
		catch (ProductNotDeliveredException pnde) {
			gotException = true;
			exceptionMessage = pnde.getMessage();
		}
		assertFalse("Got exception from product delivery: " + exceptionMessage, gotException);
	}
	
	@Test
	public void testHandleProductSelectionEventWithValidProductDecrementsInventoryCount() {
		/*
		 * Although we are not implementing this yet, we want to keep track of the fact that it is part of the end functionality that is not done.
		 */
		fail("Not yet implemented!");
	}
	
	@Test
	public void testHandleProductSelectionEventWithValidProductClearsHeldMoney() {
		/*
		 * Although we are not implementing this yet, we want to keep track of the fact that it is part of the end functionality that is not done.
		 */
		fail("Not yet implemented!");
	}
	
	@Test
	public void testHandleProductSelectionEventWithValidProductReturnsCorrectChange() {
		/*
		 * Although we are not implementing this yet, we want to keep track of the fact that it is part of the end functionality that is not done.
		 */
		fail("Not yet implemented!");
	}
	
	
	/**************
	 * helpers
	 */
	private void stockMachine() {
		IInventoryManager inman = vmac.getInventoryManager();
		
		HashMap<ProductSlot, Integer> i = new HashMap<ProductSlot, Integer>();
		i.put(new ProductSlot("A", "Cola", 1.00d), 8);
		i.put(new ProductSlot("B", "Chips", 0.50d), 7);
		i.put(new ProductSlot("C", "Candy", 0.65d), 5);
		inman.reStock(i);
	}

}
