package model;


import java.util.ArrayList;
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
	
	/**
	 * Gets the queue of people.
	 */
	public LinkedList<Person> getQueue() {
		return queue;
	}
	
	/**
	 * Removes a person from the queue.
	 * @param person the person to remove
	 */
	public void removePerson(Person person) {
		queue.remove(person);
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
	
	/**
	 * Gets the {@link Person} at the top of the queue.
	 */
	public Person getHeadOfQueue() {
		return queue.getFirst();
	}
	
	public ArrayList<ShortOfTimeCustomer> getShortOfTimeCustomers() {
		ArrayList<ShortOfTimeCustomer> customers = new ArrayList<ShortOfTimeCustomer>();
		for (Person person : queue) {
			if (person instanceof ShortOfTimeCustomer) {
				customers.add((ShortOfTimeCustomer) person);
			}
		}
		return customers;
	}
}
