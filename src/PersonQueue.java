

import java.util.LinkedList;

/**
 * Represents a queue of people.
 * @author Denver Fernandes
 *
 */

public class PersonQueue implements Queue {
	
	/**
	 * Holds all the different people in the queue.
	 */
	private LinkedList<Person> queue;
	
	public PersonQueue() {
		queue = new LinkedList<Person>();
	}
	

	@Override
	public boolean addPerson(Person person) {
		if(queue.isEmpty() || queue == null) {
			return false;
		}
		queue.add(person);
		return true;
	}

	@Override
	public int getLength() {
		return queue.size();
	}

	@Override
	public boolean removeHeadOfQueue() {
		if(queue.isEmpty()) {
			return false;
		}
		queue.removeFirst();
		return true;
	}
	
}
