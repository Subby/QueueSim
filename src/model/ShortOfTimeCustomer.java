package model;

/**
 * Class that models a customer who leaves the queue if they're kept waiting for longer than a specified period of time 
 * @author Ben Lawton
 */

public class ShortOfTimeCustomer extends UnhappyCustomer {
	
	//Time spent waiting in the queue, measured in ticks 
	private int timeSpentQueueing;
	//The cutoff point where the customer becomes irritated and leaves
	private static final int PATIENCE_LIMIT = 60;
	
	public ShortOfTimeCustomer() {
		initialiseServeTime();
	}
	
	/**
	 * Increments the time spent in the Queue by one  
	 */
	public void incrementTimeSpentQueueing() {
		this.timeSpentQueueing++;
	}
	
	/**
	 * Returns the amount of time (in ticks) the Customer has spent queueing 
	 * @return timeSpentQueueing 
	 */
	public int getTimeSpentQueueing() {
		return this.timeSpentQueueing;
	}
	
	/**
	 * Returns a flag for whether or not the customer has been waiting for too long
	 * If the Customer's timeSpentQueuing is longer than their patience limit, returns true 
	 * @return boolean value for whether or not the Customer has exceeded their patience limit 
	 */
	public boolean queuedForTooLong() {
		boolean unhappy = false;
		if (this.timeSpentQueueing >= PATIENCE_LIMIT) {
			unhappy = true;
		}
		return unhappy;
	}
	
	/**
	 * Returns the class' patience limit (how long the customer type will wait before they leave)
	 * @return the static variable PATIENCE_LIMIT 
	 */
	public int getPatienceLimit() {
		return PATIENCE_LIMIT;
	}
	

}
