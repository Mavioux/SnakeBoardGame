
public class Board {
	int N, M;
	int[][] tiles;
	Snake[] snakes;
	Ladder[] ladders;
	Apple[] apples;
	
	public Board() {
		N = 0;
		M = 0;
		tiles = new int[N][M];
		snakes = new Snake[0];
		ladders = new Ladder[0];
		apples = new Apple[0];
	}
	
	public Board(int n, int m, int numOfSnakes, int numOfLadders, int numOfApples) {
		this.N = n;
		this.M= m;
		tiles = new int[n][m];
		snakes = new Snake[numOfSnakes];
		ladders = new Ladder[numOfLadders];
		apples = new Apple[numOfApples];
	}
	
	public Board(Board templateBoard) {
		this.N = templateBoard.N;
		this.M = templateBoard.M;
		tiles = new int[N][M];
		snakes = new Snake[templateBoard.snakes.length];
		ladders = new Ladder[templateBoard.ladders.length];
		apples = new Apple[templateBoard.apples.length];
		
		tiles = templateBoard.tiles.clone();
		snakes = templateBoard.snakes.clone();
		ladders = templateBoard.ladders.clone();
		apples = templateBoard.apples.clone();
	}
	
	public void setN(int a) {
		N = a;
	}
	
	public void setM(int a) {
		M = a;
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
	
	public void createBoard() {
		
		//Tiles Initialization
		int counter = 1;
		boolean flag = true;
		for(int i = tiles.length - 1; i > -1; i--) {
			if(flag) {
				for(int j = 0; j < tiles[i].length; j++){
					tiles[i][j] = counter++;
					flag = false;
				}
			}
			else{
				for(int j = tiles[i].length - 1; j > -1; j--) {
					tiles[i][j] = counter++;
					flag = true;
				}
			}
		}
		
		//Snake Initialization
		for(int i = 0; i < snakes.length;  i++) {
			int headId;
			int tailId;
			do {
				tailId = (int)(Math.random() * N * M);
			} while(tailId >= N*M);
			headId = tailId + (int)(Math.random() * (N*M - tailId));
			//headId = tailId + random Number from 0 to max size(N*M) minus the tailId that we added to affirm that head>tail
			snakes[i] = new Snake(i, headId, tailId);
		}
		
		//Ladder Initialization
		for(int i = 0; i < ladders.length;  i++) {
			int up;
			int down;
			do {
				down = (int)(Math.random() * N * M);
			} while(down >= N*M);
			up = (int)(down + Math.random() * ((N*M) - down));
			
			ladders[i] = new Ladder(i, up, down, false);
		}
		
		//Initialize a random value for applepos and then loop through each snakeHead. 
		//If applepos==snakeHead -> check = true -> do while loops again giving a different applepos
		
		for(int i = 0; i < apples.length; i++) {
			int applePos;
			boolean check = false;
			do {
				applePos = (int)(Math.random() * N * M);
				for(int j = 0; j < snakes.length; j++) {
					if(snakes[j].getHeadId() == applePos)
						check = true;
				}
				
				String clr;
				if(1 + (int)(Math.random() * 2) % 2 == 0) {
					clr = "red";
				}
				else {
					clr = "black";
				}
				
				int applePoints = (int)(Math.random() * 20);
				
				
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
			for(int j = 0; j < M; j++) {
				if( snakes[i].getHeadId() > j * M && snakes[i].getHeadId() <= (j+1) * M) {
					for(int k = 0; k < M; k++) {
						if(tiles[N-1-j][k] == snakes[i].getHeadId()) {
							elementBoardSnakes[N-1-j][k] = "SH" + snakes[i].getSnakeId();
						} 
					}
				}
			}
		}
		
		
		//Finding the i,j with the help of the tiles 2-D array in order to position the snake tails
		for(int i = 0; i < snakes.length; i++) {
			for(int j = 0; j < M; j++) {
				if( snakes[i].getTailId() > j * M && snakes[i].getTailId() <= (j+1) * M) {
					for(int k = 0; k < M; k++) {
						if(tiles[N-1-j][k] == snakes[i].getTailId()) {
							elementBoardSnakes[N-1-j][k] = "ST" + snakes[i].getSnakeId();
						} 
					}
				}
			}
		}
		
		
		//Finding the i,j with the help of the tiles 2-D array in order to position the upLadderIds
		for(int i = 0; i < ladders.length; i++) {
			for(int j = 0; j < M; j++) {
				if(ladders[i].getUpStepId() > j * M && ladders[i].getUpStepId() <= (j+1) * M) {
					for(int k = 0; k < M; k++) {
						if(tiles[N-1-j][k] == ladders[i].getUpStepId()) {
							elementBoardLadders[N-1-j][k] = "LU" + ladders[i].getLadderId();
						} 
					}
				}
			}
		}
		
		//Finding the i,j with the help of the tiles 2-D array in order to position the downLadderIds
		for(int i = 0; i < ladders.length; i++) {
			for(int j = 0; j < M; j++) {
				if( ladders[i].getDownStepId() > j * M && ladders[i].getDownStepId() <= (j+1) * M) {
					for(int k = 0; k < M; k++) {
						if(tiles[N-1-j][k] == ladders[i].getDownStepId()) {
							elementBoardLadders[N-1-j][k] = "LD" + ladders[i].getLadderId();
						} 
					}
				}
			}
		}
		
		//Finding the i,j with the help of the tiles 2-D array in order to position the Apples
		for(int i = 0; i < apples.length; i++) {
			for(int j = 0; j < M; j++) {
				if( apples[i].getAppleTileId() > j * M && apples[i].getAppleTileId() <= (j+1) * M) {
					for(int k = 0; k < M; k++) {
						if(tiles[N-1-j][k] == apples[i].getAppleTileId()) {
							char appleCharZero = apples[i].getColor().charAt(0);
							String appleUpperCaseCharZero = String.valueOf(appleCharZero);
							elementBoardApples[N-1-j][k] = "A" + appleUpperCaseCharZero.toUpperCase() + apples[i].getAppleId();
						} 
					}
				}
			}
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
	
}








