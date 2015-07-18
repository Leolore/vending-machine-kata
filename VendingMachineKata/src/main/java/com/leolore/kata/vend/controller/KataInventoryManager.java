package com.leolore.kata.vend.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.leolore.kata.vend.IInventoryManager;
import com.leolore.kata.vend.InitializationException;
import com.leolore.kata.vend.model.ProductSlot;

public class KataInventoryManager implements IInventoryManager {
	HashMap<String, ProductSlot> selectionToProduct = null;
	HashMap<String, Integer> productCount = null;

	@Override
	public void init(Map<String, String> config) throws InitializationException {
		if(null == config) {
			throw new InitializationException("ProductInventoryManager initialization with null configuration");
		}
	}

	@Override
	public void reStock(Map<ProductSlot, Integer> inventory) {
		if(null == inventory) {
			selectionToProduct = null;
			productCount = null;
		}
		else {
			int nslots = inventory.size();
			selectionToProduct = new HashMap<String, ProductSlot>(nslots);
			productCount = new HashMap<String, Integer>(nslots);
			
			Iterator<Entry<ProductSlot, Integer>> it = inventory.entrySet().iterator();
			while(it.hasNext()) {
				Entry<ProductSlot, Integer> curEntry = it.next();
				ProductSlot curprod = curEntry.getKey();
				selectionToProduct.put(curprod.getProductSelectionCode(), curprod);
				productCount.put(curprod.getProductSelectionCode(), curEntry.getValue());
			}
		}
	}

	@Override
	public ProductSlot getProductForSelectionCode(String code) {
		if(null == selectionToProduct) {
			return null;
		}
		return selectionToProduct.get(code);
	}

	@Override
	public int numberRemaining(String selectionCode) {
		if(null == selectionToProduct) {
			return 0;
		}
		Integer numLeft = productCount.get(selectionCode);
		if(null == numLeft) {
			return 0;
		}
		return numLeft.intValue();
	}
}
