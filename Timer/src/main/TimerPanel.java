package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

abstract class TimerPanel extends JPanel{
	
	// Size Variables
	protected int width = 21;
	protected int height = 7;
	protected int spacing = 6 * width;
	
	// Color Constants
	protected int R;
	protected int G;
	protected int B;
	
	public TimerPanel() {
		initComponents();
	}
	
	private void initComponents() {
		// Settings
		setOpaque(false);
		colorChange(Color.RED);	
	}
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g.setColor(new Color(R, G, B));
		createFeatures();
		drawFeatures(g2);
	}
	
	public void colorChange(Color color) {
		this.R = color.getRed();
		this.G = color.getGreen();
		this.B = color.getBlue();
	}

	protected abstract void createFeatures();
	
	protected abstract void drawFeatures(Graphics2D g2);


}
