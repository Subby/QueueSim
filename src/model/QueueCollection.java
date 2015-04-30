package model;


import java.util.ArrayList;

/**
 * Represents a collection of Queues 
 * @author Denver Fernandes
 * @author Ben Lawton 
 */

public class QueueCollection {
	
	/**
	 * The queues contained in the collection
	 */
	private ArrayList<Queue> queues;
		
	public QueueCollection() {
		queues = new ArrayList<Queue>();
	}
	
	/**
	 * Gets the queues in the collection.
	 * @return the ArrayList holding the queues 
	 */
	public ArrayList<Queue> getQueues() {
		return queues;
	}
	
	/**
	 * Returns the shortest  {@link Queue}
	 * @return the shortest {@link Queue} in the collection 
	 */
	public Queue showShortestQueue() {
		//TODO: Implement error handling for when the collection is empty 
		Queue shortestQueue = queues.get(0);
		if (shortestQueue != null) {
			int shortestQueueLength = shortestQueue.getLength();
			for(Queue currentQueue : queues) {
				if(currentQueue.getLength() < shortestQueueLength) {
					shortestQueue = currentQueue;
				}
			}
		}
		return shortestQueue;
	}
	
	/**
	 * Adds a new {@link Queue} to the collection.
	 * @param the Queue to be added 
	 */
	public void addQueue(model.Queue queue) {
		queues.add(queue);
	}
	
}
