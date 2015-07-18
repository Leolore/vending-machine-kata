package com.leolore.kata.vend;

import java.util.Map;

/**
 * This software is fundamentally an event driven controller for a vending machine, that has inventory, mechanisms for paying, returning payment,
 * selecting a product, retrieving the product, and getting change back either as a result of selecting a product that costs less than what
 * the customer has put in, or as a result of the customer using a control to return their money and cancel.
 * @author Leolore
 *
 */
public interface IVendingMachineController {
	
  /**
   * Initialize and configure the controller
   * @param cfg Map<String, String> - configuration values
   * @throws InitializationException
   */
  public void init(Map<String, String> cfg) throws InitializationException;
  
  /**
   * Sets the machine back to initialized state as configured
   */
  public void reset();
  
  /**
   * Retrieves the inventory manager for this machine.
   * @return
   */
  public IInventoryManager getInventoryManager();

  /**
   * Called after the event is dequeued and interpreted, this method does the actual work when a product selection key or button has been pressed.
   * @param selectionCode
   * @throws ProductNotDeliveredException
   */
  public void handleProductSelection(String selectionCode) throws ProductNotDeliveredException;
	
}
