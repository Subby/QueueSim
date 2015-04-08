/**
 * Class that handles the generation of queues, servers, customers and the interaction between them for the simlulation of a multi queue system
 * @author Ben Lawton 
 */

import java.util.ArrayList;


public class MultiQueueControlSystem implements QueueControlSystem {
	
	private ServerCollection servers;
	
	private QueueCollection queues; 
	
	private PersonFactory personFactory;
	
	private static MultiQueueControlSystem instance; 

	public void generateQueuesAndServers(int numServers) {
		servers = ServerCollection.getInstance();
		queues = QueueCollection.getInstance();
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
		personFactory = PersonFactory.getInstance(); 
		Person newPerson = personFactory.generatePerson();
		if (newPerson != null) {
			Queue shortestQueue = queues.showShortestQueue();
			shortestQueue.addPerson(newPerson);
		}
	}

	//Allocates a customer to an available server from their allocated queues 
	public void allocateCustomersToServers() {
		for (Server server : servers.showAvailableServers()) {
			server.setCurrentCustomer(server.getAllocatedQueue().getHeadOfQueue());
			server.getAllocatedQueue().removeHeadOfQueue();
			server.setFree(false);
		}
	}
	
	//Removes customers from the servers if their serve time has been met or exceeded
	public void removeFinishedCustomersFromServers() {
		for (Server server : servers.getServers()) {
			if (server.isFree() == false) {
				Person currentCustomer = server.getCurrentCustomer();
				if (currentCustomer.getServeTime() >= server.getTimeSpentServing()) {
					server.finishWithCustomer();
				}
			}
		}	
	}
	
	public ArrayList<Queue> getQueues() {
		return queues.getQueues();
	}
}
