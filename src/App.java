import javax.swing.*;
import java.util.ArrayList;


public class App {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainFrame mainFrame = new MainFrame();
			}
		});
		
		

	}

}
