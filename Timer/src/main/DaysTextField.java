package main;
import java.awt.Color;

import javax.swing.JTextField;

/* Definition of a Days Text Field
 * Allows program to bound inputs into
 * the text field.
 */

public class DaysTextField extends JTextField {

	// Create Constants
	private static int MAX_DAYS = 999;
	private static int MIN_DAYS = 0;

	public DaysTextField(int days) {
		setText(String.valueOf(days));
	}

	public void validate() {
		if (!getText().isEmpty() && getText().matches("\\d+|-\\d+")) {
			if (Integer.valueOf(getText()) > MAX_DAYS
					|| Integer.valueOf(getText()) < MIN_DAYS)
				setForeground(Color.RED);
			else
				setForeground(Color.BLACK);
		}
	}

	public boolean valid() {
		if (getText().isEmpty() || !getText().matches("\\d+|-\\d+"))
			return false;
		return !(Integer.valueOf(getText()) > MAX_DAYS || Integer
				.valueOf(getText()) < MIN_DAYS);
	}

	public static int getMax() {
		return MAX_DAYS;
	}

}
