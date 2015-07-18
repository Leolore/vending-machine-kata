package com.leolore.kata.vend;

import java.util.Map;

import com.leolore.kata.vend.model.ProductSlot;

public interface IInventoryManager {

	public ProductSlot getProductForSelectionCode(String code);

	public void reStock(Map<ProductSlot, Integer> inventory);

	public void init(Map<String, String> config) throws InitializationException;

}
