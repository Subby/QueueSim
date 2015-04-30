package model;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Represents a queue of type People.
 * @author Denver Fernandes
 * @author Ben Lawton 
 *
 */

public class PersonQueue implements Queue {
	
	/**
	 * Holds all the different Person(s) in the queue.
	 */
	private LinkedList<Person> queue;
	
	public PersonQueue() {
		queue = new LinkedList<Person>();
	}
	
	/**
	 * Adds a Person to the queue
	 * @param person to add to the queue 
	 */
	@Override
	public void addPerson(Person person) {
		if(queue != null) {
			queue.add(person);
		}
	}
	
	/**
	 * Gets the queue.
	 * @return a LinkedList containing the people in the queue 
	 */
	@Override
	public LinkedList<Person> getQueue() {
		return queue;
	}
	
	/**
	 * Removes a person from the queue.
	 * @param person the person to be removed 
	 */
	@Override 
	public void removePerson(Person person) {
		queue.remove(person);
	}
	
	/**
	 * Gets the length of the queue
	 * @return the length of the queue as an int 
	 */
	@Override
	public int getLength() {
		return queue.size();
	}
	
	/**
	 * Removes the element at the head of the queue 
	 * @return whether or not the operation was successful
	 */
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
	@Override
	public Person getHeadOfQueue() {
		return queue.getFirst();
	}
	
	/**
	 * Returns all instances of ShortOfTimeCustomer in the queue 
	 * @return an ArrayList containing all ShortOfTimeCustomer instances
	 */
	@Override
	public ArrayList<ShortOfTimeCustomer> getShortOfTimeCustomers() {
		ArrayList<ShortOfTimeCustomer> customers = new ArrayList<ShortOfTimeCustomer>();
		for (Person person : queue) {
			if (person instanceof ShortOfTimeCustomer) {
				customers.add((ShortOfTimeCustomer) person);
			}
		}
		return customers;
	}
	
	/**
	 * Returns all instances of ComplainingCustomer in the queue
	 * @return an ArrayList containing all ComplainingCustomer instances
	 */
	@Override
	public ArrayList<ComplainingCustomer> getComplainingCustomers() {
		ArrayList<ComplainingCustomer> customers = new ArrayList<ComplainingCustomer>();
		for (Person person : queue) {
			if (person instanceof ComplainingCustomer) {
				customers.add((ComplainingCustomer) person);
			}
		}
		return customers;
	}
}
