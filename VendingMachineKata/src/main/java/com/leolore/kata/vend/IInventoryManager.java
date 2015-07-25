package com.leolore.kata.vend;

import java.util.Map;

import com.leolore.kata.vend.model.ProductSlot;

/**
 * This interface represents the inventory management tracking in the system. 
 * @author Leolore
 *
 */
public interface IInventoryManager {
	public void init(Map<String, String> config) throws InitializationException;
	public void reStock(Map<ProductSlot, Integer> inventory);
	public ProductSlot getProductForSelectionCode(String code);
	public int numberRemaining(String selectionCode);
	public int decrementNumberRemaining(String selectionCode);
}
