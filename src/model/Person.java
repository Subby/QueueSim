package model;
/**
 * Interface modelling any kind of person that could join a queue i.e. customer, inspector or colleague 
 * @author Ben Lawton
 */

public interface Person {
	
	/**
	 * Gives the Person a randomised serve time (time it takes to serve them) within a given range
	 */
	public void initialiseServeTime();
	
	/**
	 * Gets the serve time of a person.
	 * @return The Person's serve time (time it takes to serve them) 
	 */
	public int getServeTime();
	
	/**
	 * Increments the time the Person has spent in the queue 
	 */
	public void incrementTimeSpentQueueing();
}
