package main;
import javax.swing.SwingUtilities;

public class TimerMain {

	/**
	 * @param args
	 */
	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new TimerWindow();
			}
		});
	}

}
