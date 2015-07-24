package com.leolore.kata.vend.application;

import java.util.HashMap;

import com.leolore.kata.vend.controller.KataVendingMachine;

public class BootVendingMachine {

	/**
	 * Starts up and runs the vending machine
	 * @param args
	 */
	public static void main(String[] args) {
		KataVendingMachine vmac = new KataVendingMachine();
		HashMap<String, String> cfg = new HashMap<String, String>();
		
		try {
			vmac.init(cfg);
		}
		catch (Throwable t) {
			System.err.println("Failed to initialize machine.");
			t.printStackTrace(System.err);
		}
		
		// Here we start the event loop
		// Just in case there is some sort of fancy shmancy power loss detector or something
		boolean keepRunning=true;
		while(keepRunning) {
			// Detect and process events
			// while there is no implementation, let's avoid the infinite loop
			keepRunning = true; // TODO: Remove this
		}
	}

}
