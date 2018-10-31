

public class Game {

	public static void main(String[] args) {
		Board gameBoard = new Board(10, 10, 2, 2, 2);
		gameBoard.createBoard();
		gameBoard.createElementBoard();
		Player makispaiktis = new Player(1, "ma", 1, gameBoard);
		makispaiktis.move(80, 7);
		
		for(int i = 0; i < gameBoard.tiles.length; i++) {
			for(int j = 0; j < gameBoard.tiles[i].length; j++) {
				System.out.print(gameBoard.tiles[i][j] + " ");
			}
			System.out.println();
		}
		
		
	}
	
	
	

}
