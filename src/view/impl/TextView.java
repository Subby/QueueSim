package view.impl;

import model.Stats;
import view.SimulatorView;

/**
 * This class handles the command line output
 * @author Tony Ung
 *
 */


public class TextView implements SimulatorView {

	private Stats stats = Stats.getInstance();
	
	@Override
	public String output() {
		StringBuilder strBlr = new StringBuilder();
		strBlr.append("The fraction of customers served was: " + stats.calcFractionServed());
		strBlr.append("The number of customers served was: " + stats.calcNumCustomersServed());
		strBlr.append("The average waiting time was: " + stats.calcAvgWaitingTime());
		return strBlr.toString();

	}
	
	public void run(){
		System.out.println(output());
	}

}
