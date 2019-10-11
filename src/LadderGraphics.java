import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LadderGraphics extends JPanel{
	
	Board board;
	int width;
	int height;
	ImageIcon ladderIcon;
	
	public LadderGraphics(Board board, int width, int height) {
		this.board = board;
		this.width = width;
		this.height = height;
		ladderIcon = new ImageIcon(".\\textures\\ladder.jpg");
				
		//setPreferredSize(new Dimension(width / 10, height / 10));		
		setLayout(null);
		//setOpaque(false);	
		
		/*int x = Math.abs(board.ladderDownInde([0][0] - board.ladderUpIndex[0][0]);
		int y = Math.abs(board.ladderDownIndex[0][1] - board.ladderUpIndex[0][1]);
		
		double angle;
		
		if(board.ladderDownIndex[0][0] - board.ladderUpIndex[0][0] > 0) {
			angle = Math.tan(y/x);
		}
		else if(board.ladderDownIndex[0][1] - board.ladderUpIndex[0][1] < 0) {
			angle = Math.PI - Math.tan(y/x);
		}
		else {
			angle = Math.PI / 2 ;
		}*/
	    
		
		
		//repaint(board.ladderDownIndex[0][0] * 10, board.ladderDownIndex[0][1] * 10, board.ladderUpIndex[0][0] * 10, board.ladderDownIndex[0][1] * 10);
	}
	
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g); 
        g.drawLine(width/2, height/2, width, height);
    }

}


