/**
 * Abstract class that provides a model for an unhappy customer 
 * @author Ben Lawton
 */

public abstract class UnhappyCustomer extends Customer {
	
	//Increments the time spent in the queue; to be called each time tick  
	abstract void incrementTimeSpentQueueing();
	
	//If the time spent queueing is greater than the patience limit, take appropriate action 
	abstract void getUnhappyIfQueuedForTooLong(); 
		
}
