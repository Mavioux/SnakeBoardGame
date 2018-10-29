
public class Ladder {
	int ladderId;
	int upStepId;
	int downStepId;
	boolean broken;
	
	public Ladder() {
		ladderId = 0;
		upStepId = 0;
		downStepId = 0;
		broken = true;
	}
	
	public Ladder(int ladderId, int upStepId, int downStepId, boolean broken) {
		this.ladderId = ladderId;
		this.upStepId = upStepId;
		this.downStepId = downStepId;
		this.broken = broken;
	}
	
	public Ladder(Ladder templateLadder) {
		this.ladderId = templateLadder.ladderId;
		this.upStepId = templateLadder.upStepId;
		this.downStepId = templateLadder.downStepId;
		this.broken = templateLadder.broken;
	}
	
	public void setLadderId(int ladderId) {
		this.ladderId = ladderId;
	}
	
	public void setUpStepId(int upStepId) {
		this.upStepId = upStepId;
	}
	
	public void setDownStepId(int downStepId) {
		this.downStepId = downStepId;
	}
	
	public void setbroken(boolean broken) {
		this.broken = broken;
	}
	
	public int getLadderId() {
		return ladderId;
	}
	
	public int getUpStepId() {
		return upStepId;
	}
	
	public int getDownStepId() {
		return downStepId;
	}
	
	public boolean getBroken() {
		return broken;
	}
}
