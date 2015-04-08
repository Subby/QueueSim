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

	@Override
	public void customerArrival() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void allocateCustomersToServers() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeCustomersFromServers() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Queue> getQueues() {
		// TODO Auto-generated method stub
		return null;
	}

}
