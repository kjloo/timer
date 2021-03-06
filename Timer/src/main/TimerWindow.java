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
import javax.swing.Timer;

import net.miginfocom.swing.MigLayout;

public class TimerWindow extends JFrame {

	// Create Constants
	private int SECOND = 1000;

	// Create Timer
	private Timer timer;
	
	// Create Alarm
	private Alarm alarm;
	private String filename = "Siren.wav";

	// Create Clock
	private ClockPanel clock = new ClockPanel();
	private ClockFrame frame;

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

		// Setup Timer
		ActionListener taskPerformer = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				countDown();		
			}
	  	};
		this.timer = new Timer(SECOND, taskPerformer);
		this.timer.setInitialDelay(0);
		
		// Setup Alarm
		this.alarm = new Alarm(filename);

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
		settings.countDown();
		if (settings.getDays() + settings.getHours()
				+ settings.getMinutes() + settings.getSeconds() == 0) {
			resetButtons();

			this.timer.stop();
			playSound();
		}
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				updateDisplay(clock);
				if (frame != null && frame.getClock() != null) {
					updateDisplay(frame.getClock());
				}
			}
		});
	}

	private void playSound() {
		dismissButton.setEnabled(true);
		alarm.play();
	}

	private void stopAlarm(ActionEvent evt) {
		resetButtons();
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

	private void resetButtons() {
		setButton.setEnabled(true);
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
		stopAlarm(evt);
		settings.setTime();
		startButton.setEnabled(settings.getDays() + settings.getHours()
				+ settings.getMinutes() + settings.getSeconds() != 0);
		setDisplay(this.clock);
	}

	private void startClock(ActionEvent evt) {
		changeEnabled(false);
		setPlay(this.clock, true);
		// Start Countdown
		this.timer.start();
	}

	private void stopClock(ActionEvent evt) {
		this.timer.stop();
		changeEnabled(true);
		setPlay(this.clock, false);
	}

	private void resetClock(ActionEvent evt) {
		stopClock(evt);
		settings.setTime();
		setDisplay(this.clock);
	}

	private void fullScreen(ActionEvent evt) {
		new Thread(new Runnable() {
			public void run() {
				frame = new ClockFrame(clock);
				Screen s = new Screen();
				try {
					s.setFullScreen(dm, frame);
					setPlay(clock, false);
					setPlay(frame.getClock(), true);
					fullScreenButton.setEnabled(false);
					
					try {
						while (frame.getFullScreen()) {
							// wait
							Thread.sleep(SECOND/2);
						}

					} catch (Exception ex) {
						ex.printStackTrace();
					}
				} finally {
					// cleanup unused clock frame
					fullScreenButton.setEnabled(true);
					s.restoreScreen();
					setPlay(clock, true);
					setPlay(frame.getClock(), false);
					
					
					frame.dispose();
					frame = null;
					s = null;
				}
			}
		}).start();

	}
}
