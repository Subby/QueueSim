package model;

public class CustomerObserver {
		
	public CustomerObserver() {}
	
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
