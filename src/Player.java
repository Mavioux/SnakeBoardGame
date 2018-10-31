
public class Player {
	int playerId;
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
	
	public void move(int id, int dice) {
		int tileNow = id + dice;
		int i, j;
		
		i = board.getN() - 1 -(int)((tileNow-1) / board.getM());
		
		if(board.getN() % 2 == 0) {
			if(i % 2 != 0) {
				j = tileNow -(board.getM()*board.getN() - (i+1)*board.getM()) - 1;
			}
			else {
				j = board.getM() - (tileNow - (board.getN()*board.getM() - (i+1)*board.getM()));
			}
		}
		else {
			if(i % 2 != 0) {
				j = board.getM() - (tileNow - (board.getN()*board.getM() - (i+1)*board.getM()));
			}
			else {
				j = tileNow -(board.getM()*board.getN() - (i+1)*board.getM()) - 1;
			}
		}
		
		System.out.println(i + "," + j);
			
	}
}







