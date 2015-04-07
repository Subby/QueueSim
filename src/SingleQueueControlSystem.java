
public class SingleQueueControlSystem implements QueueControlSystem {
	
	private Queue queue; 
	
	private ServerCollection servers; 
	
	private PersonFactory personFactory;
	
	private static SingleQueueControlSystem instance = null; 
	
	private SingleQueueControlSystem() {}
	
	public static SingleQueueControlSystem getInstance() {
		if (instance == null) {
			instance = new SingleQueueControlSystem();
		}
		return instance; 
	}

	public void generateQueueLayout() {
		queue = new PersonQueue();
	}
	
	public void generateServers(int numServers) {
		servers = ServerCollection.getInstance();
		for (int i = 0; i < numServers; i++) {
			Server server = new HumanServer();
			servers.addServer(server);
		}
	}

	public void customerArrival() {
		personFactory = PersonFactory.getInstance(); 
		Person newPerson = personFactory.generatePerson();
		if (newPerson != null) {
			queue.addPerson(newPerson);
		}
	}

	public void allocateCustomersToServers() {
		for (Server server : servers.showAvailableServers()) {
			server.setCurrentCustomer(queue.getHeadOfQueue());
			queue.removeHeadOfQueue();
		}
		
	}

	public void removeCustomersFromServers() {
		// TODO Auto-generated method stub
		
	}

}
