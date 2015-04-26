package model;

public class CustomerObserver {
	
	private static CustomerObserver instance;
	
	private CustomerObserver() {}
	
	public static CustomerObserver getInstance() {
		if (instance == null) {
			instance = new CustomerObserver();
		}
		return instance;
	}
	
	public void incrementTimeSpentQueueing(QueueControlSystem queueSystem) {
		int totalTimeWaitedIncrement = 0;
		for (Queue queue : queueSystem.getQueues()) {
			for (Person person : queue.getQueue()) {
				person.incrementTimeSpentQueueing();
				totalTimeWaitedIncrement++;
			}
		}
		for (int i = 0; i < totalTimeWaitedIncrement; i++) {
			queueSystem.getStats().incrementTotalWaitingTime();
		}
	}

}
