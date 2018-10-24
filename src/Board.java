
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
		this.N= m;
		tiles = new int[N][M];
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
	
	public void createBoard() {
		
	}
	
}
