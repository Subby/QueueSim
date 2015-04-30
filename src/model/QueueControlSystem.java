package model;
import java.util.ArrayList;

/**
 * Interface that models the queueing system
 * Acts as the "puppet master" for all of the different moving parts in the simulation 
 * @author Ben Lawton 
 *
 */

public interface QueueControlSystem {
	
	/**
	 * Generates the the queues and servers to be used in the system 
	 */
	public void generateQueuesAndServers(int numServers);
	
	/**
	 * Calls the PersonFactory generatePerson() method
	 * If a Person is generated, adds them to the shortest queue in the system 
	 */
	public void customerArrival();
	
	/**
	 * Allocates a customer to an available server until there are no more available servers or no more customers 
	 */
	public void allocateCustomersToServers();
	
	/**
	 * Serves all customers allocated to a server
	 * Deallocates customers from servers if their serve time (time it takes to serve them) has been reached  
	 */
	public void serveAndFinishWithCustomers();
	
	/**
	 * Gets all the queues.
	 * @return an ArrayList containing all queues in the system 
	 */
	public ArrayList<Queue> getQueues();
	
	/**
	 * Gets the {@link ServerCollection}
	 * @return the ServerCollection object holding all servers in the system 
	 */
	public ServerCollection getServerCollection();
	
	/**
	 * Gets the {@link Stats} object.
	 * @return the Stats object that holds and calculates the simulation's statistics 
	 */
	public Stats getStats();
	
}
