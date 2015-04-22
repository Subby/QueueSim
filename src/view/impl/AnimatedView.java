package view.impl;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.MultiQueueControlSystem;
import model.QueueControlSystem;
import model.Simulator;
import model.SingleQueueControlSystem;
import model.Stats;

import view.SimulatorView;
import view.components.LabelledSlider;

public class AnimatedView extends JPanel implements SimulatorView {
	
	private JLabel queuingSystemLabel;
	private JComboBox<String> queuingSystem;
    
    private JLabel numOfServersLabel;
	private JComboBox<Integer> numOfServers;
	
	private JLabel simulationTimeLabel;
	private JComboBox simulationTime;
	
	private JTextArea outputArea;
	
	private JButton okBtn;
	
    private LabelledSlider slider;
    
    private MultiQueueControlSystem MQCSInstanced;
    private SingleQueueControlSystem SQCSInstanced;
    private QueueControlSystem selectedQueueControlSystem;
    
    private Stats stats = Stats.getInstance();
    
    private int numOfServersSelected;
    private int simulationTimeSelected;
    
    private boolean firstRun = true;
    
	private static JFrame frame;
    
	public AnimatedView() {
		JPanel westPanel = new JPanel();
		JPanel eastPanel = new JPanel();
		JPanel southPanel = new JPanel();
		
		//initialise default values
        MQCSInstanced = MultiQueueControlSystem.getInstance();
        SQCSInstanced = SingleQueueControlSystem.getInstance();
        selectedQueueControlSystem = MQCSInstanced;
        outputArea = new JTextArea(25, 50);
        outputArea.setEditable(false);
        okBtn = new JButton ("OK");
        okBtn.addActionListener(new ActionListener() {
        	

			@Override
			public void actionPerformed(ActionEvent e) {
				if(firstRun){
					Simulator simulator = new Simulator();
					simulator.main(null);
					appenedToTextArea("Simulator Running");
					firstRun = false;
				}
				
				else{
					outputArea.append(output());
					firstRun = true;
				}
				
			}
        	
        });
        slider = new LabelledSlider("Simulation Length", 5, 5, 30, 5);
        String[] queuingSystemItems = {"Single Queue", "Multiple Queue "};
        String[] numOfServersItems = {"3", "4", "5", "6"};
        String[] simulationTimeItems = {"1440", "2880", "5760"};
        
        
		//queue
        queuingSystemLabel = new JLabel ("Queuing System");
        queuingSystem = new JComboBox<String>(queuingSystemItems);
        queuingSystem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String s = ((String) queuingSystem.getSelectedItem());
				switch(s) {
				case "Multiple Queue":
					selectedQueueControlSystem = MQCSInstanced;
					break;
				case "Single Queue":
					selectedQueueControlSystem = SQCSInstanced;
					break;
				default:
					System.out.println("Unexpected error!");
					break;
				}
				System.out.println(s);
				
			}
        });
        
		//servers
        
        numOfServersLabel = new JLabel ("Number of Servers");
        numOfServers = new JComboBox(numOfServersItems);
        numOfServers.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s = ((String) numOfServers.getSelectedItem());
				Simulator.NUM_OF_SERVERS = Integer.parseInt(s);
			}
        	
        });

		//simulation
        simulationTimeLabel = new JLabel ("Simulation Time (Seconds)");
        simulationTime = new JComboBox (simulationTimeItems);
        simulationTime.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String s = ((String) simulationTime.getSelectedItem());
				Simulator.LENGTH_OF_SIMULATION = Integer.parseInt(s);
			}
        	
        });
        
		setLayout(new BorderLayout());
		westPanel.setLayout(new BorderLayout());
		westPanel.add(queuingSystemLabel, BorderLayout.NORTH);
		westPanel.add(queuingSystem, BorderLayout.NORTH);
		westPanel.add(numOfServersLabel, BorderLayout.CENTER);
		westPanel.add(numOfServers, BorderLayout.CENTER);
		westPanel.add(simulationTimeLabel, BorderLayout.SOUTH);
		westPanel.add(simulationTime, BorderLayout.SOUTH);
		
		eastPanel.add(outputArea);
				
		southPanel.setLayout(new BorderLayout());
		southPanel.add(slider, BorderLayout.NORTH);
		southPanel.add(okBtn, BorderLayout.SOUTH);
		
		
		
		add(westPanel,BorderLayout.WEST);
		add(eastPanel,BorderLayout.EAST);
		add(southPanel,BorderLayout.SOUTH);
	}
	
	public static void main(String... args) {
		frame = new JFrame("Animated View");
		frame.getContentPane().add(new AnimatedView());
		frame.pack();
		frame.setVisible(true);
	}
	
    private void appenedToTextArea(String message) {
    	outputArea.append(message);
    	outputArea.append("\n");
    }
    
	@Override
	public String output() {
		StringBuilder strBlr = new StringBuilder();
		strBlr.append("The fraction of customers served was: " + stats.calcFractionServed());
		strBlr.append("\n");
		strBlr.append("The number of customers served was: " + stats.calcNumCustomersServed());
		strBlr.append("\n");
		strBlr.append("The average waiting time was: " + stats.calcAvgWaitingTime());
		strBlr.append("\n");
		return strBlr.toString();

	}
	
}
