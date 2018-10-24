
public class Snake {
	int snakeId;
	int headId;
	int tailId;
	
	public Snake() {
		snakeId = 0;
		headId = 0;
		tailId = 0;
	}
	
	public Snake(int snakeId, int headId, int tailId) {
		this.snakeId = snakeId;
		this.headId = headId;
		this.tailId = tailId;
	}
	
	public Snake(Snake templateSnake) {
		this.snakeId = templateSnake.snakeId;
		this.headId = templateSnake.headId;
		this.tailId = templateSnake.tailId;
	}
	
	public void setSnakeId(int snakeId) {
		this.snakeId = snakeId;
	}
	
	public void setHeadId(int headId) {
		this.headId = headId;
	}
	
	public void setTailId(int tailId) {
		this.tailId = tailId;
	}
	
	public int getSnakeId() {
		return snakeId;
	}
	
	public int getHeadId() {
		return headId;
	}
	
	public int getTailId() {
		return tailId;
	}
	
	
	
}
