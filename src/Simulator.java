/**
 * The main class the runs the simulator.
 * @author Denver Fernandes
 */
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Simulator {
	
	public static long TICK = 10;
	public static int NUM_OF_SERVERS = 3;
	public static int LENGTH_OF_SIMULATION = 7200; //should equal 4 hours in simulated time unless I have my math wrong :s
	
	public static void main(String[] args) {
		
		MultiQueueControlSystem.getInstance().generateQueuesAndServers(NUM_OF_SERVERS);
		SingleQueueControlSystem.getInstance().generateQueuesAndServers(NUM_OF_SERVERS);
		
        final ScheduledExecutorService ticker = Executors.newSingleThreadScheduledExecutor();
        
        ticker.scheduleAtFixedRate(new Runnable(){
        	
        	long currentTime = 0;
        	
			@Override
			public void run() {
				if(currentTime < LENGTH_OF_SIMULATION) {
					currentTime = currentTime + 10;
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
        }, 0, TICK, TimeUnit.SECONDS);
        
	}

}
