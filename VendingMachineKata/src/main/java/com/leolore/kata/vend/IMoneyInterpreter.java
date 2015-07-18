package com.leolore.kata.vend;

import java.util.Map;

import com.leolore.kata.vend.model.CashObject;
import com.leolore.kata.vend.model.ProtoCoin;

/**
 * This interface is for a module that handles translations between physical monetary objects and the normalized value, based on local money types.
 * 
 * When money is entered, it must be categorized using detectors of weight, size, and ridging of coins, and scanning for marks or strips in
 * bills. Not all machines can accept bills, and some can accept only certain bills. Categorization is interpreted in the context of the 
 * appropriate currencies for the machine's locale. The value of the money entered is then understood by standardization. Unrecognized money
 * is generally rejected by a vending machine - sent directly to the coin return if it is a coin, or spit back out if it is a bill. Money is
 * inserted sequentially, and since there is a control to cancel and return coins, the machine must keep track of what has been entered until
 * a product is selected and distributed, and change returned.
 * 
 * @author Leolore
 *
 */
public interface IMoneyInterpreter {
	  public void init(Map<String, String> cfg) throws InitializationException;
	  public double getValueOf(CashObject c);
	  public ProtoCoin getLargestCoinValuedLessOrEqual(double value);
	  public ProtoCoin getProtoCoinNamed(String name);
}
