package model;
/**
 * Class that models a customer who takes twice as long
 * to serve if they're kept waiting for more than 8 minutes
 * @author Ben Lawton
 */

public class ComplainingCustomer extends UnhappyCustomer {
	
	//Time spent waiting in the queue, measured in ticks 
	private int timeSpentQueueing;
	//The cutoff point where the customer takes twice as long to serve
	private static final int PATIENCE_LIMIT = 48;  
	//Whether or not the serveTime has been doubled thanks to the customer getting unhappy
	private boolean isDoubled;

	public ComplainingCustomer() {
		initialiseServeTime();
		isDoubled = false;
	}
	
	/**
	 * Increases the time spent queueing.
	 */
	public void incrementTimeSpentQueueing() {
		this.timeSpentQueueing++;
	}
	
	/**
	 * Gets the time spent queueing.
	 * @return the time spent queueing
	 */
	public int getTimeSpentQueueing() {
		return this.timeSpentQueueing;
	}
	
	/**
	 * Checks whether the customer is unhappy.
	 * @return whether the customer has queued for too long and is unhappy
	 */
	public boolean queuedForTooLong() {
		boolean unhappy = false;
		if (timeSpentQueueing >= PATIENCE_LIMIT && isDoubled == false) {
			unhappy = true;
		}
		return unhappy; 
	}
	
	/**
	 * Doubles the serve time for this type of customer.
	 */
	public void doubleServeTime() {
		if (this.timeSpentQueueing >= PATIENCE_LIMIT) {
			int serveTimeDoubled = getServeTime() * 2;
			setServeTime(serveTimeDoubled);
			isDoubled = true;
		}
	}
	
	/**
	 * Gets the patience limit for this type of customer.
	 * @return the patience limit
	 */
	public int getPatienceLimit() {
		return PATIENCE_LIMIT;
	}
	
}
