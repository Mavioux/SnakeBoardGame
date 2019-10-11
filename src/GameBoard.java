import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class GameBoard extends JLayeredPane {
	
	private int numOfTiles;
	private int N;
	private int M;
	private int width;
	private int height;
	private JLabel[][] tiles;
	
	public GameBoard(int N, int M) {
		//setBackground(new Color(107, 106, 104));
		
		
		this.N = N;
		this.M = M;
		numOfTiles = N * M;
		width = 900;
		height = 900;
		
		setLayout(null);
		
		
		Board board = new Board(N, M, 6, 6, 6);
		board.createBoard();
		board.createElementBoard();
		
		add(board, 0);
	

		Insets insets = getInsets();
		Dimension size = board.getPreferredSize();
		board.setBounds(25 + insets.left, 5 + insets.top,
		             size.width, size.height);
		
		LadderGraphics ladderGraphics = new LadderGraphics(board, N, M);
		
		add(ladderGraphics, 1);
		
		insets = getInsets();
		size = ladderGraphics.getPreferredSize();
		ladderGraphics.setBounds(100 + insets.left, 50 + insets.top,
		             size.width, size.height);
		
		ladderGraphics.repaint(100, 100, 200, 200);
		
		
		
		
		
		
	}

}
