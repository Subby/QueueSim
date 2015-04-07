import java.util.ArrayList;

/**
 * Interface that models a queueing system
 * Controls the generation of Queues and Servers for the simulation, and
 * the flow of customers between them 
 * @author Ben Lawton 
 *
 */

public interface QueueControlSystem {
	
	//Generates the queues for the simulation 
	public void generateQueueLayout();
	
	//Generates the servers for the simulation 
	public void generateServers(int numServers);
	
	/**
	 * Calls the PersonFactory generatePerson() method
	 * If a Person is generated, this method adds them to the shortest queue
	 */
	public void customerArrival();
	
	//Allocates customers to free servers until there are no more free servers or no more customers
	public void allocateCustomersToServers();
	
	//When their serving times have been reached, removes customers from servers and sets the servers' availability to free
	public void removeCustomersFromServers();
	
	//Returns all of the queues in the system 
	public ArrayList<Queue> getQueues();
	
}
