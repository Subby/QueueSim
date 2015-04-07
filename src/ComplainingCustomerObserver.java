/**
 * Class that observes ComplainingCustomer objects and doubles their waiting times if they have been waiting in the queue for too long 
 * @author Ben Lawton
 *
 */

public class ComplainingCustomerObserver implements UnHappyCustomerObserver {

	public void actOnInconveniencedCustomers(QueueControlSystem queueSystem) {
		for (Queue queue : queueSystem.getQueues()) {
			for (Person person : queue.getQueue()) {
				if (person instanceof ComplainingCustomer) {
					((ComplainingCustomer) person).incrementTimeSpentQueueing();
					if (((ComplainingCustomer) person).queuedForTooLong() == true) {
						((ComplainingCustomer) person).doubleServeTime();
					}
				}
			}
		}
	}
}
