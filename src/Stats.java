/**
 * A class to handle all the statistics collection, calculation and operations.
 * @author Denver Fernandes
 */

import java.io.FileNotFoundException;
import java.io.PrintWriter;


public class Stats {

	public static int CUSTOMERS_SERVED = 0;
	public static int CUSTOMERS_GENERATED = 0;
	
	public static Stats instance = null;
	
	protected Stats() {
		//To allow only a single point of access, the constructor is protected.

	}
	
	public static Stats getInstance() {
		if(instance == null) {
			instance = new Stats();
		}
		return instance;
	}
	
	public int calcAvgWaitingTime() {
		//TODO: Finish this method when time scale has been adjusted correctly
		return 0;
	}
	
	public int calcNumCustomersServed() {
		return CUSTOMERS_SERVED;
	}
	
	public int calcFractionServed() {
		return (CUSTOMERS_SERVED / CUSTOMERS_GENERATED);
	}
	
	public void outputToFile() throws FileNotFoundException {
		PrintWriter output = new PrintWriter("output.txt");
		StringBuilder strBlr = new StringBuilder();
		strBlr.append("Average waiting time: " + calcAvgWaitingTime() + "\n");
		strBlr.append("Number of customers served: " + calcNumCustomersServed() + "\n");
		strBlr.append("Number of customers served: " + calcFractionServed() + "\n");
		output.write(strBlr.toString());
		output.close();
	}
	
}
