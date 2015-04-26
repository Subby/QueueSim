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
	
	/**
	 * Sets the serve time for this customer.
	 * @param newServeTime the serve time
	 */
	public void setServeTime(int newServeTime) {
		serveTime = newServeTime; 
	}
	
	/**
	 * Gets the serve time for this customer.
	 * @return the serve time
	 */
	public int getServeTime() {
		return this.serveTime;
	}
	
	/**
	 * Increments the time spent queueing for this customer.
	 */
	public void incrementTimeSpentQueueing() {
		this.timeSpentQueueing++;
	}

}
