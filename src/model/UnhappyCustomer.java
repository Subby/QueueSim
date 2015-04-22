package model;
/**
 * Abstract class that provides a model for an unhappy customer 
 * @author Ben Lawton
 */

public abstract class UnhappyCustomer extends Customer {
	
	//Increments the time spent in the queue; to be called each time tick  
	abstract void incrementTimeSpentQueueing();
	
	//Return true if the customer has waited for too long
	abstract boolean queuedForTooLong(); 
	
	//Return the time spent queueing 
	abstract int getTimeSpentQueueing(); 
	
	abstract int getPatienceLimit();
			
}
