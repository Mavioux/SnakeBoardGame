import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePane extends JLayeredPane {

    private int width;
    private int height;
    private int N;
    private int M;
    private String opponentChoice;
    private JButton boardGenerator;
    private JLabel credits;
    public Board board;
    private JButton beginButton;
    Game game;
    //private JButton updateBoard;

    public GamePane(int width, int height, int N, int M) {
        super();
        this.width = width;
        this.height = height;
        this.N = N;
        this.M = M;



        setLayout(new BorderLayout());

        Intro intro = new Intro();
        intro.setLocation(150,200);
        add(intro, 2);
        opponentChoice = intro.getOpponentChoice();




        ////////// Initialize Gameboard with tiles ////////////
        //GameBoard gameBoard = new GameBoard(N, M);

        ////////////// Initialize Elements on top of the GameBoard //////////

        Board board = new Board(N, M, 3, 3, 6);
        board.createBoard();
        board.createElementBoard();
        this.board = board;

        add(board, BorderLayout.CENTER, 1);

        boardGenerator = new JButton("Generate Board");
        boardGenerator.setSize(300,50);
        boardGenerator.setPreferredSize(new Dimension(300,50));
        boardGenerator.setMaximumSize(new Dimension(300, 50));
        boardGenerator.setBackground(new Color(0xA0B1FF));
        boardGenerator.setLocation(150, 0);

        credits = new JLabel("Snake Board Game by: Boufikos Thomas and Marios Pakas ©");
        credits.setSize(new Dimension(500, 100));
        credits.setBackground(new Color(0xE8F2C7));

        beginButton = new JButton("Begin Game");
        beginButton.setSize(250,50);
        beginButton.setPreferredSize(new Dimension(250,50));
        beginButton.setMaximumSize(new Dimension(250, 50));
        //beginButton.setVisible(false);

        //updateBoard = new JButton("Update Board");




        add(boardGenerator, BorderLayout.NORTH);
        //add(updateBoard, BorderLayout.EAST);
        add(credits, BorderLayout.AFTER_LAST_LINE);

        boardGenerator.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.setVisible(true);
                boardGenerator.setVisible(false);
                add(beginButton, BorderLayout.NORTH);
            }
        });

        beginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                beginButton.setVisible(false);
                opponentChoice = intro.getOpponentChoice();
                System.out.println(opponentChoice);
                beginGame();
                add(game, BorderLayout.WEST);
                System.out.println("Game added to GamePane");
            }
        });

    }

    public void beginGame() {
        game = new Game(opponentChoice, board);
    }

    public Board getBoard() {
        return board;
    }

    public void addElements() {
        remove(board);
        remove(credits);
        remove(game);

        for(int i = 0; i < 2; i++) {
            String color;

            GridBagConstraints gc = new GridBagConstraints();

            int k = N - 1 -(int)((game.players[i].getPlayerTileId()-1) / M);
            int j;

            if(N % 2 == 0) {
                if(k % 2 != 0) {
                    j = game.players[i].getPlayerTileId() -(M*N - (k+1)*M) - 1;
                }
                else {
                    j = M - (game.players[i].getPlayerTileId() - (N*M - (k+1)*M));
                }
            }
            else {
                if(k % 2 != 0) {
                    j = M - (game.players[i].getPlayerTileId() - (N * M - (k + 1) * M));
                }
                else {
                    j = game.players[i].getPlayerTileId() -(M*N - (k+1)*M) - 1;
                }
            }


            gc.gridx = j;
            gc.gridy = k;

            if(i == 0)
                color = "red";
            else
                color = "blue";

            ImageIcon temp = new ImageIcon(".\\textures\\" + color + "-player.png");
            JLabel tempLabel = new JLabel("Player " + (i+1));

            board.add(tempLabel , gc, 10);

            System.out.println("Player " + (i+1) + " is in position " + game.players[i].getPlayerTileId());
            System.out.println("gridx " + gc.gridx + " gridy " + gc.gridy);
        }

        add(board, BorderLayout.CENTER, 1);
        add(credits, BorderLayout.AFTER_LAST_LINE);
        add(game, BorderLayout.WEST);



        validate();
        repaint();
    }


}
