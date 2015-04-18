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
		
		
		final MultiQueueControlSystem multiController = MultiQueueControlSystem.getInstance();
		final SingleQueueControlSystem singleController = SingleQueueControlSystem.getInstance();
		
		multiController.generateQueuesAndServers(NUM_OF_SERVERS);
		singleController.generateQueuesAndServers(NUM_OF_SERVERS);
		
        final ScheduledExecutorService ticker = Executors.newSingleThreadScheduledExecutor();
        
        ticker.scheduleAtFixedRate(new Runnable() {
        	
    		int currentTime = 0;
        	
			public void run() {
				
				if(currentTime < LENGTH_OF_SIMULATION) {
					currentTime = currentTime + 1;
				} else {
					ticker.shutdown();
					return;
				}
				
	            multiController.customerArrival();
	            multiController.allocateCustomersToServers();
	            multiController.removeFinishedCustomersFromServers();
	            	
	            singleController.customerArrival();
	            singleController.allocateCustomersToServers();
	            singleController.removeFinishedCustomersFromServers();
			}
		}, 1, TICK, TimeUnit.MILLISECONDS);
	}
}

