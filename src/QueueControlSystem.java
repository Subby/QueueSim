import java.util.ArrayList;

/**
 * Interface that models a queueing system
 * Controls the generation of Queues and Servers for the simulation, and
 * the flow of customers between them 
 * @author Ben Lawton 
 *
 */

public interface QueueControlSystem {
	
	//Generates the queues and servers for the simulation 
	public void generateQueuesAndServers(int numServers);
	
	/**
	 * Calls the PersonFactory generatePerson() method
	 * If a Person is generated, this method adds them to the shortest queue
	 */
	public void customerArrival();
	
	//Allocates customers to free servers until there are no more free servers or no more customers
	public void allocateCustomersToServers();
	
	//When their serving times have been reached, removes customers from servers and sets the servers' availability to free
	public void removeFinishedCustomersFromServers();
	
	//Returns all of the queues in the system 
	public ArrayList<Queue> getQueues();
	
}
