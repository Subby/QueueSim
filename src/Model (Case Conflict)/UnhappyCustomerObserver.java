package model;
/**
 * Interface that models the Observer pattern
 * Implementing classes will look for unhappy customers in the queues, and
 * then take the appropriate action if their waiting time has exceeded their patience limit
 * @author Ben Lawton
 *
 */

public interface UnhappyCustomerObserver {
		
	//Act if any of the customers have been waiting for longer than their patience limit can handle
	public void actOnInconveniencedCustomers(QueueControlSystem queueSystem);
	
}
