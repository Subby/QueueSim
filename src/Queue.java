


/**
 * An interface to represent queues.
 * @author Denver Fernandes
 *
 */

public interface Queue {
	
	/**
	 * Adds a person to the queue.
	 * @param person the person to add
	 * @return whether the operation is successful
	 */
	public boolean addPerson(Person person);
	
	/**
	 * Gets the length of the queue.
	 * @return the length of the queue.
	 */
	public int getLength();
	
	/**
	 * Removes the element at the head (top) of the queue.
	 * @return whether the operation is successful
	 */
	public boolean removeHeadOfQueue();
}
