package view.components;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.text.*;


/**
 * Enhanced version of standard Swing JSlider widget
 *
 * @author Ian T. Nabney
 * @version 20/04/2006
 */


public class LabelledSlider extends JComponent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1978073598544126546L;
	private String labelString;
	private JLabel label;
	private JSlider slider;
	private int scale;
	
	private NumberFormat nf;
	
	/**
	 * Creates a <code>LabelledSlider</code>.
	 *
	 * @param text a {@link java.lang.String} that names the slider
	 * @param value a double that gives the initial value
	 * @param min an int that is the minimum value for the slider
	 * @param max an int that is the maximum value for the slider
	 * @param scale an int that multiplies the value to give the value the slider displays
	 */
	public LabelledSlider(String text, double value, int min, int max, 
			int scale) {
		if (value*scale < min || value*scale > max)
			throw new IllegalArgumentException("Value not in range for LabelledSlider.");
		this.setDoubleBuffered(true);
		label = new JLabel(text + value);
		labelString = new String(text);
		this.scale = scale;
		slider = new JSlider(min, max, (int)(value*scale));
		nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);
		
		// Set slider properties
		slider.setPaintTicks(true);
		slider.addChangeListener(new SliderListener());
		// Put in 5 ticks
		slider.setMajorTickSpacing((max-min)/5);
		
		this.setLayout(new BorderLayout());
		this.add(label, BorderLayout.NORTH);
		this.add(slider, BorderLayout.SOUTH);
		int margin = 10;
		this.setBorder(new CompoundBorder(
				new EmptyBorder(margin, margin, margin, margin),
				new EtchedBorder()));
		
	}
	
	/**
	 * Sets the major tick spacing for the LabelledSlider
	 *
	 * @param spacing an int that gives the new spacing value
	 */
	public void setMajorTickSpacing(int spacing) {
		slider.setMajorTickSpacing(spacing);
		repaint();
	}
	
	/**
	 * Returns scaled slider value
	 *
	 * @return the double value.
	 */
	public double getValue() {
		return ((double)slider.getValue())/scale;
	}
	
	/**
	 * Inner class to update the slider location and the text label
	 */
	private class SliderListener implements ChangeListener {
		
		public void stateChanged(ChangeEvent e) {
			if (!slider.getValueIsAdjusting()) {
				int number = slider.getValue();
				label.setText(labelString + nf.format(((double)number/scale)));
			}
		}
	}
}
