

public class Game {
	
	int round;
	
	// Constructors
	public Game() {
		round = 0;
	}
	
	public Game(int round) {
		this.round = round;
	}
	
	// Getters, setters
	public void setRound(int round) {
		this.round = round;
	}
	
	public int getRound() {
		return round;
	}

	public static void main(String[] args) {
		Board gameBoard = new Board(20, 10, 3, 3, 6);
		gameBoard.createBoard();
		gameBoard.createElementBoard();
		Player mavioux = new Player(1, "Mavioux", 0, gameBoard);
		Player thomas = new Player(2, "Thomas", 0, gameBoard);
		Game game = new Game(0);
		
		System.out.println();
		for(int i = 0; i < gameBoard.tiles.length; i++) {
			for(int j = 0; j < gameBoard.tiles[i].length; j++) {
				if(gameBoard.tiles[i][j] < 10) 
					System.out.print("  " + gameBoard.tiles[i][j] + " ");
				else if(gameBoard.tiles[i][j] < 100)
					System.out.print(" " + gameBoard.tiles[i][j] + " ");
				else 
					System.out.print(gameBoard.tiles[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
		System.out.println();
		
		int[] maviouxArray = new int[5];
		int[] thomasArray = new int[5];
		maviouxArray[0] = 0;
		thomasArray[0] = 0;
		int turn = 1;
		int roll= 0;
		
		while(maviouxArray[0] < gameBoard.getM() * gameBoard.getN() && thomasArray[0] < gameBoard.getM() * gameBoard.getN()) {
			switch(turn) {
			case 1:
				System.out.println(mavioux.getName() + "'s turn:");
				System.out.println("Starting tile: " + maviouxArray[0]);
				roll = 1 + (int)(Math.random() * 6);
				System.out.println("You rolled: " + roll);
				maviouxArray = mavioux.move(maviouxArray[0], roll);
				System.out.println("Ending tile: " + maviouxArray[0]);
				turn++;
				break;
			case 2:
				System.out.println(thomas.getName() + "'s turn:");
				System.out.println("Starting tile: " + thomasArray[0]);
				roll = 1 + (int)(Math.random() * 6);
				System.out.println("You rolled: " + roll);
				thomasArray = thomas.move(thomasArray[0], roll);
				System.out.println("Ending tile: " + thomasArray[0]);
				turn = 1;
				break;
			}
			
			game.setRound(game.getRound() + 1);
			System.out.println();
			
			
		}
		
		// Give 15 points to the player who finished first
		switch(turn) {
		case 1:
			thomas.setScore(thomas.getScore() + 15);
			break;
			
		case 2:
			mavioux.setScore(mavioux.getScore() + 15);
			break;
			
		default:
			System.out.println("Error!");
		}
		
		System.out.println();
		System.out.println("Rounds: " + game.getRound());
		System.out.println("Score of Mavioux: " + mavioux.getScore());
		System.out.println("Score of Thomas: " + thomas.getScore());
		System.out.print("The winner of the game is: ");
		
		// Choose the winner
		if(mavioux.getScore() > thomas.getScore()) {
			System.out.println("Mavioux");
		}
		
		else if(mavioux.getScore() < thomas.getScore()) {
			System.out.println("Thomas");
			
		}
		
		else {
			switch(turn) {
			case 1:
				System.out.println("Thomas");
				break;
				
			case 2:
				System.out.println("Mavioux");
				break;
				
			default:
				System.out.println("Error!");
			}
			
		}
		
		
		
		
	}
	
	
	

}
