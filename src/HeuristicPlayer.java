

import java.util.ArrayList;

public class HeuristicPlayer extends Player {
	ArrayList<Integer[]> path;
	int perfectDice;
	
	//Constructors
	public HeuristicPlayer() {
		super();
		path = new ArrayList<Integer[]>();
	}
	
	public HeuristicPlayer(int playerId, String name, int score, Board templateBoard, ArrayList<Integer[]> templatePath) {
		super(playerId, name, score, templateBoard);
		path = new ArrayList<Integer[]>();
		for(int i = 0; i < templatePath.size(); i ++) {
			path.add(i, templatePath.get(i));
		}
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
		double weight = (finalPos - currentPos) * 0.65 + (score - pointsBefore) * 0.35;
		score = pointsBefore;
		return weight;
	}
	
	public int getNextMove(int currentPos) {
		//Initializing an array that will hold the weight result of each dice roll
		double moveWeight[] = new double[6];
		for(int i = 0; i < 6; i++) {
			moveWeight[i] = evaluate(currentPos, i+1);
		}
		//Finding the best move and saving it
		perfectDice = 1;
		double maxWeight = moveWeight[0];
		for(int i = 1; i < 6; i ++) {
			if(moveWeight[i] > maxWeight) {
				maxWeight = moveWeight[i];
				perfectDice = i + 1;
			}
		}
		//Appropriate typecasting from int[] to Integer[]
		Integer temp[] = new Integer[5];
		int tempMove[] = move(currentPos, perfectDice);
		for(int i = 0; i < temp.length; i++) {
			temp[i] = tempMove[i];
		}
		path.add(temp);	
		return tempMove[0];
		//return path.get(path.size() - 1)[0];
	}
	
	public void statistics() {
		System.out.println("Round: " + path.size());
		System.out.println("Dice: " + perfectDice);
		System.out.println("Snake bites: " + path.get(path.size() - 1)[1]);
		System.out.println("Ladders Used: " + path.get(path.size() - 1)[2]);
		System.out.println("Red Apples Eaten: " + path.get(path.size() - 1)[3]);
		System.out.println("Black Apples Eaten: " + path.get(path.size() - 1)[4]);
	}
}
