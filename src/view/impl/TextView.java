package view.impl;

import model.QueueControlSystem;
import model.SingleQueueControlSystem;
import model.Stats;
import view.SimulatorView;

/**
 * This class handles the command line output
 * @author Tony Ung
 *
 */


public class TextView implements SimulatorView {

	private QueueControlSystem selectedQueueControlSystem = SingleQueueControlSystem.getInstance();
	private Stats stats = selectedQueueControlSystem.getStats();
	
	@Override
	public String output() {
		StringBuilder strBlr = new StringBuilder();
		strBlr.append("Average waiting time: " + stats.getAvgWaitingTime());
		strBlr.append("Number of customers served: " + stats.getNumCustomersLeftNotServed());
		strBlr.append("Percentage of customers served: " + stats.getFractionServed() + "%");
		strBlr.append("Number of customers who left without being served: " + stats.getNumCustomersNotServed());
		return strBlr.toString();

	}
	
	public void run(){
		System.out.println(output());
	}

}
