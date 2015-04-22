package model;
/**
 * Abstract class that provides a model for an unhappy customer 
 * @author Ben Lawton
 */

public abstract class UnhappyCustomer extends Customer {
		
	//Return true if the customer has waited for too long
	abstract boolean queuedForTooLong(); 
	
	//Return the time spent queueing 
	abstract int getTimeSpentQueueing(); 
	
	abstract int getPatienceLimit();
			
}
