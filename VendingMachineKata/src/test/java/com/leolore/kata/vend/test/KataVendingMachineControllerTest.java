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
import com.leolore.kata.vend.model.Coin;
import com.leolore.kata.vend.model.DisplayContents;
import com.leolore.kata.vend.model.ProductSlot;
import com.leolore.kata.vend.model.ProtoCoin;

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

	//************************
	// initialization tests
	//
	
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
	

	//******************************
	// Functional / Use case tests
	//
/*
Accept Coins.
------------
  
_As a vendor_  
_I want a vending machine that accepts coins_  
_So that I can collect money from the customer_  

The vending machine will accept valid coins (nickels, dimes, and quarters) and reject invalid ones (pennies).  When a
valid coin is inserted the amount of the coin will be added to the current amount and the display will be updated.
When there are no coins inserted, the machine displays INSERT COIN.  Rejected coins are placed in the coin return.

NOTE: The temptation here will be to create Coin objects that know their value.  However, this is not how a real
  vending machine works.  Instead, it identifies coins by their weight and size and then assigned a value to what
  was inserted.  You will need to do something similar.  This can be simulated using strings, constants, enums,
  symbols, or something of that nature.
  
Requires coin slot, coin return, money interpreter, display, held money bucket
*/
	@Test
	public void testAcceptCoins() {
		fail("Not yet implemented");
	}
	
/*
Select Product
--------------

_As a vendor_  
_I want customers to select products_  
_So that I can give them an incentive to put money in the machine_  

There are three products: cola for $1.00, chips for $0.50, and candy for $0.65.  When the respective button is pressed
and enough money has been inserted, the product is dispensed and the machine displays THANK YOU.  If the display is
checked again, it will display INSERT COINS and the current amount will be set to $0.00.  If there is not enough money
inserted then the machine displays PRICE and the price of the item and subsequent checks of the display will display
either INSERT COINS or the current amount as appropriate.

Requires product select buttons, coin slot, coin return, display, inventory manager
*/
	@Test
	public void testSelectProduct() {
		fail("Not yet implemented");
	}
	
/*
Make Change
-----------

_As a vendor_  
_I want customers to receive correct change_  
_So that they will use the vending machine again_  

When a product is selected that costs less than the amount of money in the machine, then the remaining amount is placed
in the coin return.

Requires coin slot, product select buttons, inventory manager, coin return
*/
	@Test
	public void testMakeChange() {
		fail("Not yet implemented");
	}
	
/*
Return Coins
------------

_As a customer_  
_I want to have my money returned_  
_So that I can change my mind about buying stuff from the vending machine_  

When the return coins is selected, the money the customer has placed in the machine is returned and the display shows
INSERT COIN.

Requires coin slot, coin return, display
*/
	@Test
	public void testReturnCoins() {
		fail("Not yet implemented");
	}

/*
Sold Out
--------

_As a customer_  
_I want to be told when the item I have selected is not available_  
_So that I can select another item_  

When the item selected by the customer is out of stock, the machine displays SOLD OUT.  If the display is checked again,
it will display the amount of money remaining in the machine or INSERT COIN if there is no money in the machine.

Requires product select buttons, inventory manager, display
*/
	@Test
	public void testSoldOut() {
		fail("Not yet implemented");
	}
	
/*
Exact Change Only
-----------------

_As a customer_  
_I want to be told when exact change is required_  
_So that I can determine if I can buy something with the money I have before inserting it_  

When the machine is not able to make change with the money in the machine for any of the items that it sells, it will
display EXACT CHANGE ONLY instead of INSERT COINS.

Requires change manager, display
*/
	@Test
	public void testExactChangeOnly() {
		fail("Not yet implemented");
	}

	
	//******************************
	// Machinery tests
	//
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
		assertFalse("Got exception from product selection: " + exceptionMessage, gotException);
	}
	
	@Test
	public void testHandleProductSelectionEventWithInvalidProductCausesException() {
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
		try {
			vmac.handleProductSelection("Nonexistent");
		}
		catch (ProductNotDeliveredException pnde) {
			gotException = true;
		}
		assertTrue("Did not get exception from product selection of bad product", gotException);
	}
	
	@Test
	public void testHandleProductSelectionEventWithValidProductDecrementsInventoryCount() {
		fail("Not yet implemented!");
	}
	
	@Test
	public void testHandleProductSelectionEventWithValidProductClearsHeldMoney() {
		fail("Not yet implemented!");
	}
	
	@Test
	public void testHandleProductSelectionEventWithValidProductReturnsCorrectChange() {
		fail("Not yet implemented!");
	}
	
	@Test
	public void testHandleCoinSlotTriggerRecognizesAcceptedCoins() {
		Coin nickel = generateCoin("Nickel");
		Coin dime = generateCoin("Dime");
		Coin quarter = generateCoin("Quarter");
		
		vmac.handleCoinInserted(nickel);
		assertEquals(vmac.getDisplayContent(), "$0.05");
		vmac.handleCoinInserted(dime);
		assertEquals(vmac.getDisplayContent(), "$0.15");
		vmac.handleCoinInserted(quarter);
		assertEquals(vmac.getDisplayContent(), "$0.40");
	}
	
	@Test
	public void testHandleCoinSlotTriggerRejectsUnacceptedCoins() {
		// Again, full test would be to create the event with the coin object created and queue it,
		// we are calling the handler directly
		// reset has already been called in the setup
		
		Coin penny = generateCoin("Penny");
		vmac.handleCoinInserted(penny);
		assertEquals(vmac.getDisplayContent(), DisplayContents.INSERT.toString());
		// eventually need to assert that the coin return has the penny in it, when we have a way of getting coins from the return
	}
	
	@Test
	public void testGetDefaultDisplayValueIsInsertCoins() {
		assertEquals(vmac.getDisplayContent(), DisplayContents.INSERT.toString());
	}
	

	//*************
	// helpers
	//
	
	private Coin generateCoin(String type) {
		Coin c = null;
		if(type.equals("Penny")) {
			c = new Coin(4.1d, 6.002d, false, null);
			return c;
		}
		if(type.equals("Nickel")) {
			c = new Coin(5.1d, 7.002d, false, null);
			return c;
		}
		if(type.equals("Dime")) {
			c = new Coin(1.2d, 5.003d, true, null);
			return c;
		}
		if(type.equals("Quarter")) {
			c = new Coin(10.3d, 14.005d, true, null);
			return c;
		}
		return null;
	}

	private void stockMachine() {
		IInventoryManager inman = vmac.getInventoryManager();
		
		HashMap<ProductSlot, Integer> i = new HashMap<ProductSlot, Integer>();
		i.put(new ProductSlot("A", "Cola", 1.00d), 8);
		i.put(new ProductSlot("B", "Chips", 0.50d), 7);
		i.put(new ProductSlot("C", "Candy", 0.65d), 5);
		i.put(new ProductSlot("E", "Empty", 0.50d), 0);
		inman.reStock(i);
	}

}
