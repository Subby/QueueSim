/**
 * Class responsible for iterating over all Customer types in the queue system and incrementing their 
 * timeSpentQueueing fields
 * @author Ben Lawton
 */

package model;

public class CustomerObserver {
		
	public CustomerObserver() {}
	
	/**
	 * Iterates over all Customer objects in the queue system and increments their timeSpentQueueing fields 
	 * Also increments the total time waited (across all customers) in the queue system's Stats object 
	 * @param queueSystem
	 */
	public void incrementTimeSpentQueueing(QueueControlSystem queueSystem) {
		int totalTimeWaitedIncrement = 0;
		for (Queue queue : queueSystem.getQueues()) {
			for (Person person : queue.getQueue()) {
				person.incrementTimeSpentQueueing();
				totalTimeWaitedIncrement++;
			}
		}
		for (int i = 0; i < totalTimeWaitedIncrement; i++) {
			queueSystem.getStats().incrementTotalWaitingTime();
		}
	}

}
