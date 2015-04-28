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
	 * Iterates all of the ComplainingCustomer instances in all of the queues in the simulation, increments their time spent in the queue by 1
	 * and then doubles their ServeTime if they've been waiting in the queue for longer than their patience limit 
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
