/**
 * The main class the runs the simulator.
 * @author Denver Fernandes
 */
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Simulator {
	
	public static long TICK = 2;
	
	public static void main(String[] args) {
		MultiQueueControlSystem.getInstance().generateQueuesAndServers(3);
		
		SingleQueueControlSystem.getInstance().generateQueuesAndServers(3);
        final ScheduledExecutorService ticker = Executors.newSingleThreadScheduledExecutor();
        ticker.scheduleAtFixedRate(new Runnable(){

			@Override
			public void run() {
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
