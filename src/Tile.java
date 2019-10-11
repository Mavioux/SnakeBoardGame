import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;




public class Tile extends JPanel{
	
	private JLabel counter;
	
	public Tile(int width, int height, int rows, int cols, String str) {
		super();
		
		counter = new JLabel(str, JLabel.CENTER);
		
		int ratio = rows/cols;
		
		setFont(getFont().deriveFont(20f));
		
		setPreferredSize(new Dimension(width/cols/ratio, height/rows));
		setBackground(new Color((int)(Math.random() * 256), (int)(Math.random() * 256), (int)(Math.random() * 256)));
		setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
		
		setLayout(new GridBagLayout());
		add(counter);
		
	}

}
