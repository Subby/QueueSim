package Model;
/**
 * Class that observes ComplainingCustomer objects and doubles their waiting times if they have been waiting in the queue for too long 
 * @author Ben Lawton
 *
 */

public class ComplainingCustomerObserver implements UnhappyCustomerObserver {
	
	//(Singleton pattern) variable to point to the single instance of the class 
	private static ComplainingCustomerObserver instance; 
	
	//Constructor kept private so class can't be instantiated at will
	private ComplainingCustomerObserver() {}
	
	//Used to return the single instance of the class 
	public static ComplainingCustomerObserver getInstance() {
		if (instance == null) {
			instance = new ComplainingCustomerObserver();
		}
		return instance; 
	}
	
	/**
	 * Iterates all of the ComplainingCustomer instances in all of the queues in the simulation, increments their time spent in the queue by 1
	 * and then doubles their ServeTime if they've been waiting in the queue for longer than their patience limit 
	 */
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
