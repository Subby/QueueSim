package model;
/**
 * A superclass modelling the standard, compliant customer
 * @author Ben Lawton 
 */

import java.util.Random;

public class Customer implements Person {
	
	//The amount of time (ticks) it takes to serve a customer
	private int serveTime;
	
	@SuppressWarnings("unused")
	private int timeSpentQueueing;
	
	public Customer() {
		initialiseServeTime();
	}
	
	/**
	 * Gives the amount of time it takes to serve a customer
	 * a random number between 18 and 30
	 */
	public void initialiseServeTime() {
		Random rand = new Random(42);
		this.serveTime = rand.nextInt(12) + 18; 
	}
	
	public void setServeTime(int newServeTime) {
		serveTime = newServeTime; 
	}
	
	public int getServeTime() {
		return this.serveTime;
	}
	
	public void incrementTimeSpentQueueing() {
		this.timeSpentQueueing++;
	}

}
