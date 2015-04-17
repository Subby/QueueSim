/**
 * 
 * @author Ben Lawton
 *
 */

public class ShortOfTimeCustomerObserver implements UnHappyCustomerObserver {
	
	//(Singleton pattern) Variable that points to the single instance of the ShortOftimeCustomerObserver object
	private static ShortOfTimeCustomerObserver instance;
	
	//Constructor is private so that the class can't be instantiated at-will
	private ShortOfTimeCustomerObserver() {}
	
	//Returns the single instance of the class, and instantiates it if an instance hasn't already been assigned to the instance static field
	public static ShortOfTimeCustomerObserver getInstance() {
		if (instance == null) {
			instance = new ShortOfTimeCustomerObserver();
		}
		return instance; 
	}
	
	/**
	 * Iterates over all of the queues, and removes any ShortOfTimeCustomer objects which have exceeded their patience limits 
	 */
	public void actOnInconveniencedCustomers(QueueControlSystem queueSystem) {
		for (Queue queue : queueSystem.getQueues()) {
			for (Person person : queue.getQueue()) {
				if (person instanceof ShortOfTimeCustomer) {
					((ShortOfTimeCustomer) person).incrementTimeSpentQueueing();
					if (((ShortOfTimeCustomer) person).queuedForTooLong() == true) {
						queue.removePerson(person);
						Stats.CUSTOMERS_LEFT_NOT_SERVED++;
					}
				}
			}
		}
		
	}
}
