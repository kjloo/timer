package main;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import net.miginfocom.swing.MigLayout;

public class SettingsPanel extends JPanel {

	// Create Constants
	private int MAX_SECONDS = SecondsTextField.getMax();
	private int MAX_MINUTES = MinutesTextField.getMax();
	private int MAX_HOURS = HoursTextField.getMax();

	// Create Time Holders
	private int daysCount;
	private int hoursCount;
	private int minutesCount;
	private int secondsCount;

	// Pass in Window
	private TimerWindow window;

	// Create GUI Objects
	JLabel daysCountLabel = new JLabel(String.valueOf(0));
	JLabel hoursCountLabel = new JLabel(String.valueOf(0));
	JLabel minutesCountLabel = new JLabel(String.valueOf(0));
	JLabel secondsCountLabel = new JLabel(String.valueOf(0));
	DaysTextField days = new DaysTextField(0);
	HoursTextField hours = new HoursTextField(0);
	MinutesTextField minutes = new MinutesTextField(0);
	SecondsTextField seconds = new SecondsTextField(0);
	JLabel daysLabel;
	JLabel hoursLabel;
	JLabel minutesLabel;
	JLabel secondsLabel;

	public SettingsPanel(TimerWindow window) {
		// Setup Variable
		this.window = window;
		initComponents();
	}

	private void initComponents() {
		// Settings
		setOpaque(false);

		// Setup GUI Objects;
		daysLabel = new JLabel("Days");
		hoursLabel = new JLabel("Hours");
		minutesLabel = new JLabel("Minutes");
		secondsLabel = new JLabel("Seconds");

		daysLabel.setForeground(Color.YELLOW);
		hoursLabel.setForeground(Color.YELLOW);
		minutesLabel.setForeground(Color.YELLOW);
		secondsLabel.setForeground(Color.YELLOW);

		daysCountLabel.setForeground(Color.YELLOW);
		hoursCountLabel.setForeground(Color.YELLOW);
		minutesCountLabel.setForeground(Color.YELLOW);
		secondsCountLabel.setForeground(Color.YELLOW);

		// Check Data Input
		// Check Data Input
		days.addKeyListener(new KeyListener() {
			public void keyReleased(KeyEvent evt) {
				checkFields(evt);
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
		hours.addKeyListener(new KeyListener() {
			public void keyReleased(KeyEvent evt) {
				checkFields(evt);
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
		minutes.addKeyListener(new KeyListener() {
			public void keyReleased(KeyEvent evt) {
				checkFields(evt);
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
		seconds.addKeyListener(new KeyListener() {
			public void keyReleased(KeyEvent evt) {
				checkFields(evt);
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

		// Set Layout
		setLayout(new MigLayout("", "[20] 20 [20] 20 [20] 20 [20]", "[] 20 []"));
		add(daysLabel, "grow, center");
		add(hoursLabel, "grow, center");
		add(minutesLabel, "grow, center");
		add(secondsLabel, "grow, center, wrap");
		add(daysCountLabel, "grow, center");
		add(hoursCountLabel, "grow, center");
		add(minutesCountLabel, "grow, center");
		add(secondsCountLabel, "grow, center, wrap");
		add(days, "grow, center");
		add(hours, "grow, center");
		add(minutes, "grow, center");
		add(seconds, "grow, center, wrap");
	}

	public void setText() {
		daysCountLabel.setText(String.valueOf(daysCount));
		hoursCountLabel.setText(String.valueOf(hoursCount));
		minutesCountLabel.setText(String.valueOf(minutesCount));
		secondsCountLabel.setText(String.valueOf(secondsCount));
	}

	public void countDown() {
		secondsCount--;
		if (secondsCount < 0) {
			minutesCount--;
			secondsCount = MAX_SECONDS;
		}
		if (minutesCount < 0) {
			hoursCount--;
			minutesCount = MAX_MINUTES;
		}
		if (hoursCount < 0) {
			daysCount--;
			hoursCount = MAX_HOURS;
		}
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				setText();
			}
		});
	}

	// Action Function
	private void checkFields(KeyEvent evt) {
		this.window
				.checkFields((!(days.getText().isEmpty()
						|| hours.getText().isEmpty()
						|| minutes.getText().isEmpty() || seconds.getText()
						.isEmpty())
						&& days.valid()
						&& hours.valid()
						&& minutes.valid() && seconds.valid()));
	}

	public void setTime() {
		daysCount = Integer.valueOf(days.getText());
		hoursCount = Integer.valueOf(hours.getText());
		minutesCount = Integer.valueOf(minutes.getText());
		secondsCount = Integer.valueOf(seconds.getText());
		setText();
	}

	public void changeEnabled(Boolean enable) {
		days.setEditable(enable);
		hours.setEditable(enable);
		minutes.setEditable(enable);
		seconds.setEditable(enable);
	}

	public int getSeconds() {
		return this.secondsCount;
	}

	public int getMinutes() {
		return this.minutesCount;
	}

	public int getHours() {
		return this.hoursCount;
	}

	public int getDays() {
		return this.daysCount;
	}

}
