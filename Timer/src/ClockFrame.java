import java.awt.Color;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import net.miginfocom.swing.MigLayout;


public class ClockFrame extends JFrame{
	
	private ClockPanel clock;	
	
	public ClockFrame() {
		this.clock = new ClockPanel();
		initComponents();
	}
	
	public ClockFrame(ClockPanel clock) {
		this.clock = clock;
		initComponents();
	}
	
	private void initComponents() {
		//Settings
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		setBackground(Color.BLACK);
		
		setLayout(new MigLayout("", "[grow]", "[grow]"));
		add(clock, "grow");
	}

}
