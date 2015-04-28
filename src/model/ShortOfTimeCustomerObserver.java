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
		if (queueSystem.getQueues().size() > 0) {
			for (Queue queue : queueSystem.getQueues()) {
				if (queue.getQueue().size() > 0) {
					for (Person person : queue.getQueue()) {
						if (person instanceof ShortOfTimeCustomer) {
							//((ShortOfTimeCustomer) person).incrementTimeSpentQueueing();
							//TODO queueSystem.getStats().incrementTotalWaitingTime();
							if (((ShortOfTimeCustomer) person).queuedForTooLong() == true) {
								queue.removePerson(person);
								queueSystem.getStats().incrementCustomersLeftNotServed();
							}
						}
					}
				}
			}
		}
	}
}
