import java.awt.Color;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import net.miginfocom.swing.MigLayout;


public class ClockFrame extends JFrame{
	
	private ClockPanel clock;	
	private boolean fullScreen = false;
	
	public ClockFrame() {
		this.clock = new ClockPanel();
		initComponents();
	}
	
	public ClockFrame(ClockPanel clock) {
		this.fullScreen = true;
		this.clock = new ClockPanel();
		this.clock.setClock(clock.getSecondsCount(), clock.getMinutesCount(), clock.getHoursCount(), clock.getDaysCount());
		this.clock.setDisplay(clock.getSecondsCount(), clock.getMinutesCount(), clock.getHoursCount(), clock.getDaysCount());
		initComponents();
	}
	
	private void initComponents() {
		//Settings
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		setBackground(Color.BLACK);
		
		setLayout(new MigLayout("", "[grow]", "[grow]"));
		add(clock, "grow");
		
		addKeyListener(new KeyListener() {
			public void keyReleased(KeyEvent evt) {
				if(evt.getKeyChar() == 'q')
					setFullScreen(false);
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	private void setFullScreen(boolean set) {
		this.fullScreen = set;
	}
	
	public ClockPanel getClock() {
		return this.clock;
	}
	
	public boolean getFullScreen(){
		return this.fullScreen;
	}
}
