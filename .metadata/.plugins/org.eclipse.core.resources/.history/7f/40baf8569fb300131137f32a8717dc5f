import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Window;

import javax.swing.JFrame;


public class Screen{

	//Create Graphics Device
	private GraphicsDevice vc;
	
	public Screen() {
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		vc = env.getDefaultScreenDevice();
	}
	
	public void setFullScreen(DisplayMode dm, JFrame window) {
		window.setUndecorated(true);
		window.setVisible(true);
		window.setResizable(false);
		vc.setFullScreenWindow(window);
		
//		if(dm != null && vc.isDisplayChangeSupported()) {
//			try {
//				vc.setDisplayMode(dm);
//			}catch(Exception ex) {
//				ex.printStackTrace();
//			}
//		}
		
	}
	
	public Window getWindow() {
		return vc.getFullScreenWindow();
	}
	
	public void restoreScreen() {
		Window w = vc.getFullScreenWindow();
		if(w != null)
			w.dispose();
			
		vc.setFullScreenWindow(null);
	}
}
