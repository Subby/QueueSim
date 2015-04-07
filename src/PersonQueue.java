

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
	public void addPerson(Person person) {
		if(queue != null) {
			queue.add(person);
		}
	}
	
	public LinkedList<Person> getQueue() {
		return queue;
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
	
	public Person getHeadOfQueue() {
		return queue.getFirst();
	}
	
}
