

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
	
	/**
	 * The instance
	 */
	public static QueueCollection instance = null;
	
	private QueueCollection() {
		queues = new ArrayList<Queue>();
	}
	
	/**
	 * The object creation point.
	 * @return the instance
	 */
	public static QueueCollection getInstance() {
		if(instance == null) {
			instance = new QueueCollection();
		}
		return instance;
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
		int shortestQueueLength = shortestQueue.getLength();
		if (shortestQueue != null) {
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
