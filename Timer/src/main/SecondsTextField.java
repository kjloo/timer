package main;
import java.awt.Color;

import javax.swing.JTextField;

/* Definition of a Seconds Text Field
 * Allows program to bound inputs into
 * the text field.
 */

public class SecondsTextField extends JTextField {

	// Create Constants
	private static int MAX_SECONDS = 59;
	private static int MIN_SECONDS = 0;

	public SecondsTextField(int seconds) {
		setText(String.valueOf(seconds));
	}

	public void validate() {
		if (!getText().isEmpty() && getText().matches("\\d+|-\\d+")) {
			if (Integer.valueOf(getText()) > MAX_SECONDS
					|| Integer.valueOf(getText()) < MIN_SECONDS)
				setForeground(Color.RED);
			else
				setForeground(Color.BLACK);
		}
	}

	public boolean valid() {
		if (getText().isEmpty() || !getText().matches("\\d+|-\\d+"))
			return false;
		return !(Integer.valueOf(getText()) > MAX_SECONDS || Integer
				.valueOf(getText()) < MIN_SECONDS);
	}

	public static int getMax() {
		return MAX_SECONDS;
	}

}
