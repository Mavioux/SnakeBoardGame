
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
	
	/*public int[] move(int id, int die) {
		
	}*/
}







