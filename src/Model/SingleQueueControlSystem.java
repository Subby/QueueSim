package Model;
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
	
	//(Singleton pattern) The only instance of the class 
	private static SingleQueueControlSystem instance; 
	
	private SingleQueueControlSystem() {
		queue = new PersonQueue();
		servers = new ServerCollection();
		personFactory = new PersonFactory();
	}
	
	//Instantiates the object if it hasn't already been instantiated. Otherwise just returns the lone object. 
	public static SingleQueueControlSystem getInstance() {
		if (instance == null) {
			instance = new SingleQueueControlSystem();
		}
		return instance; 
	}
	
	//Generates the servers for the simulation 
	public void generateQueuesAndServers(int numServers) {
		for (int i = 0; i < numServers; i++) {
			Server server = new HumanServer();
			servers.addServer(server);
		}
	}
	
	//If a customer is generated, adds it to the queue 
	public void customerArrival() {
		Person newPerson = personFactory.generatePerson();
		if (newPerson != null) {
			queue.addPerson(newPerson);
			Stats.CUSTOMERS_GENERATED++;
		}
	}
	
	//Allocate a customer to all available servers 
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
	
	//Remove customers from the servers if their serve time has bee exceeded 
	public void serveAndFinishWithCustomers() {
		servers.serveCustomers();
		servers.finishWithCustomers();
	}

	public ArrayList<Queue> getQueues() {
		ArrayList<Queue> queueList = new ArrayList<Queue>();
		queueList.add(queue);
		return queueList; 
	}

}
