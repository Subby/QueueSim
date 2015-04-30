package model;
/**
 * Abstract class that provides a model for an unhappy customer 
 * @author Ben Lawton
 */

public abstract class UnhappyCustomer extends Customer {
		
	/**
	 * Checks whether a customer has queued for too long
	 * @return whether the customer has queued for too long
	 */
	abstract boolean queuedForTooLong(); 
	
	/**
	 * Gets the time spent queueing.
	 * @return how long the customer has queued for
	 */
	abstract int getTimeSpentQueueing(); 
	
	/**
	 * Gets the patience limit of the customer.
	 * @return the patience limit of the customer
	 */
	abstract int getPatienceLimit();
			
}
