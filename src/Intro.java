import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Intro extends JPanel{
	
	private Button user;
	private Button heuristic;
	private Button minMax;
	private JLabel label;
	private String opponentChoice;


	public Intro() {
		super();


	
		setBackground(new Color(107, 22, 41));
		setSize(600,500);
		setLayout(null);


		
		user = new Button("User");
		user.setLocation(0, 250);
		user.setSize(200, 50);

		heuristic = new Button("Heuristic Player");
		heuristic.setLocation(200, 250);
		heuristic.setSize(200, 50);

		minMax = new Button("Min-Max");
		minMax.setLocation(400, 250);
		minMax.setSize(200, 50);

		label = new JLabel("Choose your Opponent");
		label.setLocation(240, 50);
		label.setSize(400, 100);
		label.setForeground(new Color(249, 169, 61));




		add(label);
		add(user);
		add(heuristic);
		add(minMax);


/*

		GridBagConstraints c = new GridBagConstraints();



		c.gridy = 1;
		add(user, c);
		c.gridy = 2;
		add(heuristic, c);
		c.gridy = 3;
		add(minMax, c);

*/
		user.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				opponentChoice = user.getLabel();
				setVisible(false);

			}
		});

		heuristic.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(heuristic.getLabel());
				opponentChoice = heuristic.getLabel();
				setVisible(false);
			}
		});

		minMax.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				opponentChoice = minMax.getLabel();
				setVisible(false);
			}
		});
	}

	public String getOpponentChoice() {
		return opponentChoice;
	}
}
