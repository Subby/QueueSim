package Model;


import java.util.ArrayList;

/**
 * Represents a collection of many different queues.
 * @author Denver Fernandes
 *
 */

public class QueueCollection {
	
	/**
	 * The queues contained in this collection
	 */
	private ArrayList<Queue> queues;
		
	public QueueCollection() {
		queues = new ArrayList<Queue>();
	}
	
	public ArrayList<Queue> getQueues() {
		return queues;
	}
	
	/**
	 * Returns the shortest queue.
	 * @return the shortest queue
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
	
	public void addQueue(Queue queue) {
		queues.add(queue);
	}
	
}
