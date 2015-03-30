/**
 * Abstract class that provides a model for an unhappy customer 
 * @author Ben Lawton
 */

public abstract class UnhappyCustomer extends Customer {
	
	//The threshold value before the customer gets unhappy and takes action
	@SuppressWarnings("unused")
	private int patienceLimit;
	//The total time the customer has spent queueing
	@SuppressWarnings("unused")
	private int timeSpentQueueing;
	
	public UnhappyCustomer() {
		super.setServeTime();
	}
	
	//Increments the time spent in the queue; to be called each time tick  
	public void incrementTimeSpentQueueing() {
		this.timeSpentQueueing++;
	}
	
	//If the time spent queueing is greater than the patience limit, take appropriate action 
	abstract void getUnhappyIfQueuedForTooLong(); 
	
	//Sets the point at which the customer gets unhappy and takes action
	abstract void setPatienceLimit(); 
	
}
