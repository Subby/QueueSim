package model;
/**
 * 
 * @author Ben Lawton
 *
 */

public class ShortOfTimeCustomerObserver implements UnhappyCustomerObserver {

	//Constructor is private so that the class can't be instantiated at-will
	public ShortOfTimeCustomerObserver() {}

	/**
	 * Iterates over all of the queues, and removes any ShortOfTimeCustomer objects which have exceeded their patience limits 
	 */
	public void actOnInconveniencedCustomers(QueueControlSystem queueSystem) {
		for (Queue queue : queueSystem.getQueues()) {
			if (queue.getSOT().size() > 0) {
				for (ShortOfTimeCustomer sot : queue.getSOT()) {
					if (sot.queuedForTooLong()) {
						queue.removePerson(sot);
						queueSystem.getStats().incrementCustomersLeftNotServed();
					}
				}
			}
		}
	}
}
