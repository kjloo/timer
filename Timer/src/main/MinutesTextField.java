package main;
import java.awt.Color;

import javax.swing.JTextField;

/* Definition of a Minutes Text Field
 * Allows program to bound inputs into
 * the text field.
 */

public class MinutesTextField extends JTextField {

	// Create Constants
	private static int MAX_MINUTES = 59;
	private static int MIN_MINUTES = 0;

	public MinutesTextField(int minutes) {
		setText(String.valueOf(minutes));
	}

	public void validate() {
		if (!getText().isEmpty() && getText().matches("\\d+|-\\d+")) {
			if (Integer.valueOf(getText()) > MAX_MINUTES
					|| Integer.valueOf(getText()) < MIN_MINUTES)
				setForeground(Color.RED);
			else
				setForeground(Color.BLACK);
		}
	}

	public boolean valid() {
		if (getText().isEmpty() || !getText().matches("\\d+|-\\d+"))
			return false;
		return !(Integer.valueOf(getText()) > MAX_MINUTES || Integer
				.valueOf(getText()) < MIN_MINUTES);
	}

	public static int getMax() {
		return MAX_MINUTES;
	}

}
