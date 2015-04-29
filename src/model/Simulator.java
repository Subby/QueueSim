package model;

/**
 * The class that runs the simulation 
 * @author Denver Fernandes
 * @author Ben Lawton 
 */

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Simulator {
	
	//Execution frequency for the simulation ticks
	private int tick = 10;
	//Number of servers to be used in the simulation
	private int numOfServers;
	//Length of the simulation
	private int lengthOfSimulation;
	//Whether or not the simulation should still be running 
	private boolean shouldRun = true;
	//The queue system used to manage the simulation's moving parts 
	public QueueControlSystem selectedQueueSystem;
	
	public void run() {
		
		//Initialise observers/iterators used in the simulation 
		final ComplainingCustomerObserver complainerObserver = new ComplainingCustomerObserver();
		final ShortOfTimeCustomerObserver shortOfTimeObserver = new ShortOfTimeCustomerObserver(); 
		final CustomerObserver customerObserver = new CustomerObserver();
		
		//The value for numOfServers is assigned in the views 
		selectedQueueSystem.generateQueuesAndServers(numOfServers);
		
		//Initialise service that schedules & runs ticks in the simulation 
        final ScheduledExecutorService ticker = Executors.newSingleThreadScheduledExecutor();
        
        ticker.scheduleAtFixedRate(new Runnable() {
        	
    		int currentTime = 0;
        	
			public void run() {
				if(!shouldRun) {
					ticker.shutdown();
					return;
				}
				
				if(currentTime < lengthOfSimulation) {
					currentTime = currentTime + 1;
				} else {
					ticker.shutdown();
					System.out.println("Simulator stopped.");
					return;
				}
				
				selectedQueueSystem.customerArrival();
				selectedQueueSystem.serveAndFinishWithCustomers();
				customerObserver.incrementTimeSpentQueueing(selectedQueueSystem);
	            complainerObserver.actOnInconveniencedCustomers(selectedQueueSystem);
	            shortOfTimeObserver.actOnInconveniencedCustomers(selectedQueueSystem);
	            selectedQueueSystem.allocateCustomersToServers();
	            
			}
		}, 1, tick, TimeUnit.MILLISECONDS);
	}
	
	/**
	 * Sets the number of servers to be used in the simulation.
	 * @param the number of servers to be used
	 */
	public void setNumberOfServers(int numServers) {
		if(numServers >= 1) {
			this.numOfServers = numServers;
		}
	}
	
	/**
	 * Sets the length of the simulation.
	 * @param length the length of simulation
	 */
	public void setLengthOfSimulation(int length) {
		if(length <= 0) {
			return;
		}
		//Multiply by 360 to get scaled hour.
		lengthOfSimulation = length * 360;
	}
	
	/**
	 * Sets a flag to indicate whether or not the simulator should be running.
	 * @param run the flag
	 */
	public void setShouldRun(boolean run) {
		this.shouldRun = run;
	}
	
	/**
	 * Assigns the simulator a control system for a single queue simulation 
	 */
	public void setSingleQueueControlSystem() {
		this.selectedQueueSystem = new SingleQueueControlSystem();
	}
	
	/**
	 * Assigns the simulator a control system for a multiple queue simulation 
	 */
	public void setMultiQueueControlSystem() {
		this.selectedQueueSystem = new MultiQueueControlSystem();
	}
	
	/**
	 * Returns the currently assigned control system for the simulation 
	 * @return the current QueueControlSystem implementation in use 
	 */
	public QueueControlSystem getQueueSystem() {
		return this.selectedQueueSystem;
	}
	
}

