package model;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Stats {
	
	private int customersServed;
	private int customersGenerated;
	private int customersLeftNotServed;
	private int totalWaitingTime;
	
	public Stats() {}
	
	public double getAvgWaitingTime() {
		return (this.totalWaitingTime/this.customersGenerated);
	}
	
	public void incrementCustomersServed() {
		this.customersServed++;
	}
	
	public void incrementCustomersGenerated() {
		this.customersGenerated++;
	}
	
	public void incrementCustomersLeftNotServed() {
		this.customersLeftNotServed++;
	}
	
	public void incrementTotalWaitingTime() {
		this.totalWaitingTime++;
	}
	
	public int getNumCustomersLeftNotServed() {
		return this.customersServed;
	}
	
	public int getFractionServed() {
		return (this.customersServed/this.customersGenerated) * 100;
	}
	
	public int getNumCustomersNotServed() {
		return this.customersLeftNotServed;
	}
	
	public void outputToFile() throws FileNotFoundException {
		PrintWriter output = new PrintWriter("output.txt");
		StringBuilder strBlr = new StringBuilder();
		strBlr.append("Average waiting time: " + getAvgWaitingTime() + "\n");
		strBlr.append("Number of customers served: " + getNumCustomersLeftNotServed() + "\n");
		strBlr.append("Percentage of customers served: " + getFractionServed() + "% \n");
		strBlr.append("Number of customers who left without being served: " + getNumCustomersNotServed());
		output.write(strBlr.toString());
		output.close();
	}
}
