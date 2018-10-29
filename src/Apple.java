
public class Apple {
	
	int appleId;
	int appleTileId;
	String color;
	int points;
	
	// Constructors
	public Apple() {
		
		appleId = 0;
		appleTileId = 0;
		color = "";
		points = 0;
	}
	
	public Apple(int appleId, int appleTileId, String color, int points) {
		
		this.appleId = appleId;
		this.appleTileId = appleTileId;
		this.color = color;
		this.points = points;
	}
	
	// Getters, setters
	public void setAppleId(int appleId) {
		
		this.appleId = appleId;
	}
	
	public void setAppleTileId(int appleTileId) {
		
		this.appleTileId = appleTileId;
	}
	
	public void setColor(String color) {
		
		this.color = color;
	}
	
	public void setPoints(int points) {
		
		this.points = points;
	}
	
	
	public int getAppleId() {
		
		return appleId;
	}
	
	public int getAppleTileId() {
		
		return appleTileId;
	}
	
	public String getColor() {
		
		return color;
	}
	
	public int getPoints() {
		
		return points; 
	}
	
}
