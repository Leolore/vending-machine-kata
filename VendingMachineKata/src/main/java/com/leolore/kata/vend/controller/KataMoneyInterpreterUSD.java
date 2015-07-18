package com.leolore.kata.vend.controller;

import java.util.Map;

import com.leolore.kata.vend.IMoneyInterpreter;
import com.leolore.kata.vend.InitializationException;
import com.leolore.kata.vend.model.CashObject;
import com.leolore.kata.vend.model.ProtoCoin;

public class KataMoneyInterpreterUSD implements IMoneyInterpreter {

	@Override
	public void init(Map<String, String> cfg) throws InitializationException {
		// TODO Auto-generated method stub

	}

	@Override
	public double getValueOf(CashObject c) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ProtoCoin getLargestCoinValuedLessOrEqual(double value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProtoCoin getProtoCoinNamed(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
