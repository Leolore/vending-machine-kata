package com.leolore.kata.vend.controller;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Map;

import com.leolore.kata.vend.IInventoryManager;
import com.leolore.kata.vend.IMoneyInterpreter;
import com.leolore.kata.vend.IVendingMachineController;
import com.leolore.kata.vend.InitializationException;
import com.leolore.kata.vend.ProductNotDeliveredException;
import com.leolore.kata.vend.model.Coin;
import com.leolore.kata.vend.model.DisplayContents;
import com.leolore.kata.vend.model.ProductSlot;
import com.leolore.kata.vend.model.ProtoCoin;

public class KataVendingMachine implements IVendingMachineController {
	private Map<String, String> config = null;
	private IInventoryManager inman = null;
	private IMoneyInterpreter monint = null;
	
	private DisplayContents display = DisplayContents.INSERT;
	
	private double heldMoney = 0.0d;
	private List<Coin> coinsInReturn = new ArrayList<Coin>();
	
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
		}
		inman.init(cfg);
		
		// Instantiate the Inventory Manager
		String sClassForMoneyInterpreter = cfg.get("money.interpreter.class");
		if(null == sClassForMoneyInterpreter) {
			monint = new KataMoneyInterpreterUSD();
		}
		else {
			try {
				@SuppressWarnings("unchecked")
				Class<IMoneyInterpreter> classForMoneyInterpreter = (Class<IMoneyInterpreter>)Class.forName(sClassForMoneyInterpreter);
				monint = (IMoneyInterpreter)classForMoneyInterpreter.newInstance();
			}
			catch (Throwable t) {
				throw new InitializationException("Could not instantiate money interpreter with class " + sClassForMoneyInterpreter, t);
			}
			if(null == monint) {
				throw new InitializationException("Somehow got null instance of money interpreter with class " + sClassForMoneyInterpreter);
			}
		}
		monint.init(cfg);
		
		reset();
	}
	
	@Override
	public void reset() {
		heldMoney = 0.0d;
		coinsInReturn.clear();
		display = DisplayContents.INSERT;
	}
	
	@Override
	public IInventoryManager getInventoryManager() {
		return inman;
	}
	
	@Override
	public IMoneyInterpreter getMoneyInterpreter() {
		return monint;
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
		
		if(inman.numberRemaining(selectionCode) < 1) {
			display = DisplayContents.SOLDOUT;
		}
		
		// call delivery method
		dispenseProduct(sDeliveryCode);
		
		// update display TODO: not yet implemented
	}
	
	@Override
	public void handleCoinInserted(Coin inserted) {
		// detect coin type
		double val = monint.getValueOf(inserted);
		if(0.0d == val) {
			// drop the coin into the return.
		}
		else {
			// if valid, update the money held and the display
			heldMoney += val;
			display = DisplayContents.MONEY;
		}
	}
	
	private void dispenseProduct(String whichProduct) {
		// this is where the code to run the dispensing machinery goes
		System.out.println("Dispensing from channel: " + whichProduct);
	}
	
	@Override
	public String getDisplayContent() {
		if(DisplayContents.MONEY == display) {
			NumberFormat nf = NumberFormat.getCurrencyInstance();
			return nf.format(heldMoney);
		}
		return display.toString();
	}
}
