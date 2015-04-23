package view.impl;
/**
 * The main class the runs the simulator.
 * @author Tony Ung
 * @author Denver Fernandes
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
		
	private JTextArea outputArea;
	private JScrollPane outputAreaScroll;
	
	private JButton runBtn;
	private JButton cancelBtn;
	
    private LabelledSlider probabilitySlider;
    private LabelledSlider simulationLengthSlider;
    
    //Static values so that a new AnimatedView object is not created by the simulator.
    public static double PROBABILITYSLIDERVALUE;
    public static double SIMULATIONLENGTHSLIDER;
    
    private MultiQueueControlSystem MQCSInstanced;
    private SingleQueueControlSystem SQCSInstanced;
    public static QueueControlSystem selectedQueueControlSystem;
    
    private Stats stats;
    
    private boolean simulatorRunning = false;
    
	private static JFrame frame;
    
	public AnimatedView() {
		JPanel westPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		JPanel southPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		JPanel sliderPanel = new JPanel();
		JPanel comboPanel = new JPanel();
		JPanel serverPanel = new JPanel();
		JPanel queueTypePanel = new JPanel();
		
		//initialise default values
        MQCSInstanced = MultiQueueControlSystem.getInstance();
        SQCSInstanced = SingleQueueControlSystem.getInstance();
        selectedQueueControlSystem = MQCSInstanced;
        stats = selectedQueueControlSystem.getStats();
        probabilitySlider = new LabelledSlider("Probability: ", 5, 5, 30, 5);
        simulationLengthSlider = new LabelledSlider("Simulation Length (hours) : ", 10, 5, 60, 5);
        PROBABILITYSLIDERVALUE = probabilitySlider.getValue();
        SIMULATIONLENGTHSLIDER = simulationLengthSlider.getValue();
        outputArea = new JTextArea(13, 23);
        outputArea.setEditable(false);
        //outputArea.setEditable(false);
        outputAreaScroll = new JScrollPane();
        outputAreaScroll.setViewportView(outputArea);
        
        runBtn = new JButton("Run");
        runBtn.addActionListener(new ActionListener() {
        	

        	@Override
			public void actionPerformed(ActionEvent e) {
				Simulator simulator = new Simulator();
				simulator.main(null);
				appenedToTextArea("Simulator running");
				simulatorRunning = true;
				
			}
         });
        
        cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(!simulatorRunning) {
					appenedToTextArea("Please run the simulator before cancelling it");
					return;
				}
				Simulator.SHOULD_RUN = true;
				simulatorRunning = false;
				appenedToTextArea("Simulator stopped");
				outputArea.append(output());
			}
        });
        

        String[] queuingSystemItems = {"Single Queue", "Multiple Queue"};
        Integer[] numOfServersItems = {3, 4, 5, 6};        
        
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
					Simulator.SELECTEDQUEUESYSTEM = selectedQueueControlSystem;
					break;
				case "Single Queue":
					selectedQueueControlSystem = SQCSInstanced;
					Simulator.SELECTEDQUEUESYSTEM = selectedQueueControlSystem;
					break;
				default:
					System.out.println("Unexpected error!");
					break;
				}
			}
        });
        
		//servers
        
        numOfServersLabel = new JLabel ("Number of Servers");
        numOfServers = new JComboBox<Integer>(numOfServersItems);
        numOfServers.setSize(new Dimension(20, 20));
        numOfServers.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int s = ((Integer) numOfServers.getSelectedItem());
				Simulator.NUM_OF_SERVERS = s;
			}
        	
        });
        
        
        
		setLayout(new BorderLayout());
		westPanel.setLayout(new BorderLayout());
		sliderPanel.setLayout(new BorderLayout());
		comboPanel.setLayout(new BorderLayout());
		serverPanel.setLayout(new BorderLayout());
		queueTypePanel.setLayout(new BorderLayout());
		comboPanel.add(serverPanel, BorderLayout.NORTH);
		comboPanel.add(queueTypePanel, BorderLayout.SOUTH);
		westPanel.add(comboPanel, BorderLayout.NORTH);
		sliderPanel.add(simulationLengthSlider, BorderLayout.NORTH);
		sliderPanel.add(probabilitySlider, BorderLayout.SOUTH);
		westPanel.add(sliderPanel, BorderLayout.SOUTH);
		serverPanel.add(numOfServersLabel, BorderLayout.NORTH);
		serverPanel.add(numOfServers, BorderLayout.SOUTH);
		queueTypePanel.add(queuingSystemLabel, BorderLayout.NORTH);
		queueTypePanel.add(queuingSystem, BorderLayout.SOUTH);
		
		
		centerPanel.add(outputAreaScroll);
		
		southPanel.setLayout(new BorderLayout());
		buttonPanel.setLayout(new BorderLayout());
		buttonPanel.add(runBtn, BorderLayout.WEST);
		buttonPanel.add(cancelBtn, BorderLayout.EAST);
		southPanel.add(buttonPanel, BorderLayout.SOUTH);

		add(westPanel,BorderLayout.WEST);
		add(centerPanel,BorderLayout.CENTER);
		add(southPanel,BorderLayout.SOUTH);
	}
	
	public static void main(String... args) {
		frame = new JFrame("Animated View");
		frame.getContentPane().add(new AnimatedView());
		frame.setSize(500, 275);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			  public void windowClosing(WindowEvent e) {
				  int confirm = JOptionPane.showOptionDialog(frame,
		                    "Are you sure you want to exit this program?",
		                    "Exit Confirmation", JOptionPane.YES_NO_OPTION,
		                    JOptionPane.QUESTION_MESSAGE, null, null, null);
		            if (confirm == JOptionPane.OK_OPTION) {
		                System.exit(0);
		            }
			  }
			});
	}
	
    private void appenedToTextArea(String message) {
    	outputArea.append(message);
    	outputArea.append("\n");
    }

	@Override
	public String output() {
		StringBuilder strBlr = new StringBuilder();
		strBlr.append("Average waiting time: " + stats.getAvgWaitingTime());
		strBlr.append("\n");
		strBlr.append("Number of customers served: " + stats.getNumCustomersLeftNotServed());
		strBlr.append("\n");
		strBlr.append("Fraction of customers served: " + stats.getFractionServed());
		strBlr.append("\n");
		strBlr.append("Number of customers who left without being served: " + stats.getNumCustomersNotServed());
		strBlr.append("\n");
		return strBlr.toString();
	}
	
}