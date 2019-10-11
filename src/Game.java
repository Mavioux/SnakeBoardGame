import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;

public class Game extends JPanel {

    int round;
    int roll;
    boolean flag;
    Board gameBoard;
    JLabel rollPanel;
    JLabel scorePanelOne;
    JLabel scorePanelTwo;
    JButton playerOne;
    JButton playerTwo;
    JButton beginGame;
    String playerChoice;
    Player mavioux;
    Player thomas;
    HeuristicPlayer kosmas;
    MinMaxPlayer vaggos;
    int[][] playersMove = new int[6][5];
    Integer[][] playersMovements = new Integer[6][5];
    int numOfPlayers = 2;
    Player[] players = new Player[numOfPlayers];
    ArrayList<Integer[]> path;
    HeuristicPlayer[] hPlayers = new HeuristicPlayer[numOfPlayers];
    MinMaxPlayer[] mmPlayers = new MinMaxPlayer[numOfPlayers];

    public Game(String playerChoice, Board board) {

        this.gameBoard = board;
        this.playerChoice = playerChoice;
        roll = 0;
        path = new ArrayList<>();


        playerOne = new JButton("Player 1, Roll the Dice");
        playerOne.setVisible(false);
        playerTwo = new JButton("Player 2, Roll the Dice");
        playerTwo.setVisible(false);
        beginGame = new JButton("Press to start the Game");

        setVisible(true);
        setLayout(new FlowLayout());

        add(playerOne, BorderLayout.AFTER_LAST_LINE);
        add(playerTwo, BorderLayout.EAST);
        add(beginGame, BorderLayout.AFTER_LAST_LINE);

        beginGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                beginGame.setVisible(false);
                playerOne.setVisible(true);
                playerTwo.setVisible(true);
                mavioux = new Player(1, "Player 1", 0, gameBoard);
                thomas = new Player(2, "Player 2", 0, gameBoard);
                kosmas = new HeuristicPlayer(2, "Player 2", 0, gameBoard, path);
                vaggos = new MinMaxPlayer(2, "Player 2", 0, gameBoard, path);
                players[0] = mavioux;
                players[1] = thomas;
                playerOne.setVisible(true);
                playerTwo.setVisible(false);
                //userGame();
            }
        });


        playerOne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(playerChoice == "User") {
                    if(rollPanel != null) {
                        remove(rollPanel);
                    }
                    if(scorePanelOne != null) {
                        remove(scorePanelOne);
                    }
                    System.out.println(players[0].getName() + "'s turn:");
                    scorePanelOne = new JLabel(players[0].getName()+ ": " + players[0].getScore() + " points");
                    add(scorePanelOne, FlowLayout.LEFT);
                    System.out.println("Starting tile: " + playersMove[0][0]);
                    roll = 1 + (int)(Math.random() * 6);
                    rollPanel = new JLabel("Roll: " + roll);
                    add(rollPanel, FlowLayout.LEFT);
                    System.out.println("You rolled: " + roll);
                    playersMove[0] = players[0].move(playersMove[0][0], roll);
                    System.out.println("Ending tile: " + playersMove[0][0]);
                    System.out.println();
                    players[0].setPlayerTileId(playersMove[0][0]);
                    playerOne.setVisible(false);
                    playerTwo.setVisible(true);
                    if(playersMove[0][0] >= gameBoard.getM() * gameBoard.getN()) {
                        if(players[0].getScore() > players[1].getScore()) {
                            add(new JLabel(players[0].getName() + " won!"));
                        }
                        else if(players[0].getScore() < players[1].getScore()) {
                            add(new JLabel(players[1].getName() + " won!"));
                        }
                        else {
                            add(new JLabel(players[0].getName() + " won!"));
                        }

                        rollPanel.setVisible(false);
                        playerOne.setVisible(false);
                        playerTwo.setVisible(false);
                    }
                }
            }
        });

        playerTwo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(playerChoice == "User") {
                    if(rollPanel != null) {
                        remove(rollPanel);
                    }
                    if(scorePanelTwo != null) {
                        remove(scorePanelTwo);
                    }
                    System.out.println(players[1].getName() + "'s turn:");
                    scorePanelTwo = new JLabel(players[1].getName()+ ": " + players[1].getScore() + " points");
                    add(scorePanelTwo);
                    scorePanelTwo.setLocation(0,50);
                    System.out.println("Starting tile: " + playersMove[1][0]);
                    roll = 1 + (int)(Math.random() * 6);
                    rollPanel = new JLabel("Roll: " + roll);
                    add(rollPanel, FlowLayout.RIGHT);
                    System.out.println("You rolled: " + roll);
                    playersMove[1] = players[1].move(playersMove[1][0], roll);
                    System.out.println("Ending tile: " + playersMove[1][0]);
                    System.out.println();
                    players[1].setPlayerTileId(playersMove[1][0]);
                    playerOne.setVisible(true);
                    playerTwo.setVisible(false);
                    if(playersMove[1][0] >= gameBoard.getM() * gameBoard.getN()) {
                        if(players[1].getScore() > players[0].getScore()) {
                            add(new JLabel(players[0].getName() + " won!"));
                        }
                        else if(players[1].getScore() < players[0].getScore()) {
                            add(new JLabel(players[0].getName() + " won!"));
                        }
                        else {
                            add(new JLabel(players[1].getName() + " won!"));
                        }

                        rollPanel.setVisible(false);
                        playerOne.setVisible(false);
                        playerTwo.setVisible(false);
                    }
                }
                else if(playerChoice == "Heuristic Player") {
                    if(rollPanel != null) {
                        remove(rollPanel);
                    }
                    if(scorePanelTwo != null) {
                        remove(scorePanelTwo);
                    }
                    System.out.println(kosmas.getName() + "'s turn:");
                    scorePanelTwo = new JLabel(kosmas.getName()+ ": " + kosmas.getScore() + " points");
                    add(scorePanelTwo);
                    scorePanelTwo.setLocation(0,50);
                    System.out.println("Starting tile: " + playersMove[2][0]);
                    roll = 1 + (int)(Math.random() * 6);
                    rollPanel = new JLabel("Roll: " + roll);
                    add(rollPanel, FlowLayout.RIGHT);
                    System.out.println("You rolled: " + roll);
                    playersMove[2] = kosmas.move(playersMove[2][0], roll);
                    System.out.println("Ending tile: " + playersMove[2][0]);
                    System.out.println();
                    kosmas.setPlayerTileId(playersMove[2][0]);
                    playerOne.setVisible(true);
                    playerTwo.setVisible(false);
                    if(playersMove[2][0] >= gameBoard.getM() * gameBoard.getN()) {
                        if(kosmas.getScore() > players[0].getScore()) {
                            add(new JLabel(kosmas.getName() + " won!"));
                        }
                        else if(kosmas.getScore() < players[0].getScore()) {
                            add(new JLabel(players[0].getName() + " won!"));
                        }
                        else {
                            add(new JLabel(kosmas.getName() + " won!"));
                        }

                        rollPanel.setVisible(false);
                        playerOne.setVisible(false);
                        playerTwo.setVisible(false);
                    }
                }
                else if(playerChoice == "Min-Max") {
                    if(rollPanel != null) {
                        remove(rollPanel);
                    }
                    if(scorePanelTwo != null) {
                        remove(scorePanelTwo);
                    }
                    System.out.println(vaggos.getName() + "'s turn:");
                    scorePanelTwo = new JLabel(vaggos.getName()+ ": " + vaggos.getScore() + " points");
                    add(scorePanelTwo);
                    scorePanelTwo.setLocation(0,50);
                    System.out.println("Starting tile: " + playersMove[3][0]);
                    roll = 1 + (int)(Math.random() * 6);
                    rollPanel = new JLabel("Roll: " + roll);
                    add(rollPanel, FlowLayout.RIGHT);
                    System.out.println("You rolled: " + roll);
                    playersMove[3] = vaggos.move(playersMove[3][0], roll);
                    System.out.println("Ending tile: " + playersMove[3][0]);
                    System.out.println();
                    kosmas.setPlayerTileId(playersMove[3][0]);
                    playerOne.setVisible(true);
                    playerTwo.setVisible(false);
                    if(playersMove[3][0] >= gameBoard.getM() * gameBoard.getN()) {
                        if(vaggos.getScore() > players[0].getScore()) {
                            add(new JLabel(vaggos.getName() + " won!"));
                        }
                        else if(vaggos.getScore() < players[0].getScore()) {
                            add(new JLabel(players[0].getName() + " won!"));
                        }
                        else {
                            add(new JLabel(vaggos.getName() + " won!"));
                        }

                        rollPanel.setVisible(false);
                        playerOne.setVisible(false);
                        playerTwo.setVisible(false);
                    }
                }
            }
        });

    }

    // Getters, setters
    public void setRound(int round) {
        this.round = round;
    }

    public int getRound() {
        return round;
    }

    Map<Integer,Integer> setTurns(ArrayList<Player> players){
        Map<Integer, Integer> playerTurns = new TreeMap<Integer, Integer>();
        int rolls[] = new int[players.size()];
        boolean flag;
        do {
            flag =  false;
            for(int i = 0; i < rolls.length; i++) {
                rolls[i] = 1 + (int)(Math.random() * 6);
                for(int j = i - 1; j > -1; j--) {
                    if(rolls[i] == rolls[j]) {
                        flag = true;	//if one dice of a player matches another player's dice do while is repeated;
                    }
                }
                //System.out.println("Key: " + rolls[i] + " Value: " + (i+1));
                playerTurns.put(rolls[i], i + 1);	 //key = roll of the player with id i + 1 , value = id of player (i + 1)
                if(flag) {
                    playerTurns.clear();
                    break;
                }
            }
        } while(flag);
        return playerTurns;
    }



}

