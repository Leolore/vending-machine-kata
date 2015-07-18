package com.leolore.kata.vend.controller;

import java.util.Map;

import com.leolore.kata.vend.IInventoryManager;
import com.leolore.kata.vend.IVendingMachineController;
import com.leolore.kata.vend.InitializationException;
import com.leolore.kata.vend.ProductNotDeliveredException;
import com.leolore.kata.vend.model.ProductSlot;

public class KataVendingMachine implements IVendingMachineController {
	Map<String, String> config = null;
	IInventoryManager inman = null;

	@Override
	public void init(Map<String, String> cfg) throws InitializationException {
		if(null == cfg) {
			throw new InitializationException("Vending Machine Controller got null configuration");
		}
		config = cfg;
		
		// Instantiate the Inventory Manager
		String sClassForInventoryManager = cfg.get("inventory.manager.class");
		if(null == sClassForInventoryManager) {
			inman = new KataInventoryManager();
		}
		else {
			try {
				@SuppressWarnings("unchecked")
				Class<IInventoryManager> classForInventoryManager = (Class<IInventoryManager>)Class.forName(sClassForInventoryManager);
				inman = (IInventoryManager)classForInventoryManager.newInstance();
			}
			catch (Throwable t) {
				throw new InitializationException("Could not instantiate inventory manager with class " + sClassForInventoryManager, t);
			}
			if(null == inman) {
				throw new InitializationException("Somehow got null instance of inventory manager with class " + sClassForInventoryManager);
			}
			inman.init(cfg);
		}
		reset();
	}
	
	@Override
	public void reset() {
		// Nothing to do yet
	}
	
	@Override
	public IInventoryManager getInventoryManager() {
		return inman;
	}
	
	@Override
	public void handleProductSelection(String selectionCode) throws ProductNotDeliveredException {
		// get product from inventory manager
		ProductSlot s = inman.getProductForSelectionCode(selectionCode);
		if(null == s) {
			throw new ProductNotDeliveredException("Product with selection code " + selectionCode + " not found");
		}
		// get destination code from product
		String sDeliveryCode = s.getProductDispensingCode();
		
		// call delivery method
		dispenseProduct(sDeliveryCode);
		
		// update display TODO: not yet implemented
	}
	
	private void dispenseProduct(String whichProduct) {
		// this is where the code to run the dispensing machinery goes
		System.out.println("Dispensing from channel: " + whichProduct);
	}
}
