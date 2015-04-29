package model;
import java.util.LinkedList;
import java.util.ArrayList;




/**
 * An interface to represent queues.
 * @author Denver Fernandes
 *
 */

public interface Queue {
	
	/**
	 * Adds a person to the queue.
	 * @param person the person to add
	 */
	public void addPerson(Person person);
	
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
	
	/**
	 * Gets the {@link Person} at the top of the queue.
	 */
	public Person getHeadOfQueue();
	
	/**
	 * @return the people in the queue 
	 */
	public LinkedList<Person> getQueue();
	
	/**
	 * Removes a person from the queue.
	 * @param person the person to be removed 
	 */
	public void removePerson (Person person);
	
	/**
	 * Returns all instances of ShortOfTimeCustomer in the queue 
	 * @return an ArrayList containing all ShortOfTimeCustomer instances
	 */
	public ArrayList<ShortOfTimeCustomer> getShortOfTimeCustomers();
	
	/**
	 * Returns all instances of ComplainingCustomer in the queue
	 * @return an ArrayList containing all ComplainingCustomer instances
	 */
	public ArrayList<ComplainingCustomer> getComplainingCustomers();

}
