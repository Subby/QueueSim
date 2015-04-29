package view.impl;
/**
 * The GUI for the simulation.
 * @author Tony Ung
 * @author Denver Fernandes
 * @author Ben Lawton
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.Simulator;
import model.Stats;
import view.SimulatorView;
import view.components.LabelledSlider;


public class AnimatedView extends JPanel implements SimulatorView {
	
	private static final long serialVersionUID = -34020607785964557L;
	
	private JLabel queuingSystemLabel;
	private JComboBox<String> queueingSystemChoices;
    
    private JLabel numOfServersLabel;
	private JComboBox<Integer> numOfServers;
		
	private JTextArea outputArea;
	private JScrollPane outputAreaScroll;
	
	private JButton runBtn;
	private JButton cancelBtn;
	
    private LabelledSlider simulationLengthSlider;
    
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
				  int confirm = JOptionPane.showOptionDialog(frame,
		                    "Are you sure you want to exit the simulator?",
		                    "Exit Confirmation", JOptionPane.YES_NO_OPTION,
		                    JOptionPane.QUESTION_MESSAGE, null, null, null);
		            if (confirm == JOptionPane.OK_OPTION) {
		                System.exit(0);
		            }
			  }
			});
	}

	public AnimatedView() {
		
		JPanel westPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		JPanel southPanel = new JPanel();
		
		JPanel buttonPanel = new JPanel();
		JPanel sliderPanel = new JPanel();
		JPanel comboPanel = new JPanel();
		JPanel serverPanel = new JPanel();
		JPanel queueTypePanel = new JPanel();
				
		final Simulator simulator = new Simulator();
		simulator.setSingleQueueControlSystem();
        
        stats = simulator.getQueueSystem().getStats();
        
        simulationLengthSlider = new LabelledSlider("Simulation Length (hours) : ", 1, 1, 10, 1);
        
        outputArea = new JTextArea(13, 23);
        outputArea.setEditable(false);
        outputAreaScroll = new JScrollPane();
        outputAreaScroll.setViewportView(outputArea);
        
        //run button
        runBtn = new JButton("Run");
        runBtn.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
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
				simulator.main(null);
				appenedToTextArea("Simulator running");
				simulatorRunning = true;
				//TODO implement error-handling for if JComboBox isn't working 
			}
         });
        
        //cancel button
        cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(new ActionListener() {
			@Override
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
        
		//queue system select combo box
        String[] queuingSystemItems = {"Single Queue", "Multiple Queue"};
        queuingSystemLabel = new JLabel ("Queuing System");
        queueingSystemChoices = new JComboBox<String>(queuingSystemItems);
        
        
		//number of servers select combo box
        Integer[] numOfServersItems = {3, 4, 5, 6};
        numOfServersLabel = new JLabel ("Number of Servers");
        numOfServers = new JComboBox<Integer>(numOfServersItems);
        numOfServers.setSize(new Dimension(20, 20));
        numOfServers.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int s = ((Integer) numOfServers.getSelectedItem());
				simulator.setNumberOfServers(s);
			}
        	
        });
        
		setLayout(new BorderLayout());
		
		sliderPanel.setLayout(new BorderLayout());
		sliderPanel.add(simulationLengthSlider, BorderLayout.NORTH);
		
		comboPanel.setLayout(new BorderLayout());
		comboPanel.add(serverPanel, BorderLayout.NORTH);
		comboPanel.add(queueTypePanel, BorderLayout.SOUTH);
				
		westPanel.setLayout(new BorderLayout());
		westPanel.add(comboPanel, BorderLayout.NORTH);
		westPanel.add(sliderPanel, BorderLayout.SOUTH);
		
		serverPanel.setLayout(new BorderLayout());
		serverPanel.add(numOfServersLabel, BorderLayout.NORTH);
		serverPanel.add(numOfServers, BorderLayout.SOUTH);
		
		queueTypePanel.setLayout(new BorderLayout());
		queueTypePanel.add(queuingSystemLabel, BorderLayout.NORTH);
		queueTypePanel.add(queueingSystemChoices, BorderLayout.SOUTH);
		
		centerPanel.add(outputAreaScroll);
		
		buttonPanel.setLayout(new BorderLayout());
		buttonPanel.add(runBtn, BorderLayout.WEST);
		buttonPanel.add(cancelBtn, BorderLayout.EAST);
		southPanel.setLayout(new BorderLayout());
		southPanel.add(buttonPanel, BorderLayout.SOUTH);

		add(westPanel, BorderLayout.WEST);
		add(centerPanel, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);
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