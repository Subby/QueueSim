/**
 * The main class the runs the simulator.
 * @author Denver Fernandes
 */
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Simulator {
	
	public static int TICK = 10;
	public static int NUM_OF_SERVERS = 3;
	//Length of the simulation, should equal 4 hours in simulated time 
	public static int LENGTH_OF_SIMULATION = 1440;
	
	public static void main(String[] args) {
		
		
		MultiQueueControlSystem multiController = MultiQueueControlSystem.getInstance();
		SingleQueueControlSystem singleController = SingleQueueControlSystem.getInstance();
		
		multiController.generateQueuesAndServers(NUM_OF_SERVERS);
		singleController.generateQueuesAndServers(NUM_OF_SERVERS);
		
        final ScheduledExecutorService ticker = Executors.newSingleThreadScheduledExecutor();
        
        ticker.scheduleAtFixedRate(new Runnable() {
        	
			public void run() {
				
				int currentTime = 0;
				
				if(currentTime < LENGTH_OF_SIMULATION) {
					currentTime = currentTime + 1;
				} else {
					ticker.shutdown();
					return;
				}
				
	            MultiQueueControlSystem.getInstance().customerArrival();
	            MultiQueueControlSystem.getInstance().allocateCustomersToServers();
	            MultiQueueControlSystem.getInstance().removeFinishedCustomersFromServers();
	            	
	            SingleQueueControlSystem.getInstance().customerArrival();
	            SingleQueueControlSystem.getInstance().allocateCustomersToServers();
	            SingleQueueControlSystem.getInstance().removeFinishedCustomersFromServers();
			}
		}, 1, TICK, TimeUnit.MILLISECONDS);
	}
}

