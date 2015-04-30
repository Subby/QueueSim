package model;
/**
 * Class that handles the generation of queues, servers, customers and the interaction between them for the simlulation of a single queue system 
 * @author Ben Lawton 
 *
 */

import java.util.ArrayList;

public class SingleQueueControlSystem implements QueueControlSystem {
	
	//The single queue in the simulation 
	private Queue queue; 
	
	//A collection of all of the servers in the simulation 
	private ServerCollection servers; 
	
	//The PersonFactory that generates the customers 
	private PersonFactory personFactory;
		
	//The statistics for the simulation
	private Stats stats;
	
	public SingleQueueControlSystem() {
		queue = new PersonQueue();
		servers = new ServerCollection();
		personFactory = new PersonFactory();
		stats = new Stats();
	}
	
	/**
	 * Generates the queues and the servers.
	 */
	public void generateQueuesAndServers(int numServers) {
		for (int i = 0; i < numServers; i++) {
			Server server = new HumanServer();
			servers.addServer(server);
		}
	}
	
	/**
	 * If a customer has been generated, adds it to the queue.
	 */
	public void customerArrival() {
		Person newPerson = personFactory.generatePerson();
		if (newPerson != null) {
			queue.addPerson(newPerson);
			this.stats.incrementCustomersGenerated();
		}
	}
	
	/**
	 * Allocates customers to available servers.
	 */
	public void allocateCustomersToServers() {
		if (servers.showAvailableServers().size() > 0) {
			for (Server server : servers.showAvailableServers()) {
				if (queue.getLength() > 0) {
					server.setCurrentCustomer(queue.getHeadOfQueue());
					queue.removeHeadOfQueue();
					server.setFree(false);
				}
			}
		}
	}
	
	/**
	 * Removes customers from the servers if their serve time has been exceeded.
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
	 * Gets the {@link Queue} elements.
	 * @return the queues
	 */
	public ArrayList<Queue> getQueues() {
		ArrayList<Queue> queueList = new ArrayList<Queue>();
		queueList.add(queue);
		return queueList; 
	}

	/**
	 * Gets the server collection
	 * @return the server collection
	 */
	@Override
	public ServerCollection getServerCollection() {
		return this.servers;
	}
	
	/**
	 * Gets the {@link Stats} instance.
	 * @return the stats instance
	 */
	public Stats getStats() {
		return this.stats;
	}

}
