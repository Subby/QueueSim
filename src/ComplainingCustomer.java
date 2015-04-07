/**
 * Class that models a customer who takes twice as long
 * to serve if they're kept waiting for more than 8 minutes
 * @author Ben Lawton
 */

public class ComplainingCustomer extends UnhappyCustomer {
	
	//Time spent waiting in the queue, measured in ticks 
	private int timeSpentQueueing;
	//The cutoff point where the customer takes twice as long to serve
	private static final int PATIENCE_LIMIT = 48;  

	public ComplainingCustomer() {
		initialiseServeTime();
	}
	
	//Increases time spent queueing by one tick 
	public void incrementTimeSpentQueueing() {
		this.timeSpentQueueing++;
	}
	
	//If the time spent queueing is greater than the patience limit, double serving time
	public boolean queuedForTooLong() {
		boolean unhappy = false;
		if (timeSpentQueueing >= PATIENCE_LIMIT) {
			unhappy = true;
		}
		return unhappy; 
	}
	
	public void doubleServeTime() {
		if (this.timeSpentQueueing >= PATIENCE_LIMIT) {
			int serveTimeDoubled = getServeTime() * 2;
			changeServeTime(serveTimeDoubled);
		}
	}
	
}
