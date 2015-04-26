package model;
/**
 * The main class the runs the simulator.
 * @author Denver Fernandes
 */
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Simulator {
	
	private static int tick = 10;
	private static int numOfServers = 3;
	//Length of the simulation, should equal 4 hours in simulated time 
	private static int lengthOfSimulation = 1440;
	private static boolean shouldRun = true;
	public static QueueControlSystem selectedQueueSystem = SingleQueueControlSystem.getInstance();
	
	public static void main(String[] args) {
		
		final ComplainingCustomerObserver complainerObserver = ComplainingCustomerObserver.getInstance();
		final ShortOfTimeCustomerObserver shortOfTimeObserver = ShortOfTimeCustomerObserver.getInstance();
		final CustomerObserver customerObserver = CustomerObserver.getInstance();
		
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
	
	/**
	 * Sets the queue system type.
	 * @param controlSystem the queue system type to set
	 */
	public void setQueueSystem(QueueControlSystem controlSystem) {
		if(controlSystem == null) {
			return;
		}
		selectedQueueSystem = controlSystem;
	}
	
	public QueueControlSystem getQueueSystem() {
		return selectedQueueSystem;
	}
	
}

