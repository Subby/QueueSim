package Model;
/**
 * A class to handle all the statistics collection, calculation and operations.
 * @author Denver Fernandes
 */

import java.io.FileNotFoundException;
import java.io.PrintWriter;


public class Stats {

	public static int CUSTOMERS_SERVED = 0;
	public static int CUSTOMERS_GENERATED = 0;
	public static int CUSTOMERS_LEFT_NOT_SERVED = 0;
	public static int TOTAL_WAITING_TIME = 0;
	
	public static Stats instance = null;
	
	private Stats() {
		//To allow only a single point of access, the constructor is private.
	}
	
	public static Stats getInstance() {
		if(instance == null) {
			instance = new Stats();
		}
		return instance;
	}
	
	public double calcAvgWaitingTime() {
		return (TOTAL_WAITING_TIME / CUSTOMERS_GENERATED);
	}
	
	public int calcNumCustomersServed() {
		return CUSTOMERS_SERVED;
	}
	
	public int calcFractionServed() {
		return (CUSTOMERS_SERVED / CUSTOMERS_GENERATED);
	}
	
	public int calcCustomersWithoutBeingServed() {
		return CUSTOMERS_LEFT_NOT_SERVED;
	}
	
	public void outputToFile() throws FileNotFoundException {
		PrintWriter output = new PrintWriter("output.txt");
		StringBuilder strBlr = new StringBuilder();
		strBlr.append("Average waiting time: " + calcAvgWaitingTime() + "\n");
		strBlr.append("Number of customers served: " + calcNumCustomersServed() + "\n");
		strBlr.append("Fraction of customers served: " + calcFractionServed() + "\n");
		strBlr.append("Number of customers who left without being served: " + calcCustomersWithoutBeingServed() + "\n");
		output.write(strBlr.toString());
		output.close();
	}
	
}
