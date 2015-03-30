/**
 * A superclass modelling the standard, compliant customer
 * @author Ben Lawton 
 */

import java.util.Random;

public class Customer implements Person {
	
	//The amount of time (ticks) it takes to serve a customer
	@SuppressWarnings("unused")
	private int serveTime;
	
	public Customer() {
		setServeTime();
	}
	
	/**
	 * Gives the amount of time it takes to serve a customer
	 * a random number between 18 and 30
	 */
	public void setServeTime() {
		Random rand = new Random(42);
		this.serveTime = rand.nextInt(12) + 18; 
	}

}
