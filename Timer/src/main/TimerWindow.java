package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import net.miginfocom.swing.MigLayout;

public class TimerWindow extends JFrame {

	// Create Constants
	private int SECOND = 1000;

	// Create Alarm
	private Alarm alarm;
	private String filename = "Siren.wav";

	// Create Clock
	private ClockPanel clock = new ClockPanel();

	// Create Display variables
	private DisplayMode dm;
	private int width = 800;
	private int height = 600;

	// Create GUI Objects
	private SettingsPanel settings;
	private JButton setButton;
	private JButton startButton;
	private JButton stopButton;
	private JButton dismissButton;
	private JButton resetButton;
	private JButton closeButton;
	private JButton fullScreenButton;

	public TimerWindow() {
		// Grab Screen Size
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.width = (int) screenSize.getWidth();
		this.height = (int) screenSize.getHeight();

		// Setup Environment Variables
		this.settings = new SettingsPanel(this);
		this.dm = new DisplayMode(width, height, 16,
				DisplayMode.REFRESH_RATE_UNKNOWN);
		initComponents();
	}

	private void initComponents() {
		// Settings
		setVisible(true);
		setTitle("Segment Timer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(800, 825));
		setSize(800, 825);
		getContentPane().setBackground(Color.BLACK);
		setResizable(true);

		// Setup Alarm
		alarm = new Alarm(filename);

		// Setup GUI Objects;
		// Create Panel
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		// Create Buttons
		setButton = new JButton("Set");
		setButton.setEnabled(false);
		setButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				setTime(evt);
			}
		});

		startButton = new JButton("Start");
		startButton.setBackground(Color.GREEN);
		startButton.setEnabled(false);
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				startClock(evt);
			}
		});

		stopButton = new JButton("Stop");
		stopButton.setBackground(Color.RED);
		stopButton.setEnabled(false);
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				stopClock(evt);
			}
		});

		dismissButton = new JButton("Dismiss");
		dismissButton.setEnabled(false);
		dismissButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				stopAlarm(evt);
			}
		});

		resetButton = new JButton("Reset");
		resetButton.setEnabled(false);
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				resetClock(evt);
			}
		});

		closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				dispose();
				System.exit(0);
			}
		});

		fullScreenButton = new JButton("FullScreen");
		fullScreenButton.setEnabled(false);
		fullScreenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				fullScreen(evt);
			}
		});

		// Set Layout
		setLayout(new MigLayout("", "[grow] []", "[grow] [] []"));
		add(clock, "grow, wrap");
		add(settings, "wrap");
		panel.setLayout(new MigLayout("wrap 3"));
		panel.add(setButton, "grow");
		panel.add(startButton, "grow");
		panel.add(stopButton, "grow, wrap");
		panel.add(dismissButton, "grow");
		panel.add(resetButton, "grow");
		panel.add(closeButton, "wrap");
		add(panel, "grow");
		add(fullScreenButton, "right");
	}

	public void setDisplay(ClockPanel clock) {
		clock.setClock(settings.getSeconds(), settings.getMinutes(),
				settings.getHours(), settings.getDays());
		clock.setDisplay();
	}

	public void updateDisplay(ClockPanel clock) {
		clock.updateDisplay(settings.getSeconds(), settings.getMinutes(),
				settings.getHours(), settings.getDays());
		clock.setClock(settings.getSeconds(), settings.getMinutes(),
				settings.getHours(), settings.getDays());
	}

	private void countDown() {
		while (stopButton.isEnabled()) {
			settings.countDown();
			if (settings.getDays() + settings.getHours()
					+ settings.getMinutes() + settings.getSeconds() == 0) {
				stopButton.setEnabled(false);

				playSound();
			}
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					updateDisplay(clock);
				}
			});
			try {
				Thread.sleep(SECOND);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void playSound() {
		dismissButton.setEnabled(true);
		alarm.play();
	}

	private void stopAlarm(ActionEvent evt) {
		dismissButton.setEnabled(false);
		disableButtons();
		alarm.stop();
	}

	private void changeEnabled(Boolean enable) {
		setButton.setEnabled(enable);
		startButton.setEnabled(enable);
		stopButton.setEnabled(!enable);
		resetButton.setEnabled(!enable);
		settings.changeEnabled(enable);
		closeButton.setEnabled(enable);
		fullScreenButton.setEnabled(!enable);
	}

	private void disableButtons() {
		setButton.setEnabled(false);
		startButton.setEnabled(false);
		stopButton.setEnabled(false);
		resetButton.setEnabled(false);
		dismissButton.setEnabled(false);
		settings.changeEnabled(true);
		fullScreenButton.setEnabled(false);
	}

	public void checkFields(boolean valid) {
		setButton.setEnabled(valid);
	}

	public void setPlay(ClockPanel clock, boolean play) {
		clock.setPlay(play);
	}

	// Action Function
	public void setTime(ActionEvent evt) {
		settings.setTime();
		startButton.setEnabled(settings.getDays() + settings.getHours()
				+ settings.getMinutes() + settings.getSeconds() != 0);
		setDisplay(this.clock);
	}

	private void startClock(ActionEvent evt) {
		changeEnabled(false);
		setPlay(this.clock, true);
		// Start Countdown
		new Thread(new Runnable() {
			public void run() {
				countDown();
			}
		}).start();
	}

	private void stopClock(ActionEvent evt) {
		changeEnabled(true);
		setPlay(this.clock, false);
	}

	private void resetClock(ActionEvent evt) {
		changeEnabled(true);
		setPlay(this.clock, false);
		settings.setTime();
		setDisplay(this.clock);
	}

	private void fullScreen(ActionEvent evt) {
		new Thread(new Runnable() {
			public void run() {
				ClockFrame frame = new ClockFrame(clock);
				Screen s = new Screen();
				try {
					s.setFullScreen(dm, frame);
					setPlay(frame.getClock(), true);
					try {
						while (frame.getFullScreen()) {
							updateDisplay(frame.getClock());
							Thread.sleep(SECOND);
						}

					} catch (Exception ex) {
						ex.printStackTrace();
					}
				} finally {
					s.restoreScreen();
				}
			}
		}).start();

	}
}
