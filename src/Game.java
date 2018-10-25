

public class Game {

	public static void main(String[] args) {
		Board gameBoard = new Board(10, 10, 2, 2, 2);
		gameBoard.createBoard();
		
		for(int i = 0; i < gameBoard.tiles.length; i++) {
			for(int j = 0; j < gameBoard.tiles[i].length; j++) {
				System.out.print(gameBoard.tiles[i][j] + " ");
			}
			System.out.println();
		}
		
	}
	

}
