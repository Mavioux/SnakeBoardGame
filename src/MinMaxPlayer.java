import java.util.ArrayList;

public class MinMaxPlayer extends HeuristicPlayer {
	//BinaryTree <Node> tree = new BinaryTree <Node> ();
	int nodeDepth;
	
	// Constructors
	public MinMaxPlayer(int playerId, String name, int score, Board templateBoard, ArrayList<Integer[]> templatePath) {
		super(playerId, name, score, templateBoard, templatePath);
		nodeDepth = 4;
	}
	
	
	public double evaluate(int currentPos, int dice, int opponentPosition) {
		int pointsBefore = score;
		int finalPos = heuristicMove(currentPos, dice);
		double weight;
		/*if(opponentPos > currentPos) {
			weight = (finalPos - currentPos) * 0.65 + (score - pointsBefore) * 0.35;
		}*/
		
	
		//weight = (finalPos - currentPos) * 0.3 + (score - pointsBefore) * 0.5 + (currentPos - opponentPosition) * 0.2;
		weight = (finalPos - currentPos) * 0.65 + (score - pointsBefore) * 0.35;
		
		score = pointsBefore;
		return weight;
	}
	
	public void createMySubTree(Node parent, int depth, int currentPos, int opponentCurrentPos) {
		int movements = 6;
		double movesWeight[] = new double[6];
		//double opponentMovesWeight[] = new double[6];
		double max = -1000000;
		double min = 1000000;
		//double opponentMax = 0;
		//double opponentMin = 0;
		
		int maxIndex = 0;
		int minIndex = 0;
		
		for(int i = 0 ; i < movements; i++) {
			movesWeight[i] = evaluate(currentPos, i + 1, opponentCurrentPos);
			//System.out.println(movesWeight[i]);
			if(movesWeight[i] > max) {
				max = movesWeight[i];
				maxIndex = i;
				//System.out.println("mphka!");
				
			}
			if(movesWeight[i] < min) {
				min = movesWeight[i];
				minIndex = i;
			}
		}
		Node lChild = new Node(parent, depth, parent.getBoard(), min, minIndex + 1);
		Node rChild = new Node(parent, depth, parent.getBoard(), max, maxIndex + 1);
		parent.children.add(lChild);
		parent.children.add(rChild);
		
		
		
		/*// Create the children of lChild and rChild
		for(int i=0; i<movements; i++) {
			opponentMovesWeight[i] = evaluate(opponentCurrentPos, i+1);
			if(opponentMovesWeight[i] > opponentMax) {
				opponentMax = opponentMovesWeight[i];
			}
			
			if(opponentMovesWeight[i] < opponentMin) {
				opponentMin = opponentMovesWeight[i];
			}
		}
		
		Node lrChild = new Node(rChild, depth+1, rChild.getBoard(), opponentMin);
		Node rrChild = new Node(rChild, depth+1, rChild.getBoard(), opponentMax);
		rChild.children.add(lrChild);
		rChild.children.add(rrChild);*/
	}
	
	public void createOpponentSubTree(Node parent, int depth, int currentPos, int opponentCurrentPos, double parentEval, Player opponent) {
		int movements = 6;
		double movesWeight[] = new double[6];
		double max = -100000;
		double min = 100000;
		
		for(int i=0; i<movements; i++) {
			movesWeight[i] = opponent.evaluate(opponentCurrentPos, i+1);
			if(movesWeight[i] > max) {
				max = movesWeight[i];
			}
			
			if(movesWeight[i] < min) {
				min = movesWeight[i];
			}
		}
		
		Node lChild = new Node(parent, depth, parent.getBoard(), min);
		Node rChild = new Node(parent, depth, parent.getBoard(), max);
		parent.children.add(lChild);
		parent.children.add(rChild);
	}
	
	public int chooseMinMaxMove(Node root) {
		int indexI = 0;
		//int indexJ = 0;
		//int indexK = 0;
		double max = root.children.get(0).children.get(0).children.get(0).getNodeEvaluation();
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				for(int k = 0; k < 2; k++) {
					System.out.println(root.children.get(i).children.get(j).children.get(k).getNodeEvaluation());
					System.out.println(max);
					System.out.println(root.children.get(i).children.get(j).children.get(k).getNodeEvaluation() > max);
					if(root.children.get(i).children.get(j).children.get(k).getNodeEvaluation() >= max) {
						indexI = i;
						//indexJ = j;
						//indexK = k;
						max = root.children.get(i).children.get(j).children.get(k).getNodeEvaluation();
					}
				}
			}
		}
		System.out.println("IndexI: " + indexI);
		return indexI;
	}
	
	public int getNextMinMaxMove(int currentPos, int opponentCurrentPos, Player opponent) {
		int movements = 6;
		double movesWeight[] = new double[6];
		double max = 0;
		
		
		for(int i=0; i<movements; i++) {
			movesWeight[i] = opponent.evaluate(opponentCurrentPos, i+1);
			if(movesWeight[i] > max) {
				max = movesWeight[i];
			}
		}
		
		// 0
		Node root = new Node(0, board, max);
		
		// 1
		createMySubTree(root, 1, playerTileId, opponent.getPlayerTileId());
		// 2
		for(int i = 0; i < 2; i++) {
			createOpponentSubTree(root.children.get(i), 2, currentPos, opponentCurrentPos, root.children.get(i).getNodeEvaluation(), opponent);
		}
		// 3
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				createMySubTree(root.children.get(i).children.get(j), 3, currentPos, opponentCurrentPos);
			}
		}
		
		int moveI = chooseMinMaxMove(root);
		int perfectDice = root.children.get(moveI).getDice();
		
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
		
	
}