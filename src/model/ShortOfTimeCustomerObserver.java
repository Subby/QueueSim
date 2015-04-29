package model;
/**
 * Class that loops over and acts on ShortOfTimeCustomers (removes them when queuedForTooLong)
 * @author Ben Lawton
 */

public class ShortOfTimeCustomerObserver implements UnhappyCustomerObserver {

	public ShortOfTimeCustomerObserver() {}

	/**
	 * Iterates over all of the queues, and removes any ShortOfTimeCustomer objects which have exceeded their patience limits 
	 */
	public void actOnInconveniencedCustomers(QueueControlSystem queueSystem) {
		for (Queue queue : queueSystem.getQueues()) {
			if (queue.getShortOfTimeCustomers().size() > 0) {
				for (ShortOfTimeCustomer shortOfTimeCustomer : queue.getShortOfTimeCustomers()) {
					if (shortOfTimeCustomer.queuedForTooLong()) {
						queue.removePerson(shortOfTimeCustomer);
						queueSystem.getStats().incrementCustomersLeftNotServed();
					}
				}
			}
		}
	}
}
