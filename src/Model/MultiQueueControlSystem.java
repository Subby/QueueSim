package Model;
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
	
	private static MultiQueueControlSystem instance = null; 
	
	private MultiQueueControlSystem() {
		servers = new ServerCollection();
		queues = new QueueCollection();
		personFactory = new PersonFactory();		
	}

	public static MultiQueueControlSystem getInstance() {
		if(instance == null) {
			instance = new MultiQueueControlSystem();
		}
		return instance;
	}
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
			Stats.CUSTOMERS_GENERATED++;
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
		servers.finishWithCustomers();
	}
	
	
	public ArrayList<Queue> getQueues() {
		return queues.getQueues();
	}
}
