package com.leolore.kata.vend.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import com.leolore.kata.vend.IMoneyInterpreter;
import com.leolore.kata.vend.InitializationException;
import com.leolore.kata.vend.model.CashObject;
import com.leolore.kata.vend.model.Coin;
import com.leolore.kata.vend.model.ProtoCoin;

public class KataMoneyInterpreterUSD implements IMoneyInterpreter {

	private ProtoCoin[] acceptedCoins = { ProtoCoin.NICKEL, ProtoCoin.DIME, ProtoCoin.QUARTER };
	private Map<String, String> config = null;
	
	@Override
	public void init(Map<String, String> cfg) throws InitializationException {
		  if(null == cfg) {
			  throw new InitializationException("MoneyDetectorUSD init failed with null configuration");
		  }
		  // accept the possibility of configuration changing under us for now, no copying
		  config = cfg;
	}

	@Override
	public double getValueOf(CashObject c) {
		if(c instanceof ProtoCoin) {
			ProtoCoin p = (ProtoCoin)c;
			return ((ProtoCoin)p).getValue();
		}

		Coin coin = (Coin)c;
		
		for(int cnt = acceptedCoins.length - 1; cnt>= 0; cnt--) {
			if(acceptedCoins[cnt].match(coin.getWeightInGrams(), coin.getRadiusInMillimeters(), coin.hasRidges(), coin.getScanCode())) {
				return acceptedCoins[cnt].getValue();
			}
		}
		
		return 0.0d;
	}

	@Override
	public ProtoCoin getLargestCoinValuedLessOrEqual(double value) {
		ProtoCoin largest = null;
		for(int cnt = acceptedCoins.length-1; cnt >= 0; cnt--) {
			ProtoCoin current = acceptedCoins[cnt];
			if(current.getValue() <= value) {
				if((null == largest) || (current.getValue() > largest.getValue())) {
					largest = current;
				}
			}
		}
		return largest;
	}

	@Override
	public ProtoCoin getProtoCoinNamed(String name) {
		for(int cnt = acceptedCoins.length-1; cnt >= 0; cnt--) {
			if(acceptedCoins[cnt].getName().equals(name)) {
				return acceptedCoins[cnt];
			}
		}
		return null;
	}

}
