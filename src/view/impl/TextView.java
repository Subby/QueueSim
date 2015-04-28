package view.impl;

import java.util.Scanner;

import model.MultiQueueControlSystem;
import model.QueueControlSystem;
import model.Simulator;
import model.SingleQueueControlSystem;
import model.Stats;
import view.SimulatorView;

/**
 * This class handles the command line output
 * @author Tony Ung
 *
 */


public class TextView implements SimulatorView {

	private static QueueControlSystem selectedQueueControlSystem;
	private static int numOfservers;
	private static int lenOfSimulation;
	private static Simulator simulator;
	private static Stats stats;
	private static boolean finished = false;
	
	public static void main(String... args) {
		simulator = new Simulator();
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to the queue simulator");
        while(!finished) {
        	System.out.print("> ");
            parseInput(input);
        }
        input.close();
        System.exit(0);
	}
	
	@Override
	public String output() {
		return stats.toString();
	}
	
	/**
	 * Parses the {@link Scanner} input.
	 * @param input the input given by the user
	 */
	private static void parseInput(Scanner input){
        System.out.println("What type of queue system would you like (sq - Single Queue System, mq - Multi Queue System, default - sq)?");
        System.out.print("> ");
        
        switch(input.next()) {
        case "sq":
        	selectedQueueControlSystem = new SingleQueueControlSystem();
        	break;
        case "mq":
        	selectedQueueControlSystem = new MultiQueueControlSystem();
        	break;
        default:
        	System.out.println("Not a valid queue system, using the default queue system.");
        	break;
        }
        
        System.out.println("How many servers would you like?");
        System.out.print("> ");
        numOfservers = input.nextInt();
        System.out.println("How long would you like the simulation to last (in hours)?");
        System.out.print("> ");
        lenOfSimulation = input.nextInt();
        
		stats = selectedQueueControlSystem.getStats();
		simulator.setQueueSystem(selectedQueueControlSystem);
		simulator.setNumberOfServers(numOfservers);
		simulator.setLengthOfSimulation(lenOfSimulation);
        simulator.main(null);
        System.out.println(stats.toString());
        
        System.out.println("Would you like the statistics to be saved to a text file (y - yes, default - no)?");
        switch(input.next()) {
        case "y":
        	System.out.println("Printing to file...");
        	stats.outputToFile();
        	System.out.println("Sucessfully prinited to file.");
        	break;
        }
        System.out.println("Thank you for using this simulator");
        finished = true;
	}

}
