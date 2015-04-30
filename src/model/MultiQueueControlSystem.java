package model;
/**
 * Class that acts as "puppet master" for all of the moving pieces in the simulation 
 * @author Ben Lawton 
 * @author Denver Fernandes
 */

import java.util.ArrayList;


public class MultiQueueControlSystem implements QueueControlSystem {
	
	//A collection of servers in the system
	private ServerCollection servers;
	
	//A collection of queues in the system 
	private QueueCollection queues; 
	
	//Factory responsible for the generation of Person types  
	private PersonFactory personFactory;
	
	//Statistics for the simulation 
	private Stats stats;
	
	public MultiQueueControlSystem() {
		servers = new ServerCollection();
		queues = new QueueCollection();
		personFactory = new PersonFactory();		
		stats = new Stats();
	}
	
	/**
	 * Generates queues and servers
	 * @param the number of queues and servers to be generated 
	 */
	public void generateQueuesAndServers(int numServers) {
		for (int i = 0; i < numServers; i++) {
			Server server = new HumanServer();
			servers.addServer(server);
			Queue queue = new PersonQueue();
			queues.addQueue(queue);
			server.setAllocatedQueue(queue);
		}
	}

	/**
	 * Calls the PersonFactory object's generatePerson() method, 
	 * and if it generates a Person, adds them to the shortest queue 
	 */
	public void customerArrival() {
		Person newPerson = personFactory.generatePerson();
		if (newPerson != null) {
			Queue shortestQueue = queues.showShortestQueue();
			shortestQueue.addPerson(newPerson);
			this.stats.incrementCustomersGenerated();
		}
	}

	/**
	 * Allocates customers to all available servers 
	 */
	public void allocateCustomersToServers() {
		if (servers.showAvailableServers().size() > 0) {
			for (Server server : servers.showAvailableServers()) {
				if (server.getAllocatedQueue().getLength() > 0) {
					server.setCurrentCustomer(server.getAllocatedQueue().getHeadOfQueue());
					server.getAllocatedQueue().removeHeadOfQueue();
					server.setFree(false);
				}
			}
		}
	}
	
	/**
	 * Serves all customers assigned to a server and then deallocates them if their serveTime has been exceeded 
	 */
	public void serveAndFinishWithCustomers() {
		servers.serveCustomers();
		int numBefore = servers.showAvailableServers().size();
		servers.finishWithCustomers();
		int numAfter = servers.showAvailableServers().size();
		int numCustomersServed = numAfter - numBefore;
		for (int i = 0; i < numCustomersServed; i++) {
			this.stats.incrementCustomersServed();
		}
	}
	
	/**
	 * Gets the collection of servers.
	 * @return the collection of servers  
	 */
	public ServerCollection getServerCollection() {
		return this.servers;
	}
	
	/**
	 * Gets all the {@link Queue} elements in this collection.
	 * @return the queues in the system 
	 */
	public ArrayList<Queue> getQueues() {
		return this.queues.getQueues();
	}
	
	/**
	 * Gets the {@link Stats} instance.
	 */
	public Stats getStats() {
		return this.stats;
	}
}