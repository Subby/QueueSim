package model;
/**
 * Class that observes ComplainingCustomer objects and doubles their waiting times if they have been waiting in the queue for too long 
 * @author Ben Lawton
 *
 */

public class ComplainingCustomerObserver implements UnhappyCustomerObserver {

	//Constructor kept private so class can't be instantiated at will
	public ComplainingCustomerObserver() {}
	
	/**
	 * Iterates over ComplainingCustomer instances in all queues and doubles their serveTime PATIENCE_LIMIT has been exceeded 
	 */
	public void actOnInconveniencedCustomers(QueueControlSystem queueSystem) {
		for (Queue queue : queueSystem.getQueues()) {
			for (Person person : queue.getQueue()) {
				if (person instanceof ComplainingCustomer) {
					//((ComplainingCustomer) person).incrementTimeSpentQueueing();
					//TODO queueSystem.getStats().incrementTotalWaitingTime();
					if (((ComplainingCustomer) person).queuedForTooLong() == true) {
						((ComplainingCustomer) person).doubleServeTime();
					}
				}
			}
		}
	}
}
