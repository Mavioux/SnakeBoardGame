import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.awt.Graphics;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Board extends JLayeredPane{
	int N, M;
	int[][] tiles;
	Snake[] snakes;
	Ladder[] ladders;
	Apple[] apples;
	int[][] snakeHeadIndex;
	int[][] ladderUpIndex;
	int[][] snakeTailIndex;
	int[][] ladderDownIndex;
	private int width;
	private int height;
	private JLabel[][] graphicTiles;
	private int ratio;

	public Board() {
		N = 0;
		M = 0;
		tiles = new int[N][M];
		snakes = new Snake[0];
		ladders = new Ladder[0];
		apples = new Apple[0];
		snakeHeadIndex = new int[3][2];
		ladderUpIndex = new int[3][2];
		snakeTailIndex = new int[3][2];
		ladderDownIndex = new int[3][2];
		width = 900;
		height = 900;
		graphicTiles = new JLabel[N][M];
		//ratio = N/M;
	}

	public Board(int n, int m, int numOfSnakes, int numOfLadders, int numOfApples) {
		this.N = n;
		this.M= m;
		tiles = new int[n][m];
		snakes = new Snake[numOfSnakes];
		ladders = new Ladder[numOfLadders];
		apples = new Apple[numOfApples];
		snakeHeadIndex = new int[numOfSnakes][2];
		ladderUpIndex = new int[numOfLadders][2];
		snakeTailIndex = new int[numOfLadders][2];
		ladderDownIndex = new int[numOfLadders][2];
		width = 900;
		height = 900;
		graphicTiles = new JLabel[N][M];
		ratio = N/M;

		setLayout(new GridBagLayout());
		setVisible(false);


	}

	public Board(Board templateBoard) {
		this.N = templateBoard.N;
		this.M = templateBoard.M;
		tiles = new int[N][M];
		snakes = new Snake[templateBoard.snakes.length];
		ladders = new Ladder[templateBoard.ladders.length];
		apples = new Apple[templateBoard.apples.length];
		snakeHeadIndex = new int[templateBoard.snakes.length][2];
		ladderUpIndex = new int[templateBoard.snakes.length][2];
		snakeTailIndex = new int[templateBoard.snakes.length][2];
		ladderDownIndex = new int[templateBoard.snakes.length][2];

		tiles = templateBoard.tiles.clone();
		snakes = templateBoard.snakes.clone();
		ladders = templateBoard.ladders.clone();
		apples = templateBoard.apples.clone();

		width = 900;
		height = 900;

		this.ratio = templateBoard.ratio;
		setLayout(new GridBagLayout());

		graphicTiles = new JLabel[N][M];
	}

	public void setN(int a) {
		N = a;
	}

	public void setM(int a) {
		M = a;
	}

	public int getN() {

		return N;
	}

	public int getM() {

		return M;
	}

	public boolean setTiles(int[][] a) {
		if(a.length != tiles.length || a[0].length != tiles[0].length) {
			System.out.println("Error! Arrays have different size!");
			return false;
		}
		tiles = a;
		return true;
	}

	public boolean setSnakes(Snake[] a) {
		if(a.length != snakes.length) {
			System.out.println("Error! Arrays have different size!");
			return false;
		}
		snakes = a;
		return true;
	}

	public boolean setLadders(Ladder[] a) {
		if(a.length != ladders.length) {
			System.out.println("Error! Arrays have different size!");
			return false;
		}
		ladders = a;
		return true;
	}

	public boolean setApples(Apple[] a) {
		if(a.length != apples.length) {
			System.out.println("Error! Arrays have different size!");
			return false;
		}
		apples = a;
		return true;
	}

	public int[][] getTiles(){

		return tiles;
	}

	public Snake[] getSnakes(){

		return snakes;
	}

	public Ladder[] getLadders(){

		return ladders;
	}

	public Apple[] getApples(){

		return apples;
	}

	public void createBoard() {

		//Tiles Initialization
		setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();

		//gc.weightx = 0.01;
		//gc.weighty = 0.01;

		int counter = 1;
		boolean flag = true;
		for(int i = tiles.length - 1; i > -1; i--) {
			if(flag) {
				for(int j = 0; j < tiles[i].length; j++){
					flag = false;
					gc.gridx = j;
					gc.gridy = i;
					add(new Tile(width, height, N, M, Integer.toString(counter)), gc, 1);
					counter++;
				}
			}
			else{
				for(int j = tiles[i].length - 1; j > -1; j--) {
					flag = true;
					gc.gridx = j;
					gc.gridy = i;
					add(new Tile(width, height, N, M, Integer.toString(counter)), gc, 1);
					counter++;
				}
			}
		}



		//Snake Initialization
		for(int i = 0; i < snakes.length;  i++) {
			int headId;
			int tailId;
			boolean snakeFlag;
			do {
				snakeFlag = false;
				do {
					tailId = 1 + (int)(Math.random() * ((N * M) - 1));
				} while(tailId >= N*M);
				headId = tailId + 1 + (int)(Math.random() * (N*M - tailId));
				//headId = tailId + random Number from 1 to max size(N*M) minus the tailId that we added to affirm that head>tail
				for(int j = i - 1; j > -1; j--) {
					if(snakes[j].getTailId() == tailId || snakes[j].getHeadId() == tailId || snakes[j].getTailId() == headId || snakes[j].getHeadId() == headId) {
						snakeFlag = true;
					}
				}
			} while(snakeFlag);
			snakes[i] = new Snake(i, headId, tailId);
		}

		//Ladder Initialization
		for(int i = 0; i < ladders.length;  i++) {
			int up;
			int down;
			do {
				flag = false;
				do {
					down = 1 + (int)(Math.random() * ((N * M) -1));
				} while(down >= N*M);
				up = 1 + (int)(down + Math.random() * ((N*M) - down));

				for(int j = i - 1; j > -1; j--) {
					if(ladders[j].getUpStepId() == up || ladders[j].getUpStepId() == down || ladders[j].getDownStepId() == up || ladders[j].getDownStepId() == down)
						flag = true;
				}
			} while(flag);

			ladders[i] = new Ladder(i, up, down, false);
		}

		//Initialize a random value for applepos and then loop through each snakeHead.
		//If applepos==snakeHead -> check = true -> do while loops again giving a different applepos

		for(int i = 0; i < apples.length; i++) {
			int applePos;
			boolean check = false;
			do {
				applePos = 1 + (int)(Math.random() * N * M);
				for(int j = 0; j < snakes.length; j++) {
					if(snakes[j].getHeadId() == applePos)
						check = true;
				}

				for(int k = i - 1; k > -1; k--) {
					if(apples[k].getAppleTileId() == applePos) {
						check = true;
					}
				}

				String clr;
				if((int)(Math.random() * 2) % 2 == 0) {
					clr = "red";
				}
				else {
					clr = "black";
				}

				int applePoints = 1 + (int)(Math.random() * 20);


				apples[i] = new Apple(i, applePos, clr, applePoints);
			} while(check);
			check = false;
		}
	}

	public void createElementBoard() {
		String[][] elementBoardSnakes = new String[N][M];
		String[][] elementBoardLadders = new String[N][M];
		String[][] elementBoardApples = new String[N][M];

		//Finding the i,j with the help of the tiles 2-D array in order to position the snake heads
		for(int i = 0; i < snakes.length; i++) {
			if(snakes[i] == null)
				continue;
			int k, j;

			k = N - 1 -(int)((snakes[i].getHeadId()-1) / M);

			if(N % 2 == 0) {
				if(k % 2 != 0) {
					j = snakes[i].getHeadId() -(M*N - (k+1)*M) - 1;
				}
				else {
					j = M - (snakes[i].getHeadId() - (N*M - (k+1)*M));
				}
			}

			else {
				if(k % 2 != 0) {
					j = M - (snakes[i].getHeadId() - (N*M - (k+1)*M));
				}
				else {
					j = snakes[i].getHeadId() - (M*N - (k+1)*M) - 1;
				}
			}

			elementBoardSnakes[k][j] = "SH" + snakes[i].getSnakeId();
			snakeHeadIndex[i][0] = k;
			snakeHeadIndex[i][1] = j;

			GridBagConstraints gc = new GridBagConstraints();
			gc.gridx = j;
			gc.gridy = k;
			String path = ".\\textures\\snake-face-resized-" + Integer.toString(i) + ".jpg";
			ImageIcon icon = new ImageIcon(path);
			JLabel label = new JLabel(icon);
			label.setPreferredSize(new Dimension(width/M/ratio, height/N));
			add(label, gc, 3);
		}


		//Finding the i,j with the help of the tiles 2-D array in order to position the snake tails
		for(int i = 0; i < snakes.length; i++) {
			if(snakes[i] == null)
				continue;
			int k, j;

			k = N - 1 -(int)((snakes[i].getTailId()-1) / M);

			if(N % 2 == 0) {
				if(k % 2 != 0) {
					j = snakes[i].getTailId() -(M*N - (k+1)*M) - 1;
				}
				else {
					j = M - (snakes[i].getTailId() - (N*M - (k+1)*M));
				}
			}

			else {
				if(k % 2 != 0) {
					j = M - (snakes[i].getTailId() - (N*M - (k+1)*M));
				}
				else {
					j = snakes[i].getTailId() -(M*N - (k+1)*M) - 1;
				}
			}

			elementBoardSnakes[k][j] = "ST" + snakes[i].getSnakeId();
			snakeTailIndex[i][0] = k;
			snakeTailIndex[i][1] = j;

			GridBagConstraints gc = new GridBagConstraints();
			gc.gridx = j;
			gc.gridy = k;
			String path = ".\\textures\\snake-tail-" + Integer.toString(i) + ".png";
			ImageIcon icon = new ImageIcon(path);
			JLabel label = new JLabel(icon);
			label.setPreferredSize(new Dimension(width/M/ratio, height/N));
			add(label, gc, 3);
		}


		//Finding the i,j with the help of the tiles 2-D array in order to position the upLadderIds
		for(int i = 0; i < ladders.length; i++) {
			if(ladders[i] == null)
				continue;
			int k, j;

			k = N - 1 -(int)((ladders[i].getUpStepId()-1) / M);

			if(N % 2 == 0) {
				if(k % 2 != 0) {
					j = ladders[i].getUpStepId() -(M*N - (k+1)*M) - 1;
				}
				else {
					j = M - (ladders[i].getUpStepId() - (N*M - (k+1)*M));
				}
			}

			else {
				if(k % 2 != 0) {
					j = M - (ladders[i].getUpStepId() - (N*M - (k+1)*M));
				}
				else {
					j = ladders[i].getUpStepId() -(M*N - (k+1)*M) - 1;
				}
			}
			elementBoardLadders[k][j] = "LU" + ladders[i].getLadderId();
			ladderUpIndex[i][0] = k;
			ladderUpIndex[i][1] = j;

			GridBagConstraints gc = new GridBagConstraints();
			gc.gridx = j;
			gc.gridy = k;
			String path = ".\\textures\\ladder-up-resized-" + Integer.toString(i) + ".png";
			ImageIcon icon = new ImageIcon(path);
			JLabel label = new JLabel(icon);
			label.setPreferredSize(new Dimension(width/M/ratio, height/N));
			add(label, gc, 4);
		}

		//Finding the i,j with the help of the tiles 2-D array in order to position the downLadderIds
		for(int i = 0; i < ladders.length; i++) {
			if(ladders[i] == null)
				continue;
			int k, j;

			k = N - 1 -(int)((ladders[i].getDownStepId()-1) / M);

			if(N % 2 == 0) {
				if(k % 2 != 0) {
					j = ladders[i].getDownStepId() -(M*N - (k+1)*M) - 1;
				}
				else {
					j = M - (ladders[i].getDownStepId() - (N*M - (k+1)*M));
				}
			}

			else {
				if(k % 2 != 0) {
					j = M - (ladders[i].getDownStepId() - (N*M - (k+1)*M));
				}
				else {
					j = ladders[i].getDownStepId() -(M*N - (k+1)*M) - 1;
				}
			}
			elementBoardLadders[k][j] = "LD" + ladders[i].getLadderId();
			ladderDownIndex[i][0] = k;
			ladderDownIndex[i][1] = j;

			GridBagConstraints gc = new GridBagConstraints();
			gc.gridx = j;
			gc.gridy = k;
			String path = ".\\textures\\ladder-down-resized-" + Integer.toString(i) + ".png";
			ImageIcon icon = new ImageIcon(path);
			JLabel label = new JLabel(icon);
			label.setPreferredSize(new Dimension(width/M/ratio, height/N));
			add(label, gc, 4);
		}

		//Finding the i,j with the help of the tiles 2-D array in order to position the Apples
		for(int i = 0; i < apples.length; i++) {
			if(apples[i] == null)
				continue;
			int k, j;

			k = N - 1 -(int)((apples[i].getAppleTileId()-1) / M);

			if(N % 2 == 0) {
				if(k % 2 != 0) {
					j = apples[i].getAppleTileId() -(M*N - (k+1)*M) - 1;
				}
				else {
					j = M - (apples[i].getAppleTileId() - (N*M - (k+1)*M));
				}
			}

			else {
				if(k % 2 != 0) {
					j = M - (apples[i].getAppleTileId() - (N*M - (k+1)*M));
				}
				else {
					j = apples[i].getAppleTileId() -(M*N - (k+1)*M) - 1;
				}
			}

			char appleCharZero = apples[i].getColor().charAt(0);
			String appleUpperCaseCharZero = String.valueOf(appleCharZero);
			System.out.println(k + "," + j);
			elementBoardApples[k][j] = "A" + appleUpperCaseCharZero.toUpperCase() + apples[i].getAppleId();
			GridBagConstraints gc = new GridBagConstraints();
			gc.gridx = j;
			gc.gridy = k;
			ImageIcon icon = null;
			if(apples[i].getColor() == "red") {
				icon = new ImageIcon(".\\textures\\apple-resized.png");
			}
			else if(apples[i].getColor() == "black") {
				icon = new ImageIcon(".\\textures\\apple-resized-black.png");
			}
			else {
				System.out.println("Error in apple initialization");
			}
			JLabel label = new JLabel(icon);
			label.setPreferredSize(new Dimension(width/M/ratio, height/N));
			add(label, gc, 5);
		}


		//Printing elementBoard for Snakes, Ladders and Apples
		for(int i = 0; i < elementBoardSnakes.length; i++) {
			for(int j = 0; j < elementBoardSnakes[i].length; j++) {
				if(elementBoardSnakes[i][j] == null)
					elementBoardSnakes[i][j] = " _ ";
				System.out.print(elementBoardSnakes[i][j] + " ");
			}
			System.out.println();
		}

		System.out.println();
		System.out.println();

		for(int i = 0; i < elementBoardLadders.length; i++) {
			for(int j = 0; j < elementBoardLadders[i].length; j++) {
				if(elementBoardLadders[i][j] == null)
					elementBoardLadders[i][j] = " _ ";
				System.out.print(elementBoardLadders[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();

		for(int i = 0; i < elementBoardApples.length; i++) {
			for(int j = 0; j < elementBoardApples[i].length; j++) {
				if(elementBoardApples[i][j] == null)
					elementBoardApples[i][j] = " _ ";
				System.out.print(elementBoardApples[i][j] + " ");
			}
			System.out.println();
		}





	}

	public void updatePlayerPos(Player player, String color) {
		GridBagConstraints gc = new GridBagConstraints();

		int k = N - 1 -(int)((player.getPlayerTileId()-1) / M);
		int j;

		if(N % 2 == 0) {
			if(k % 2 != 0) {
				j = player.getPlayerTileId() -(M*N - (k+1)*M) - 1;
			}
			else {
				j = M - (player.getPlayerTileId() - (N*M - (k+1)*M));
			}
		}
		else {
			if(k % 2 != 0) {
				j = M - (player.getPlayerTileId() - (N*M - (k+1)*M));
			}
			else {
				j = player.getPlayerTileId() -(M*N - (k+1)*M) - 1;
			}
		}


		gc.gridx = j;
		gc.gridy = k;

		ImageIcon temp = new ImageIcon(".\\textures\\" + color + "-player.png");
		JLabel tempLabel = new JLabel(temp);
		add(tempLabel, gc, 7);
	}
}








