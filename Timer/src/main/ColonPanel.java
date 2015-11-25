package main;

import java.awt.Graphics2D;

public class ColonPanel extends TimerPanel{

	// Color Constants
	private int R;
	private int G;
	private int B;
	
	public ColonPanel() {
		
	}
	
	protected void createFeatures() {
		// TODO Auto-generated method stub
		
	}

	protected void drawFeatures(Graphics2D g2) {
		g2.fillRect(0, 2 * spacing + 3 * height, height, height);
		g2.fillRect(0, 2 * spacing + 5 * height, height, height);
	}
	
}
