package model;
/**
 * Class that handles the generation of queues, servers, customers and the interaction between them for the simlulation of a multi queue system
 * @author Ben Lawton 
 * @author Denver Fernandes
 */

import java.util.ArrayList;


public class MultiQueueControlSystem implements QueueControlSystem {
	
	private ServerCollection servers;
	
	private QueueCollection queues; 
	
	private PersonFactory personFactory;
	
	private Stats stats;
	
	private static MultiQueueControlSystem instance = null; 
	
	private MultiQueueControlSystem() {
		servers = new ServerCollection();
		queues = new QueueCollection();
		personFactory = new PersonFactory();		
		stats = new Stats();
	}

	public static MultiQueueControlSystem getInstance() {
		if(instance == null) {
			instance = new MultiQueueControlSystem();
		}
		return instance;
	}
	
	/**
	 * Generates the queues and servers with a given number of servers.
	 * @param numServers the number of servers to use
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

	//If a customer is generated, add it to the shortest queue 
	public void customerArrival() {
		Person newPerson = personFactory.generatePerson();
		if (newPerson != null) {
			Queue shortestQueue = queues.showShortestQueue();
			shortestQueue.addPerson(newPerson);
			this.stats.incrementCustomersGenerated();
		}
	}

	//Allocates a customer to an available server from their allocated queues 
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
	
	//Removes customers from the servers if their serve time has been met or exceeded
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
	
	public ServerCollection getServerCollection() {
		return this.servers;
	}
	
	/**
	 * Gets all the queues.
	 */
	public ArrayList<Queue> getQueues() {
		return queues.getQueues();
	}
	
	/**
	 * Gets the stats instance.
	 */
	public Stats getStats() {
		return this.stats;
	}
}