package main;
import java.awt.Color;

import javax.swing.JTextField;

/* Definition of a Minutes Text Field
 * Allows program to bound inputs into
 * the text field.
 */

public class HoursTextField extends JTextField {

	// Create Constants
	private static int MAX_HOURS = 23;
	private static int MIN_HOURS = 0;

	public HoursTextField(int hours) {
		setText(String.valueOf(hours));
	}

	public void validate() {
		if (!getText().isEmpty() && getText().matches("\\d+|-\\d+")) {
			if (Integer.valueOf(getText()) > MAX_HOURS
					|| Integer.valueOf(getText()) < MIN_HOURS)
				setForeground(Color.RED);
			else
				setForeground(Color.BLACK);
		}
	}

	public boolean valid() {
		if (getText().isEmpty() || !getText().matches("\\d+|-\\d+"))
			return false;
		return !(Integer.valueOf(getText()) > MAX_HOURS || Integer
				.valueOf(getText()) < MIN_HOURS);
	}

	public static int getMax() {
		return MAX_HOURS;
	}
}
