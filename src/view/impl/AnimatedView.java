package view.impl;

//Import application packages
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import model.*;
import view.*;
import view.components.LabelledSlider;

/**
 * The GUI for the simulation.
 * @author Tony Ung
 * @author Denver Fernandes
 * @author Ben Lawton
 */

public class AnimatedView extends JPanel implements SimulatorView {
	
	private static final long serialVersionUID = -34020607785964557L;
	
	private static JFrame frame;
	
	private JLabel queuingSystemLabel;
	private JComboBox<String> queueingSystemChoices;
    
    private JLabel numOfServersLabel;
	private JComboBox<Integer> numOfServers;
		
	private final JTextArea outputArea;
	private JScrollPane outputAreaScroll;
		
    private LabelledSlider simulationLengthSlider;
    
    private Simulator simulator;
    private Stats stats;
    
    private boolean simulatorRunning = false;
    
    public static void main(String[] args) {
    	final JFrame frame = new JFrame("Animated View");
		frame.getContentPane().add(new AnimatedView());
    	frame.setSize(800, 275);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setVisible(true);
    	frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				exitApp();
			}
		});
	}

	public AnimatedView() {
		
		// Create the components 
		simulator = new Simulator();
        stats = simulator.getQueueSystem().getStats();
		JButton runBtn = new JButton();
		JButton cancelBtn = new JButton("Cancel");
		simulationLengthSlider = new LabelledSlider("Simulation Lengeth (hours) : ", 1, 1, 10, 1);
		String[] queuingSystemItems = {"Single Queue", "Multiple Queue"};
        queueingSystemChoices = new JComboBox<String>(queuingSystemItems);
        outputArea = new JTextArea(13, 23);
        outputAreaScroll = new JScrollPane();
        Integer[] numOfServersItems = {3, 4, 5, 6};
        numOfServersLabel = new JLabel ("Number of Servers");
        numOfServers = new JComboBox<Integer>(numOfServersItems);
        queuingSystemLabel = new JLabel("Queueing System");
        
        // Set the properties of the components 
        runBtn.setText("Run");
        runBtn.setToolTipText("Run simulation");
        cancelBtn.setText("Cancel");
        cancelBtn.setToolTipText("Cancel simulation");
        simulationLengthSlider.setToolTipText("Set the length of the simulation (in hours)");
        queueingSystemChoices.setToolTipText("Select the queue system type");
        outputArea.setEditable(false);
        outputAreaScroll.setViewportView(outputArea);
        numOfServers.setSize(new Dimension(20, 20));
        
        // Create containers to hold the components        
        JPanel westPanel = new JPanel();
        JPanel centerPanel = new JPanel();
        JPanel southPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JPanel sliderPanel = new JPanel();
        JPanel comboPanel = new JPanel();
        JPanel serverPanel = new JPanel();
        JPanel queueTypePanel = new JPanel();
        
        // Specify LayoutManagers
        setLayout(new BorderLayout());
        sliderPanel.setLayout(new BorderLayout());
        comboPanel.setLayout(new BorderLayout());
        westPanel.setLayout(new BorderLayout());
        serverPanel.setLayout(new BorderLayout());
        queueTypePanel.setLayout(new BorderLayout());
        buttonPanel.setLayout(new BorderLayout());
        southPanel.setLayout(new BorderLayout());
        
        // Add components to the containers
        sliderPanel.add(simulationLengthSlider, BorderLayout.NORTH);
        comboPanel.add(serverPanel, BorderLayout.NORTH);
        comboPanel.add(queueTypePanel, BorderLayout.SOUTH);
        westPanel.add(comboPanel, BorderLayout.NORTH);
		westPanel.add(sliderPanel, BorderLayout.SOUTH);
		serverPanel.add(numOfServersLabel, BorderLayout.NORTH);
		serverPanel.add(numOfServers, BorderLayout.SOUTH);
		queueTypePanel.add(queuingSystemLabel, BorderLayout.NORTH);
		queueTypePanel.add(queueingSystemChoices, BorderLayout.SOUTH);
		centerPanel.add(outputAreaScroll);
		buttonPanel.add(runBtn, BorderLayout.WEST);
		buttonPanel.add(cancelBtn, BorderLayout.EAST);
		southPanel.add(buttonPanel, BorderLayout.SOUTH);
		add(westPanel, BorderLayout.WEST);
		add(centerPanel, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);
		
		// Arrange event handlers in the GUI
		runBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				runSimulation();
			}
		});
		
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!simulatorRunning) {
				appenedToTextArea("Please run the simulator before cancelling it");
				return;
				}
				simulator.setShouldRun(false);
				simulatorRunning = false;
				appenedToTextArea("Simulator stopped");
				outputArea.append(outputSimulationStats());
			}
		});

		
		numOfServers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setServers();
			}
        });
	}
		
	// Helper methods to aid event handler functionality 
	
	private void runSimulation() {
		if (queueingSystemChoices.getSelectedItem().toString().equals("Single Queue")) {
			simulator.setSingleQueueControlSystem();
			stats = simulator.getQueueSystem().getStats();
		}
		else if (queueingSystemChoices.getSelectedItem().toString().equals("Multiple Queue")) {
			simulator.setMultiQueueControlSystem();
			stats = simulator.getQueueSystem().getStats();
		}
		simulator.setShouldRun(true);
		simulator.setLengthOfSimulation((int)simulationLengthSlider.getValue());
		simulator.run();
		appenedToTextArea("Simulator running");
		simulatorRunning = true;
	}
	
	private static void exitApp() {
		int confirm = JOptionPane.showOptionDialog(frame,
				"Are you sure you want to exit the simulator?",
				"Exit Confirmation", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (confirm == JOptionPane.OK_OPTION) {
			System.exit(0);
		}
	}
	
	private void setServers() {
		int s = ((Integer) numOfServers.getSelectedItem());
		simulator.setNumberOfServers(s);
	}
	
	/**
	 * Appends a message to the output text area.
	 * @param message the message to output.
	 */
    private void appenedToTextArea(String message) {
    	outputArea.append(message);
    	outputArea.append("\n");
    }
    
	@Override
	public String outputSimulationStats() {
		return stats.toString();
	}
}