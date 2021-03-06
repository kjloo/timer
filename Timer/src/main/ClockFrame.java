package main;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

import net.miginfocom.swing.MigLayout;

public class ClockFrame extends JFrame {

	private ClockPanel clock;
	private JButton exitButton;
	private boolean fullScreen = false;

	public ClockFrame(ClockPanel clock) {
		this.fullScreen = true;
		this.clock = new ClockPanel();
		this.clock.setClock(clock.getSecondsCount(), clock.getMinutesCount(),
				clock.getHoursCount(), clock.getDaysCount());
		this.clock.setDisplay();
		initComponents();
	}

	private void initComponents() {
		// Settings
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		getContentPane().setBackground(Color.BLACK);

		this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "dispose");
		this.getRootPane().getActionMap().put("dispose", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				setFullScreen(false);
			}
		});

		exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				setFullScreen(false);
			}
		});

		setLayout(new MigLayout("", "[grow]", "[grow] [10]"));
		add(this.clock, "grow, center, span");
		add(exitButton, "right, bottom, span");

	}

	private void setFullScreen(boolean set) {
		this.fullScreen = set;
	}

	public ClockPanel getClock() {
		return this.clock;
	}

	public boolean getFullScreen() {
		return this.fullScreen;
	}
}
