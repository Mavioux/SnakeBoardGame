import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class MainFrame extends JFrame {
	
	private int width;
	private int height;
	private int N;
	private int M;
	GamePane gamePane;
	JButton updateBoard;
	
	
	public MainFrame() {
		super("Snake Board Game");
		width = 1500;
		height = 1500;
		N = 10;
		M = 10;

		GridBagConstraints gc = new GridBagConstraints();
		gc.gridy= 0;
		gc.gridx= 0;

		//add(gamePane, BorderLayout.CENTER);
		//setLayout(new BorderLayout());
		//setBackground(new Color(107, 6, 9));
		//setSize(width, height);

		setLayout(new GridBagLayout());
		setSize(new Dimension(width, height));
		setMinimumSize(new Dimension(950, 950));
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		gamePane = new GamePane(width, height, N, M);
		add(gamePane, gc);

		updateBoard = new JButton("Update Board");
		add(updateBoard);


		updateBoard.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				gamePane.addElements();

				validate();
				repaint();


			}
		});
	}





}
