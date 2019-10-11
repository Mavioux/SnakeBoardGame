import java.util.ArrayList;

public class Node {
	Node parent;
	public ArrayList<Node> children;
	int nodeDepth;
	int[] nodeMove;
	Board nodeBoard;
	double nodeEvaluation;
	int dice;
	
	// Constructors
	public Node() {
		//parent = new Node();
		children = new ArrayList<Node>();
		nodeDepth = 0;
		nodeMove = new int[0];
		nodeBoard = new Board();
		nodeEvaluation = 0;		
	}
	
	public Node(Node parent, int nodeDepth, Board gameBoard, double nodeEvaluation, int dice) {
		this.parent = parent;
		children = new ArrayList<Node>();
		this.nodeDepth = nodeDepth;
		nodeMove = new int[0];
		this.nodeBoard = gameBoard;
		this.nodeEvaluation = nodeEvaluation;
		this.dice = dice;
	}
	
	public Node(Node parent, int nodeDepth, Board gameBoard, double nodeEvaluation) {
		this.parent = parent;
		children = new ArrayList<Node>();
		this.nodeDepth = nodeDepth;
		nodeMove = new int[0];
		this.nodeBoard = gameBoard;
		this.nodeEvaluation = nodeEvaluation;
	}
	
	public Node(int nodeDepth, Board gameBoard, double nodeEvaluation) {
		parent = new Node();
		children = new ArrayList<Node>();
		this.nodeDepth = nodeDepth;
		nodeMove = new int[0];
		this.nodeBoard = gameBoard;
		this.nodeEvaluation = nodeEvaluation;
	}
	
	// Getters
	public Board getBoard() {
		return nodeBoard;
	}
	
	public double getNodeEvaluation() {
		return nodeEvaluation;
	}
	
	public int getDice() {
		return dice;
	}
	
	
	
}
