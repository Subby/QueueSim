package model;
/**
 * A class to handle all the statistics in this Simulation.
 * @author Ben Lawton
 * @author Denver Fernandes
 */
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Stats {
	
	private int customersServed;
	private int customersGenerated;
	private int customersLeftNotServed;
	private int totalWaitingTime;
	
	public Stats() {}
	
	/**
	 * Gets the average waiting time.
	 * @return the average waiting time
	 */
	public int getAvgWaitingTime() {
		return (this.totalWaitingTime/this.customersGenerated);
	}
	
	/**
	 * Increments the number of customers served.
	 */
	public void incrementCustomersServed() {
		this.customersServed++;
	}
	
	/**
	 * Increments the number of customers generated.
	 */
	public void incrementCustomersGenerated() {
		this.customersGenerated++;
	}
	
	/**
	 * Increments the number of customers who left without being served.
	 */
	public void incrementCustomersLeftNotServed() {
		this.customersLeftNotServed++;
	}
	
	/**
	 * Increments the total waiting time.
	 */
	public void incrementTotalWaitingTime() {
		this.totalWaitingTime++;
	}
	
	/**
	 * Gets the number of customers who left without being served.
	 * @return the number of customers who left without being served
	 */
	public int getNumCustomersLeftNotServed() {
		return this.customersServed;
	}
	
	/**
	 * Gets the fraction of customers who left without being served.
	 * @return the fraction of customers who left without being served
	 */
	public double getFractionServed() {
		return ((double)this.customersServed/(double)this.customersGenerated);
	}
	
	/**
	 * Gets the number of customer who were not served.
	 * @return the number of customers who were not served
	 */
	public int getNumCustomersNotServed() {
		return this.customersLeftNotServed;
	}
	
	/**
	 * Outputs all of the statistics collected to a file.
	 */
	public void outputToFile(){
		PrintWriter output;
		try {
			output = new PrintWriter("output.txt");
			StringBuilder strBlr = new StringBuilder();
			strBlr.append("Average waiting time: " + getAvgWaitingTime() + "\n");
			strBlr.append("Number of customers served: " + getNumCustomersLeftNotServed() + "\n");
			strBlr.append("Fraction of customers served: " + getFractionServed() + "\n");
			strBlr.append("Number of customers who left without being served: " + getNumCustomersNotServed());
			output.write(strBlr.toString());
			output.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Converts all the statistics to a formatted {@link String}.
	 */
	public String toString() {
		StringBuilder strBlr = new StringBuilder();
		strBlr.append("Average waiting time: " + getAvgWaitingTime());
		strBlr.append("\n");
		strBlr.append("Number of customers served: " + getNumCustomersLeftNotServed());
		strBlr.append("\n");
		strBlr.append("Fraction of customers served: " + getFractionServed());
		strBlr.append("\n");
		strBlr.append("Number of customers who left without being served: " + getNumCustomersNotServed());
		strBlr.append("\n");
		return strBlr.toString();
	}
}
