
public class Player {
	int playerId;
	int playerTileId;
	String name;
	int score;
	Board board;
	
	public Player() {
		playerId = 0;
		name = "";
		score = 0;
		board = new Board();
	}
	
	public Player(int playerId, String name, int score, Board templateBoard) {
		this.playerId = playerId;
		this.name = name;
		this.score = score;
		board = new Board(templateBoard);
	}
	
	public Player(Player templatePlayer) {
		this.playerId = templatePlayer.playerId;
		this.name = templatePlayer.name;
		this.score = templatePlayer.score;
		this.board = new Board(templatePlayer.board);
	}
	
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public void setBoards(Board board) {
		this.board = board;
	}
	
	public int getPlayerId() {
		return playerId;
	}
	
	public String getName() {
		return name;
	}
	
	public int getScore() {
		return score;
	}
	
	public Board getBoard() {
		return board;
	}
	
	public void setPlayerTileId(int tile) {
		playerTileId = tile;
	}
	
	public int getPlayerTileId() {
		return playerTileId;
	}
	
	public int[] move(int id, int dice) {
		int tileNow = id + dice;
		int snakeBites = 0;
		int laddersUsed = 0;
		int redApplesEaten = 0;
		int blackApplesEaten = 0;
		int[] arrayReturned = new int[5];
		
		// Search if tileNow == SnakeHead
		for(int i=0; i<board.snakes.length; i++) {
			if(board.snakes[i].getHeadId() == tileNow) {
				snakeBites++;
				tileNow = board.snakes[i].getTailId();
				System.out.println("A little snake bit you!");
				break;
			}	
		}
		
		// Search if tileNow == LadderUp
		for(int i=0; i<board.ladders.length; i++) {
			if(board.ladders[i].getDownStepId() == tileNow && !board.ladders[i].getBroken()) {
				laddersUsed++;
				tileNow = board.ladders[i].getUpStepId();
				board.ladders[i].setbroken(true);
				System.out.println("Lucky you! You found a ladder.");
				break;
			}
		}
		
		// Search if tileNow = appleTileId
		for(int i=0; i<board.apples.length; i++) {
			if(board.apples[i].getAppleTileId() == tileNow) {
				switch(board.apples[i].getColor()) {
				
					case "red":
						score += board.apples[i].getPoints();
						redApplesEaten++;
						System.out.println("You ate a red apple. You earned " + board.apples[i].getPoints() + " points.");
						board.apples[i].setAppleTileId(-100);
						break;
					
					
					case "black":
						score -= board.apples[i].getPoints();
						blackApplesEaten++;
						System.out.println("You ate a black apple... You lost " + board.apples[i].getPoints() + " points.");
						board.apples[i].setAppleTileId(-100);
						break;
						
					default:
						System.out.println("Apples are red or black!");
				}
			}
		}
		
		arrayReturned[0] = tileNow;
		arrayReturned[1] = snakeBites;
		arrayReturned[2] = laddersUsed;
		arrayReturned[3] = redApplesEaten;
	    arrayReturned[4] = blackApplesEaten;
	    
	    return arrayReturned;
		
		
		}
	
	public int heuristicMove(int id, int dice) {		
		int tileNow = id + dice;
		
		// Search if tileNow == SnakeHead
		for(int i=0; i<board.snakes.length; i++) {
			if(board.snakes[i].getHeadId() == tileNow) {
				tileNow = board.snakes[i].getTailId();
				break;
			}	
		}
		
		// Search if tileNow == LadderUp
		for(int i=0; i<board.ladders.length; i++) {
			if(board.ladders[i].getDownStepId() == tileNow && !board.ladders[i].getBroken()) {
				tileNow = board.ladders[i].getUpStepId();
				break;
			}
		}
		
		// Search if tileNow = appleTileId
		for(int i=0; i<board.apples.length; i++) {
			if(board.apples[i].getAppleTileId() == tileNow) {
				switch(board.apples[i].getColor()) {
				
					case "red":
						score += board.apples[i].getPoints();
						break;
					
					
					case "black":
						score -= board.apples[i].getPoints();
						break;
						
					default:
						System.out.println("Apples are red or black!");
				}
			}
		}
		return tileNow;
	}
	
	public double evaluate(int currentPos, int dice) {
		int pointsBefore = score;
		int finalPos = heuristicMove(currentPos, dice);
		double weight;
		/*if(opponentPos > currentPos) {
			weight = (finalPos - currentPos) * 0.65 + (score - pointsBefore) * 0.35;
		}*/
		
	
		weight = (finalPos - currentPos) * 0.2 + (score - pointsBefore) * 0.8;
		
		score = pointsBefore;
		return weight;
	}
}







