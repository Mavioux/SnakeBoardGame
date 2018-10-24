
public class Ladder {
	int ladderId;
	int upStepId;
	int downStepId;
	boolean broken;
	
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
