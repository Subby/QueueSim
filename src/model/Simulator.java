package model;
/**
 * The main class the runs the simulator.
 * @author Denver Fernandes
 */
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import view.impl.AnimatedView;


public class Simulator {
	
	public static int TICK = 10;
	public static int NUM_OF_SERVERS = 3;
	//Length of the simulation, should equal whatever the given value is in hours.
	public static int LENGTH_OF_SIMULATION = (int) AnimatedView.SIMULATIONLENGTHSLIDER * 360;
	public static boolean SHOULD_RUN = true;
	public static QueueControlSystem SELECTEDQUEUESYSTEM = MultiQueueControlSystem.getInstance();
	
	public static void main(String[] args) {
		
		final ComplainingCustomerObserver complainerObserver = ComplainingCustomerObserver.getInstance();
		final ShortOfTimeCustomerObserver shortOfTimeObserver = ShortOfTimeCustomerObserver.getInstance();
		
		SELECTEDQUEUESYSTEM.generateQueuesAndServers(NUM_OF_SERVERS);
		
        final ScheduledExecutorService ticker = Executors.newSingleThreadScheduledExecutor();
        
        ticker.scheduleAtFixedRate(new Runnable() {
        	
    		int currentTime = 0;
        	
			public void run() {
				if(!SHOULD_RUN) {
					ticker.shutdown();
					return;
				}
				
				if(currentTime < LENGTH_OF_SIMULATION) {
					currentTime = currentTime + 1;
				} else {
					ticker.shutdown();
					return;
				}
				
				SELECTEDQUEUESYSTEM.customerArrival();
				SELECTEDQUEUESYSTEM.serveAndFinishWithCustomers();
	            complainerObserver.actOnInconveniencedCustomers(SELECTEDQUEUESYSTEM);
	            shortOfTimeObserver.actOnInconveniencedCustomers(SELECTEDQUEUESYSTEM);
	            SELECTEDQUEUESYSTEM.allocateCustomersToServers();
	            
			}
		}, 1, TICK, TimeUnit.MILLISECONDS);
	}
}

