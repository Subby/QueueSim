package model;
/**
 * The main class the runs the simulator.
 * @author Denver Fernandes
 */
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Simulator {
	
	private int tick = 10;
	private int numOfServers = 3;
	//Length of the simulation
	private int lengthOfSimulation;
	private boolean shouldRun = true;
	public QueueControlSystem selectedQueueSystem;
	
	public void main(String[] args) {
		
		final ComplainingCustomerObserver complainerObserver = new ComplainingCustomerObserver();
		final ShortOfTimeCustomerObserver shortOfTimeObserver = new ShortOfTimeCustomerObserver(); 
		final CustomerObserver customerObserver = new CustomerObserver();
		
		selectedQueueSystem.generateQueuesAndServers(numOfServers);
		
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
	 * Sets the number of servers to be used in this simulation.
	 * @param number the number of servers to be used
	 */
	public void setNumberOfServers(int number) {
		if(number <= 0) {
			return;	
		}
		numOfServers = number;
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
	 * Sets a flag to indicate whether the simulator should be running.
	 * @param run the flag
	 */
	public void setShouldRun(boolean run) {
		shouldRun = run;
	}
	
	public void setSingleQueueControlSystem() {
		this.selectedQueueSystem = new SingleQueueControlSystem();
	}
	
	public void setMultiQueueControlSystem() {
		this.selectedQueueSystem = new MultiQueueControlSystem();
	}
	
	public QueueControlSystem getQueueSystem() {
		return selectedQueueSystem;
	}
	
}

